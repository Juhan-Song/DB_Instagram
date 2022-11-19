package Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    public static void UpdateLoginTime(ConnectDB connectDB, String nickname) {
        PreparedStatement pstmt = null;

        try {
            String sql = "update userinfo set login = (select login from logined where nickname = ?), logout = NULL where nickname = ?";
            pstmt = connectDB.getCon().prepareStatement(sql);

            pstmt.setString(1, nickname);
            pstmt.setString(2, nickname);

            int count = pstmt.executeUpdate();

            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
