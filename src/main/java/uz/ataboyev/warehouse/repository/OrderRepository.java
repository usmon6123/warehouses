package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByClientId(Long clientId);
    boolean existsByWarehouseId(Long warehouseId);


}
