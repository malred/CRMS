package ui;

import entity.user;
import javafx.scene.layout.VBox;
import utils.jdbcUtils;
import utils.mybox;

import javax.rmi.CORBA.ClassDesc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class login {
    public static user nowUser = null;
    JFrame jf = new JFrame("登录");
    //组装
    public void init() {
        //用户名box
        Box ub = mybox.createHBoxWithLabelTextFile("用户名:", 15);
        JTextField ujtf = mybox.nowText;
        //密码box
        Box pb = mybox.createHBoxWithLabelTextFile("密     码:", 15);
        JTextField pjtf = mybox.nowText;
        //按钮box
        Box btnB = mybox.createHBoxWithBtn(
                new JButton[]{
                        new JButton("登录"),
                        new JButton("注册")
                },
                new ActionListener[]{
                        (new ActionListener() { //登录
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //获取用户名和密码
                                String username = ujtf.getText();
                                String password = pjtf.getText();
                                //查询数据库,成功则把new用户对象,保存数据,根据其权限显示内容(打开新页面)
                                nowUser = jdbcUtils.getOne(
                                        "select * from `user` where username=? and password=?", username, password);
                                System.out.println(nowUser);
                                if(nowUser.getUsername()!=null) {
                                    //如果存在
                                    //打开新窗口
                                    new systemUi().init();
                                    jf.dispose();
                                }else {
                                    JOptionPane.showMessageDialog(jf,"用户名或密码错误");
                                }
                            }
                        }),
                        (new ActionListener() { //注册
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //打开注册页面,关闭当前页面
                                new registUi().init();
                                jf.dispose();
                            }
                        })
                });
        //整体组装
        Box all = Box.createVerticalBox();
        mybox.addAll(all, new Component[]{Box.createVerticalStrut(80)
                , ub, Box.createVerticalStrut(30),
                pb, Box.createVerticalStrut(30),
                btnB, Box.createVerticalStrut(80)});
        jf.add(all);
        jf.setResizable(false);//不可更改宽高
        jf.setBounds(650, 350, 500, 350);//位置
        jf.setVisible(true);//可见
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭按钮监听
    }

    public static void main(String[] args) {
        new login().init();
    }
}
