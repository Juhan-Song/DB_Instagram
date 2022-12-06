import ImageResizer.ImageResizer;
import Query.*;
import User.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class OthersBoard extends JFrame {
    private String user;
    private String otherUser;
    private JPanel othersBoard;
    private JLabel lblFollower;
    private JButton btnFollower;
    private JButton btnFollowing;
    private JLabel lblFollowing;
    private JLabel lblOther;
    private JScrollPane scrOther;
    private JButton btnFollow;
    private JLabel lblLogo;
    private JTextField txtText;
    private JButton btnEnter;
    private JPanel container;
    private ArrayList<Messages> messages;

    OthersBoard(String user, String nickName) {
        this.user = new String(user);
        this.otherUser = new String(nickName);

        lblOther.setText(otherUser);
        messages = new ArrayList<Messages>();
        container.setLayout(new GridLayout(1000, 1));
        scrOther.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        try {
            ConnectDB con = new ConnectDB();

            lblFollower.setText(String.valueOf(Select.CountFollower(con.getCon(), otherUser)));
            lblFollowing.setText(String.valueOf(Select.CountFollowing(con.getCon(), otherUser)));

            boolean isFollowing = Select.isFollowing(con.getCon(), user, otherUser);
            if (isFollowing == true) {
                btnFollow.setBackground(Color.WHITE);
                btnFollow.setForeground(Color.BLUE);
                btnFollow.setText("Followed");
            }
            else {
                btnFollow.setBackground(Color.BLUE);
                btnFollow.setForeground(Color.WHITE);
                btnFollow.setText("Follow");
            }

            txtText.setText("");

            messages = Select.SelectUserMessages(con.getCon(), otherUser);
            for (int i = 0; i < messages.size(); i++) {
                JLabel lbl = new JLabel();
                if (messages.get(i).getFrom().equals(otherUser)) {
                    lbl.setText(messages.get(i).getFrom() + ": " + messages.get(i).getMessage());
                    lbl.setHorizontalAlignment(JLabel.RIGHT);
                }
                else {
                    lbl.setText(messages.get(i).getFrom() + ": " + messages.get(i).getMessage());
                    lbl.setHorizontalAlignment(JLabel.LEFT);
                }

                container.add(lbl);
            }

            con.Disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btnFollow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (btnFollow.getText()) {
                    case "Follow":
                        try {
                        ConnectDB con = new ConnectDB();

                        boolean isExist = Select.isFollowExist(con.getCon(), user, otherUser);
                        if (isExist == false) Insert.InsertFollow(con.getCon(), user, otherUser);
                        else Update.UpdateFollow(con.getCon(), user, otherUser, true);

                        btnFollow.setBackground(Color.WHITE);
                        btnFollow.setForeground(Color.BLUE);
                        btnFollow.setText("Followed");

                        con.Disconnect();
                        } catch(SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "Followed":
                        try {
                            ConnectDB con = new ConnectDB();

                            Update.UpdateFollow(con.getCon(), user, otherUser, false);
                            Delete.DeleteFollow(con.getCon(), user, otherUser);

                            btnFollow.setBackground(Color.BLUE);
                            btnFollow.setForeground(Color.WHITE);
                            btnFollow.setText("Follow");

                            con.Disconnect();
                        } catch(SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        btnFollower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Follow follower = new Follow(otherUser, btnFollower.getText());
            }
        });

        btnFollowing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Follow following = new Follow(otherUser, btnFollowing.getText());
            }
        });

        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtText.getText().equals("")) {
                    container.removeAll();
                    scrOther.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                    try {
                        ConnectDB connectDB = new ConnectDB();

                        Insert.UploadMessage(connectDB.getCon(), user, otherUser, txtText.getText());
                        txtText.setText("");

                        messages = Select.SelectUserMessages(connectDB.getCon(), otherUser);
                        for (int i = 0; i < messages.size(); i++) {
                            JLabel lbl = new JLabel();
                            if (messages.get(i).getFrom().equals(otherUser)) {
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

        setContentPane(othersBoard);

        setSize(400, 500);
        ImageResizer.MainBoardImage(lblLogo);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(otherUser);
        setVisible(true);
    }
}
