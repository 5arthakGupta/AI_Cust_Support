package com.sarthak.ai_customer_support.repository;

import com.sarthak.ai_customer_support.model.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JPARepository{
    List<Document> findByContentContainingIgnoreCase(String keyword);
}
