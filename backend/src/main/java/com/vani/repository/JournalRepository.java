package com.vani.repository;

import com.vani.entity.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface JournalRepository extends MongoRepository<Journal, String> {
    List<Journal> findByUserId(String userId);
    Optional<Journal> findByIdAndUserId(String id , String userId);
    long deleteByUserId(String userId);

}