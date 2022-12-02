import Query.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

    OthersBoard(String user, String nickName) {
        this.user = new String(user);
        this.otherUser = new String(nickName);
        lblOther.setText(otherUser);

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

        setContentPane(othersBoard);
        setSize(400, 500);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(otherUser);
        setVisible(true);
    }
}
