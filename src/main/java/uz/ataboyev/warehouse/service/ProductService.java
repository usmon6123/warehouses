package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.ProductReqDto;
import uz.ataboyev.warehouse.payload.ProductResDto;

import java.util.List;

public interface ProductService {
    ApiResult<?> add(ProductReqDto productReqDto);

    ApiResult<?> getOne(Long productId);

    ApiResult<?> getAllProductsByCategoryId(Long categoryId);

    List<ProductResDto> getAllProductListByCategoryId(Long categoryId);

    ApiResult<?> edit(Long productId, ProductReqDto productReqDto);

    ApiResult<?> delete(Long productId);


    void checkingProductByIdListOrElseThrow(List<Long> productIdList);

}
