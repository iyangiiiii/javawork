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
 * ������
 * @author K.X
 * 
 * */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainInterface extends JFrame{
	/*
	 * ѡ�     ������       ͼ���ѯ          ͼ��軹       �˺Ź���
	 * 
	 * */
	//ѡ�
	public  JTabbedPane jTabbedPane = new JTabbedPane();
	//���������
	private JPanel jPanel = new JPanel();
	//��ǩ
	// ����
	private Font font2 = new Font("����", Font.BOLD, 20);
	
	private Container con = getContentPane();
	public MainInterface(String user) {

		
		// �ı䱳��ͼƬ
		ImageIcon icon = new ImageIcon("S:\\project\\ideajava\\Java_Library_Management_System\\img\\login.jpg");
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel label = new JLabel(scaledIcon);
		label.setBounds(0, 0, 1500, 800);
		jPanel.setLayout(null); // ��ȷ�� jPanel ����ȷ��ʼ��1
		jPanel.add(label);

		jTabbedPane.setFont(font2);
		jTabbedPane.add("�� �� ��", jPanel);

		CommoditySearch search = new CommoditySearch();
		jTabbedPane.add("��������",search.jLayeredPane);

		ChatFrame chatFrame = new ChatFrame();
		jTabbedPane.add("�ͷ���ͨ", chatFrame.createChatPanel());

		UserRatingInterface ratingPanel = new UserRatingInterface("123456");
		jTabbedPane.add("�û�����", ratingPanel);

		Leaderboard leaderboardFrame = new Leaderboard();
		leaderboardFrame.setVisible(true);
		jTabbedPane.add("���а�", leaderboardFrame);

		
		con.add(jTabbedPane);
		// �����Ըı䴰��Ĵ�С
		setResizable(false);
		setTitle("�������ϵͳ");
		setSize(1500, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new MainInterface("123456");
	}
}




