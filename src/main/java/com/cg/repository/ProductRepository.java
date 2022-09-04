package com.cg.repository;


import com.cg.model.Product;
import com.cg.model.dto.ProductListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT NEW com.cg.model.dto.ProductListDTO (" +
            "p.id, " +
            "p.title, " +
            "p.slug, " +
            "p.productMedia.fileName, " +
            "p.productMedia.fileFolder, " +
            "p.price " +
            ") " +
            "FROM Product AS p")
    List<ProductListDTO> findAllProductListDTO();


    //    @Query("SELECT NEW com.cg.model.dto.ProductListDTO (" +
//                "p.id, " +
//                "p.title, " +
//                "p.slug, " +
//                "p.productMedia.fileName, " +
//                "p.productMedia.fileFolder, " +
//                "p.price " +
//            ") " +
//            "FROM Product AS p " +
//            "WHERE p.id = :id")
    @Query("SELECT p FROM Product AS p WHERE p.id = :id")
    Optional<Product> findById(@Param("id") String id);
}
