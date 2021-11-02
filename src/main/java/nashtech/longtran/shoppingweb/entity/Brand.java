package nashtech.longtran.shoppingweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="brand", uniqueConstraints = @UniqueConstraint(columnNames = "brand_id"),
        indexes = {@Index(name = "brand_id_index", columnList = "brand_id", unique = true)})
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer id;

    @Column(name = "brand_name")
    @NotBlank
    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
