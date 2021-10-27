package nashtech.longtran.shoppingweb.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="category", uniqueConstraints = @UniqueConstraint(columnNames = "category_id"),
        indexes = {@Index(name = "cate_id_index", columnList = "category_id", unique = true)})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @NotBlank
    @Column(name = "category_name")
    private String name;

    public Category(String name){
        this.name = name;
    }
}
