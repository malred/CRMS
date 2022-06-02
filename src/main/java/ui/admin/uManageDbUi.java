package ui.admin;

import entity.user;
import utils.jdbcUtils;
import utils.mybox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 学生管理系统的增改
 *
 * @author malguy-wang sir
 * @create ---
 */
public class uManageDbUi {
    public void init(user u) {
        JFrame jf = new JFrame("修改学生信息");
        //jlabel和输入框
        Box uhb = mybox.createHBoxWithLabelTextFile("姓    名", 15);
        JTextField utf = mybox.nowText;
        utf.setText(u.getUsername());
        Box phb = mybox.createHBoxWithLabelTextFile("密    码", 15);
        JTextField ptf = mybox.nowText;
        ptf.setText(u.getPassword());
        Box nhb = mybox.createHBoxWithLabelTextFile("学    号", 15);
        JTextField ntf = mybox.nowText;
        ntf.setText(u.getUid());
        //上面三个加进垂直容器
        Box vb = Box.createVerticalBox();
        mybox.addAll(vb,
                uhb, Box.createVerticalStrut(50),
                nhb, Box.createVerticalStrut(50),
                phb, Box.createVerticalStrut(50)
        );
        jf.add(vb);
        Box hb = Box.createHorizontalBox();
        hb.add(Box.createHorizontalStrut(140));
        JButton jb = new JButton("提交");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tusername = utf.getText();
                String tuid = ntf.getText();
                String tpassword = ptf.getText();
                if(!tuid.equals("")||!tusername.equals("") || !tpassword.equals("")) {
                    jdbcUtils.change("update user set username=?,password=?,uid=? where id=?", tusername, tpassword, tuid,u.getId());
                    jf.dispose();
                    new userManage().init();
                }else{
                    return;
                }
            }
        });
        JButton re = new JButton("返回");
        re.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new userManage().init();
            }
        });
        hb.add(jb);
        hb.add(re);
        jf.add(hb, BorderLayout.SOUTH);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(650, 350, 400, 400);
    }
    public void init() {
        JFrame jf = new JFrame("添加学生信息");
        //jlabel和输入框
        Box uhb = mybox.createHBoxWithLabelTextFile("姓    名", 15);
        JTextField utf = mybox.nowText;
        Box phb = mybox.createHBoxWithLabelTextFile("密    码", 15);
        JTextField ptf = mybox.nowText;
        Box nhb = mybox.createHBoxWithLabelTextFile("学    号", 15);
        JTextField ntf = mybox.nowText;
        //上面三个加进垂直容器
        Box vb = Box.createVerticalBox();
        mybox.addAll(vb,
                uhb, Box.createVerticalStrut(50),
                nhb, Box.createVerticalStrut(50),
                phb, Box.createVerticalStrut(50)
        );
        jf.add(vb);
        Box hb = Box.createHorizontalBox();
        hb.add(Box.createHorizontalStrut(140));
        JButton jb = new JButton("提交");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tusername = utf.getText();
                String tpassword = ptf.getText();
                String tuid = ntf.getText();
                if(!tpassword.equals("")|| !tusername.equals("") || !tuid.equals("") ) {
                    jdbcUtils.change("insert into user(username,password,uid) value(?,?,?)", tusername, tpassword, tuid);
                    jf.dispose();
                    new userManage().init();
                }else{
                    return;
                }
            }
        });
        JButton re = new JButton("返回");
        re.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new userManage().init();
            }
        });
        hb.add(jb);
        hb.add(re);
        jf.add(hb, BorderLayout.SOUTH);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(650, 350, 400, 400);
    }
}
