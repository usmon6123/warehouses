package uz.ataboyev.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.enums.PermissionEnum;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleDto {

    private UUID userId;

    private Long roleId;

    private Set<PermissionEnum> permissionEnumList;
}
