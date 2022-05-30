package ui;

import entity.user;
import utils.jdbcUtils;

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
        Object[] titles = {"姓名", "密码", "权限"};
        List<user> users = jdbcUtils.getNoOne("select username,password,security from user where security=? or security=?",
                "user", "company");
        for (
                int i = 0;
                i < titles.length; i++) {
            titleV.add(titles[i]);
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
        jf.add(new

                JScrollPane(jtable));
        JButton jb = new JButton("获取选中行的数据");
        jb.addActionListener(new

                                     ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             int sr = jtable.getSelectedRow();
                                             Object valueAt = myTableModel.getValueAt(sr, 0);
                                             System.out.println(valueAt);
                                         }
                                     });
        jf.add(jb, BorderLayout.SOUTH);
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

