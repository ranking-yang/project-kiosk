package manager.menu_management;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import database.dao.ProductDAO;
import manager.ImageFile.ImageFileDelete;
import project.PosFrame;
import project.PosFrameProperties;

/** 관리자 메뉴관리 화면
 *  @author BAE */
public class ManagerModeScreen extends JPanel {
	
	/** JPanel for ManagerModeScreen */
	private static final long serialVersionUID = 8384747874280601623L;
	static final String [] col = {"제품 No.","제품명", "가격", "이미지", "카테고리", "샷", "우유", "얼음", "스테비아", "크림"};
	static String [][] row = new String[0][10];	

	public static DefaultTableModel model = new DefaultTableModel(row, col) {		
		/** DefaultTableModel for ManagerModeScreen */
		private static final long serialVersionUID = 347312815396895352L;

		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	
	private Font font = PosFrameProperties.BASIC2;
	private Color mainColor = PosFrameProperties.MAIN_COLOR;
	private Color redColor = PosFrameProperties.RED_COLOR;
	
	PosFrame frame = PosFrameProperties.frame;
	
	JTable menuTable;
	ProductDAO dao;
	ImageFileDelete imageDelete;
	
	public ManagerModeScreen()  {
		
		dao = new ProductDAO(this);
		imageDelete = new ImageFileDelete(this);
		
		menuTable = new JTable(model);
		menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuTable.setRowHeight(25);
		menuTable.getColumnModel().getColumn(1).setPreferredWidth(450);
		menuTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		menuTable.getColumnModel().getColumn(3).setPreferredWidth(350);
		menuTable.getTableHeader().setReorderingAllowed(false);
		menuTable.getTableHeader().setResizingAllowed(false);
		menuTable.setFont(PosFrameProperties.BASIC);

		JScrollPane scroll = new JScrollPane(menuTable);
		
		scroll.setBounds(40, 40, 805, 750);
		scroll.setOpaque(true);
		add(scroll);
		
		JButton edit = new JButton("수정");
		JButton delBtn = new JButton("삭제");
		JButton addBtn = new JButton("추가");
		JButton refresh = new JButton("새로고침");
		JButton backBtn = new JButton("뒤로가기");
		
		edit.setFocusable(false);
		delBtn.setFocusable(false);
		addBtn.setFocusable(false);
		refresh.setFocusable(false);
		backBtn.setFocusable(false);
		
		edit.setBackground(mainColor);
		delBtn.setBackground(mainColor);
		addBtn.setBackground(mainColor);
		refresh.setBackground(Color.LIGHT_GRAY);
		backBtn.setBackground(redColor);
		
		add(edit);
		add(delBtn);		
		add(addBtn);
		add(refresh);
		add(backBtn);
		
		edit.setBounds(110, 830, 140, 60);
		delBtn.setBounds(280, 830, 140, 60);
		addBtn.setBounds(450, 830, 140, 60);
		refresh.setBounds(620, 830, 140, 60);
		backBtn.setBounds(620, 910, 140, 60);
		
		edit.setFont(font);
		delBtn.setFont(font);
		addBtn.setFont(font);
		refresh.setFont(font);
		backBtn.setFont(font);
	    
		menuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					edit.doClick();
				}
			}			
		});
		
	    edit.addActionListener((e) -> new EditMenuDialog()); 				

		addBtn.addActionListener((e) -> new NewMenu());			
		
		delBtn.addActionListener((e) -> dao.deleteProduct()); 			
	
		backBtn.addActionListener((e) -> {
			ManagerModeScreen.model.setRowCount(0);
			PosFrameProperties.card.show(frame.getContentPane(), "p4");
		});

		refresh.addActionListener((e) -> {
			model.setRowCount(0);
			dao.showMenu();				
		});	
		
		setLayout(null);
		setBackground(new Color(243, 255, 247));		
		setBounds(frame.getX(),frame.getY(),
	       		frame.getWidth(), frame.getHeight());
	}

	public JTable getMenuTable() {
		return menuTable;
	}

	public ImageFileDelete getImageDelete() {
		return imageDelete;
	}

	public ProductDAO getDao() {
		return dao;
	}
	
}
