package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Data
public class ClientOrderDto {

    @NotNull(message = "date")
    private Timestamp date;

    @NotNull(message = "categoryId_not_null")
    private String categoryName;

    @NotNull(message = "productId_not_null")
    private String productName;

    @NotNull(message = "count_not_null")
    private Double count;

    @NotNull(message = "price_not_null")
    private Double countSum;//bir donaning bahosi

    @NotNull(message = "currencyTypeEnum_not_null")
    private CurrencyTypeEnum currencyTypeEnum;

    @NotNull(message = "price_not_null")
    private Double price ;//count * countSum


}
