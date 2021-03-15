package ru.pentragon.spring1l7.spring1l7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pentragon.spring1l7.spring1l7.model.Product;
import ru.pentragon.spring1l7.spring1l7.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable Long id ){
        return productService.getProductByID(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    //http://localhost:8189/app/products/parse/min?cost=100
    @GetMapping("/parse/min")
    public List<Product> findProductsByCostGreaterThan(@RequestParam String cost){
        System.out.println("COST = "+cost);
        return productService.findProductsByCostGreaterThan(Float.parseFloat(cost));
    }




}
