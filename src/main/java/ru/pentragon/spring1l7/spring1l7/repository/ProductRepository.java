package ru.pentragon.spring1l7.spring1l7.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.pentragon.spring1l7.spring1l7.model.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

//    List<Product> findProductsByCostGreaterThan(float cost);
//    List<Product> findProductsByCostLessThan(float cost);
//    List<Product> findProductsByCostBetween(float cost1, float cost2);
}
