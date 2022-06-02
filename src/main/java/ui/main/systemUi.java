package ui.main;

import entity.user;
import ui.admin.adminUi;
import ui.main.login;
import ui.student.studentUi;

import javax.swing.*;

/**
 * @author malguy-wang sir
 * @create 2022-05-29-15:00
 * 不同权限不同系统
 */
public class systemUi {
    JFrame jf = new JFrame("管理系统");
    public void init(){
        user nowUser = login.nowUser;
        if (nowUser.getSecurity().equals("admin")){
            //如果是管理员
            new adminUi().init();
        }
        if(nowUser.getSecurity().equals("student")){
            //如果是学生
            new studentUi().init();
        }
        else {
            JOptionPane.showMessageDialog(jf,"账号非法");
            jf.dispose();
            new login().init();
        }
    }
}
