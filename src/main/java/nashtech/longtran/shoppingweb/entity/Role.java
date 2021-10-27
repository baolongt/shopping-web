package nashtech.longtran.shoppingweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nashtech.longtran.shoppingweb.enums.ERole;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = "id"),
        indexes = {@Index(name = "role_id_index", columnList = "id", unique = true)})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
