package nashtech.longtran.shoppingweb.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name ="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    private String address;

    private String status;

    private Timestamp date;

    @OneToMany(mappedBy="order")
    private Set<OrderDetail> products;

    public Order(User user, String address, String status, Timestamp date) {
        this.user = user;
        this.address = address;
        this.status = status;
        this.date = date;
    }
}
