package com.example.activitidemo.repository;

import com.example.activitidemo.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Integer> {

    Optional<Record> findById(Integer id);

}
