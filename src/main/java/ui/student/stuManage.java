package ui.student;

import entity.user;
import ui.admin.userManage;
import ui.main.login;
import utils.jdbcUtils;
import utils.mybox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class stuManage {
    JFrame jf = new JFrame("修改信息");

    public void init(user u) {
        //修改信息
        Box nbx = mybox.createHBoxWithLabelTextFile("姓    名", 15);
        JTextField ntf = mybox.nowText;
        ntf.setText(u.getUsername());
        Box pbx = mybox.createHBoxWithLabelTextFile("密    码", 15);
        JTextField ptf = mybox.nowText;
        ntf.setText(u.getPassword());
        Box ibx = mybox.createHBoxWithLabelTextFile("信    息", 15);
        JTextField itf = mybox.nowText;
        ntf.setText(u.getDescribe());
        Box ubx = mybox.createHBoxWithLabelTextFile("学    号", 15);
        JTextField utf = mybox.nowText;
        ntf.setText(u.getUid());
        //按钮
        Box hBoxWithBtn = mybox.createHBoxWithBtn(
                new JButton[]{
                        new JButton("提交"),
                        new JButton("返回")
                },
                new ActionListener[]{
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String tusername = ntf.getText();
                                String tuid = utf.getText();
                                String tpassword = ptf.getText();
                                String tdescribe = itf.getText();
                                if (!tuid.equals("") || !tusername.equals("") || !tpassword.equals("") || !tdescribe.equals("")) {
                                    jdbcUtils.change("UPDATE USER SET `username`=?,PASSWORD=?,uid=?,`describe`=? WHERE id=?", tusername, tpassword, tuid, tdescribe, u.getId());
                                    if (!tpassword.equals(u.getPassword())) {
                                        JOptionPane.showMessageDialog(jf, "检测到密码修改");
                                        jf.dispose();
                                        new login().init();
                                        return;
                                    } else {
                                        jf.dispose();
                                        new studentUi().init();
                                    }
                                } else {
                                    return;
                                }
                            }
                        },
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                jf.dispose();
                                new studentUi().init();
                            }
                        }
                }
        );
        Box vb = Box.createVerticalBox();
        mybox.addAll(vb, Box.createVerticalStrut(100),
                nbx,Box.createVerticalStrut(20),
                pbx,Box.createVerticalStrut(20),
                ibx,Box.createVerticalStrut(20),
                ubx,Box.createVerticalStrut(20),
                hBoxWithBtn,Box.createVerticalStrut(20),
                Box.createVerticalStrut(100)
        );
        jf.add(vb);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setBounds(650,350,400,400);
    }
}
