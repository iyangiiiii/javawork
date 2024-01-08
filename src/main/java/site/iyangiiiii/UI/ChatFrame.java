package site.iyangiiiii.UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ChatPanel extends JPanel {
    private Image backgroundImage;

    public ChatPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class ChatFrame {
    public JPanel createChatPanel() {
        ImageIcon icon = new ImageIcon("src/main/java/site/iyangiiiii/img/chatframe.jpg");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(1500, 695, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        ChatPanel panel = new ChatPanel(scaledIcon.getImage());
        panel.setLayout(new BorderLayout());

        JTextArea chatArea = new JTextArea();
        chatArea.setOpaque(false);
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setFont(new Font("宋体", Font.PLAIN, 20));

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

        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage(messageField, chatArea);
            }
        });

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                chatArea.append("User1: " + message + "\n");
                messageField.setText("");

                // 模拟延迟后的回复
                simulateResponse(chatArea);
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

}