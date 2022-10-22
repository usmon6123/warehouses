package uz.ataboyev.warehouse.controller;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ProductDto;
import uz.ataboyev.warehouse.utils.RestConstant;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = RestConstant.PRODUCT_CONTROLLER)
public interface ProductController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid ProductDto productDto);

    @GetMapping("/get-one/{categoryId}")
    ApiResult<?> getOne(@PathVariable Long categoryId);

    @GetMapping("/get-child-category/{parentId}")
    ApiResult<?> getChildCategories(@PathVariable Long parentId);

    @GetMapping("/get-parent-category")
    ApiResult<?> getParentCategories();

    @PutMapping("/edit/{categoryId}")
    ApiResult<?> edit(@PathVariable Long categoryId);

    @DeleteMapping("/delete/{categoryId}")
    ApiResult<?> delete(@PathVariable Long categoryId);

}
