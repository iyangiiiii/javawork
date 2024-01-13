package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class UserRatingInterface extends JPanel {
    private JTextField inputField;
    private JComboBox<String> ratingComboBox;

    private JComboBox<String> GoodsBox;
    private DefaultTableModel tableModel;
    private Image backgroundImage;
    public UserRatingInterface(String username, Boolean admin) {
        Color color = new Color(179, 206, 255);

        ImageIcon backgroundIcon = new ImageIcon(Global.getImgPath("comment.jpg"));
        backgroundImage = backgroundIcon.getImage(); // 将图标转换为图像

        setLayout(new BorderLayout());
        setOpaque(false); // 设置当前面板为透明

        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(!admin) {
            inputField = new JTextField("", 100);
            inputField.setPreferredSize(new Dimension(200, 30));
            ratingPanel.add(inputField);

            String[] ratings = {"1星", "2星", "3星", "4星", "5星"};
            ratingComboBox = new JComboBox<>(ratings);
            ratingPanel.add(ratingComboBox);

            String[] goods = APIUtils.SoldGoods(Global.curUser.getUid());
            GoodsBox = new JComboBox<>(goods);
            ratingPanel.add(GoodsBox);

            JButton submitButton = new JButton("提交");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String comment = inputField.getText();
                    String selectedRating = (String) ratingComboBox.getSelectedItem();
                    String goods = (String) GoodsBox.getSelectedItem();
                    addReviewToTable(username, goods, comment, selectedRating);
                }
            });
            ratingPanel.add(submitButton);

            add(ratingPanel, BorderLayout.SOUTH);
            ratingPanel.setOpaque(false);
        }
        // 创建用于显示评价的表格
        String[] columnNames = {"用户名", "商品名", "评价", "评分"};
        tableModel = new DefaultTableModel(columnNames, 0);
//        tableModel.setRowCount(0);
        JTable reviewTable = new JTable(tableModel);



        JTableHeader head = reviewTable.getTableHeader();
        // 设置表头字体大小
        head.setFont(new Font("宋体", Font.BOLD, 20));
        head.setForeground(Color.BLACK); // 设置表头文字颜色
        head.setBackground(color);
        head.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(reviewTable);
        scrollPane.getViewport().setBackground(Color.WHITE); // 设置表格背景颜色为白色
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // 去除滚动面板的边框
        add(scrollPane, BorderLayout.CENTER);

        reviewTable.setFont(new Font("仿宋", Font.PLAIN, 20));
        reviewTable.getColumnModel().getColumn(0).setMinWidth(20);
        reviewTable.getColumnModel().getColumn(1).setMinWidth(20);
        reviewTable.getColumnModel().getColumn(2).setMinWidth(800);
        reviewTable.getColumnModel().getColumn(2).setMinWidth(10);

        reviewTable.setOpaque(false);
        reviewTable.getTableHeader().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        reviewTable.setRowHeight(30); // 设置行高为30像素
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景图片
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    private void addReviewToTable(String username, String goods, String comment, String rating) {
        Object[] rowData = {username, goods,  comment, rating};
        tableModel.addRow(rowData);
    }
}