import ImageResizer.ImageResizer;
import Query.ConnectDB;
import Query.Select;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Follow extends JFrame {
    private String user;
    private ArrayList<User> follow;
    private JLabel lblFollow;
    private JPanel mainPanel;
    private JScrollPane scrFollow;
    private JPanel container;
    private JLabel lblCount;

    Follow(String nickName, String type) {
        this.user = new String(nickName);
        follow = new ArrayList<User>();

        scrFollow.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        container.setLayout(new GridLayout(1000, 1));
        lblFollow.setText(type);

        switch (type) {
            case "Follower":
                try {
                    ConnectDB con = new ConnectDB();

                    lblCount.setText(String.valueOf(Select.CountFollower(con.getCon(), user)));
                    follow = Select.SelectFollower(con.getCon(), user);

                    con.Disconnect();
                } catch(SQLException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < follow.size(); i++) {
                    JLabel lblFollower = new JLabel();
                    lblFollower.setText(follow.get(i).getNickName());
                    lblFollower.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            OthersBoard other = new OthersBoard(user, lblFollower.getText());
                        }
                    });
                    container.add(lblFollower);
                }
                break;
            case "Following":
                try {
                    ConnectDB con = new ConnectDB();

                    lblCount.setText(String.valueOf(Select.CountFollowing(con.getCon(), user)));
                    follow = Select.SelectFollowing(con.getCon(), user);

                    con.Disconnect();
                } catch(SQLException e) {
                    e.printStackTrace();
                }

                for (int k = 0; k < follow.size(); k++) {
                    JLabel lblFollowing = new JLabel();
                    lblFollowing.setText(follow.get(k).getNickName());
                    lblFollowing.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            OthersBoard other = new OthersBoard(user, lblFollowing.getText());
                        }
                    });

                    container.add(lblFollowing);
                }
                break;
            default:
                break;
        }

        setContentPane(mainPanel);
        setSize(250, 300);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(type);
        setVisible(true);
    }
}
