package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.PayTypeEnum;

@AllArgsConstructor@NoArgsConstructor@Data
public class OrderPriceDtoForPayType {

    private PayTypeEnum payTypeEnum;
    private Double sum;
    private Double dollar;
}
