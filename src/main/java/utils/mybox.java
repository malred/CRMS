package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class mybox {
    public static void addAll(Box box,Component...args){
        for (int i = 0; i < args.length; i++) {
            box.add(args[i]);
        }
    }
    public static JTextField nowText;//获取当前文本条
    public static Box createHBoxWithLabelTextFile(String label, int LColumn){
        Box horizontalBox = Box.createHorizontalBox();
        JLabel jl = new JLabel(label);
        JTextField jTextField = new JTextField(LColumn);
        nowText = jTextField;
        horizontalBox.add(Box.createHorizontalStrut(40));
        horizontalBox.add(jl);
        horizontalBox.add(Box.createHorizontalStrut(20));
        horizontalBox.add(jTextField);
        horizontalBox.add(Box.createHorizontalStrut(40));
        return horizontalBox;
    }
    public static Box createHBoxWithBtn(JButton[] btns,ActionListener listener[]){
        Box horizontalBox = Box.createHorizontalBox();
        for (int i = 0; i < btns.length; i++) {
            btns[i].addActionListener(listener[i]);
            horizontalBox.add(btns[i]);
        }
        return horizontalBox;
    }
    //批量添加按钮和监听器
    public static Box createVBoxWithBtn(ActionListener listener[],JButton...btns){
        Box vBox = Box.createVerticalBox();
        for (int i = 0; i < btns.length; i++) {
            btns[i].addActionListener(listener[i]);
            vBox.add(btns[i]);
        }
        return vBox;
    }
    public static Box createVBoxWithLabelTextFile(String label,int LColumn){
        Box vBox = Box.createVerticalBox();
        JLabel jl = new JLabel(label);
        JTextField jTextField = new JTextField(LColumn);
        vBox.add(jl);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(jTextField);
        return vBox;
    }
}
