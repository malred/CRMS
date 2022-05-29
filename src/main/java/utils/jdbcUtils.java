package utils;

import entity.user;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class jdbcUtils {
    public static Connection getConn() {
        try {
            //导入配置文件
            InputStream is =
                    jdbcUtils.class.getClassLoader().getResourceAsStream("conn.properties");
            Properties pro = new Properties();
            pro.load(is);
            //获取四要素
            String user = pro.getProperty("username");
            String password = pro.getProperty("password");
            String url = pro.getProperty("url");
            String driverName = pro.getProperty("driver");
            //实例化驱动
            Class.forName(driverName);
            //获取连接
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void close(Connection conn, PreparedStatement ps) {
        try {
            if (conn != null) conn.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if(conn!=null) conn.close();
            if(ps!=null) ps.close();
            if(rs!=null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static user getInstance(String sql, Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            conn = jdbcUtils.getConn();
            //获取预编译sql语句
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //获取结果集
            rs = ps.executeQuery();
            //得到结果集的元数据（一张数据表）
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSet得到列数
            int columnCount = rsmd.getColumnCount();
            Object[] columnVals = new Object[columnCount];
            if (rs.next()) {//指针向下移动，如果不为空就返回true
                for (int i = 0; i < columnCount; i++) {// 遍历每一个列
                    // 获取列值
                    columnVals[i] = rs.getObject(i + 1);
                }
                return new user(columnVals);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.close(conn,ps,rs);
        }
        return null;
    }
}

