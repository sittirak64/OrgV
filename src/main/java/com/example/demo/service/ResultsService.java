package com.example.demo.service;

import com.example.demo.entity.Results;
import com.example.demo.dto.ResultsDTO;
import com.example.demo.repository.ResultsRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResultsService {

    private final ResultsRepository repository;

    public ResultsService(ResultsRepository repository) {
        this.repository = repository;
    }

    public Results saveResult(Results result) {
        return repository.save(result);
    }

    public List<Results> getResultsByShop(Long shopId) {
        return repository.findByShopId(shopId);
    }

    public List<Results> getResultsByVegeName(String vegeName) {
        return repository.findByVegeName(vegeName);
    }

    public List<Results> getAllResults() {
        return repository.findAll();
    }

    // ✅ ดึงเฉพาะ shopname + location
    public List<ResultsDTO> getShopnameAndLocation() {
        return repository.findShopnameAndLocation();
    }
}
