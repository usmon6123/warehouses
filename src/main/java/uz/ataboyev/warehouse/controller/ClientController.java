package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.ClientReqDto;
import uz.ataboyev.warehouse.payload.ClientResDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

@RequestMapping(path = RestConstant.CLIENT_CONTROLLER)
public interface ClientController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid ClientReqDto clientReqDto);

    @GetMapping("/get-one/{clientId}")
    ApiResult<?> getOne(@PathVariable Long clientId);

    @GetMapping("/get-all-clients/{companyId}")
    ApiResult<?> getAllByCompanyId(@PathVariable Long companyId);

    @PutMapping("/edit/{clientId}")
    ApiResult<?> edit(@PathVariable Long clientId, @RequestBody ClientReqDto clientReqDto);

    @DeleteMapping("/delete/{clientId}")
    ApiResult<?> delete(@PathVariable Long clientId);


}
