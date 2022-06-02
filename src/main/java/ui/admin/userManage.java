package ui.admin;

import entity.user;
import utils.jdbcUtils;
import utils.mybox;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class userManage {
    JFrame jf = new JFrame("用户管理系统");
    private Vector titleV = new Vector();//存标题
    private Vector<Vector> dataV = new Vector();//存标题

    public void init() {
        //创建一维数组,存放标题
        Object[] titles = {"姓名", "密码", "学号", "其他信息"};
        List<user> users = jdbcUtils.getNoOne("SELECT username,`password`,uid,`describe` FROM USER WHERE `security`=?",
                "user");
        for (
                int i = 0;
                i < titles.length; i++) {
            titleV.add(titles[i]);//标题
        }
        for (
                int i = 0; i < users.size(); i++) {
            Vector t = new Vector();//临时vector
            for (int j = 0; j < titles.length; j++) {
                t.add(users.get(i).get(j));
            }
            dataV.add(t);
        }
        //        JTable jtable = new JTable(data,titles);
        myTableModel myTableModel = new myTableModel();
        JTable jtable = new JTable(myTableModel);
        jf.add(new JScrollPane(jtable));
        Box hb = mybox.createHBoxWithBtn(
                new JButton[]{
                        new JButton("更改选中数据"),
                        new JButton("插入数据"),
                        new JButton("删除选中的数据"),
                },
                new ActionListener[]{
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int sr = jtable.getSelectedRow();
                                Object uid = myTableModel.getValueAt(sr, 2);
//                                System.out.println(uid);
                                //根据学号获取user类实例
                                user one = jdbcUtils.getOne("select * from user where uid=?", uid);
//                                System.out.println(one);
                                new uManageDbUi().init(one);
                                jf.dispose();
                            }
                        },
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new uManageDbUi().init();
                            }
                        },
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int sr = jtable.getSelectedRow();
                                Object uid = myTableModel.getValueAt(sr, 2);
                                System.out.println(uid);
                                user one = jdbcUtils.getOne("select * from user where uid=?", uid);
                                System.out.println(one);
                                if (one != null || !one.getId().equals("") || one.getId() != null || !one.getId().equals("")) {
                                    jdbcUtils.change("delete from user where id=?", one.getId());
                                    jf.dispose();
                                    new userManage().init();
                                }
                            }
                        }
                }
        );
        jf.add(hb, BorderLayout.SOUTH);
        jf.setBounds(650, 350, 400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    private class myTableModel extends AbstractTableModel {
        @Override
        public int getRowCount() {
            return dataV.size();
        }

        @Override
        public int getColumnCount() {
            return titleV.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return dataV.get(rowIndex).get(columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            return (String) titleV.get(column);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }
    }
}

