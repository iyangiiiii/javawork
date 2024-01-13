package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.Global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class ShoppingCart extends JPanel {
    private DefaultListModel<Product> productList;
    private JList<Product> productListView;
    private DefaultTableModel cartTableModel;
    private JTable cartTable;

    public ShoppingCart() {
        // 初始化商品列表和购物车列表的数据模型
        productList = new DefaultListModel<>();
        productList.addElement(new Product("商品1", Global.getImgPath("test.png")));
        productList.addElement(new Product("商品2", "path_to_image2.jpg"));
        productList.addElement(new Product("商品3", "path_to_image3.jpg"));
        productList.addElement(new Product("商品4", "path_to_image4.jpg"));
        productList.addElement(new Product("商品5", "path_to_image5.jpg"));
        productList.addElement(new Product("商品6", "path_to_image6.jpg"));
        productList.addElement(new Product("商品7", "path_to_image7.jpg"));
        productList.addElement(new Product("商品8", "path_to_image8.jpg"));
        productList.addElement(new Product("商品9", "path_to_image9.jpg"));
        productList.addElement(new Product("商品10", "path_to_image10.jpg"));
        productList.addElement(new Product("商品11", "path_to_image11.jpg"));
        productList.addElement(new Product("商品12", "path_to_image12.jpg"));

        // 初始化购物车表格模型
        cartTableModel = new DefaultTableModel();
        cartTableModel.addColumn("商品名称");


        // 初始化购物车表格
        cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);

        // 初始化购买按钮
        JButton buyButton = new JButton("购买");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理购买按钮的点击事件
                // 在这里可以编写购买商品的逻辑
                JOptionPane.showMessageDialog(ShoppingCart.this, "购买成功！");
                // 清空购物车
                cartTableModel.setRowCount(0);
            }
        });

        // 设置商品网格布局
        JPanel gridPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        for (int i = 0; i < productList.size(); i++) {
            final int index = i;
            Product product = productList.getElementAt(i);
            JButton productButton = new JButton(product.getName(), new ImageIcon(product.getImagePath()));
            productButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            productButton.setHorizontalTextPosition(SwingConstants.CENTER);

            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 处理商品按钮的点击事件
                    Product selectedProduct = productList.getElementAt(index);
                    if (selectedProduct != null) {
                        cartTableModel.addRow(new Object[]{selectedProduct.getName()});
                    }
                }
            });
            gridPanel.add(productButton);
        }

        // 使用GridBagLayout进行布局
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        // 将商品网格布局放在中间
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(gridPanel, gbc);

        // 将购买按钮放在中间下方
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 50, 10, 50);
        mainPanel.add(buyButton, gbc);

        // 将购物车表格放在右下角
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(cartScrollPane, gbc);

        // 设置布局
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.EAST);

        // 设置窗口属性
        setSize(1500, 800);
    }

    // Product 类表示每个商品，包含商品名称和图像路径
    private static class Product {
        private String name;
        private String imagePath;

        public Product(String name, String imagePath) {
            this.name = name;
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public String getImagePath() {
            return imagePath;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
