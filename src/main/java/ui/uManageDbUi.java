package ui;

import utils.jdbcUtils;
import utils.mybox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 学生管理系统的增改
 *
 * @author malguy-wang sir
 * @create ---
 */
public class uManageDbUi {
    public void init(String username, String password, String uid) {
        JFrame jf = new JFrame("修改学生信息");
        //jlabel和输入框
        Box uhb = mybox.createHBoxWithLabelTextFile("姓    名", 15);
        JTextField utf = mybox.nowText;
        utf.setText(username);
        Box nhb = mybox.createHBoxWithLabelTextFile("密    码", 15);
        JTextField ntf = mybox.nowText;
        ntf.setText(password);
        Box phb = mybox.createHBoxWithLabelTextFile("学    号", 15);
        JTextField ptf = mybox.nowText;
        ptf.setText(uid);
        //上面三个加进垂直容器
        Box vb = Box.createVerticalBox();
        mybox.addAll(vb,
                uhb, Box.createVerticalStrut(50),
                nhb, Box.createVerticalStrut(50),
                phb, Box.createVerticalStrut(50)
        );
        jf.add(vb);
        Box hb = Box.createHorizontalBox();
        hb.add(Box.createHorizontalStrut(80));
        JButton jb = new JButton("提交");
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tusername = utf.getText();
                String tuid = ntf.getText();
                String tpassword = ptf.getText();
                if(!tuid.equals(tuid.equals("") || !tuid.equals("") || !tpassword.equals(""))) {
                    jdbcUtils.change("insert into user(username,password,uid) value(?,?,?)", username, password, uid);
                    jf.dispose();
                    new userManage().init();
                }else{
                    return;
                }
            }
        });
        hb.add(Box.createHorizontalStrut(80));
        jf.add(hb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(650, 350, 400, 400);
    }
}
