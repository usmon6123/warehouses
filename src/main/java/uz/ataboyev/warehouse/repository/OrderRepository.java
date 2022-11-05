package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.entity.Order;
import uz.ataboyev.warehouse.payload.OrderDTO;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
