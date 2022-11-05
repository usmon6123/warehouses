package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.SaveOrderDTO;

public interface OrderService {
    ApiResult<?> byOrder();

    ApiResult<?> addOrder(SaveOrderDTO orderDTO);

    ApiResult<?> getAllOrder(SaveOrderDTO orderDTO);
}
