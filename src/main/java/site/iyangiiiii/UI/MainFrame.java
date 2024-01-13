package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * 主界面
 * @author iyangiii
 * 
 * */


public class MainFrame extends JFrame{
	/*
	 *
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
	public MainFrame(String user) {
		// 改变背景图片
		ImageIcon icon = new ImageIcon(Global.getImgPath("login.jpg"));
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel label = new JLabel(scaledIcon);
		label.setBounds(0, 0, 1500, 800);
		jPanel.setLayout(null);
		jPanel.add(label);

		Boolean isadmin = APIUtils.isAdmin();

		jTabbedPane.setFont(font2);
		jTabbedPane.add("主 界 面", jPanel);
		ManageCommodity addCommodity = new ManageCommodity();
		ManageCommodity.AddCommodityPanel acPanel = addCommodity.new AddCommodityPanel();
		if (isadmin)
		{

			ChatFrame chatFrame = new ChatFrame();
			jTabbedPane.add("客户沟通", chatFrame.createChatPanel());
		}
		else {
			ShoppingCart shoppingPanel = new ShoppingCart();
			jTabbedPane.add("商品选购", shoppingPanel);
			ChatFrame chatFrame = new ChatFrame();
			jTabbedPane.add("客服沟通", chatFrame.createChatPanel());
		}
		jTabbedPane.add("商品管理", acPanel);
		CommoditySearch search = new CommoditySearch();
		jTabbedPane.add("订单管理",search.jLayeredPane);

		UserRatingInterface ratingPanel = new UserRatingInterface(Global.curUser.getUsername(), Global.curUser.isAdmin());
		jTabbedPane.add("客户评价", ratingPanel);

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
}




