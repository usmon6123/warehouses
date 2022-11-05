package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.SaveOrderDTO;
import uz.ataboyev.warehouse.service.OrderService;


@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;


    @Override
    public ApiResult<?> byOrder() {
        return orderService.byOrder();
    }

    @Override
    public ApiResult<?> addOrder(SaveOrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @Override
    public ApiResult<?> getAllOrder(SaveOrderDTO orderDTO) {
        return orderService.getAllOrder(orderDTO);
    }
}
