
package com.example.backendTeam12.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendTeam12.model.HomestayService;

@Repository
public interface ServiceRepository extends JpaRepository<HomestayService, Long> {
    List<HomestayService> findByServiceNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String serviceName, String description);
    List<HomestayService> findByPriceBetween(BigDecimal price1, BigDecimal price2);
}
