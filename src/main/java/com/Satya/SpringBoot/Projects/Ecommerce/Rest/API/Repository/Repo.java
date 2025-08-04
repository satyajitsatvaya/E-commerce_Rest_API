package com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Repository;

import com.Satya.SpringBoot.Projects.Ecommerce.Rest.API.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Products ,Integer> {

}
