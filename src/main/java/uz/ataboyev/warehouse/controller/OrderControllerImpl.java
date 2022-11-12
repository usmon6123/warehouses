package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.service.OrderService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;


    @Override
    public ApiResult<?> byOrder(Long whId,Long categoryId) {
        return orderService.byOrder(whId,categoryId);
    }

    @Override
    public ApiResult<?> addOrder(SaveOrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @Override
    public ApiResult<?> getAllOrder(SaveOrderDTO orderDTO) {
        return orderService.getAllOrder(orderDTO);
    }

    @Override
    public List<CustomPage<OrderPageDTO>> getAllPageable(int page, int size) {
        return orderService.getOrdersPageable(page,size);
    }

    @Override
    public List<OrderPriceDto> generalPriceOrders() {
        return orderService.generalPriceOrders();
    }


}
