package uz.ataboyev.warehouse.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.Role;
import uz.ataboyev.warehouse.enums.PermissionEnum;

import java.util.Set;

@AllArgsConstructor@NoArgsConstructor@Data@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResDto {

    private Long id;

    private String name;

    private String description;

    private Set<PermissionEnum> permissions;

//todo role per
    public RoleResDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
    }
}
