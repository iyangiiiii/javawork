package site.iyangiiiii.UI;

import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.JTableHeader;

public class Leaderboard extends JPanel {
    private JTable leaderboardTable;
    public Leaderboard() {

        // 创建表头和数据
        String[] columns = {"商品", "销量", "好评率", "价格"};
        Object[][] data = APIUtils.getRanking();

        setOpaque(false);

        // 创建默认表格模型
        DefaultTableModel model = new DefaultTableModel(data, columns);
        leaderboardTable = new JTable(model);

        Font headerFont = new Font("仿宋", Font.BOLD, 24); // 字体、风格、字号
        leaderboardTable.getTableHeader().setFont(headerFont);
        // 设置表格字体和大小
        Font tableFont = new Font("仿宋", Font.PLAIN, 24); // 字体、风格、字号
        leaderboardTable.setFont(tableFont);

        JTableHeader head = leaderboardTable.getTableHeader();
        // 设置表头字体大小
        head.setFont(new Font("宋体", Font.BOLD, 20));
        head.setForeground(Color.BLACK); // 设置表头文字颜色
        head.setBackground(Color.CYAN);
        head.setOpaque(true);

        // 设置行高度和列宽度
        int rowHeight = 40; // 行高度为40
        int columnWidth = 200; // 列宽度为200
        int tableWidth = 1200; // 表格宽度
        int tableHeight = 600; // 表格高度
        leaderboardTable.setRowHeight(rowHeight);
        for (int i = 0; i < leaderboardTable.getColumnCount(); i++) {
            leaderboardTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
        }

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);

        // 设置表格和滚动面板的大小

        // 设置表格大小
        leaderboardTable.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));

        scrollPane.setPreferredSize(new Dimension(tableWidth, tableHeight));

        add(scrollPane); // 将表格滚动面板添加到排行榜面板中
        leaderboardTable.setBounds(60,150,tableWidth,tableHeight);
        leaderboardTable.setOpaque(false);
        leaderboardTable.getTableHeader().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 加载图片
        ImageIcon imageIcon = new ImageIcon(Global.getImgPath("rank.jpg"));
        Image image = imageIcon.getImage();

        // 绘制背景图片
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
