package com.example.backendTeam12.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendTeam12.model.BookingHistory;

@Repository
public interface  BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {
    List<BookingHistory> findByUserId(Long userId);
}
