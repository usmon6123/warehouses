package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ProductDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

@RequestMapping(path = RestConstant.PRODUCT_CONTROLLER)
public interface ProductController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid ProductDto productDto);

    @GetMapping("/get-one/{categoryId}")
    ApiResult<?> getOne(@PathVariable Long categoryId);


    @GetMapping("/get-all-category-by-whId/{whId}")
    ApiResult<?> getAllCategoryByWarehouseId(@PathVariable Long whId);

    @PutMapping("/edit/{categoryId}")
    ApiResult<?> edit(@PathVariable Long categoryId, @RequestBody @Valid ProductDto productDto);

    @DeleteMapping("/delete/{categoryId}")
    ApiResult<?> delete(@PathVariable Long categoryId);

}
