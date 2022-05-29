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
        //用户名box
        Box ub = mybox.createHBoxWithLabelTextFile("用户名:", 15);
        JTextField ujtf = mybox.nowText;
        //密码box
        Box pb = mybox.createHBoxWithLabelTextFile("密     码:", 15);
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
                                String username = ujtf.getText();
                                String password = pjtf.getText();
                                user u = jdbcUtils.getUser(false, username);
                                if (u.getId() != null) { //如果已存在
                                    System.out.println(u);
                                    JOptionPane.showMessageDialog(jf, "用户名已存在");
                                }
                                else {
                                    //插入数据
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
                Box.createVerticalStrut(70),
                ub,
                Box.createVerticalStrut(20),
                pb,
                Box.createVerticalStrut(20),
                hBoxWithBtn,
                Box.createVerticalStrut(70)
        );
        jf.add(vb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(650, 350, 400, 500);
    }
}
