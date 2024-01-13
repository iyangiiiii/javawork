package site.iyangiiiii.UI;
import site.iyangiiiii.Bean.ChatInfo;
import site.iyangiiiii.Entities.Chat;
import site.iyangiiiii.Service.ChatService;
import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class ChatFrame {
    protected static List<ChatInfo> curHistory = new ArrayList<>();
    protected static DefaultTableModel model = new DefaultTableModel();
    public JPanel createChatPanel() {
        ImageIcon icon = new ImageIcon(Global.getImgPath("chatframe.jpg"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(1500, 695, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        ChatPanel panel = new ChatPanel(scaledIcon.getImage());
        panel.setLayout(new BorderLayout());

        JTable chatArea = new JTable(model);
        chatArea.setRowHeight(30);
        chatArea.setOpaque(false);
        chatArea.setFont(new Font("宋体", Font.PLAIN, 25));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        JTextField messageField = new JTextField();
        messageField.setPreferredSize(new Dimension(280, 30));
//        messageField.setOpaque(false);
        bottomPanel.add(messageField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        bottomPanel.add(sendButton, BorderLayout.EAST);

        JTableHeader blankHeader = new JTableHeader();
        blankHeader.setVisible(false);
        chatArea.setTableHeader(blankHeader);

        chatArea.setShowVerticalLines(false);
        model.addColumn("1");
        model.addColumn("2");
        model.addColumn("3");

        int tableWidth = 1500;
        int sideWidth = (int) (tableWidth * 0.1);
        int restWidth = tableWidth - sideWidth * 2;
        chatArea.getColumnModel().getColumn(0).setPreferredWidth(sideWidth);
        chatArea.getColumnModel().getColumn(1).setPreferredWidth(restWidth);
        chatArea.getColumnModel().getColumn(2).setPreferredWidth(sideWidth);

        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                if(curHistory.get(row).getDirection() == 1)
                    setHorizontalAlignment(SwingConstants.RIGHT);
                else
                    setHorizontalAlignment(SwingConstants.LEFT);

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        refresh();

        chatArea.getColumnModel().getColumn(1).setCellRenderer(customRenderer);

        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                sendMessage(messageField, chatArea);
            }
        });

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                ChatService.addChat(1, message);
                messageField.setText("");
                refresh();

                // 模拟延迟后的回复
//                simulateResponse(chatArea);
            }
        });

        return panel;
    }
    private void sendMessage(JTextField messageField, JTextArea chatArea) {
        String message = messageField.getText();
        chatArea.append("User1: " + message + "\n");
        messageField.setText("");

        // 模拟延迟后的回复
        simulateResponse(chatArea);
    }
    private void simulateResponse(JTextArea chatArea) {
        // 模拟延迟
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            chatArea.append("User2: Hello, I'm User2!\n");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected void refresh(){
        curHistory = APIUtils.getHistory(Global.curUser.getUid());
        model.setRowCount(0);
        for(ChatInfo chatInfo: curHistory) {
            model.addRow(chatInfo.toVector());
        }
    }
}