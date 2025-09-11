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
    public Results createResult(@RequestBody Results result) {
        return service.saveResult(result);
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
