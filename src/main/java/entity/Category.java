package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    private Integer id;
    private String name;
    private boolean active;

}
