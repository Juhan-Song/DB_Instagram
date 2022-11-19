package Query;

import java.sql.*;

public class Create {
    public static void CreateUserTable(ConnectDB connectDB, String nickName) {
        PreparedStatement pstmt = null;

        try {
            String sql = "CREATE TABLE instagram." + nickName + "(" +
                        "follower VARCHAR(30) NULL DEFAULT NULL, " +
                        "following VARCHAR(30) NULL DEFAULT NULL, " +
                        "UNIQUE INDEX follower_UNIQUE (follower ASC) VISIBLE, " +
                        "UNIQUE INDEX following_UNIQUE (following ASC) VISIBLE)";
//            String sql = "create table " + nickName + "(" +
//                    "follower varchar(30) null default null, " +
//                    "following varchar(30) null default null, " +
//                    "unique index follower_unique (follower asc) visible, " +
//                    "unique index following_unique (follower asc) visible)";
            pstmt = connectDB.getCon().prepareStatement(sql);

            int count = pstmt.executeUpdate();

            pstmt.close();
            connectDB.Disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
