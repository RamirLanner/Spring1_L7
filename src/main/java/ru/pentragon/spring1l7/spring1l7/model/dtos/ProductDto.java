package ru.pentragon.spring1l7.spring1l7.model.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pentragon.spring1l7.spring1l7.model.entities.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private float price;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getCost();
    }
}
