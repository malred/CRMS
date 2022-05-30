package utils;

import entity.user;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class jdbcUtils {
    public static Connection getConn() {
        try {
            //导入配置文件(用类加载器)
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

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) conn.close();
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static user getOne(String sql, Object... args) {
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
                ps.setObject(i + 1, args[i]);
            }
            //获取结果集
            rs = ps.executeQuery();
            //得到结果集的元数据（一张数据表）
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSet得到列数
            int columnCount = rsmd.getColumnCount();
            user t = new user();//临时user
            if (rs.next()) {//指针向下移动，如果不为空就返回true
                for (int i = 0; i < columnCount; i++) {
                    Object studentValue = rs.getObject(i + 1);
                    //获取每一个列的列名
                    String userName = rsmd.getColumnLabel(i + 1);
                    //通过反射studentName属性，赋值为studentValue
                    Field filed = user.class.getDeclaredField(userName);
                    //属性可能是私有的，通过setAccessible(true)访问
                    filed.setAccessible(true);
                    filed.set(t, studentValue);
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(conn, ps, rs);
        }
        return null;
    }

    /**
     * 获取是否有这个用户
     * @param password  是否加入密码查询
     * @param args
     * @return
     */
    public static user getUser(boolean password,Object... args) {
        String sql = "";
        if(!password)
        sql += "select * from user where username=? ";
        else
            sql += "select * from user where username=? and password=?";
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
                ps.setObject(i + 1, args[i]);
            }
            //获取结果集
            rs = ps.executeQuery();
            //得到结果集的元数据（一张数据表）
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSet得到列数
            int columnCount = rsmd.getColumnCount();
            user t = new user();//临时user
            if (rs.next()) {//指针向下移动，如果不为空就返回true
                for (int i = 0; i < columnCount; i++) {
                    Object studentValue = rs.getObject(i + 1);
                    //获取每一个列的列名
                    String userName = rsmd.getColumnLabel(i + 1);
                    //通过反射studentName属性，赋值为studentValue
                    Field filed = user.class.getDeclaredField(userName);
                    //属性可能是私有的，通过setAccessible(true)访问
                    filed.setAccessible(true);
                    filed.set(t, studentValue);
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(conn, ps, rs);
        }
        return null;
    }

    public static List<user> getNoOne(String sql, Object... args) {
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
                ps.setObject(i + 1, args[i]);
            }
            //获取结果集
            rs = ps.executeQuery();
            //得到结果集的元数据（一张数据表）
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSet得到列数
            int columnCount = rsmd.getColumnCount();
            List<user> list = new ArrayList<>();
            //如果有值，创建一个student对象
            while (rs.next()) {
                user t = new user();
                for (int i = 0; i < columnCount; i++) {
                    Object studentValue = rs.getObject(i + 1);
                    //获取每一个列的列名
                    String userName = rsmd.getColumnLabel(i + 1);
                    //通过反射studentName属性，赋值为studentValue
                    Field filed = user.class.getDeclaredField(userName);
                    //属性可能是私有的，通过setAccessible(true)访问
                    filed.setAccessible(true);
                    filed.set(t, studentValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(conn, ps, rs);
        }
        return null;
    }

    /**
     * 通用增删改
     * @param sql
     * @param args
     */
    public static void change(String sql,Object...args){
        Connection conn = getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                //逐个填入参数,索引从1开始
                ps.setObject(i+1,args[i]);
            }
            ps.execute();//执行
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(conn,ps);
        }
    }
    @Test
    public void t(){
        user admin = getOne("select * from user where username=?", "admin");
        System.out.println(admin);
        List<user> noOne = getNoOne("select * from user where id < ?", 10);
        for (user user : noOne) {
            System.out.println(user);
        }
    }
}

