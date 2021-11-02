package nashtech.longtran.shoppingweb.entity;

import lombok.*;
import nashtech.longtran.shoppingweb.enums.EOrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name ="orders", uniqueConstraints = @UniqueConstraint(columnNames = "order_id"),
        indexes = {@Index(name = "order_id_index", columnList = "order_id", unique = true)})
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotBlank
    private EOrderStatus status;

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

    public Order(User user, String address, EOrderStatus status, Timestamp date) {
        this.user = user;
        this.address = address;
        this.status = status;
        this.createDate = date;
    }
}
