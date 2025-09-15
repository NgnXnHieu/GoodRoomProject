package com.example.backendTeam12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendTeam12.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
    
}
