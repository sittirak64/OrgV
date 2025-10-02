package com.example.demo.controller;

import com.example.demo.entity.Results;
import com.example.demo.dto.ResultsDTO;
import com.example.demo.service.ResultsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultsService service;

    public ResultController(ResultsService service) {
        this.service = service;
    }

    // สร้างผลตรวจใหม่
    @PostMapping("/create")
    public ResultsDTO createResult(@RequestBody Results result) {
        Results saved = service.saveResult(result);

        // ✅ map ไปเป็น DTO เพื่อไม่ให้ shop.* โผล่มา
        return new ResultsDTO(
                saved.getShop().getId(),
                saved.getShopName(),
                saved.getVegeName(),
                saved.getResult(),
                saved.getLocation()
        );
    }


    // ดึงผลตรวจตาม shopId
    @GetMapping("/shop/{shopId}")
    public List<Results> getResultsByShop(@PathVariable Long shopId) {
        return service.getResultsByShop(shopId);
    }

    // ดึงผลตรวจตามชื่อพืช (resu_vegename)
    @GetMapping("/vege/{vegeName}")
    public List<Results> getResultsByVege(@PathVariable String vegeName) {
        return service.getResultsByVegeName(vegeName);
    }

    // ดึงผลตรวจทั้งหมด
    @GetMapping("/all")
    public List<Results> getAllResults() {
        return service.getAllResults();
    }

    // ดึงเฉพาะ shopname + location
    @GetMapping("/shops-location")
    public List<ResultsDTO> getShopnameAndLocation() {
        return service.getShopnameAndLocation();
    }
}
