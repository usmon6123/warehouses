package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.aop.annotation.CheckPermission;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

@RequestMapping(path = RestConstant.WAREHOUSE_CONTROLLER)
public interface WareHouseController {

    @PostMapping("/add")
    @CheckPermission(values = {"ADD"})
    ApiResult<?> add(@RequestBody @Valid WareHouseReqDto wareHouseReqDto);

    @GetMapping("/get-one/{whId}")
    @CheckPermission(values = {"GET"})
    ApiResult<?> getOne(@PathVariable Long whId);

    @GetMapping("/get-all")
    @CheckPermission(values = {"GET"})
    ApiResult<?> getAll();

    @PutMapping("/edit/{whId}")
    @CheckPermission(values = {"EDIT"})
    ApiResult<?> edit(@PathVariable Long whId, @RequestBody WareHouseReqDto wareHouseReqDto);

    @DeleteMapping("/delete/{whId}")
    @CheckPermission(values = {"DELETE"})
    ApiResult<?> delete(@PathVariable Long whId);

}
