package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Category;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.payload.CategoryResDto;
import uz.ataboyev.warehouse.repository.CategoryRepository;
import uz.ataboyev.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public ApiResult<?> add(CategoryDto categoryDto) {

        if (categoryRepository.existsByNameAndWarehouseId(categoryDto.getName(), categoryDto.getWarehouseId()))
            throw RestException.exception("Bu catalog bazada mavjud", HttpStatus.CONFLICT);
        Category category = new Category(categoryDto.getName(), categoryDto.getWarehouseId());

        categoryRepository.save(category);

        return ApiResult.successResponse("SUCCESS ADDED \nid: " + category.getId() + "\n name: " + category.getName());
    }

    @Override
    public ApiResult<?> getOne(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RestException("Category not Found", HttpStatus.NOT_FOUND));

        CategoryResDto categoryResDto =
                new CategoryResDto(category.getId(), category.getName());

        return ApiResult.successResponse(categoryResDto);
    }


    @Override
    public ApiResult<?> getAllCategories(Long wareHouseId) {
        List<CategoryResDto> allCategoryList = getAllCategoryList(wareHouseId);
        return ApiResult.successResponse(allCategoryList);
    }

    @Override
    public List<CategoryResDto> getAllCategoryList(Long wareHouseId) {
        List<Category> categories = categoryRepository.findAllByWarehouseId(wareHouseId);

        return categories.stream().map(category -> new CategoryResDto(category.getId(), category.getName())).collect(Collectors.toList());

    }

    @Override
    public ApiResult<?> edit(Long categoryId, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> RestException.restThrow("Bu idli Categoriya mavjudmas", HttpStatus.NOT_FOUND));
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return ApiResult.successResponse("SUCCESS_EDITED");

    }

    @Override
    public ApiResult<?> delete(Long categoryId) {
        if (productRepository.existsByCategoryId(categoryId))
            throw RestException.restThrow("Bu categoriyani o'chira olmaysiz, ichida productlar mavjud", HttpStatus.CONFLICT);
        if (!categoryRepository.existsById(categoryId))
            throw RestException.restThrow("Bu idli Categoriya mavjudmas", HttpStatus.NOT_FOUND);

        categoryRepository.deleteById(categoryId);

        return ApiResult.successResponse("DELETED_CATEGORY");
    }

}
