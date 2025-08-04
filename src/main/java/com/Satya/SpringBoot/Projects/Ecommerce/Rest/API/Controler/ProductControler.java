package com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Controler;

import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Model.Products;
import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductControler {

    @Autowired
    private ProductService service;

    @GetMapping("products")
    public List<Products> showAll() {
        return service.showAll();
    }

    @PostMapping("add")
    public Products addProduct(@PathVariable Products product){
        return service.addProduct(product);
    }

    @DeleteMapping("delete/{id}")
    public String DeleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return "Deleted";
    }

    @PutMapping("update")
    public Products updateProduct(@PathVariable Products product){
        return service.updateProduct(product);
    }

    @GetMapping("load")
    public String load(){
        service.load();
        return "Sucess";
    }

    @GetMapping("product/{id}")
    public Products getProduct(@PathVariable int id){
        return service.getProduct(id);
    }
}
