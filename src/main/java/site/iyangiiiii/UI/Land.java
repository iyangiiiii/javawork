package site.iyangiiiii.UI;

import site.iyangiiiii.MainInterface;
import site.iyangiiiii.Service.Landing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * ��½����
 *
 * @author K.X
 *
 */

public class Land {

	/*
	 * ���崰�� һ�����ǩ ����С��ǩ �����ı��� ������ť ������
	 */
	// ��ǩ
	private JLabel jLabel2 = new JLabel("�û����� ");
	private JLabel jLabel3 = new JLabel("��  �룺 ");

	// ����
	private Font font2 = new Font("����", Font.BOLD, 25);
	private Font font3 = new Font("����", Font.BOLD, 20);

	// �ı���
	private JTextField field = new JTextField(22);
	private JPasswordField field2 = new JPasswordField(22);

	// ��ť
	private JButton button = new JButton("��½");
	private JButton button2 = new JButton("ע��");

	// ���
	private JPanel jPanel = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JPanel jPanel3 = new JPanel();
	private JPanel jPanel4 = new JPanel();
	private JPanel jPanel5 = new JPanel();
	// ����
	private JFrame frame = new JFrame("��½");
	// ��С
	private Dimension dimension = new Dimension(30, 30);
	private Dimension dimension2 = new Dimension(100, 50);

	public String user;
	private String password;

	public Land() {

		frame.setTitle("��½");
		// ���ô�С
		frame.setSize(750, 550);
		// ����
		frame.setLocationRelativeTo(null);
		// ����Ϊ��
		frame.setLayout(null);

		// ������
		addassembly();

		// ����͸��
		transparent();

		// ����¼�
		addEvent();

		// �ı䱳��ͼƬ
		ImageIcon originalIcon  = new ImageIcon("S:\\project\\ideajava\\Java_Library_Management_System\\img\\login.jpg");
		Image originalImage = originalIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(750, 550, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel backgroundLabel = new JLabel(scaledIcon);
		backgroundLabel.setBounds(0, 0, 750, 550);
		frame.add(backgroundLabel);
		// �����Ըı䴰��Ĵ�С
		frame.setResizable(false);
		// ���ڹر�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ڿɼ�
		frame.setVisible(true);
	}

	private void addassembly() {
		// �������
		jLabel2.setFont(font2);
		jLabel3.setFont(font2);
		button.setFont(font3);
		button2.setFont(font3);
		field.setFont(font3);
		field2.setFont(font3);
		field.setPreferredSize(dimension);
		field2.setPreferredSize(dimension);
		button.setPreferredSize(dimension2);
		button2.setPreferredSize(dimension2);
//		button.setBackground(Color.pink);
//		button2.setBackground(Color.GRAY);

		jPanel2.add(jLabel2);
		jPanel2.add(field);
		jPanel3.add(jLabel3);
		jPanel3.add(field2);
		jPanel4.add(button);
		jPanel5.add(button2);

		jPanel.setBounds(0, 60, 550, 80);
		jPanel2.setBounds(80, 170, 550, 80);
		jPanel3.setBounds(80, 260, 550, 60);
		jPanel4.setBounds(200, 320, 150, 80);
		jPanel5.setBounds(375, 320, 150, 80);

		frame.add(jPanel);
		frame.add(jPanel2);
		frame.add(jPanel3);
		frame.add(jPanel4);
		frame.add(jPanel5);

	}

	private void transparent() {
		// ����͸��
		jLabel2.setOpaque(false);
		jLabel3.setOpaque(false);
		field.setOpaque(false);
		field2.setOpaque(false);
		// button.setOpaque(false);
		// button2.setOpaque(false);
		jPanel.setOpaque(false);
		jPanel2.setOpaque(false);
		jPanel3.setOpaque(false);
		jPanel4.setOpaque(false);
		jPanel5.setOpaque(false);

	}

	private void addEvent() {

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				user = field.getText().trim();
				password = field2.getText().trim();
				if(Landing.test(user, password)) {
//					JOptionPane.showMessageDialog(null, "��½�ɹ�");
					frame.dispose();
					new MainInterface(user);
				}else {
					empty();
				}

			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//frame.setVisible(false);
				frame.dispose();
				new Register();
			}
		});
	}
	private void empty() {
		field.setText("");
		field2.setText("");
	}

}
