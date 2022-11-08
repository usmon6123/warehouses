package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.payload.ClientReqDto;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"full_name","phone_number"})})
public class Client extends AbsLongEntity {

    @Column(nullable = false,name = "full_name")
    private String fullName;

    @Column(nullable = false,name = "phone_number")
    private String phoneNumber;

    public static Client make(ClientReqDto clientReqDto) {
        return new Client(clientReqDto.getFullName(),clientReqDto.getPhoneNumber());
    }

//    private Double totalSum;//clientning bir qancha savdolaridagi umumiy PULI

}
