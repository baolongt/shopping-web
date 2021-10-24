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
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Timestamp createDate;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", date=" + createDate +
                '}';
    }

    public Order(User user, String address, String status, Timestamp date) {
        this.user = user;
        this.address = address;
        this.status = status;
        this.createDate = date;
    }
}
