import Query.ConnectDB;
import Query.Select;
import User.Messages;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainBoard extends JFrame {
    private String user;
    private JPanel mainBoard;
    private JScrollPane scrBoard;
    private JPanel container;
    private ArrayList<Messages> messages;

    public JPanel getMainBoard() {
        return mainBoard;
    }
    MainBoard(String nickName) {
        this.user = new String(nickName);

        messages = new ArrayList<Messages>();
        container.setLayout(new GridLayout(3000, 1));
        scrBoard.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        try {
            ConnectDB connectDB = new ConnectDB();

            messages = Select.SelectAllMessages(connectDB.getCon());
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
