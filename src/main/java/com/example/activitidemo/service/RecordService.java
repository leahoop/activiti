package com.example.activitidemo.service;

import com.example.activitidemo.model.Record;
import com.example.activitidemo.repository.RecordRepository;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecordService {

  @Autowired
  private RecordRepository recordRepository;


  public Record save(Record record) {
    return recordRepository.save(record);
  }

  public Optional<Record> findById(Integer id) {
    return recordRepository.findById(id);
  }

}
