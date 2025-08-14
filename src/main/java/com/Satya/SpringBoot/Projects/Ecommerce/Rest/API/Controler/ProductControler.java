package com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Controler;

import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Model.Products;
import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductControler {

    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public List<Products> getAllProducts() {
        return productService.showAll();
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int productId) {
        Products product = productService.getProduct(productId);
        if (product.getId() > 0) {
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("product")
    public ResponseEntity<?> addProduct(@RequestPart Products product, @RequestPart MultipartFile imageFile) {
        Products saveProduct= null;
        try {
            saveProduct = productService.addProduct(product,imageFile);
            return new ResponseEntity<>(saveProduct,HttpStatus.CREATED);

        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("delete/{id}")
    public String DeleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Deleted";
    }

    @PutMapping("/product/update/${id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id) {

        Products product =productService.getProduct(id);

        return new ResponseEntity<>(productService.updateProduct(product));
    }

    @GetMapping("load")
    public ResponseEntity<String> load() {           //ResponseEntity --> Status Code
//        productService.load();
        return new ResponseEntity<>("Records Loaded", HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable int id) {
        Products product = productService.getProduct(id);

        if(product.getId() >0){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("deleteAll")
    public String DeleteAll() {
        productService.deleteAll();
        return "Deleted All Records";
    }

}
