package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShoppingCart extends JPanel {

    private DefaultListModel<Product> productList;
    private DefaultTableModel cartTableModel;
    private JTable cartTable;
    private JLabel totalLabel = new JLabel("总价格: 0");

    public ShoppingCart() {

        // 初始化商品列表和购物车列表的数据模型
        productList = new DefaultListModel<>();
        productList.addElement(new Product("头皮按摩梳", Global.getImgPath("good1.jpg"), 180, 130, APIUtils.getprice()));
        productList.addElement(new Product("迪士尼皮克斯开心烘焙店系列常规眼罩", Global.getImgPath("good2.jpg"),180, 130, APIUtils.getprice()));
        productList.addElement(new Product("萌兔口罩", Global.getImgPath("good3.jpg"),180,130, APIUtils.getprice()));
        productList.addElement(new Product("Sanrio Characters圆形擦手巾", Global.getImgPath("good4.jpg"),180,130, APIUtils.getprice()));
        productList.addElement(new Product("Sanrio Characters暖贴", Global.getImgPath("good5.jpg"),180,130, APIUtils.getprice()));
        productList.addElement(new Product("Sanrio characters大号趣味扑克牌", Global.getImgPath("good6.jpg"),180,130, APIUtils.getprice()));
        productList.addElement(new Product("Sanrio Characters蒸汽眼罩", Global.getImgPath("good7.jpg"),180,130, APIUtils.getprice()));
        productList.addElement(new Product("迪士尼奇奇蒂蒂系列DIY装饰长挂件 (中国结款)", Global.getImgPath("good8.jpg"),180,130, APIUtils.getprice()));
        productList.addElement(new Product("游园周末香薰膏", Global.getImgPath("good9.jpg"),180,130, APIUtils.getprice()));
        productList.addElement(new Product("迪士尼毛毛季系列绒绒便携气囊梳",Global.getImgPath( "good10.jpg"),180, 130, APIUtils.getprice()));
        productList.addElement(new Product("芭比系列小香风果冻包",Global.getImgPath( "good11.jpg"), 180, 130, APIUtils.getprice()));
        productList.addElement(new Product("芭比系列时尚宠物包",Global.getImgPath("good12.jpg"),180, 130, APIUtils.getprice()));

        // 初始化购物车表格模型
        cartTableModel = new DefaultTableModel();
        cartTableModel.addColumn("商品名称");
        cartTableModel.addColumn("价格");

        totalLabel.setText("总价格: 0");

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
                double rowCount = cartTableModel.getRowCount();
                double total = 0.0;
                for (int i = 0; i < rowCount; i++) {
                    total += (double) cartTableModel.getValueAt(i, 1);
                }
                // 清空购物车
                showConfirmationDialog(total);
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
                        cartTableModel.addRow(new Object[]{selectedProduct.getName(), selectedProduct.getPrice()});
                        updateTotalPrice();
                    }
                }
            });
            gridPanel.add(productButton);
        }
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);
        cartPanel.add(totalLabel, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(gridPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 50, 10, 50);
        mainPanel.add(buyButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;  // 添加这一行
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(cartPanel, gbc);

// 设置布局
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }

    private void showConfirmationDialog(double total) {
        String message = "总价格为 " + total + "，是否确认支付？";
        int option = JOptionPane.showConfirmDialog(ShoppingCart.this, message, "确认支付", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // 用户确认支付，可以在这里处理支付逻辑
            JOptionPane.showMessageDialog(ShoppingCart.this, "支付成功！");
            // 清空购物车
            cartTableModel.setRowCount(0);
            updateTotalPrice(); // 更新总价格显示
        }
    }
    private void updateTotalPrice() {
        int rowCount = cartTableModel.getRowCount();
        double total = 0.0;
        for (int i = 0; i < rowCount; i++) {
            total += (double) cartTableModel.getValueAt(i, 1);  // Assuming the price column is at index 1
        }
        totalLabel.setText("总价格: " + total);
    }

    // Product 类表示每个商品，包含商品名称和图像路径
    private static class Product {
        private String name;
        private BufferedImage originalImage;
        private BufferedImage resizedImage;
        private double price;

        public Product(String name, String imagePath, int newWidth, int newHeight, double price) {
            this.name = name;
            this.price = price;

            try {
                // 读取原始图片
                this.originalImage = ImageIO.read(new File(imagePath));

                // 调整图片尺寸
                resizeImage(newWidth, newHeight);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void resizeImage(int newWidth, int newHeight) {
            // 创建一个新的BufferedImage对象，并设置其宽度和高度
            this.resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

            // 使用Graphics2D绘制调整大小后的图像
            this.resizedImage.getGraphics().drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        }

        public String getName() {
            return name;
        }

        public BufferedImage getImagePath() {
            return resizedImage;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
