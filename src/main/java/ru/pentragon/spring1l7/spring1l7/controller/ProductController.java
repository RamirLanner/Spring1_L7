package ru.pentragon.spring1l7.spring1l7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pentragon.spring1l7.spring1l7.model.Product;
import ru.pentragon.spring1l7.spring1l7.services.ProductService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Transactional
    public List<Product> getAll(int page, int size, Sort.Direction sortCost, Sort.Direction sortName){
        return productService.getAll(page-1, size,sortCost, sortName);
    }

    //http://localhost:8189/app/products?page=1&size=21&sortCost=DESC&sortName=DESC
    @GetMapping
    public ResponseEntity<Object> returnAll(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(defaultValue = "ASC") String sortCost,
                                            @RequestParam(defaultValue = "ASC") String sortName){
        Sort.Direction sortCostDir = Sort.Direction.ASC;
        Sort.Direction sortNameDir = Sort.Direction.ASC;
        if(sortCost.equals("DESC")) sortCostDir = Sort.Direction.DESC;
        if(sortName.equals("DESC")) sortNameDir = Sort.Direction.DESC;
        List<Product> prod = getAll(page, size,sortCostDir,sortNameDir);
        if (prod.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(prod);//getAll(page, size);
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
