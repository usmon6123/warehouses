package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.Type;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    private Type type;


    private String description;


    @OneToMany(mappedBy = "order_id")
    private List<OrderItem> orderItems;
}
