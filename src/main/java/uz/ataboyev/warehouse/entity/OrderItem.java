package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends AbsLongEntity {

   @JoinColumn(name = "order_id")
   @ManyToOne(fetch = FetchType.LAZY,optional = false)
   private Order order_id;

   @ManyToOne(fetch = FetchType.LAZY,optional = false)
   private Product product;

   @Column(nullable = false)
   private Double count;

   @JoinColumn(nullable = false)
   @Enumerated(EnumType.STRING)
   private CurrencyTypeEnum currencyType;

   @Column(nullable = false)
   private Double amount;

}
