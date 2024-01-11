package site.iyangiiiii.UI;

/**
 * 图书查询界面
 * @author K.X
 * 
 * */

import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Service.GoodsService;
import site.iyangiiiii.Utils.Global;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
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
	 * 一个大标签
	 * 
	 * 一个下拉框 一个文本框 一个按钮
	 * 
	 * 一个表格
	 */
	// 分层窗格
	public JLayeredPane jLayeredPane = new JLayeredPane();
	// 标签
	private JLabel jLabel2 = new JLabel("请选择查询方式：");
	// 文本框
	private JTextField field = new JTextField(25);
	// 大小
	private Dimension dimension = new Dimension(220, 30);
	// 下拉框
	private JComboBox<String> box = new JComboBox<String>();
	// 按钮
	private JButton button = new JButton("搜索");
	// 表格
	public DefaultTableModel model = new DefaultTableModel();
	private Font font1 = new Font("宋体", Font.BOLD, 25);
	private Font font2 = new Font("宋体", Font.BOLD, 20);
	// 存储下拉选项
	private String s;
	private String commodity;
	private int id;

	public CommoditySearch() {
		Color color = new Color(179, 206, 255);
		// 改变背景图片
		ImageIcon icon = new ImageIcon(Global.getImgPath("commoditysearch.png"));
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel label = new JLabel(scaledIcon);
		label.setBounds(0, 0, 1500, 800);
		// 标签

		jLabel2.setFont(font1);
		jLabel2.setBounds(350, 130, 250, 30);
		jLabel2.setForeground(Color.BLACK);

		// 下拉框
		box.setSize(dimension);
		box.addItem("按照编号查找");
		box.addItem("按照类别查找");
		box.addItem("按照商品名查找");
		box.addItem("按照商品状态查找");
		box.addItem("按照厂家查找");
		box.setFont(font2);
		box.setBounds(350, 185, 200, 40);
		// box.setBackground(Color.cyan);
		box.setOpaque(false);

		// 文本框
		field.setFont(font2);
		field.setSize(dimension);
		field.setBounds(600, 188, 250, 35);
		field.setBackground(Color.WHITE);
		field.setForeground(Color.BLACK);
		field.setText("123");
		field.setOpaque(true);

		// 按钮
		button.setFont(font1);
		button.setBounds(880, 185, 100, 40);
		button.setForeground(Color.BLACK);
		button.setBackground(color);
		button.setOpaque(false);

		// 表格
		model.addColumn("编号", new Vector<Integer>());
		model.addColumn("类别", new Vector<Integer>());
		model.addColumn("商品名", new Vector<Integer>());
		model.addColumn("厂家", new Vector<Integer>());
		model.addColumn("状态", new Vector<Integer>());
		JTable jTable = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 设置单元格不可编辑
			}
		};
		jTable.getTableHeader().setReorderingAllowed(false); // 设置列不可拖动

		JScrollPane pane = new JScrollPane(jTable);
		pane.setBounds(350, 265, 800, 400);

		JTableHeader head = jTable.getTableHeader();
		// 设置表头的大小
		head.setPreferredSize(new Dimension(head.getWidth(), 30));
		// 设置表头字体大小
		head.setFont(new Font("宋体", Font.BOLD, 20));
		// head.setForeground(Color.cyan);
		head.setBackground(color);
		// 设置表格的行宽
		jTable.setRowHeight(30);
		// 设置表格行中字体大小
		jTable.setFont(new Font("宋体", Font.ROMAN_BASELINE, 17));
		/* 设置表格中的内容居中 */
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jTable.setDefaultRenderer(Object.class, renderer);

		// 添加事件
		addEvent();

		// 加入分层窗口
		jLayeredPane.add(label, 0, 0);
		jLayeredPane.add(jLabel2, 100, 2);
		jLayeredPane.add(box, 100, 3);
		jLayeredPane.add(field, 100, 4);
		jLayeredPane.add(button, 100, 5);
		jLayeredPane.add(pane, 100, 6);

		// TODO 在这里添加商品
		List<Goods> goodsList = GoodsService.getAllGoods();
		for(Goods goods: goodsList) {

		}
	}

	private void addEvent() {

		// 获取下拉列表值
		s = "按照编号查找";
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					s = (String) e.getItem();
				}
			}
		});

		// 添加搜索按钮事件
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setRowCount(0);
//				if (s.equals("按照编号查找")) {
//					commodity = field.getText().trim();
//					id = Integer.parseInt(commodity);
//					FindCommodity.findcomid(model, id);
//				} else if (s.equals("按照商品名查找")) {
//					commodity = field.getText().trim();
//					FindCommodity.findcommodityname(model, commodity);
//				} else if (s.equals("按照商品状态查找")) {
//					commodity = field.getText().trim();
//					FindCommodity.findstatus(model, commodity);
//				}else if (s.equals("按照类别查找")){
//					commodity = field.getText().trim();
//					FindCommodity.findtype(model, commodity);
//				} else if (s.equals("按照厂家查找")) {
//					try {
//						commodity = field.getText().trim();
//						FindCommodity.findmanufacturer(model, commodity);
//					} catch (Exception e1) {
//					}
//				}
				field.setText("");
			}
		});
	}
}
