import ImageResizer.ImageResizer;
import Query.ConnectDB;
import Query.Delete;
import Query.Insert;
import Query.Select;
import User.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBoard extends JFrame {
    private String user;
    private JButton btnLogout;
    private JPanel userBoard;
    private JTextField txtText;
    private JButton btnEnter;
    private JScrollPane scrUser;
    private JPanel container;
    private JButton btnPwd;
    private JLabel lblSetting;
    private ArrayList<Messages> messages;

    private boolean isLogout = false;
    public JPanel getUserBoard() {
        return userBoard;
    }
    public boolean getIsLogout() {
        return isLogout;
    }

    UserBoard(String nickName) {
        this.user = new String(nickName);

        messages = new ArrayList<Messages>();
        container.setLayout(new GridLayout(1000, 1));
        scrUser.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        try {
            ConnectDB connectDB = new ConnectDB();
            txtText.setText("");

            messages = Select.SelectUserMessages(connectDB.getCon(), user);
            for (int i = 0; i < messages.size(); i++) {
                JLabel lbl = new JLabel();
                if (messages.get(i).getFrom().equals(user)) {
                    lbl.setText(messages.get(i).getFrom() + ": " + messages.get(i).getMessage());
                    lbl.setHorizontalAlignment(JLabel.RIGHT);
                }
                else {
                    lbl.setText(messages.get(i).getFrom() + ": " + messages.get(i).getMessage());
                    lbl.setHorizontalAlignment(JLabel.LEFT);
                }

                container.add(lbl);
            }

            connectDB.Disconnect();
        } catch(SQLException e3) {
            e3.printStackTrace();
        }

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete.Logout(new ConnectDB(), user);
                MainFrame.setLogout(true);
            }
        });

        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtText.getText().equals("")) {
                    container.removeAll();
                    scrUser.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                    try {
                        ConnectDB connectDB = new ConnectDB();

                        Insert.UploadMessage(connectDB.getCon(), user, user, txtText.getText());
                        txtText.setText("");

                        messages = Select.SelectUserMessages(connectDB.getCon(), user);
                        for (int i = 0; i < messages.size(); i++) {
                            JLabel lbl = new JLabel();
                            if (messages.get(i).getFrom().equals(user)) {
                                lbl.setText(messages.get(i).getFrom() + ": " + messages.get(i).getMessage());
                                lbl.setHorizontalAlignment(JLabel.RIGHT);
                            }
                            else {
                                lbl.setText(messages.get(i).getFrom() + ": " + messages.get(i).getMessage());
                                lbl.setHorizontalAlignment(JLabel.LEFT);
                            }

                            container.add(lbl);
                        }

                        connectDB.Disconnect();
                    } catch(SQLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        });

        ImageResizer.UserBoardSetting(lblSetting);
    }
}
