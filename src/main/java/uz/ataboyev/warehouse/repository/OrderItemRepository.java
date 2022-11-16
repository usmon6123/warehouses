package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.OrderItem;
import uz.ataboyev.warehouse.payload.clientDtos.OrderItemByOrderId;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    @Query(value = "select * from order_item oi " +
            "where oi.order_id in(" +
            "select o.id from orders o where o.client_id = :clientId) " +
            "order by oi.created_at desc ",nativeQuery = true)
    List<OrderItem> findAllByOrder_ClientId(@Param("clientId") Long clientId);

    @Query(value = "select to_char(oi.created_at,'dd-MM-yyyy') as date," +
            "c.name as categoryName," +
            "p.name as productName," +
            "cast(oi.count as varchar) as count," +
            "cast(oi.amount as varchar) as countSum, " +
            "oi.currency_type as currencyTypeenum, " +
            "cast(oi.count*oi.amount as varchar) as price " +
            "from order_item oi " +
            "inner join product p on oi.product_id = p.id " +
            "inner join category c on c.id = p.category_id " +
            "where oi.order_id = :orderId " +
            "order by oi.created_at asc ",nativeQuery = true)
    List<OrderItemByOrderId> findAllByOrderId(Long orderId);
}
