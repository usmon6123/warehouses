package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.*;

import java.util.List;


public interface OrderService {
    ApiResult<?> byOrder(Long whId, Long categoryId);

    ApiResult<?> addOrder(SaveOrderDTO orderDTO);

    ApiResult<?> getAllOrder(SaveOrderDTO orderDTO);

    List<CustomPage<OrderPageDTO>> getOrdersPageable(int page, int size);

    OrderPriceDto generalPriceOrders(Long whId);

}
