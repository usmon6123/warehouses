package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCategoryId(Long categoryId);

    boolean existsByCategory_Name(String category_name);
    boolean existsByName(String name);


    Integer countByIdIn(Collection<Long> id);


    List<Product> findAllByCategoryId(Long categoryId);
}
