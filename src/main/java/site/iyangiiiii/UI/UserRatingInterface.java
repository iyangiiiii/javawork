package site.iyangiiiii.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class UserRatingInterface extends JPanel {
    private JTextField inputField;
    private JComboBox<String> ratingComboBox;
    private DefaultTableModel tableModel;
    private Image backgroundImage;
    public UserRatingInterface(String username) {

        ImageIcon backgroundIcon = new ImageIcon("S:\\project\\ideajava\\Java_Library_Management_System\\img\\comment.jpg");
        backgroundImage = backgroundIcon.getImage(); // ��ͼ��ת��Ϊͼ��

        setLayout(new BorderLayout());
        setOpaque(false); // ���õ�ǰ���Ϊ͸��

        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        inputField = new JTextField("", 100);
        inputField.setPreferredSize(new Dimension(200, 30));
        ratingPanel.add(inputField);

        String[] ratings = {"1��", "2��", "3��", "4��", "5��"};
        ratingComboBox = new JComboBox<>(ratings);
        ratingPanel.add(ratingComboBox);

        JButton submitButton = new JButton("�ύ");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comment = inputField.getText();
                String selectedRating = (String) ratingComboBox.getSelectedItem();
                addReviewToTable(username, comment, selectedRating);
            }
        });
        ratingPanel.add(submitButton);

        add(ratingPanel, BorderLayout.SOUTH);
        ratingPanel.setOpaque(false);

        // ����������ʾ���۵ı��
        String[] columnNames = {"�û���", "����", "����"};
        tableModel = new DefaultTableModel(columnNames, 0);
//        tableModel.setRowCount(0);
        JTable reviewTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reviewTable);
        scrollPane.getViewport().setBackground(Color.WHITE); // ���ñ�񱳾���ɫΪ��ɫ
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // ȥ���������ı߿�
        add(scrollPane, BorderLayout.CENTER);

        reviewTable.setFont(new Font("����", Font.PLAIN, 20));
        reviewTable.getColumnModel().getColumn(0).setMinWidth(20); // ��һ�еĿ��Ϊ200����
        reviewTable.getColumnModel().getColumn(1).setMinWidth(600); // �ڶ��еĿ��Ϊ100����
        reviewTable.getColumnModel().getColumn(1).setMinWidth(20); // �ڶ��еĿ��Ϊ100����

        reviewTable.setOpaque(false);
        reviewTable.getTableHeader().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        reviewTable.setRowHeight(30); // �����и�Ϊ30����
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ���Ʊ���ͼƬ
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    private void addReviewToTable(String username, String comment, String rating) {
        Object[] rowData = {username, comment, rating};
        tableModel.addRow(rowData);
    }
}