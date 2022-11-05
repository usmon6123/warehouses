package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String code;

    private Long categoryId;

    private Long minCount;

    public static ProductDto makeDTO(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getCategoryId(),
                product.getMinCount()
        );
    }
}
