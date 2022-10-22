package uz.ataboyev.warehouse.controller;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CategoryDto;
import uz.ataboyev.warehouse.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public ApiResult<?> add(@Valid CategoryDto categoryDto) {

        return categoryService.add(categoryDto);
    }

    @Override
    public ApiResult<?> getOne(Long categoryId) {
        return categoryService.getOne(categoryId);
    }

    @Override
    public ApiResult<?> getAllCategories(Long wareHouseId) {
        return categoryService.getAllCategories(wareHouseId);
    }

    @Override
    public ApiResult<?> edit(Long categoryId, CategoryDto categoryDto) {
        return categoryService.edit(categoryId, categoryDto);
    }

    @Override
    public ApiResult<?> delete(Long categoryId) {
        return categoryService.delete(categoryId);
    }


}
