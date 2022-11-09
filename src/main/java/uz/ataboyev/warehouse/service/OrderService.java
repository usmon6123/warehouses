package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.SaveOrderDTO;

public interface OrderService {
    ApiResult<?> byOrder(Long whId,Long categoryId);

    ApiResult<?> addOrder(SaveOrderDTO orderDTO);

    ApiResult<?> getAllOrder(SaveOrderDTO orderDTO);
}
