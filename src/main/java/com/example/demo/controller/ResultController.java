package com.example.demo.controller;

import com.example.demo.dto.ResultsDTO;
import com.example.demo.entity.Results;
import com.example.demo.service.ResultsService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultsService service;

    public ResultController(ResultsService service) {
        this.service = service;
    }

    // ✅ สร้างผลตรวจ (ส่ง id กลับ)
    @PostMapping("/create")
    public ResponseEntity<ResultsDTO> createResult(@RequestBody Results result) {
        Results saved = service.saveResult(result);
        ResultsDTO dto = ResultsDTO.fromEntity(saved); // ยังใช้ได้ตามเดิม
        return ResponseEntity
                .created(URI.create("/api/results/" + saved.getId()))
                .body(dto);
    }

    // ✅ ดึงผลตรวจตาม shopId -> ส่ง DTO (แก้ 500/Lazy/recursion)
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<ResultsDTO>> getResultsByShop(@PathVariable Long shopId) {
        return ResponseEntity.ok(service.getResultsByShopDto(shopId));
    }

    // ✅ ดึงผลตรวจตามชื่อผัก (คงเดิม ถ้าจำเป็นค่อยเปลี่ยนเป็น DTO)
    @GetMapping("/vege/{vegeName}")
    public List<Results> getResultsByVege(@PathVariable String vegeName) {
        return service.getResultsByVegeName(vegeName);
    }

    // ✅ ดึงผลตรวจทั้งหมด (คงเดิม)
    @GetMapping("/all")
    public List<Results> getAllResults() {
        return service.getAllResults();
    }

    // ✅ เฉพาะ shopname + location (สำหรับ map)
    @GetMapping("/shops-location")
    public List<ResultsDTO> getShopnameAndLocation() {
        return service.getShopnameAndLocation();
    }

    // ✅ หมุดทั้งหมดที่ “ผ่าน” + มีพิกัด (ไม่บังคับส่ง shopId)
    @GetMapping("/map-pins")
    public List<ResultsDTO> getApprovedShopsForMapAll() {
        return service.getApprovedShopsForMap("ผ่าน", null);
    }

    // ---------- 📄 Certificate ----------
    @PostMapping(path = "/{id}/certificate-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCertificate(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }
        service.setCertificate(id, file.getBytes());
        return ResponseEntity.ok("Uploaded certificate for result " + id);
    }

    // ✅ เส้นหลัก: ดาวน์โหลดใบรับรองด้วย resu_id เดียว
    @GetMapping("/{resuId}/certificate")
    public ResponseEntity<byte[]> getCertificate(@PathVariable Long resuId) {
        byte[] data = service.getCertificate(resuId);
        if (data == null || data.length == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("certificate_" + resuId + ".pdf")
                        .build()
        );
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    // ---------- (ออปชัน) เส้นที่ใช้คู่กับ shopId ----------
    @PostMapping(
            path = "/shop/{shopId}/result/{resuId}/certificate-upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadCertificateByShop(
            @PathVariable Long shopId,
            @PathVariable Long resuId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }
        service.setCertificateFor(resuId, shopId, file.getBytes());
        return ResponseEntity.ok(
                "Uploaded certificate for result " + resuId + " (shop " + shopId + ")"
        );
    }

    @GetMapping("/shop/{shopId}/result/{resuId}/certificate")
    public ResponseEntity<byte[]> getCertificateByShop(
            @PathVariable Long shopId,
            @PathVariable Long resuId
    ) {
        byte[] data = service.getCertificateFor(resuId, shopId);
        if (data == null || data.length == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("certificate_" + resuId + "_shop_" + shopId + ".pdf")
                        .build()
        );
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
