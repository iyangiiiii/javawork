package site.iyangiiiii;

import site.iyangiiiii.Service.CommoditySearch;
import site.iyangiiiii.UI.ChatFrame;
import site.iyangiiiii.UI.Leaderboard;
import site.iyangiiiii.UI.UserRatingInterface;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * 主界面
 * @author K.X
 * 
 * */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainInterface extends JFrame{
	/*
	 * 选项卡     主界面       图书查询          图书借还       账号管理
	 * 
	 * */
	//选项卡
	public  JTabbedPane jTabbedPane = new JTabbedPane();
	//主界面面板
	private JPanel jPanel = new JPanel();
	//标签
	// 字体
	private Font font2 = new Font("宋体", Font.BOLD, 20);

	private Container con = getContentPane();
	public MainInterface(String user) {

		
		// 改变背景图片
		ImageIcon icon = new ImageIcon("S:\\project\\ideajava\\javawork\\src\\main\\java\\site\\iyangiiiii\\img\\login.jpg");
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel label = new JLabel(scaledIcon);
		label.setBounds(0, 0, 1500, 800);
		jPanel.setLayout(null); // 请确保 jPanel 已正确初始化1
		jPanel.add(label);

		jTabbedPane.setFont(font2);
		jTabbedPane.add("主 界 面", jPanel);

		CommoditySearch search = new CommoditySearch();
		jTabbedPane.add("订单管理",search.jLayeredPane);

		ChatFrame chatFrame = new ChatFrame();
		jTabbedPane.add("客服沟通", chatFrame.createChatPanel());

		UserRatingInterface ratingPanel = new UserRatingInterface("123456");
		jTabbedPane.add("用户评价", ratingPanel);

		Leaderboard leaderboardFrame = new Leaderboard();
		leaderboardFrame.setVisible(true);
		jTabbedPane.add("排行榜", leaderboardFrame);

		
		con.add(jTabbedPane);
		// 不可以改变窗体的大小
		setResizable(false);
		setTitle("网店管理系统");
		setSize(1500, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new MainInterface("123456");
	}
}




