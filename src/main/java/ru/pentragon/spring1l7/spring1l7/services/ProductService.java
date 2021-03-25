package ru.pentragon.spring1l7.spring1l7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.pentragon.spring1l7.spring1l7.model.dtos.ProductDto;
import ru.pentragon.spring1l7.spring1l7.model.entities.Product;
import ru.pentragon.spring1l7.spring1l7.repository.ProductRepository;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        if(page < 0)
            throw new RuntimeException();
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

    public Optional<ProductDto> saveOrUpdate(ProductDto product) {
//        System.out.println(product.toString());
        Product tmp = new Product();
        tmp.setId(product.getId());
        tmp.setTitle(product.getTitle());
        tmp.setCost(product.getPrice());
//        System.out.println("product cost = "+product.getPrice());
//        System.out.println("TMP cost = "+tmp.getCost());
        tmp = productRepository.saveAndFlush(tmp);
        return findProductById(tmp.getId());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


//    public List<Product> getAll(){
//        return productRepository.findAll();
//    }
//
//    public List<Product> getAll(int page, int size, Sort.Direction sortCost, Sort.Direction sortName){
//        return productRepository.findAll(PageRequest.of(page, size
//                , Sort.by(sortCost,"cost").and(Sort.by(sortName,"title")))).toList();
//        //return productrRepository.findAll(PageRequest.of(page, size)).toList();
//    }
//
//    public Product getProductByID(Long id){
//        return productRepository.findById(id).get();
//    }
//
//    public Product addProduct(Product product){
//        return productRepository.saveAndFlush(product);
//    }
//
//    public void deleteProduct(Long id){
//        productRepository.deleteById(id);
//    }
//
//    public List<Product> findProductsByCostGreaterThan(float cost){
//        return productRepository.findProductsByCostGreaterThan(cost);
//    }



}
