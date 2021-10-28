package nashtech.longtran.shoppingweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="color", uniqueConstraints = @UniqueConstraint(columnNames = "color_id"),
        indexes = {@Index(name = "color_id_index", columnList = "color_id", unique = true)})
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Integer id;

    @Column(name = "color_name")
    private String name;

    @Column(name = "color_hex")
    private String colorHex;

    public Color(String name, String colorHex) {
        this.name = name;
        this.colorHex = colorHex;
    }
}
