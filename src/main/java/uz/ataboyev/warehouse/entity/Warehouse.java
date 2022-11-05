package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Warehouse extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    //----------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(insertable = false, updatable = false, name = "company_id")
    private Company company;

    @Column(name = "company_id")
    private Long companyId;
    //----------------------------------------------------------------------------

    public Warehouse(WareHouseReqDto wareHouseReqDto) {
        this.name = wareHouseReqDto.getName();
        this.companyId = wareHouseReqDto.getCompanyId();
    }


//    public static Warehouse mapWarehouse(WareHouseReqDto wareHouseReqDto){
//        return new Warehouse(wareHouseReqDto.getName(), wareHouseReqDto.getCompanyId());
//    }
}
