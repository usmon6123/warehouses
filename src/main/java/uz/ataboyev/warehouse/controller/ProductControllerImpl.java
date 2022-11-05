package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CompanyReqDto;
import uz.ataboyev.warehouse.payload.ProductDto;
import uz.ataboyev.warehouse.service.CompanyService;
import uz.ataboyev.warehouse.service.ProductService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ApiResult<?> add(ProductDto productDto) {
        return productService.add(productDto);
    }

    @Override
    public ApiResult<?> getOne(Long categoryId) {
        return productService.getOne(categoryId);
    }

    @Override
    public ApiResult<?> getAllCategoryByWarehouseId(Long whId) {
        return null;// TODO: 06/11/22  
    }

    @Override
    public ApiResult<?> edit(Long categoryId, ProductDto productDto) {
        return productService.edit(categoryId, productDto);
    }


    @Override
    public ApiResult<?> delete(Long categoryId) {
        return productService.delete(categoryId);
    }
}
