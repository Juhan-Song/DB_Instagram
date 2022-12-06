import Query.ConnectDB;
import Query.Select;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchBoard extends JFrame {
    private String user;
    private Connection con;
    private JPanel searchBoard;
    private JTextField txtSearch;
    private JScrollPane scrSearch;
    private JPanel container;
    private ArrayList<User> searched;

    public JPanel getSearchBoard() {
        return searchBoard;
    }

    SearchBoard(String user) {
        this.user = new String(user);

        searched = new ArrayList<User>();
        container.setLayout(new GridLayout(1000, 1));

        scrSearch.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        txtSearch.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ConnectDB connectDB = new ConnectDB();
                con = connectDB.getCon();
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        txtSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                return;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                return;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                container.removeAll();

                scrSearch.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                String nickName = txtSearch.getText();

                searched = Select.SearchUser(con, user, nickName);
                System.out.println(nickName);

                for (int i = 0; i < searched.size(); i++) {
                    JLabel lbl = new JLabel();
                    lbl.setText(searched.get(i).getNickName());
                    lbl.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            OthersBoard other = new OthersBoard(user, lbl.getText());
                        }
                    });

                    container.add(lbl);
                    System.out.println(searched.get(i).getNickName());
                }

                MainFrame.getTarget().removeAll();
                MainFrame.getTarget().add(searchBoard);
                txtSearch.requestFocus();
            }
        });
    }
}
