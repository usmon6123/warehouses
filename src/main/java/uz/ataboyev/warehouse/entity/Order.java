package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.payload.SaveOrderDTO;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order extends AbsLongEntity {

    @JoinColumn(updatable = false, insertable = false, name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    private String description;

    //umumiy jamlangan summa bitta savdodagi
    private Double orderPriceSum = 0D;
    private Double orderPriceDollar = 0D;


    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;


    public Order(Long clientId, OrderType type, String description) {
        this.clientId = clientId;
        this.type = type;
        this.description = description;
    }

    public static Order make(SaveOrderDTO orderDTO) {

        return new Order(
                orderDTO.getClientId(),
                orderDTO.getOrderType(),
                orderDTO.getDescription()
        );
    }
}
