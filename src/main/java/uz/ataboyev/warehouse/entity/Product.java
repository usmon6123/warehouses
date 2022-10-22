package uz.ataboyev.warehouse.entity;

import lombok.*;
import uz.ataboyev.warehouse.entity.template.AbsLongEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product extends AbsLongEntity {

    @Column(unique = true, nullable = false)
    private String name;
    private String code;
    private Long minCount;


    //----------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "category_id")
    private Category category;

    @Column(name = "category_id")
    private Long categoryId;
    //----------------------------------------------------------------------------


    public Product(String name, String code, Long categoryId) {
        this.name = name;
        this.code = code;
        this.categoryId = categoryId;
    }
}
