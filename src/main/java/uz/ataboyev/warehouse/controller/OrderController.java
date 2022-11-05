package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.SaveOrderDTO;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

@RequestMapping(path = OrderController.ORDER_CONTROLLER)
public interface OrderController {

    String ORDER_CONTROLLER=RestConstant.BASE_PATH+"/order";


    @GetMapping("/by-order")
    ApiResult<?> byOrder();


    @GetMapping("/add-order")
    ApiResult<?> addOrder(@RequestBody @Valid SaveOrderDTO orderDTO);


    @GetMapping("/get-all-order")
    ApiResult<?> getAllOrder(@RequestBody @Valid SaveOrderDTO orderDTO);



}
