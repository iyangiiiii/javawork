package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageCommodity {

    /**
     * 商品管理界面
     *
     * @author iyangiii
     */
    public class AddCommodityPanel extends JPanel {

        // 标签
        private JLabel jLabel = new JLabel("添加商品");
        private JLabel jLabel2 = new JLabel("商品名：");
        private JLabel jLabel3 = new JLabel("厂  商：");
        private JLabel jLabel4 = new JLabel("库  存：");
        private JLabel jLabel5 = new JLabel("类  别：");
        private JLabel jLabel6 = new JLabel("状  态：");
        private JLabel jLabel7 = new JLabel("价  格：");
        private JLabel jLabel_d1 = new JLabel("删除商品");
        private JLabel jLabel_d2 = new JLabel("商品编号：");
        // 文本框
        private JTextField field = new JTextField(20);
        private JTextField field2 = new JTextField(20);
        private JTextField field3 = new JTextField(20);
        private JTextField field4 = new JTextField(20);
        private JTextField field5 = new JTextField(20);
        private JTextField field6 = new JTextField(20);
        private JTextField field_d1 = new JTextField(22);
        private JButton button_d1 = new JButton("确定");
        private Font font_d1 = new Font("宋体", Font.BOLD, 40);
        private Font font_d2 = new Font("宋体", Font.BOLD, 25);
        // 按钮
        private JButton button = new JButton("确定");
        // 字体
        private Font font = new Font("宋体", Font.BOLD, 40);
        private Font font2 = new Font("宋体", Font.BOLD, 25);
        private Font font3 = new Font("宋体", Font.BOLD, 20);

        public AddCommodityPanel() {
            // 设置布局为null，以便手动控制组件的位置
            setLayout(null);
            addComponents();
            // 添加背景图片
            ImageIcon backgroundIcon = new ImageIcon(Global.getImgPath("manage.jpg"));
            Image backgroundImg = backgroundIcon.getImage();
            Image scaledBackgroundImg = backgroundImg.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
            ImageIcon scaledBackgroundIcon = new ImageIcon(scaledBackgroundImg);

            JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
            backgroundLabel.setBounds(0, 0, 1500, 800);
            add(backgroundLabel);

            // 添加组件

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 在这里添加添加商品的逻辑
                    String name = field4.getText();
                    String factory = field.getText();
                    String inv = field2.getText();
                    String variety = field3.getText();
                    String state = field6.getText();
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
                }
            });

            // 为删除商品按钮添加事件监听器
            button_d1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 在这里添加删除商品的逻辑
                    String productCode = field_d1.getText();

                    // 调用删除商品的方法
                    int gid = Integer.parseInt(productCode);
                    int result = APIUtils.deleteGoods(gid);

                    // 处理删除结果，可以根据实际情况进行修改
                    if (result != -1) {
                        JOptionPane.showMessageDialog(null, "成功删除商品：" + productCode);
                    } else {
                        JOptionPane.showMessageDialog(null, "删除商品失败：" + productCode);
                    }
                }
            });

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
            jLabel3.setBounds(125, 240, 250, 30);

            jLabel4.setFont(font2);
            jLabel4.setBounds(125, 280, 250, 30);

            jLabel5.setFont(font2);
            jLabel5.setBounds(125, 320, 250, 30);

            jLabel6.setFont(font2);
            jLabel6.setBounds(125, 360, 250, 30);

            jLabel7.setFont(font2);
            jLabel7.setBounds(125, 400, 250, 30);

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

            field6.setFont(font3);
            field6.setBounds(245, 360, 200, 30);
            field6.setOpaque(false);

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
            add(field);
            add(field2);
            add(field3);
            add(field4);
            add(field5);
            add(field6);
            add(button);
        }
    }

}
