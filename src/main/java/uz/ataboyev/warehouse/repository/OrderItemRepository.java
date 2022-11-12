package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    @Query(value = "select * from order_item oi where oi.order_id in(" +
            "    select o.id from orders o where o.client_id = :clientId)",nativeQuery = true)
    List<OrderItem> findAllByOrder_ClientId(@Param("clientId") Long clientId);
}
