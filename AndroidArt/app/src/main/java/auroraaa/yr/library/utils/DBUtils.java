package auroraaa.yr.library.utils;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class DBUtils {
    private static final String TAG = "DBUtils";
    private static Connection getConnection(String dbName){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String ip = "49.232.112.110";
            String connUrl = "jdbc:mysql://" + ip + ":3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8&useSSL=false" ;
            conn = DriverManager.getConnection(connUrl,"root", "123456");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static HashMap<String, String> getUserInfoByName(String name){
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection("IMD");
        try {
            Statement st = conn.createStatement();
            String sql = "select * from users where username = '" + name + "'";
            ResultSet res = st.executeQuery(sql);
            if(res == null)
                return null;
            else{
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for(int i=1;i<=cnt;i++){
                    String field = res.getMetaData().getColumnName(i);
                    System.out.println(field);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "数据操作异常");
            return null;
        }
    }

    public static boolean insertUserInfo(String name, String password){
        Connection conn = getConnection("users");
        try{
            Statement st = conn.createStatement();
            String sql = "insert into users (username, password) values (" + name + ", " + password + ")";
            int res = st.executeUpdate(sql);
            conn.close();
            st.close();
            if (res==1){
                return true;
            }
            else{
                Log.d(TAG, "用户数据插入异常");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
