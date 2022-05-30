package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author malguy-wang sir
 * @create ---
 */
//这些注解是lombok提供,data表示创建所有属性的get和set方法
@AllArgsConstructor
@NoArgsConstructor
@Data
public class user {
    private Number id; //不设置成number(long的父类),会出现类型转换出错(integer--long)
    private String username;
    private String password;
    private String describe;
    private String security;
    public Object get(int index){ //配合jtable展示数据
        if(index==0) return username;
        if(index==1) return password;
        if(index==2) return security;
        else return null;
    }
}
