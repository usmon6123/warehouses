package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    ApiResult<?> add(CategoryDto categoryDto);

    ApiResult<?> getOne(Long categoryId);

    ApiResult<?> getAllCategories(Long wareHouseId);

    ApiResult<?> edit(Long categoryId, CategoryDto categoryDto);

    ApiResult<?> delete(Long categoryId);
}
