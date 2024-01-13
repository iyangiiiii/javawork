package site.iyangiiiii.UI;

import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.Order;
import site.iyangiiiii.Service.GoodsService;
import site.iyangiiiii.Service.OrderService;
import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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
        cartTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // 返回 false 表示所有单元格都不可编辑
                return false;
            }
        };
        cartTableModel.addColumn("商品名称");
        cartTableModel.addColumn("价格");

        totalLabel.setText("总价格: 0");

        // 初始化购物车表格
        cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);

        cartTable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 不需要实现
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // 按下 Delete 键时触发
                if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    int selectedRow = cartTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // 删除选中行
                        cartTableModel.removeRow(selectedRow);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 不需要实现
            }
        });

        // 初始化购买按钮
        JButton buyButton = new JButton("购买");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double rowCount = cartTableModel.getRowCount();
                double total = 0.0;
                for (int i = 0; i < rowCount; i++) {
                    total += (double) cartTableModel.getValueAt(i, 1);
                }
                showConfirmationDialog(total);
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
            List<Goods> goodsList = new ArrayList<>();
            Vector<Vector> data = cartTableModel.getDataVector();
            for(Vector item: data) {
                String goodsName = (String) item.get(0);
                Goods goods = GoodsService.findGoodsByName(goodsName);
                goodsList.add(goods);
            }
            if(APIUtils.addOrder("未发货", goodsList) == 0) {
                updateTotalPrice(); // 更新总价格显示
                cartTableModel.setRowCount(0);
            }
            else {
                JOptionPane.showMessageDialog(ShoppingCart.this, "购买失败！");
            }
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
