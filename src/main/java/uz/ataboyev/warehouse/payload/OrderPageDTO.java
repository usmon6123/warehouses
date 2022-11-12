package uz.ataboyev.warehouse.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.CurrencyType;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.entity.Order;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPageDTO {

   private Timestamp date;
   private ClientDtoForPageable clientDto;
   private Double orderPriceSum;
   private Double orderPriceDollar;
   private OrderType type;






}
