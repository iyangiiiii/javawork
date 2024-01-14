package site.iyangiiiii.UI;
import site.iyangiiiii.Bean.ChatInfo;
import site.iyangiiiii.Entities.User;
import site.iyangiiiii.Service.ChatService;
import site.iyangiiiii.Service.UserService;
import site.iyangiiiii.Utils.APIUtils;
import site.iyangiiiii.Utils.Global;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.table.TableColumnModel;

public class ChatFrame {
    private static User cur = null;
    private DefaultTableModel tableModel;
    private List<String> objectList = new ArrayList<>();
    protected static List<ChatInfo> curHistory = new ArrayList<>();
    protected static DefaultTableModel model = new DefaultTableModel();
    protected List<String> cachedUser = new ArrayList<>();
    public JPanel createChatPanel() {
        Color color = new Color(179, 206, 255);

        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // 返回 false 表示所有单元格都不可编辑
                return row == 0 && column ==0;
            }
        };;

        tableModel.addColumn("");

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
                refresh();

                // 模拟延迟后的回复
//                simulateResponse(chatArea);
            }
        });

        JTable objectTablePanel = new JTable(tableModel);

        objectTablePanel.setTableHeader(blankHeader);
        objectTablePanel.setFont(new Font("宋体", Font.PLAIN, 20)); // 设置字体大小
        objectTablePanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        objectTablePanel.setOpaque(false);
        TableColumnModel objectColumnModel = objectTablePanel.getColumnModel();
        objectTablePanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int objectColumnIndex = 0; // 你需要调整的列的索引
        int objectNewWidth = 300; // 你想要设置的新宽度
        objectColumnModel.getColumn(objectColumnIndex).setPreferredWidth(objectNewWidth);

        JScrollPane objectScrollPane = new JScrollPane(objectTablePanel);
        objectScrollPane.setPreferredSize(new Dimension(150, panel.getHeight())); // 调整宽度
        objectScrollPane.setOpaque(false);
        panel.add(objectScrollPane, BorderLayout.WEST);

        objectTablePanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 不需要实现
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // 按下 Delete 键时触发
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int selectedRow = objectTablePanel.getSelectedRow();
                    int selectedColumn = objectTablePanel.getSelectedColumn();
                    if (selectedRow == 0 && selectedColumn == 0) {
                        Vector<String> tp = new Vector<>();
                        String value = (String) objectTablePanel.getValueAt(selectedRow, selectedColumn);
                        User user = UserService.findUserByUsername(value);
                        if(user != null) {
                            boolean is_ok = true;
                            for (int row = 1; row < objectTablePanel.getRowCount(); row++) {
                                String str = (String) objectTablePanel.getValueAt(row, selectedColumn);
                                if(str.equals(value)) is_ok = false;
                            }
                            if(is_ok) {
                                cachedUser.add(value);
                                tp.add(value);
                                tableModel.addRow(tp);
                                tableModel.setValueAt("",0,0);
                            }
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 不需要实现
            }
        });

        objectTablePanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = objectTablePanel.getSelectedRow();
                    int selectedColumn = objectTablePanel.getSelectedColumn();

                    if (selectedRow > 0 || selectedColumn > 0) {
                        String value = (String) objectTablePanel.getValueAt(selectedRow, selectedColumn);
                        cur = UserService.findUserByUsername(value);
                        refreshUser();
                        refresh();
                    }
                }
            }
        });

        // 2. 添加对象列表选择监听器

        return panel;
    }

    protected void refreshUser() {
        tableModel.setRowCount(0);
        Vector<String> temp = new Vector<>();
        tableModel.addRow(temp);
        List<User> userList = APIUtils.showChatUser(Global.curUser.getUid());
        Set<String> st = new HashSet<>();
        for(User user: userList) {
            Vector<String> tem = new Vector<>();
            tem.add(user.getUsername());
            st.add(user.getUsername());
            tableModel.addRow(tem);
        }
        List<String> newCachedUser = new ArrayList<>();
        for(String user: cachedUser) {
            if(!st.contains(user)) {
                Vector<String> tem = new Vector<>();
                tem.add(user);
                tableModel.addRow(tem);
                st.add(user);
                newCachedUser.add(user);
            }
        }

        if(cachedUser.size() != newCachedUser.size()) cachedUser = newCachedUser;
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