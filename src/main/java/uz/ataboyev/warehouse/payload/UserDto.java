package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class UserDto {

    private String username;

    private uz.ataboyev.warehouse.payload.RoleResDto roleResDto;


    //userni fieldlari proektga qarab har hil bo'ladi
}
