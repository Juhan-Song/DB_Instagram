package Query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void Logout(ConnectDB connectDB, String nickname) {
        PreparedStatement pstmt = null;

        try {
            String sql = "delete from logined where nickname = ?";
            pstmt = connectDB.getCon().prepareStatement(sql);

            pstmt.setString(1, nickname);

            int count = pstmt.executeUpdate();

            Update.UpdateLogoutTime(connectDB, nickname);

            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
