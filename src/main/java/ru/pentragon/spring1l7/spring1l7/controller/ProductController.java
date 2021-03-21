package ru.pentragon.spring1l7.spring1l7.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.pentragon.spring1l7.spring1l7.exeptions.ResourceNotFoundException;
import ru.pentragon.spring1l7.spring1l7.model.dtos.ProductDto;
import ru.pentragon.spring1l7.spring1l7.model.entities.Product;
import ru.pentragon.spring1l7.spring1l7.repository.specifications.ProductSpecifications;
import ru.pentragon.spring1l7.spring1l7.services.ProductService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //http://localhost:8189/app/api/v1/products?p=1&min_price=100
    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "s", defaultValue = "5") Integer size
    ) {
        if (page < 1) page = 1;
        if (size < 1) size = 1;
        return productService.findAll(ProductSpecifications.build(params),
                page,
                size);
    }

    // http://localhost:8189/happy/api/v1/products
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void updateProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }


//    @Transactional
//    public List<Product> getAll(int page, int size, Sort.Direction sortCost, Sort.Direction sortName) {
//        return productService.getAll(page - 1, size, sortCost, sortName);
//    }
//
//    //http://localhost:8189/app/products?page=1&size=21&sortCost=DESC&sortName=DESC
//    @GetMapping
//    public ResponseEntity<Object> returnAll(@RequestParam(defaultValue = "1") Integer page,
//                                            @RequestParam(defaultValue = "10") Integer size,
//                                            @RequestParam(defaultValue = "ASC") String sortCost,
//                                            @RequestParam(defaultValue = "ASC") String sortName) {
//        Sort.Direction sortCostDir = Sort.Direction.ASC;
//        Sort.Direction sortNameDir = Sort.Direction.ASC;
//        if (sortCost.equals("DESC")) sortCostDir = Sort.Direction.DESC;
//        if (sortName.equals("DESC")) sortNameDir = Sort.Direction.DESC;
//        List<Product> prod = getAll(page, size, sortCostDir, sortNameDir);
//        if (prod.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        return ResponseEntity.ok(prod);//getAll(page, size);
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductByID(@PathVariable Long id) {
//        return productService.getProductByID(id);
//    }
//
//    @PostMapping
//    public Product addProduct(@RequestBody Product product) {
//        return productService.addProduct(product);
//    }
//
//    @GetMapping("/delete/{id}")
//    public void deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//    }
//
//    //http://localhost:8189/app/products/parse/min?cost=100
//    @GetMapping("/parse/min")
//    public List<Product> findProductsByCostGreaterThan(@RequestParam String cost) {
//        System.out.println("COST = " + cost);
//        return productService.findProductsByCostGreaterThan(Float.parseFloat(cost));
//    }


}
