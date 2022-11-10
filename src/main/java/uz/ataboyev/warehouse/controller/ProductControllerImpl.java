package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.OptionDTO;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.ProductReqDto;
import uz.ataboyev.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ApiResult<?> add(ProductReqDto productReqDto) {
        return productService.add(productReqDto);
    }

    @Override
    public ApiResult<?> getOne(Long categoryId) {
        return productService.getOne(categoryId);
    }

    @Override
    public ApiResult<?> getAllProductByCategoryId(Long categoryId) {
        return productService.getAllProductsByCategoryId(categoryId);
    }

    @Override
    public List<OptionResDto> getProductsForOption(Long categoryId) {
        return productService.getProductsForOptionByCategoryId(categoryId);
    }


    @Override
    public ApiResult<?> edit(Long productId, ProductReqDto productReqDto) {
        return productService.edit(productId, productReqDto);
    }

    @Override
    public ApiResult<?> delete(Long productId) {
        return productService.delete(productId);
    }
}
