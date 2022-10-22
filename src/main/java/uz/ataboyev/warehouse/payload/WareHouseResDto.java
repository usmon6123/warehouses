package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Warehouse;

@AllArgsConstructor@NoArgsConstructor@Data
public class WareHouseResDto {

    private Long id;
    private String name;

    public WareHouseResDto(Warehouse warehouse) {
        this.id = warehouse.getId();
        this.name = warehouse.getName();
    }
}
