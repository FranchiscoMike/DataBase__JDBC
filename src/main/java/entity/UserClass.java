package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserClass {
    private Integer id;
    private String email;
    private String password;
    private String full_name;
    private String phone;
    private boolean active;
}
