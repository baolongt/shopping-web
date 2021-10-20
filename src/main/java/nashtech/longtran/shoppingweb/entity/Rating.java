package nashtech.longtran.shoppingweb.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name ="product_rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product productID;

    @Column(name = "rating_point")
    private float ratingPoint;

    @Column(name = "rating_content")
    private String ratingContent;

    public Rating(User username, Product productID, float ratingPoint, String ratingContent) {
        this.username = username;
        this.productID = productID;
        this.ratingPoint = ratingPoint;
        this.ratingContent = ratingContent;
    }
}
