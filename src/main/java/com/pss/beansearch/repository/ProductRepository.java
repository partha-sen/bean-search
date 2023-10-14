package com.pss.beansearch.repository;

import com.pss.beansearch.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {



    @Query(value = "SELECT MAX(version) FROM product u WHERE id = :id",
            nativeQuery = true)
    long getMaxVersion(@Param("id") long id);
}
