package uz.ataboyev.warehouse.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.CurrencyType;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.Type;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPageDTO {

   private Timestamp date;
   private ClientResDto clientResDto;
   private Double orderPrice;
   private CurrencyType currencyType;
   private OrderType type;



}
