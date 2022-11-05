package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.ProductDto;

import java.util.List;

public interface ProductService {
    ApiResult<?> add(ProductDto productDto);

    ApiResult<?> getOne(Long productId);

    List<ProductDto> getAllProductListByCategoryId(Long categoryId);

    ApiResult<?> edit(Long productId, ProductDto productDto);

    ApiResult<?> delete(Long productId);


    void checkingProductByIdListOrElseThrow(List<Long> productIdList);

}
