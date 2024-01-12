package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.Global;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame {

	private JLabel jLabel2 = new JLabel("用 户 名：");
	private JLabel jLabel5 = new JLabel(" 密  码：");
	private JLabel jLabel6 = new JLabel("确认密码:");
	private JLabel jLabel7 = new JLabel("                                       密码长度：6~16位，不能含有空格 ");

	private String user;
	private String password;
	private String password2;

	private Font font2 = new Font("宋体", Font.BOLD, 25);
	private Font font3 = new Font("宋体", Font.BOLD, 20);
	private Font font4 = new Font("小篆", Font.ITALIC, 13);
	private Font font5 = new Font("宋体", Font.BOLD, 17);

	private JTextField field = new JTextField(18);
	private JPasswordField field4 = new JPasswordField(18);
	private JPasswordField field5 = new JPasswordField(18);

	private JButton button = new JButton("注 册");
	private JButton button2 = new JButton("返回");

	private Dimension dimension = new Dimension(350, 40);

	private JPanel jPanel = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JPanel jPanel5 = new JPanel();
	private JPanel jPanel6 = new JPanel();
	private JPanel jPanel7 = new JPanel();

	private JFrame frame = new JFrame("注册");

	public RegisterFrame() {
		frame.setSize(750, 550);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);

		addassembly();

		transparent();

		addEvent();

		ImageIcon originalIcon  = new ImageIcon(Global.getImgPath("register.jpg"));
		Image originalImage = originalIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(750, 550, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel backgroundLabel = new JLabel(scaledIcon);
		backgroundLabel.setBounds(0, 0, 750, 550);
		frame.add(backgroundLabel);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void addassembly() {
		jLabel2.setFont(font2);
		jLabel5.setFont(font2);
		jLabel6.setFont(font2);
		jLabel7.setFont(font4);
		jLabel7.setForeground(Color.red);

		field.setFont(font3);
		field4.setFont(font3);
		field5.setFont(font3);
		field.setForeground(Color.blue);
		field4.setForeground(Color.blue);
		field5.setForeground(Color.blue);
		button.setPreferredSize(dimension);
		button2.setFont(font5);
		button2.setBounds(2, 2, 70, 30);
		button2.setBackground(Color.cyan);
		button2.setOpaque(false);

		jPanel.setOpaque(false);
		jPanel.add(button2);
		jPanel2.add(jLabel2);
		jPanel2.add(field);
		jPanel5.add(jLabel5);
		jPanel5.add(field4);
		jPanel5.add(jLabel7);
		jPanel6.add(jLabel6);
		jPanel6.add(field5);
		jPanel.setOpaque(false);
		jPanel7.add(button);
		jPanel.setLayout(null);
		jPanel.setBounds(0, 0, 450, 110);
		jPanel2.setBounds(130, 120, 450, 50);
		jPanel5.setBounds(130, 210, 450, 70);
		jPanel6.setBounds(130, 300, 450, 50);
		jPanel7.setBounds(130, 390, 450, 60);

		frame.add(jPanel);
		frame.add(jPanel2);
		frame.add(jPanel5);
		frame.add(jPanel6);
		frame.add(jPanel7);
	}

	private void transparent() {
		jLabel2.setOpaque(false);
		jLabel5.setOpaque(false);
		jLabel6.setOpaque(false);

		field.setOpaque(false);
		field4.setOpaque(false);
		field5.setOpaque(false);

		jPanel.setOpaque(false);
		jPanel2.setOpaque(false);
		jPanel5.setOpaque(false);
		jPanel6.setOpaque(false);
		jPanel7.setOpaque(false);
	}

	private void addEvent() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user = field.getText().trim();
				password = field4.getText().trim();
				password2 = field5.getText().trim();
				if (user.length() == 0) {
					JOptionPane.showMessageDialog(null, "用户名不能为空", "警告", JOptionPane.WARNING_MESSAGE);
					empty();
				} else if (password.length() < 6 || password.length() > 12) {
					JOptionPane.showMessageDialog(null, "密码输入不正确", "警告", JOptionPane.WARNING_MESSAGE);
					empty();
				} else if (!(password.equals(password2))) {
					JOptionPane.showMessageDialog(null, "两次输入密码不相同", "警告", JOptionPane.WARNING_MESSAGE);
					empty();
				} else {
//					if (UserService.adduser(user, password)) {
//						JOptionPane.showMessageDialog(null, "注册成功");
//						frame.dispose();
//						new Land();
//					} else {
//						empty();
//					}

				}
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginFrame();
			}
		});
	}

	private void empty() {
		field.setText("");
		field4.setText("");
		field5.setText("");
	}
}
