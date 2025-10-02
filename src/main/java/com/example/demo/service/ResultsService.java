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

    // ✅ อันนี้ return Entity
    public List<Results> getAllResults() {
        return repository.findAll();
    }

    // ✅ อันนี้ return DTO (เลือกฟิลด์)
    public List<ResultsDTO> getAllResultsDTO() {
        return repository.findAll().stream()
                .map(r -> new ResultsDTO(
                        r.getShop().getId(),   // ✅ ดึง shopId
                        r.getShopName(),
                        r.getVegeName(),
                        r.getResult(),
                        r.getLocation()
                ))
                .toList();
    }

    // ✅ ถ้าอยากดึงเฉพาะ shopName + location
    public List<ResultsDTO> getShopnameAndLocation() {
        return repository.findAll().stream()
                .map(r -> new ResultsDTO(
                        r.getShop().getId(),
                        r.getShopName(),
                        null,       // vegeName ไม่เอา
                        null,       // result ไม่เอา
                        r.getLocation()
                ))
                .toList();
    }
}
