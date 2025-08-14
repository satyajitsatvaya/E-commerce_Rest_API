package com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Service;

import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Model.Products;
import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private Repo repo;

    public List<Products> showAll() {
        return repo.findAll();
    }

    public Products addProduct(Products product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public Products updateProduct(Products product) {
        return repo.save(product);
    }

//    public void load() {
//        List<Products> products = new ArrayList<>(List.of(
//                new Products("Laptop", "Powerful laptop for everyday use.", "Dell", new BigDecimal("60000"), "Electronics", new Date(), true, 10),
//                new Products("Smartphone", "Latest model with cutting-edge features.", "Samsung", new BigDecimal("45000"), "Electronics", new Date(), true, 25),
//                new Products("Washing Machine", "Efficient and silent front-load washing machine.", "LG", new BigDecimal("30000"), "Home Appliances", new Date(), true, 8),
//                new Products("Sneakers", "Comfortable running shoes.", "Nike", new BigDecimal("4000"), "Footwear", new Date(), true, 50),
//                new Products("Backpack", "Durable backpack suitable for travel and school.", "Wildcraft", new BigDecimal("1500"), "Accessories", new Date(), true, 40)
//        ));
//
//        repo.saveAll(products);
//    }



    public Products getProduct(int id) {
        return repo.findById(id).orElse(new Products(-1));
    }


    public void deleteAll() {
        repo.deleteAll();
    }
}
