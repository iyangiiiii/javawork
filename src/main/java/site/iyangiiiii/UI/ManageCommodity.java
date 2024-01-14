package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class ManageCommodity {

    /**
     * 商品管理界面
     *
     * @author iyangiii
     */
    public class AddCommodityPanel extends JPanel {

        // 文本框
        private JTextField field_s = new JTextField(25);
        // 大小
        private Dimension dimension_s = new Dimension(220, 30);
        // 下拉框
        private JComboBox<String> box_s = new JComboBox<String>();
        // 按钮
        private JButton button_s = new JButton("搜索");
        private Font font_s = new Font("宋体", Font.BOLD, 20);


        private JLabel jLabel = new JLabel("添加商品");
        private JLabel jLabel2 = new JLabel("商品名：");
        private JLabel jLabel3 = new JLabel("厂  商：");
        private JLabel jLabel4 = new JLabel("库  存：");
        private JLabel jLabel5 = new JLabel("类  别：");
        private JLabel jLabel6 = new JLabel("状  态：");
        private JLabel jLabel7 = new JLabel("价  格：");
        // 文本框
        private JTextField field = new JTextField(20);
        private JTextField field2 = new JTextField(20);
        // 下拉框
        private JComboBox<String> box = new JComboBox<String>();
        private JTextField field3 = new JTextField(20);
        private JTextField field4 = new JTextField(20);
        private JTextField field5 = new JTextField(20);

        // 按钮
        private JButton button = new JButton("确定");
        // 字体
        private Font font = new Font("宋体", Font.BOLD, 40);
        private Font font2 = new Font("宋体", Font.BOLD, 25);
        private Font font3 = new Font("宋体", Font.BOLD, 20);

        private DefaultTableModel tableModel;
        JTable table;

        public AddCommodityPanel() {
            // 设置布局为null，以便手动控制组件的位置
            setLayout(null);
            addComponents();
            //*************************************************************
            Color color = new Color(179, 206, 255);
            // 表头
            String[] columnNames = {"商品名", "厂商", "库存", "类别", "状态", "价格"};
            // 创建表格模型
            tableModel = new DefaultTableModel(APIUtils.getAllGoodsInfo(), columnNames);

            // 创建表格
            table = new JTable(tableModel);
            // 创建表格模型

            JScrollPane scrollPane = new JScrollPane(table);
            if (APIUtils.isAdmin()) scrollPane.setBounds(500, 200, 900, 450);
            else scrollPane.setBounds(300,200,900,450);

            // 设置表格属性
            table.setOpaque(false);
            ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);

            // 设置表头属性
            JTableHeader head = table.getTableHeader();
            head.setPreferredSize(new Dimension(head.getWidth(), 30));
            head.setFont(new Font("宋体", Font.BOLD, 20));
            head.setBackground(color);

            // 设置表格行高度
            table.setRowHeight(30);

            // 设置表格行中字体大小
            table.setFont(new Font("宋体", Font.PLAIN, 17));

            // 设置表格内容居中
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            table.setDefaultRenderer(Object.class, renderer);

            // 将滚动窗格添加到面板中
            add(scrollPane);
            //***************************
            // 添加背景图片
            ImageIcon backgroundIcon = new ImageIcon(Global.getImgPath("manage.jpg"));
            Image backgroundImg = backgroundIcon.getImage();
            Image scaledBackgroundImg = backgroundImg.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
            ImageIcon scaledBackgroundIcon = new ImageIcon(scaledBackgroundImg);

            JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
            backgroundLabel.setBounds(0, 0, 1500, 800);
            add(backgroundLabel);

            // 添加组件

            button_s.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshTable(APIUtils.getGoodsData((String) box_s.getSelectedItem(), field_s.getText()));
                }
            });

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 在这里添加添加商品的逻辑
                    String name = field4.getText();
                    String factory = field.getText();
                    String inv = field2.getText();
                    String variety = field3.getText();
                    String state = (String)box.getSelectedItem();
                    String pri = field5.getText();
                    Long inventory = Long.parseLong(inv);
                    int price = Integer.parseInt(pri);
                    // 调用添加商品的方法
                    int result = APIUtils.addGoods(name, factory, inventory,null, variety,state,price);

                    // 处理添加结果，可以根据实际情况进行修改
                    if (result != -1) {
                        JOptionPane.showMessageDialog(null, "成功添加商品：" + name);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加商品失败：" + name);
                    }

                    refreshTable();
                }
            });
        }

        private void refreshTable() {
            refreshTable(APIUtils.getAllGoodsInfo());
        }

        private void refreshTable(Object[][] data) {
            tableModel.setRowCount(0);
            for(Object[] obj: data) {
                tableModel.addRow(obj);
            }
        }

        private void addComponents() {
            // 添加商品界面的其他组件
            Color color = new Color(179, 206, 255);

            jLabel.setFont(font);
            jLabel.setBounds(195, 80, 400, 100);
            if (APIUtils.isAdmin()) {
                jLabel2.setFont(font2);
                jLabel2.setBounds(125, 200, 250, 30);

                jLabel3.setFont(font2);
                jLabel3.setBounds(125, 240, 250, 30);

                jLabel4.setFont(font2);
                jLabel4.setBounds(125, 280, 250, 30);

                jLabel5.setFont(font2);
                jLabel5.setBounds(125, 320, 250, 30);

                jLabel6.setFont(font2);
                jLabel6.setBounds(125, 360, 250, 30);

                jLabel7.setFont(font2);
                jLabel7.setBounds(125, 400, 250, 30);
            // 下拉框
                box_s.setSize(dimension_s);
                box_s.addItem("查找所有商品");
                box_s.addItem("按照商品名查找");
                box_s.addItem("按照厂商查找");
                box_s.addItem("按照类别查找");
                box_s.addItem("按照状态查找");
                box_s.setFont(font_s);
                box_s.setBounds(550, 155, 200, 40);
                box_s.setOpaque(false);

                field_s.setFont(font3);
                field_s.setBounds(800, 155, 250, 40);
                field_s.setOpaque(false);

                button_s.setFont(font2);
                button_s.setBounds(1100, 155, 100, 40);
    //            button_s.setForeground(Color.BLACK);
    //            button_s.setBackground(color);
                button_s.setOpaque(false);

                field4.setFont(font3);
                field4.setBounds(245, 200, 200, 30);
                field4.setOpaque(false);

                field.setFont(font3);
                field.setBounds(245, 240, 200, 30);
                field.setOpaque(false);

                field2.setFont(font3);
                field2.setBounds(245, 280, 200, 30);
                field2.setOpaque(false);

                field3.setFont(font3);
                field3.setBounds(245, 320, 200, 30);
                field3.setOpaque(false);

                box.addItem("请选择状态");
                box.addItem("库存充足");
                box.addItem("库存不足");
                box.setFont(font3);
                box.setBounds(245, 360, 200, 30);
                box.setOpaque(false);

                field5.setFont(font3);
                field5.setBounds(245, 400, 200, 30);
                field5.setOpaque(false);

                button.setFont(font2);
                button.setBounds(120, 505, 325, 35);
                button.setOpaque(false);

                add(jLabel);
                add(jLabel2);
                add(jLabel3);
                add(jLabel4);
                add(jLabel5);
                add(jLabel6);
                add(jLabel7);
                add(field3);
                add(field);
                add(field2);
                add(field4);
                add(field5);
                add(box);
                add(button);
                add(box_s);
                add(field_s);
                add(button_s);
            }
            else {
                box_s.setSize(dimension_s);
                box_s.addItem("查找所有商品");
                box_s.addItem("按照商品名查找");
                box_s.addItem("按照厂商查找");
                box_s.addItem("按照类别查找");
                box_s.addItem("按照状态查找");
                box_s.setFont(font_s);
                box_s.setBounds(350, 155, 200, 40);
                box_s.setOpaque(false);

                field_s.setFont(font3);
                field_s.setBounds(600, 155, 250, 40);
                field_s.setOpaque(false);

                button_s.setFont(font2);
                button_s.setBounds(900, 155, 100, 40);
//            button_s.setForeground(Color.BLACK);
//            button_s.setBackground(color);
                button_s.setOpaque(false);
                add(box_s);
                add(field_s);
                add(button_s);
            }
        }
    }

}
