package nashtech.longtran.shoppingweb.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name ="order_detail", uniqueConstraints = @UniqueConstraint(columnNames = "id"),
        indexes = {@Index(name = "detail_id_index", columnList = "id", unique = true)})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order orderObj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private ProductDetail product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private float price;

    public OrderDetail(Order order, ProductDetail product, int quantity, float price) {
        this.orderObj = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + orderObj +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
