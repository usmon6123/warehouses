package uz.ataboyev.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role extends AbsLongEntity {

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

//    @Enumerated(EnumType.STRING)
//    private Set<PermissionEnum> permissions;
}
