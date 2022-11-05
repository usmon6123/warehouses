package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.payload.OrderItemDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends AbsLongEntity {

    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @JoinColumn(insertable = false, updatable = false, name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false, name = "product_id")
    private Long productId;

    @Column(nullable = false)
    private Double count;

    @JoinColumn(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyTypeEnum currencyType;

    @Column(nullable = false)
    private Double amount;


    public OrderItem(Long orderId, Long productId, Double count, CurrencyTypeEnum currencyType, Double amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.currencyType = currencyType;
        this.amount = amount;
    }

    public static List<OrderItem> makeList(List<OrderItemDto> orderItemDtoList, Order order) {

        return orderItemDtoList.stream().map(orderItemDto -> make(orderItemDto, order)).collect(Collectors.toList());

    }

    public static OrderItem make(OrderItemDto orderItemDto, Order order) {

        return new OrderItem(
                order.getId(),
                orderItemDto.getProductId(),
                orderItemDto.getCount(),
                orderItemDto.getCurrencyTypeEnum(),
                orderItemDto.getPrice()
        );
    }
}
