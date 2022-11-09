package uz.ataboyev.warehouse.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private OptionDTO<CategoryResDto> categoryDtoOptionDTO;
    private OptionDTO<ProductResDto> productDtoOptionDTO;
    private OptionDTO<ClientResDto> clientDTOOptionDTO;

}
