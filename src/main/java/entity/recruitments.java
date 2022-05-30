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
public class recruitments {
    private String recruitment;
    private String username;
    private String pass;//是否通过审核
}
