package nashtech.longtran.shoppingweb.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "product_id"),
        indexes = {@Index(name = "product_id_index", columnList = "product_id", unique = true)})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "product_detail")
    private String detail;

    @Column(name = "create_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductDetail> productDetails;

    public Product(String name, Brand brand,
                   String detail, Timestamp createdDate, Timestamp updatedDate) {
        this.name = name;
        this.brand = brand;
        this.detail = detail;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", detail='" + detail + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", categories=" + categories +
                '}';
    }
}
