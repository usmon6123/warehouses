package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
