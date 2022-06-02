package ui.student;

import entity.user;
import ui.main.login;
import utils.mybox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class studentUi {
    JFrame jf = new JFrame("学生信息页面");
    public void init() {
        user nowUser = login.nowUser;
        Box uhb = Box.createHorizontalBox();
        uhb.add(Box.createHorizontalStrut(40));
        JLabel ujl = new JLabel("姓   名: " + nowUser.getUsername());
        uhb.add(ujl);
        Box nhb = Box.createHorizontalBox();
        nhb.add(Box.createHorizontalStrut(40));
        JLabel njl = new JLabel("学   号: " + nowUser.getUid());
        nhb.add(njl);
        Box phb = Box.createHorizontalBox();
        phb.add(Box.createHorizontalStrut(40));
        JLabel pjl = new JLabel("信   息: " + nowUser.getDescribe());
        phb.add(pjl);
        //按钮
        Box hBoxWithBtn = mybox.createHBoxWithBtn(
                new JButton[]{
                        new JButton("修改信息")
                },
                new ActionListener[]{
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                jf.dispose();
                                new stuManage().init(nowUser);
                            }
                        }
                }
        );
        Box vb = Box.createVerticalBox();
        mybox.addAll(vb,Box.createVerticalStrut(100),
                uhb,Box.createVerticalStrut(20),
                nhb,Box.createVerticalStrut(20),
                phb,Box.createVerticalStrut(20),
                hBoxWithBtn,Box.createVerticalStrut(20),
                Box.createVerticalStrut(100)
                );
        jf.add(vb);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setBounds(650,350,400,400);
    }
}
