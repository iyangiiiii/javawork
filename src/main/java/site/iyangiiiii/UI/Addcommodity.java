package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Addcommodity {

    /**
     * 添加图书界面
     *
     * @author K.X
     */
    public class AddCommodityPanel extends JPanel {

        // 标签
        private JLabel jLabel = new JLabel("添加商品");
        private JLabel jLabel2 = new JLabel("类  别：");
        private JLabel jLabel3 = new JLabel("商品名：");
        private JLabel jLabel4 = new JLabel("厂  商：");
        private JLabel jLabel5 = new JLabel("库  存：");
        // 文本框
        private JTextField field = new JTextField(20);
        private JTextField field2 = new JTextField(20);
        private JTextField field3 = new JTextField(20);
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

        public void setModel(DefaultTableModel model) {
            // 设置表格模型
            // this.model = model;  // 这行可以省略，因为这里的模型并没有使用
        }
    }
}
