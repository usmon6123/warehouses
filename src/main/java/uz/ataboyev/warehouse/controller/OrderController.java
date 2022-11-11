package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.CustomPage;
import uz.ataboyev.warehouse.payload.SaveOrderDTO;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

import static uz.ataboyev.warehouse.utils.AppConstant.*;

@RequestMapping(path = OrderController.ORDER_CONTROLLER)
public interface OrderController {

    String ORDER_CONTROLLER=RestConstant.BASE_PATH+"/order";


    @GetMapping("/by-order")
    ApiResult<?> byOrder(Long whId,Long categoryId);


    @PostMapping("/add-order")
    ApiResult<?> addOrder(@RequestBody @Valid SaveOrderDTO orderDTO);


    @GetMapping("/get-all-order")
    ApiResult<?> getAllOrder(@RequestBody @Valid SaveOrderDTO orderDTO);

    @GetMapping("get-all-orders-pageble")
    ApiResult<CustomPage<?>> getAllPageable(@RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size);
}
