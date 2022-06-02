package ui;

import entity.user;
import utils.jdbcUtils;
import utils.mybox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class registUi {
    JFrame jf = new JFrame();

    public void init() {
        //学号box
        Box ub = mybox.createHBoxWithLabelTextFile("学    号:", 15);
        JTextField ujtf = mybox.nowText;
        //姓名box
        Box nb = mybox.createHBoxWithLabelTextFile("姓    名:", 15);
        JTextField njtf = mybox.nowText;
        //密码box
        Box pb = mybox.createHBoxWithLabelTextFile("密    码:", 15);
        JTextField pjtf = mybox.nowText;
        Box hBoxWithBtn = mybox.createHBoxWithBtn(
                new JButton[]{
                        new JButton("注册"),
                        new JButton("返回登录页面")
                },
                new ActionListener[]{
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //注册
                                String uid = ujtf.getText();
                                String password = pjtf.getText();
                                String username = njtf.getText();
                                user u = jdbcUtils.getUser(false, uid);
                                if (u.getId() != null || uid.equals("")) { //如果已存在
                                    System.out.println(u);
                                    JOptionPane.showMessageDialog(jf, "学号错误");
                                    return;
                                }
                                else {
                                    //插入数据
                                    jdbcUtils.change("insert into user(username,password,uid) value(?,?,?)", username, password, uid);
                                    JOptionPane.showMessageDialog(jf, "注册成功,跳转登录页面");
                                    new login().init();
                                    jf.dispose();
                                }
                            }
                        },
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new login().init();
                                jf.dispose();
                            }
                        }
                }
        );
        Box vb = Box.createVerticalBox();//整体
        mybox.addAll(
                vb,
                Box.createVerticalStrut(105),
                ub,
                Box.createVerticalStrut(30),
                nb,
                Box.createVerticalStrut(30),
                pb,
                Box.createVerticalStrut(30),
                hBoxWithBtn,
                Box.createVerticalStrut(105)
        );
        jf.setResizable(false);
        jf.add(vb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(650, 350, 400, 400);
    }
}
