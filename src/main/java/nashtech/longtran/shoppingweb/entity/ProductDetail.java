package nashtech.longtran.shoppingweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_detail", uniqueConstraints = @UniqueConstraint(columnNames = "id"),
        indexes = {@Index(name = "product_detail_id_index", columnList = "id", unique = true)})
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", nullable=false)
    private Product product;

    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Float price;

    @Column(name = "imgurl")
    private String imgURL;

    public ProductDetail(Product product, String color, String size, Integer quantity, Float price, String imgURL) {
        this.product = product;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.imgURL = imgURL;
    }
}
