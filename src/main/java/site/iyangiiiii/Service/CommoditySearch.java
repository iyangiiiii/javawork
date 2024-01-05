package site.iyangiiiii.Service;

/**
 * ͼ���ѯ����
 * @author K.X
 * 
 * */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CommoditySearch {
	/*
	 * һ�����ǩ
	 * 
	 * һ�������� һ���ı��� һ����ť
	 * 
	 * һ�����
	 */
	// �ֲ㴰��
	public JLayeredPane jLayeredPane = new JLayeredPane();
	// ��ǩ
	private JLabel jLabel2 = new JLabel("��ѡ���ѯ��ʽ��");
	// �ı���
	private JTextField field = new JTextField(25);
	// ��С
	private Dimension dimension = new Dimension(220, 30);
	// ������
	private JComboBox<String> box = new JComboBox<String>();
	// ��ť
	private JButton button = new JButton("����");
	// ���
	public DefaultTableModel model = new DefaultTableModel();
	private Font font1 = new Font("����", Font.BOLD, 25);
	private Font font2 = new Font("����", Font.BOLD, 20);
	// �洢����ѡ��
	private String s;
	private String commodity;
	private int id;

	public CommoditySearch() {
		// �ı䱳��ͼƬ
		ImageIcon icon = new ImageIcon("S:\\project\\ideajava\\Java_Library_Management_System\\img\\commoditysearch.png");
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel label = new JLabel(scaledIcon);
		label.setBounds(0, 0, 1500, 800);
		// ��ǩ

		jLabel2.setFont(font1);
		jLabel2.setBounds(350, 130, 250, 30);
		jLabel2.setForeground(Color.cyan);

		// ������
		box.setSize(dimension);
		box.addItem("���ձ�Ų���");
		box.addItem("����������");
		box.addItem("������Ʒ������");
		box.addItem("������Ʒ״̬����");
		box.addItem("���ճ��Ҳ���");
		box.setFont(font2);
		box.setBounds(350, 185, 200, 40);
		// box.setBackground(Color.cyan);
		box.setOpaque(false);

		// �ı���
		field.setFont(font2);
		field.setSize(dimension);
		field.setBackground(Color.cyan);
		field.setBounds(600, 188, 250, 35);
		field.setForeground(Color.cyan);
		field.setOpaque(false);

		// ��ť
		button.setFont(font1);
		button.setBounds(880, 185, 100, 40);
		button.setForeground(Color.cyan);
		button.setBackground(Color.cyan);
		button.setOpaque(false);

		// ���
		model.addColumn("���", new Vector<Integer>());
		model.addColumn("���", new Vector<Integer>());
		model.addColumn("��Ʒ��", new Vector<Integer>());
		model.addColumn("����", new Vector<Integer>());
		model.addColumn("״̬", new Vector<Integer>());
		JTable jTable = new JTable(model);

		JScrollPane pane = new JScrollPane(jTable);
		pane.setBounds(350, 265, 800, 400);

		// for(int k = 0; k < 30; k++) {
		// model.addRow(new Vector<Integer>());
		// }
		FindCommodity.allcommodity(model);

		JTableHeader head = jTable.getTableHeader();
		// ���ñ�ͷ�Ĵ�С
		head.setPreferredSize(new Dimension(head.getWidth(), 30));
		// ���ñ�ͷ�����С
		head.setFont(new Font("����", Font.BOLD, 20));
		// head.setForeground(Color.cyan);
		head.setBackground(Color.cyan);
		// ���ñ����п�
		jTable.setRowHeight(30);
		// ���ñ�����������С
		jTable.setFont(new Font("����", Font.ROMAN_BASELINE, 17));
		/* ���ñ���е����ݾ��� */
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jTable.setDefaultRenderer(Object.class, renderer);

		// ����¼�
		addEvent();

		// ����ֲ㴰��
		jLayeredPane.add(label, Integer.valueOf(0), 0);
		jLayeredPane.add(jLabel2, Integer.valueOf(100), 2);
		jLayeredPane.add(box, Integer.valueOf(100), 3);
		jLayeredPane.add(field, Integer.valueOf(100), 4);
		jLayeredPane.add(button, Integer.valueOf(100), 5);
		jLayeredPane.add(pane, Integer.valueOf(100), 6);
	}

	private void addEvent() {

		// ��ȡ�����б�ֵ
		s = "���ձ�Ų���";
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					s = (String) e.getItem();
				}
			}
		});

		// ���������ť�¼�
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setRowCount(0);
				if (s.equals("���ձ�Ų���")) {
					commodity = field.getText().trim();
					id = Integer.parseInt(commodity);
					FindCommodity.findcomid(model, id);
				} else if (s.equals("������Ʒ������")) {
					commodity = field.getText().trim();
					FindCommodity.findcommodityname(model, commodity);
				} else if (s.equals("������Ʒ״̬����")) {
					commodity = field.getText().trim();
					FindCommodity.findstatus(model, commodity);
				}else if (s.equals("����������")){
					commodity = field.getText().trim();
					FindCommodity.findtype(model, commodity);
				} else if (s.equals("���ճ��Ҳ���")) {
					try {
						commodity = field.getText().trim();
						FindCommodity.findmanufacturer(model, commodity);
					} catch (Exception e1) {
					}
				}
				field.setText("");
			}
		});
	}
}
