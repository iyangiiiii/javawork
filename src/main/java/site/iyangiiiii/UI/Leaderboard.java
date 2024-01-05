package site.iyangiiiii.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Leaderboard extends JPanel {
    private JTable leaderboardTable;
    public Leaderboard() {

        // ������ͷ������
        String[] columns = {"��Ʒ", "����", "�۸�"};
        Object[][] data = {
                {"��Ʒ1", 100, 800},
                {"��Ʒ2", 90, 447},
                {"��Ʒ3", 80, 123},
                {"��Ʒ4", 70, 89},
                {"��Ʒ5", 60, 98154}
        };

        setOpaque(false);

        // ����Ĭ�ϱ��ģ��
        DefaultTableModel model = new DefaultTableModel(data, columns);
        leaderboardTable = new JTable(model);

        Font headerFont = new Font("����", Font.BOLD, 24); // ���塢����ֺ�
        leaderboardTable.getTableHeader().setFont(headerFont);
        // ���ñ������ʹ�С
        Font tableFont = new Font("����", Font.PLAIN, 24); // ���塢����ֺ�
        leaderboardTable.setFont(tableFont);

        // �����и߶Ⱥ��п��
        int rowHeight = 40; // �����и߶�Ϊ40
        int columnWidth = 200; // �����п��Ϊ200
        leaderboardTable.setRowHeight(rowHeight);
        for (int i = 0; i < leaderboardTable.getColumnCount(); i++) {
            leaderboardTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
        }

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);

        // ���ñ��͹������Ĵ�С
        int tableWidth = (int) (1500 * 0.9); // �����Ϊ�������ȵ�80%
        int tableHeight = (int) (800 * 0.8); // ���߶�Ϊ������߶ȵ�60%

        // ���ñ���С
        leaderboardTable.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));

        // ���ù�������С����Ӧ���
        scrollPane.setPreferredSize(new Dimension(tableWidth, tableHeight));

        add(scrollPane); // �������������ӵ����а������
        leaderboardTable.setOpaque(false);
        leaderboardTable.getTableHeader().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ����ͼƬ
        ImageIcon imageIcon = new ImageIcon("S:\\project\\ideajava\\Java_Library_Management_System\\img\\rank.jpg");
        Image image = imageIcon.getImage();

        // ���Ʊ���ͼƬ
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
