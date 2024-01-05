package site.iyangiiiii.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Leaderboard extends JPanel {
    private JTable leaderboardTable;
    public Leaderboard() {

        // 创建表头和数据
        String[] columns = {"商品", "销量", "价格"};
        Object[][] data = {
                {"商品1", 100, 800},
                {"商品2", 90, 447},
                {"商品3", 80, 123},
                {"商品4", 70, 89},
                {"商品5", 60, 98154}
        };

        setOpaque(false);

        // 创建默认表格模型
        DefaultTableModel model = new DefaultTableModel(data, columns);
        leaderboardTable = new JTable(model);

        Font headerFont = new Font("仿宋", Font.BOLD, 24); // 字体、风格、字号
        leaderboardTable.getTableHeader().setFont(headerFont);
        // 设置表格字体和大小
        Font tableFont = new Font("仿宋", Font.PLAIN, 24); // 字体、风格、字号
        leaderboardTable.setFont(tableFont);

        // 设置行高度和列宽度
        int rowHeight = 40; // 设置行高度为40
        int columnWidth = 200; // 设置列宽度为200
        leaderboardTable.setRowHeight(rowHeight);
        for (int i = 0; i < leaderboardTable.getColumnCount(); i++) {
            leaderboardTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
        }

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);

        // 设置表格和滚动面板的大小
        int tableWidth = (int) (1500 * 0.9); // 表格宽度为主界面宽度的80%
        int tableHeight = (int) (800 * 0.8); // 表格高度为主界面高度的60%

        // 设置表格大小
        leaderboardTable.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));

        // 设置滚动面板大小以适应表格
        scrollPane.setPreferredSize(new Dimension(tableWidth, tableHeight));

        add(scrollPane); // 将表格滚动面板添加到排行榜面板中
        leaderboardTable.setOpaque(false);
        leaderboardTable.getTableHeader().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 加载图片
        ImageIcon imageIcon = new ImageIcon("S:\\project\\ideajava\\Java_Library_Management_System\\img\\rank.jpg");
        Image image = imageIcon.getImage();

        // 绘制背景图片
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
