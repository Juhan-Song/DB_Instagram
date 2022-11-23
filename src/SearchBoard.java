import javax.swing.*;
import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class SearchBoard extends JFrame implements TextListener {
    private JPanel searchBoard;
    private JTextField txtSearch;

    public JPanel getSearchBoard() {
        return searchBoard;
    }

    SearchBoard() {

    }

    @Override
    public void textValueChanged(TextEvent e) {
        TextField txt = (TextField)e.getSource();
        String nickName = txt.getText();
    }
}
