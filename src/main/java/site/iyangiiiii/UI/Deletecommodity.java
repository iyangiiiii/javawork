package site.iyangiiiii.UI;
import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import java.awt.*;


public class DeleteCommodity {

    public class DeleteCommodityPanel extends JPanel {
        // 标签
        private JLabel jLabel = new JLabel("删除商品");
        private JLabel jLabel2 = new JLabel("商品编号：");

        // 文本框
        private JTextField field = new JTextField(22);

        // 按钮
        private JButton button = new JButton("确定");

        // 字体
        private Font font = new Font("宋体", Font.BOLD, 40);
        private Font font2 = new Font("宋体", Font.BOLD, 25);
        private Font font3 = new Font("宋体", Font.BOLD, 20);

        public DeleteCommodityPanel() {
            // 设置布局为空
            setLayout(null);

            // 设置字体
            jLabel.setFont(font);
            jLabel2.setFont(font2);

            // 设置组件位置和大小
            jLabel.setBounds(800, 80, 400, 100);
            jLabel2.setBounds(825, 200, 250, 30);
            field.setBounds(845, 200, 200, 30);
            button.setBounds(820, 270, 325, 35);

            // 添加组件到面板
            add(jLabel);
            add(jLabel2);
            add(field);
            add(button);

            // 设置组件字体
            field.setFont(font3);
            button.setFont(font2);

            // 设置文本框透明
            field.setOpaque(false);

            // 设置面板透明
            setOpaque(false);
        }
    }
}
