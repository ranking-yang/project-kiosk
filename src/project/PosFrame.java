 package project;
import java.awt.CardLayout;
import javax.swing.JFrame;

import database.dao.DataCollection;
import main_menu.MenuScreen;
import manager.SelectScreen;
import manager.confirm_sale.CheckingSalesDataScreen;
import manager.menu_management.ManagerModeScreen;
import timer.MainTimer;
import timer.MainTimerTask;

/**
 * 토대가 되는 메인 프레임
 * CardLayout을 통해 component들을 배치시킨다
 */
public class PosFrame extends JFrame {
	/** JFrame for PosFrame */
	private static final long serialVersionUID = 2567201999283007064L;

	WaitingScreen waiting;
	MenuScreen menu;
	
	CheckingSalesDataScreen salesData;
	ManagerModeScreen productData;
	
	DataCollection dao;
	
	MainTimer timer;
	
	public PosFrame() {
		super("키오스크");
		setBounds(510, 5, 900, 1040);
		
		dao = new DataCollection();
		dao.updateInfo();
		
		CardLayout card = new CardLayout();
		setLayout(card);
		
		new PosFrameProperties(this, card);
				
		waiting = new WaitingScreen();		
		menu = new MenuScreen();
		
		salesData = new CheckingSalesDataScreen();
		productData = new ManagerModeScreen();
		SelectScreen select = new SelectScreen(); 
		
		timer = new MainTimer();
		MainTimerTask task = new MainTimerTask(timer, menu.getTimerLabel());
		timer.schedule(task, 0, 1000);
		
		add(waiting);
		add("p1", menu);
		add("p2", productData);
		add("p3", salesData);
		add("p4", select);
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public MainTimer getTimer() {
		return timer;
	}
	
	public WaitingScreen getWaiting() {
		return waiting;
	}

	public CheckingSalesDataScreen getSalesData() {
		return salesData;
	}

	public ManagerModeScreen getProductData() {
		return productData;
	}

	public MenuScreen getMenu() {
		return menu;
	}

	public DataCollection getDao() {
		return dao;
	}
	
}
