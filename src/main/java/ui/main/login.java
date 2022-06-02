package ui.main;

import entity.user;
import utils.jdbcUtils;
import utils.mybox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class login {
    public static user nowUser = null;
    JFrame jf = new JFrame("登录");
    //组装
    public void init() {
        //学号box
        Box ub = mybox.createHBoxWithLabelTextFile("学    号:", 15);
        JTextField ujtf = mybox.nowText;
        //密码box
        Box pb = mybox.createHBoxWithLabelTextFile("密    码:", 15);
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
                                //获取学号和密码
                                String uid = ujtf.getText();
                                String password = pjtf.getText();
                                //查询数据库,成功则把new用户对象,保存数据,根据其权限显示内容(打开新页面)
                                nowUser = jdbcUtils.getOne(
                                        "select * from `user` where uid=? and password=?", uid, password);
                                System.out.println(nowUser);
                                if(nowUser.getUsername()!=null && !uid.equals("")) {//防止空数据
//                                if(nowUser.getUsername()!=null || nowUser.getUid()!="0") {
                                    //如果存在
                                    //打开新窗口
                                    new systemUi().init();
                                    jf.dispose();
                                }else {
                                    JOptionPane.showMessageDialog(jf,"学号或密码错误");
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
