package manager.confirm_sale;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import database.dao.CheckingSalesDAO;
import project.PosFrame;
import project.PosFrameProperties;

/** 관리자 매출 확인 스크린 JPanel
 *  @author HONG */
public class CheckingSalesDataScreen extends JPanel {
	/** JPanel for ShowSalesScreen */
	private static final long serialVersionUID = 6754912888743170121L;

	private JScrollPane scrolledTable;
	
	JTable table;
	
	JButton backBtn, refresh, userSetBtn;
	
	JLabel totalSalesLabel, combine;
	JTextField totalSales;
	
	JComboBox<String> group, range;
	
	CheckingSalesTableModel model;
	
	CheckingSalesDAO dao;
	
	public static final String[] GROUP = {"전체", "연", "월", "일"};
	public static final String[] RANGE = {"전체", "사용자지정"};
	
	private Font basic = PosFrameProperties.BASIC;
	private Font basic2 = PosFrameProperties.BASIC2;
	
	String preDate;
	String postDate;
	String firstWord;
	String secondWord;
	
	PosFrame frame = PosFrameProperties.frame;
	
	private static final String[] HEADER = {"ID", "TIME", "PAY_ID", "PRICE", "PICK_UP"};
	
	public CheckingSalesDataScreen() {
		
		setLayout(null);
        setBounds(frame.getX(), frame.getY(),
        		frame.getWidth(), frame.getHeight()); // 900, 1040
        
        model = new CheckingSalesTableModel();
        
		dao = new CheckingSalesDAO(this);
		
		dao.totalSelect();
		
		TableColumnModel columnModel = new DefaultTableColumnModel();
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		
		TableColumn column;
	    for(int i = 0; i < HEADER.length; ++i) {
	    	column = new TableColumn(i);
	    	column.setCellRenderer(renderer); 
	 	    column.setHeaderValue(HEADER[i]);
	 	    columnModel.addColumn(column);
	    }
	    
	    table = new JTable(model, columnModel);
		table.setBackground(Color.white);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && group.getSelectedIndex() == 0)
					new SoldProductDialog(dao, (Integer)model.getValueAt(table.getSelectedRow(), 0)).setVisible(true);
			} 
		});
		
		setColumnWitdh(0);
		table.setRowHeight(25);
		table.setFont(basic);
		
		scrolledTable = new JScrollPane(table); //스크롤 될 수 있도록 JScrollPane 적용

		scrolledTable.setBounds(40, 40, 805, 670);
		
		scrolledTable.getViewport().setBackground(Color.WHITE); 
		
		group = new JComboBox<>(GROUP);
		range = new JComboBox<>(RANGE);
		
		group.setFont(basic);
		range.setFont(basic);
		
		int area2Y = 740;
		
		group.setBounds(40, area2Y, 100, 30);
		range.setBounds(160, area2Y, 150, 30);
		
		group.setBackground(Color.white);
		range.setBackground(Color.white);
		
		userSetBtn = new JButton("날짜");
		userSetBtn.setBounds(range.getX() + range.getWidth() + 20, area2Y, 70, 30);
		userSetBtn.setBackground(Color.LIGHT_GRAY);
		userSetBtn.setFont(basic);
		userSetBtn.setFocusable(false);
		userSetBtn.addActionListener(new UserSetBtnAction(this));
		
		combine = new JLabel();
		combine.setFont(basic2);
		combine.setBounds(40, area2Y + 80, 500, 70);
		
		firstWord = GROUP[0];
		secondWord = RANGE[0];
		setLabelText();
		
		ComboBoxListener boxListener = new ComboBoxListener(this);
		
		group.addItemListener(boxListener);
		range.addItemListener(boxListener);
		
		refresh = new JButton("새로고침");
		refresh.setBounds(500, 880, 140, 60);
		refresh.setBackground(Color.LIGHT_GRAY);
		refresh.setFont(basic2);
		refresh.setFocusable(false);
		
		refresh.addActionListener(new RefreshBtnListener(this));
		
		backBtn = new JButton("뒤로가기");
		backBtn.setSize(140,60);
		backBtn.setBackground(PosFrameProperties.RED_COLOR);
		backBtn.setLocation(670, 880); // 기준점으로 삼는 컴포넌트가 몇 개 있음
		backBtn.setFocusable(false);
		backBtn.setFont(basic2);
		
		Font sales = PosFrameProperties.HEAD_FONT2;

		totalSalesLabel = new JLabel("합계 : ");
		totalSalesLabel.setBounds(backBtn.getX() - 50, area2Y - 10, 120, 80);
		totalSalesLabel.setFont(sales);
		
		totalSales = new JTextField();
		totalSales.setBounds(backBtn.getX() + 50, area2Y - 10, 200, 80);
		totalSales.setEditable(false);
		totalSales.setFont(sales);
		totalSales.setBorder(new EmptyBorder(5, 5, 5, 5));
		totalSales.setBackground(new Color(243, 255, 247));
		setTotalSalesSum();
		
		add(scrolledTable);
		add(group);
		add(range);
		add(userSetBtn);
		add(combine);
		add(backBtn);
		add(refresh);
		add(totalSalesLabel);
		add(totalSales);
		
		backBtn.addActionListener((e) -> PosFrameProperties.card.show(frame.getContentPane(), "p4"));
		
		setOpaque(true);
		setBackground(new Color(239, 251, 255));
	}
	
	/** 라벨 텍스트 변경 메서드 */
	public void setLabelText() {
		combine.setText(String.format("<html>출력 방식 : %s 단위 <br> 기간 : %s</html>", firstWord, secondWord));
	}
	
	/** totalSales의 내용을 변경해주는 메서드. 자동으로 3자리마다 ,를 추가해준다. */
	public void setTotalSalesSum() {
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
		
		totalSales.setText(numberFormat.format(model.getTotalPrice()) + "원");
	}
	
	/** 열 넓이 한 번에 조절하기.
	 *  @param typeNum 0 = 기본, 1 = select타입. 0과 1 의외의 숫자는 0이됨*/
	public void setColumnWitdh(int typeNum) {
		if(!(typeNum == 0 || typeNum == 1))
			typeNum = 0;
		
		int[] width = new int[5];
		if(typeNum == 0) {
			width = new int[]{120, 220, 145, 220, 100};
		} else {
			width = new int[]{0, 405, 0, 400, 0};
		}
			
		for(int i = 0; i < HEADER.length; ++i) {
			TableColumn column = table.getColumn(HEADER[i]);
			column.setWidth(width[i]);
			column.setMinWidth(width[i]);
			column.setMaxWidth(width[i]);
		}
	}
	
	public JComboBox<String> getGroup() {
		return group;
	}

	public JComboBox<String> getRange() {
		return range;
	}

	public JLabel getCombine() {
		return combine;
	}

	public String getPreDate() {
		return preDate;
	}

	public void setPreDate(String preDate) {
		this.preDate = preDate;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public CheckingSalesTableModel getModel() {
		return model;
	}

	public CheckingSalesDAO getDao() {
		return dao;
	}

	public JTextField getTotalSales() {
		return totalSales;
	}

	public String getFirstWord() {
		return firstWord;
	}

	public void setFirstWord(String firstWord) {
		this.firstWord = firstWord;
	}

	public String getSecondWord() {
		return secondWord;
	}

	public void setSecondWord(String secondWord) {
		this.secondWord = secondWord;
	}

	public JButton getUserSetBtn() {
		return userSetBtn;
	}

	public JButton getRefresh() {
		return refresh;
	}
	
}
