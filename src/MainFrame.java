import ImageResizer.ImageResizer;
import Query.ConnectDB;
import Query.Select;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    public static int totalUserNumber;
    private String user;
    private JButton btnHome;
    private JButton btnSearch;
    private JButton btnUser;
    private JButton btnRefresh;
    private JLabel lblLogo;
    public JPanel mainPanel;
    private static JPanel target;

    public static JPanel getTarget() {
        return target;
    }

    public void setTarget(JPanel target) {
        this.target = target;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainFrame;
    private JLabel lblUser;
    private JLabel lblFollower;
    private JLabel lblFollowing;
    private JButton btnFollower;
    private JButton btnFollowing;
    private static boolean isChange = false;

    public static boolean getIsChange() {
        return isChange;
    }

    public static void setIsChange(boolean isChange) {
        MainFrame.isChange = isChange;
    }

    private static boolean isLogout = false;

    public boolean isLogout() {
        return isLogout;
    }

    public static void setLogout(boolean logout) {
        isLogout = logout;
    }

    public void Dispose() {
        dispose();
    }

    MainFrame(String nickName) {
        this.user = new String(nickName);
        lblUser.setText(user);

        try {
            ConnectDB con = new ConnectDB();

            totalUserNumber = Select.TotalUserNumber(con.getCon());
            lblFollower.setText(String.valueOf(Select.CountFollower(con.getCon(), user)));
            lblFollowing.setText(String.valueOf(Select.CountFollowing(con.getCon(), user)));

            con.Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isLogout == true) {
                    isLogout = false;
                    dispose();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isLogout == true) {
                    isLogout = false;
                    dispose();
                }
            }
        });

        btnFollower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnFollowing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new MainBoard().getMainBoard());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new SearchBoard(user).getSearchBoard());
                mainPanel.revalidate();
                mainPanel.repaint();
                target = mainPanel;
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ConnectDB con = new ConnectDB();

                    lblFollower.setText(String.valueOf(Select.CountFollower(con.getCon(), user)));
                    lblFollowing.setText(String.valueOf(Select.CountFollowing(con.getCon(), user)));

                    con.Disconnect();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new UserBoard(nickName).getUserBoard());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        JButton[] btnGroup = new JButton[4];
        btnGroup[0] = btnHome;
        btnGroup[1] = btnSearch;
        btnGroup[2] = btnRefresh;
        btnGroup[3] = btnUser;

        setContentPane(mainFrame);
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(new MainBoard().getMainBoard());

        setSize(400, 500);
        ImageResizer.MainBoardImage(lblLogo);
        ImageResizer.InterfaceImage(btnGroup);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Instagram - MainBoard");
        setVisible(true);
    }
}
