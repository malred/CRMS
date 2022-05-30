package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author malguy-wang sir
 * @create ---
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class describes {
    private String describe;
    private String username;
    private String get;//是否被使用
    private String company;//录取公司
}
