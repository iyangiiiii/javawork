package site.iyangiiiii.UI;
import site.iyangiiiii.Bean.ChatInfo;
import site.iyangiiiii.Entities.Chat;
import site.iyangiiiii.Entities.User;
import site.iyangiiiii.Service.ChatService;
import site.iyangiiiii.Service.UserService;
import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ChatFrame {
    private static User cur = null;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private List<String> objectList = new ArrayList<>();
    protected static List<ChatInfo> curHistory = new ArrayList<>();
    protected static DefaultTableModel model = new DefaultTableModel();
    public JPanel createChatPanel() {
        Color color = new Color(179, 206, 255);

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

        refreshUser();
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
                ChatService.addChat(cur.getUid(), message);
                messageField.setText("");
                refreshUser();

                // 模拟延迟后的回复
//                simulateResponse(chatArea);
            }
        });

        JList<String> objectListPanel = new JList<>(listModel);

        objectListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        objectListPanel.setOpaque(false);
        JScrollPane objectScrollPane = new JScrollPane(objectListPanel);
        objectScrollPane.setPreferredSize(new Dimension(150, panel.getHeight())); // 调整宽度
        objectScrollPane.setOpaque(false);
        panel.add(objectScrollPane, BorderLayout.WEST);

        // 2. 添加对象列表选择监听器
        objectListPanel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // 获取用户选择的对象，并执行相应操作
                    String username = objectListPanel.getSelectedValue();
                    model.setRowCount(0);
                    cur = UserService.findUserByUsername(username);
                    refresh();
                    // 实现切换到所选对象的聊天记录逻辑
                    // 这里可以添加你的逻辑代码
                }
            }
        });

        return panel;
    }

    protected void refreshUser() {
        listModel.clear();
        List<User> res = APIUtils.showChatUser(Global.curUser.getUid());
        for(User user: res){
            String username = user.getUsername();
            listModel.addElement(username);
        }
    }

    protected void refresh(){
        if(cur == null) curHistory = new ArrayList<>();
        else curHistory = APIUtils.getHistory(Global.curUser.getUid(), cur.getUid());
        model.setRowCount(0);
        for(ChatInfo chatInfo: curHistory) {
            model.addRow(chatInfo.toVector());
        }
    }
}