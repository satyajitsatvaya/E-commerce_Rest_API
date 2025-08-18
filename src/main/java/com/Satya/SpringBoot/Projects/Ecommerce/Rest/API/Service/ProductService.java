package com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Service;

import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Model.Product;
import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Repo repo;

    public List<Product> showAll() {
        return repo.findAll();
    }

    public Product saveOrUpdate(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
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



    public Product getProduct(int id) {
        return repo.findById(id).orElse(new Product(-1));
    }


    public void deleteAll() {
        repo.deleteAll();
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
