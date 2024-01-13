package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Addcommodity {

    /**
     * 添加商品界面
     *
     * @author iyangiii
     */
    public class AddCommodityPanel extends JPanel {

        // 标签
        private JLabel jLabel = new JLabel("添加商品");
        private JLabel jLabel2 = new JLabel("类  别：");
        private JLabel jLabel3 = new JLabel("商品名：");
        private JLabel jLabel4 = new JLabel("厂  商：");
        private JLabel jLabel5 = new JLabel("库  存：");
        private JLabel jLabel_d1 = new JLabel("删除商品");
        private JLabel jLabel_d2 = new JLabel("商品编号：");
        // 文本框
        private JTextField field = new JTextField(20);
        private JTextField field2 = new JTextField(20);
        private JTextField field3 = new JTextField(20);
        private JTextField field_d1 = new JTextField(22);
        private JButton button_d1 = new JButton("确定");
        private Font font_d1 = new Font("宋体", Font.BOLD, 40);
        private Font font_d2 = new Font("宋体", Font.BOLD, 25);
        // 下拉框
        private JComboBox<String> box = new JComboBox<String>();
        // 按钮
        private JButton button = new JButton("确定");
        // 字体
        private Font font = new Font("宋体", Font.BOLD, 40);
        private Font font2 = new Font("宋体", Font.BOLD, 25);
        private Font font3 = new Font("宋体", Font.BOLD, 20);

        public AddCommodityPanel() {
            // 设置布局为null，以便手动控制组件的位置
            setLayout(null);
            // 添加组件
            addComponents();
        }

        private void addComponents() {
            // 添加商品界面的其他组件
            // ...
            jLabel_d1.setFont(font_d1);
            jLabel_d2.setFont(font_d2);
            jLabel_d1.setBounds(1100, 80, 400, 100);
            jLabel_d2.setBounds(965, 200, 250, 30);
            field_d1.setBounds(1100, 200, 200, 30);
            button_d1.setBounds(1020, 270, 325, 35);
            add(jLabel_d1);
            add(jLabel_d2);
            add(field_d1);
            add(button_d1);

            field_d1.setFont(font3);
            button_d1.setFont(font2);

            // 设置文本框透明
            field_d1.setOpaque(false);

            jLabel.setFont(font);
            jLabel.setBounds(195, 80, 400, 100);

            jLabel2.setFont(font2);
            jLabel2.setBounds(125, 200, 250, 30);

            jLabel3.setFont(font2);
            jLabel3.setBounds(125, 260, 250, 30);

            jLabel4.setFont(font2);
            jLabel4.setBounds(125, 320, 250, 30);

            jLabel5.setFont(font2);
            jLabel5.setBounds(125, 380, 250, 30);

            box.addItem("请选择类别");
            box.addItem("日用品");
            box.setFont(font3);
            box.setBounds(245, 200, 200, 30);
            box.setOpaque(false);

            field.setFont(font3);
            field.setBounds(245, 260, 200, 30);
            field.setOpaque(false);

            field2.setFont(font3);
            field2.setBounds(245, 320, 200, 30);
            field2.setOpaque(false);

            field3.setFont(font3);
            field3.setBounds(245, 380, 200, 30);
            field3.setOpaque(false);

            button.setFont(font2);
            button.setBounds(120, 455, 325, 35);
            button.setOpaque(false);

            add(jLabel);
            add(jLabel2);
            add(jLabel3);
            add(jLabel4);
            add(jLabel5);
            add(box);
            add(field);
            add(field2);
            add(field3);
            add(button);
        }
    }
}
