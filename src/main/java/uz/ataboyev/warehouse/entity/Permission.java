package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
import uz.ataboyev.warehouse.enums.PermissionEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Permission extends AbsLongEntity {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private PermissionEnum nameEnum;

//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @ManyToMany(mappedBy = "permissions")
//    private List<RolePermissionFromUser> rolePermissionFromUser;
//
//    public Permission(PermissionEnum nameEnum) {
//        this.nameEnum = nameEnum;
//    }
}
