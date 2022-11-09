package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ProductReqDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

@RequestMapping(path = RestConstant.PRODUCT_CONTROLLER)
public interface ProductController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid ProductReqDto productReqDto);

    @GetMapping("/get-one/{productId}")
    ApiResult<?> getOne(@PathVariable Long productId);

    @GetMapping("/get-all-product-by-categoryId/{categoryId}")
    ApiResult<?> getAllProductByCategoryId(@PathVariable Long categoryId);

    @PutMapping("/edit/{productId}")
    ApiResult<?> edit(@PathVariable Long productId, @RequestBody @Valid ProductReqDto productReqDto);

    @DeleteMapping("/delete/{productId}")
    ApiResult<?> delete(@PathVariable Long productId);

}
