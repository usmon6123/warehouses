//package uz.ataboyev.warehouse.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import uz.ataboyev.warehouse.entity.template.AbsLongEntity;
//import uz.ataboyev.warehouse.enums.RoleType;
//
//import javax.persistence.*;
//
//@EqualsAndHashCode(callSuper = true)
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//public class Role extends AbsLongEntity {
//
//    @Column(unique = true)
//    private String name;
//
//    @Column(columnDefinition = "text")
//    private String description;
//
//    @JoinColumn(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType=RoleType.USER;
//
//}
