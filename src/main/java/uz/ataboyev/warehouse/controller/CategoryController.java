package uz.ataboyev.warehouse.controller;

import uz.ataboyev.warehouse.aop.annotation.CheckPermission;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.utils.RestConstant;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = RestConstant.CATEGORY_CONTROLLER)
public interface CategoryController {

    @PostMapping("/add")
    @CheckPermission(values = {"ADD"})
    ApiResult<?> add(@RequestBody @Valid CategoryDto categoryDto);

    @GetMapping("/get-one/{categoryId}")
    @CheckPermission(values = {"GET"})
    ApiResult<?> getOne(@PathVariable Long categoryId);

    @GetMapping("/get-all-categories/{wareHouseId}")
    @CheckPermission(values = {"GET"})
    ApiResult<?> getAllCategories(@PathVariable Long wareHouseId);

    @PutMapping("/edit/{categoryId}")
    @CheckPermission(values = {"EDIT"})
    ApiResult<?> edit(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto);

    @DeleteMapping("/delete/{categoryId}")
    @CheckPermission(values = {"DELETE"})
    ApiResult<?> delete(@PathVariable Long categoryId);


}
