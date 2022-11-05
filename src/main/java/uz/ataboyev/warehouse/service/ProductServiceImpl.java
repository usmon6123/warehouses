package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Product;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.ProductDto;
import uz.ataboyev.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ApiResult<?> add(ProductDto productDto) {
        Product save = productRepository.save(new Product(
                productDto.getName(),
                productDto.getCode(),
                productDto.getCategoryId(),
                productDto.getMinCount()
        ));

        return ApiResult.successResponse(save);
    }

    @Override
    public ApiResult<?> getOne(Long productId) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProductListByCategoryId(Long categoryId) {
        List<Product> productList = productRepository.findAll();

        return productList.stream().map(ProductDto::makeDTO).collect(Collectors.toList());
    }


    @Override
    public ApiResult<?> edit(Long productId, ProductDto productDto) {
        return null;
    }

    @Override
    public ApiResult<?> delete(Long productId) {
        return null;
    }

    @Override
    public void checkingProductByIdListOrElseThrow(List<Long> productIdList) {

        Integer productsCount = productRepository.countByIdIn(productIdList);

        if (!Objects.equals(productsCount, productIdList.size()))
            throw RestException.restThrow("Mavjud bolmagan product id berildi =>" + productIdList);

    }
}
