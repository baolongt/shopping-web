package nashtech.longtran.shoppingweb.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name ="rating", uniqueConstraints = @UniqueConstraint(columnNames = "rating_id"),
        indexes = {@Index(name = "rating_id_index", columnList = "rating_id", unique = true)})
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @NotBlank
    @Column(name = "rating_point")
    private float ratingPoint;

    @NotBlank
    @Column(name = "rating_content")
    private String ratingContent;

    public Rating(User user, Product product, float ratingPoint, String ratingContent) {
        this.user = user;
        this.product = product;
        this.ratingPoint = ratingPoint;
        this.ratingContent = ratingContent;
    }
}
