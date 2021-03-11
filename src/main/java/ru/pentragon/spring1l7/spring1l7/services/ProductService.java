package ru.pentragon.spring1l7.spring1l7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pentragon.spring1l7.spring1l7.model.Product;
import ru.pentragon.spring1l7.spring1l7.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productrRepository;

    public List<Product> getAll(){
        return productrRepository.findAll();
    }

    public Product getProductByID(Long id){
        return productrRepository.findById(id).get();
    }

    public Product addProduct(Product product){
        return productrRepository.saveAndFlush(product);
    }

    public void deleteProduct(Long id){
        productrRepository.deleteById(id);
    }

    public List<Product> findProductsByCostGreaterThan(float cost){
        return productrRepository.findProductsByCostGreaterThan(cost);
    }


}
