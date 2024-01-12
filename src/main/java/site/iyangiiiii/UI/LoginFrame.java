package site.iyangiiiii.UI;

import org.springframework.stereotype.Component;
import site.iyangiiiii.Entities.User;
import site.iyangiiiii.Service.UserService;
import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;
import site.iyangiiiii.Utils.UIUtils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

/**
 * 登陆界面
 *
 * @author K.X
 *
 */

@Component
public class LoginFrame {

	/*
	 * 定义窗体 一个大标签 两个小标签 两个文本框 两个按钮 五个面板
	 */
	// 标签
	private JLabel jLabel2 = new JLabel("用户名： ");
	private JLabel jLabel3 = new JLabel("密  码： ");
	private JLabel jLabel4 = new JLabel("验证码： ");
	private JLabel CaptchaImage = new JLabel();

	// 字体
	private Font font2 = new Font("宋体", Font.BOLD, 25);
	private Font font3 = new Font("宋体", Font.BOLD, 20);

	// 文本框
	private JTextField field = new JTextField(22);
	private JPasswordField field2 = new JPasswordField(22);
	private JTextField field3 = new JTextField(12);

	// 按钮
	private final JButton buttonLogin = new JButton("登陆");
	private final JButton buttonSignUp = new JButton("注册");
//	private final JButton buttonRefreshCaptcha = new JButton("刷新");

	// 面板
	private JPanel jPanel = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JPanel jPanel3 = new JPanel();
	private JPanel jPanel4 = new JPanel();
	private JPanel jPanel5 = new JPanel();
	private JPanel jPanel6 = new JPanel();
	// 窗体
	private JFrame frame = new JFrame("登陆");
	// 大小
	private Dimension dimension = new Dimension(30, 30);
	private Dimension dimension2 = new Dimension(80, 40);

	public LoginFrame() {

		frame.setTitle("登陆");
		// 设置大小
		frame.setSize(750, 550);
		// 居中
		frame.setLocationRelativeTo(null);
		// 布局为空
		frame.setLayout(null);

		initView();
	}

	/**
	 * 初始化页面
	 */
	protected void initView() {
		addassembly();

		transparent();

		addEvent();

		// 改变背景图片
		ImageIcon originalIcon  = new ImageIcon(Global.getImgPath("login.jpg"));
		Image originalImage = originalIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(750, 550, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel backgroundLabel = new JLabel(scaledIcon);
		backgroundLabel.setBounds(0, 0, 750, 550);
		frame.add(backgroundLabel);
		// 不可以改变窗体的大小
		frame.setResizable(false);
		// 窗口关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 窗口可见
		frame.setVisible(true);

		refreshCaptchaImage();
	}

	/**
	 * 添加组件
	 */
	private void addassembly() {
		// 添加字体
		jLabel2.setFont(font2);
		jLabel3.setFont(font2);
		jLabel4.setFont(font2);
		buttonLogin.setFont(font3);
		buttonSignUp.setFont(font3);
		field.setFont(font3);
		field2.setFont(font3);
		field3.setFont(font3);
		field.setPreferredSize(dimension);
		field2.setPreferredSize(dimension);
		field3.setPreferredSize(dimension);
		buttonLogin.setPreferredSize(dimension2);
		buttonSignUp.setPreferredSize(dimension2);
//		button.setBackground(Color.pink);
//		button2.setBackground(Color.GRAY);

		jPanel2.add(jLabel2);
		jPanel2.add(field);
		jPanel3.add(jLabel4);
		jPanel3.add(field3);
//		jPanel3.add(buttonRefreshCaptcha);
		jPanel3.add(jLabel3);
		jPanel3.add(field2);
		jPanel4.add(buttonLogin);
		jPanel5.add(buttonSignUp);
		jPanel6.add(jLabel4);
		jPanel6.add(field3);
		jPanel6.add(CaptchaImage);
//		jPanel6.add(buttonRefreshCaptcha);


		jPanel.setBounds(0, 60, 550, 80);
		jPanel2.setBounds(80, 170, 550, 80);
		jPanel3.setBounds(80, 260, 550, 60);
		jPanel4.setBounds(200, 380, 150, 80);
		jPanel5.setBounds(375, 380, 150, 80);
		jPanel6.setBounds(80, 320, 550, 60);


		frame.add(jPanel);
		frame.add(jPanel2);
		frame.add(jPanel3);
		frame.add(jPanel4);
		frame.add(jPanel5);
		frame.add(jPanel6);
	}

	/**
	 * 设置透明
	 */
	private void transparent() {
		// 设置透明
		jLabel2.setOpaque(false);
		jLabel3.setOpaque(false);
		jPanel4.setOpaque(false);
		field.setOpaque(false);
		field2.setOpaque(false);
		field3.setOpaque(false);
		// button.setOpaque(false);
		// button2.setOpaque(false);
		jPanel.setOpaque(false);
		jPanel2.setOpaque(false);
		jPanel3.setOpaque(false);
		jPanel4.setOpaque(false);
		jPanel5.setOpaque(false);
		jPanel6.setOpaque(false);
	}

	/**
	 * 添加事件
	 */
	private void refreshCaptchaImage() {
		// 从后端获取验证码图像的逻辑
		ImageIcon captchaIcon = getCaptchaImage();
		CaptchaImage.setIcon(captchaIcon);
		CaptchaImage.repaint();
	}

	private ImageIcon getCaptchaImage() {

		 ImageIcon captchaIcon = APIUtils.CaptchaImage();
		 captchaIcon.setImage(captchaIcon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH));
		 return captchaIcon;
	}
	private void addEvent() {

		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String captcha, username, password;
				captcha = field3.getText();
				username = field.getText().trim();
				password = String.copyValueOf(field2.getPassword());

				if(APIUtils.verifyCaptcha(captcha)) {
					User user = UserService.verify(username, password);
					if (user != null) {
						Global.curUser = user;

						frame.dispose();
						new MainFrame(username);
					} else {
						JOptionPane.showMessageDialog(null, "账号或密码错误", "警告", JOptionPane.WARNING_MESSAGE);
						field3.setText("");
						refreshCaptchaImage();

						field2.setText("");
						field2.requestFocus();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "验证码错误", "警告", JOptionPane.WARNING_MESSAGE);
					refreshCaptchaImage();
					field3.setText("");
					field3.requestFocus();
				}
			}
		});

		buttonSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//frame.setVisible(false);
				frame.dispose();
				new RegisterFrame();
			}
		});
		CaptchaImage.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				refreshCaptchaImage();
			}
		});
	}
	private void empty() {
		field.setText("");
		field2.setText("");
	}

}
