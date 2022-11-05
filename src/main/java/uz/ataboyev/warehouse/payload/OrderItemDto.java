package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    OrderItemDto {

    @NotNull(message = "productId_not_null")
    private Long productId;

    @NotNull(message = "count_not_null")
    private Double count;

    @NotNull(message = "price_not_null")
    private Double price;

    @NotNull(message = "currencyTypeEnum_not_null")
    private CurrencyTypeEnum currencyTypeEnum;

}
