package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Product;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.ProductReqDto;
import uz.ataboyev.warehouse.payload.ProductResDto;
import uz.ataboyev.warehouse.repository.ProductRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BaseService baseService;

    @Override
    public ApiResult<?> add(ProductReqDto productReqDto) {

        //CATEGORIYA TEKSHIRILYABDI
        if (!baseService.checkCategoryById(productReqDto.getCategoryId()))
            throw RestException.restThrow("Mahsulotning kategoriyasi topilmadi", HttpStatus.NOT_FOUND);

        if (productRepository.existsByName(productReqDto.getName()))
            throw RestException.restThrow("Bu nomli mahsulot mavjud", HttpStatus.CONFLICT);

        Product product = Product.make(productReqDto);

        savedProduct(product);

        ProductResDto productResDto = ProductResDto.makeDTO(product);

        return ApiResult.successResponse(productResDto,"product muvafaqiyatli saqlandi");
    }

    @Override
    public ApiResult<?> getOne(Long productId) {

        Product product = baseService.getProductById(productId);

        ProductResDto productResDto = ProductResDto.makeDTO(product);

        return ApiResult.successResponse(productResDto);
    }

    @Override
    public ApiResult<?> getAllProductsByCategoryId(Long categoryId) {

        List<Product> productList = productRepository.findAllByCategoryId(categoryId);

        List<ProductResDto> productResDtos = productList.stream().map(ProductResDto::makeDTO).collect(Collectors.toList());

        return ApiResult.successResponse(productResDtos);
    }

    @Override
    public List<OptionResDto> getProductsForOptionByCategoryId(Long categoryId) {
        List<Product> productList = productRepository.findAllByCategoryId(categoryId);
        return productList.stream().map(OptionResDto::make).collect(Collectors.toList());
    }


    @Override
    public ApiResult<?> edit(Long productId, ProductReqDto productReqDto) {
        return null;
    }

    @Override
    public ApiResult<?> delete(Long productId) {
        //todo productni o'rdera qarab ishlimiz so'ng inshaalloh
        return null;
    }

    @Override
    public void checkingProductByIdListOrElseThrow(List<Long> productIdList) {

        Integer productsCount = productRepository.countByIdIn(productIdList);

        if (!Objects.equals(productsCount, productIdList.size()))
            throw RestException.restThrow("Mavjud bolmagan product id berildi =>" + productIdList);

    }

    private void savedProduct(Product product) {
        try{
            productRepository.save(product);
        }catch (Exception e){
            e.printStackTrace();
            throw RestException.restThrow("Product saqlashda muommo bo'ldi",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
