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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    private Color color;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id", referencedColumnName = "size_id")
    private Size size;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Float price;

    public ProductDetail(Product product, Color color, Size size, Integer quantity, Float price) {
        this.product = product;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }
}
