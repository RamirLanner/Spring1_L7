package ru.pentragon.spring1l7.spring1l7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import ru.pentragon.spring1l7.spring1l7.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByCostGreaterThan(float cost);
    List<Product> findProductsByCostLessThan(float cost);
    List<Product> findProductsByCostBetween(float cost1, float cost2);

}
