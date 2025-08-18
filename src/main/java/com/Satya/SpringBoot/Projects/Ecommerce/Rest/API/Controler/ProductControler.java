package com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Controler;

import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Model.Product;
import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Product> getAllProducts() {
        return productService.showAll();
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int productId) {
        Product product = productService.getProduct(productId);
        if (product.getId() > 0) {
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product saveProduct= null;
        try {
            saveProduct = productService.saveOrUpdate(product,imageFile);
            return new ResponseEntity<>(saveProduct,HttpStatus.CREATED);

        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {

        Product updatedpPoduct =null;
        try{
           updatedpPoduct= productService.saveOrUpdate(product,imageFile);
           return new ResponseEntity<>("Updated",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }}


    @GetMapping("load")
    public ResponseEntity<String> load() {           //ResponseEntity --> Status Code
//        productService.load();
        return new ResponseEntity<>("Records Loaded", HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);

        if(product.getId() >0){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("searching with :" + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @DeleteMapping("deleteAll")
    public String DeleteAll() {
        productService.deleteAll();
        return "Deleted All Records";
    }

}
