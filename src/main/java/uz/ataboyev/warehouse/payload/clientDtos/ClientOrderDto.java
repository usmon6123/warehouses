package uz.ataboyev.warehouse.payload.clientDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientOrderDto {

    @NotNull(message = "date")
    private String date;

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
    private Double price;//count * countSum


    public static ClientOrderDto make(OrderItemByOrderId orderItemByOrderId) {
        return new ClientOrderDto(
                orderItemByOrderId.getDate(),
                orderItemByOrderId.getCategoryName(),
                orderItemByOrderId.getProductName(),
                Double.parseDouble(orderItemByOrderId.getCount()),
                Double.parseDouble(orderItemByOrderId.getCountSum()),
                CurrencyTypeEnum.valueOf(orderItemByOrderId.getCurrencyTypeEnum()),
                Double.parseDouble(orderItemByOrderId.getPrice())
        );
    }
}
