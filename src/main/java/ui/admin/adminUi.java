package ui.admin;

import ui.admin.userManage;
import ui.main.login;
import utils.mybox;

import javax.security.auth.login.LoginContext;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 管理员:管理企业和学生账户/招聘信息分类管理(专业/岗位)/发布公告/发布企业招聘/审核招聘
 *
 * @author malguy-wang sir
 * @create ---
 */
public class adminUi {
    JFrame jf = new JFrame("管理员系统");

    public void init() {
        //按钮
        Box user = mybox.createVBoxWithBtn(
                new JButton[]{
                        new JButton("用户管理"),
                        new JButton("返回登陆界面")
                },
                new ActionListener[]{
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //打开对应系统
                                new userManage().init();
                                jf.dispose();
                            }
                        },
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                jf.dispose();
                                new login().init();
                            }
                        }
                }
        );
        Box verticalBox = Box.createVerticalBox();
        mybox.addAll(verticalBox,
                Box.createVerticalStrut(100),
                user,
                Box.createVerticalStrut(100)
        );
        Box horizontalBox = Box.createHorizontalBox();
        mybox.addAll(horizontalBox,
                Box.createHorizontalStrut(150),
                verticalBox,
                Box.createHorizontalStrut(150)
                );
        jf.add(horizontalBox);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setBounds(650,350,400,400);
    }
}


