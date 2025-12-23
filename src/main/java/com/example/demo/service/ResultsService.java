package com.example.demo.service;

import com.example.demo.dto.ResultsDTO;
import com.example.demo.entity.Request;
import com.example.demo.entity.Results;
import com.example.demo.entity.Shops;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.ResultsRepository;
import com.example.demo.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResultsService {

    private final ResultsRepository repository;
    private final ShopRepository shopRepository;
    private final RequestRepository requestRepository;

    // ใช้ประกอบ certificateUrl ให้ถูก (รองรับ base ที่มี/ไม่มี /api)
    @Value("${server.public-base-url:http://10.32.110.29:8081}")
    private String publicBaseUrl;

    public ResultsService(ResultsRepository repository,
                          ShopRepository shopRepository,
                          RequestRepository requestRepository) {
        this.repository = repository;
        this.shopRepository = shopRepository;
        this.requestRepository = requestRepository;
    }

    // ✅ บันทึกผลตรวจ (รองรับการผูก shop / request)
    @Transactional
    public Results saveResult(Results result) {
        if (result.getShop() == null || result.getShop().getId() == null) {
            throw new IllegalArgumentException("shop.id is required");
        }
        Shops managedShop = shopRepository.findById(result.getShop().getId())
                .orElseThrow(() -> new IllegalArgumentException("Shop not found: " + result.getShop().getId()));
        result.setShop(managedShop);

        if (result.getRequest() != null && result.getRequest().getId() != null) {
            Request req = requestRepository.findById(result.getRequest().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Request not found: " + result.getRequest().getId()));
            result.setRequest(req);
        }

        return repository.save(result);
    }

    // ✅ สำหรับกรณีส่ง shopId แยก
    @Transactional
    public Results saveResultWithShopId(Results result, Long shopId) {
        if (shopId == null) throw new IllegalArgumentException("shopId is required");
        Shops managedShop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Shop not found: " + shopId));
        result.setShop(managedShop);

        if (result.getRequest() != null && result.getRequest().getId() != null) {
            Request req = requestRepository.findById(result.getRequest().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Request not found: " + result.getRequest().getId()));
            result.setRequest(req);
        }

        return repository.save(result);
    }

    // ---------- Queries เดิม (คงไว้ใช้ภายใน/ที่อื่น) ----------
    public List<Results> getResultsByShop(Long shopId) {
        return repository.findByShop_Id(shopId);
    }

    public List<Results> getResultsByVegeName(String vegeName) {
        return repository.findByVegeName(vegeName);
    }

    public List<Results> getAllResults() {
        return repository.findAll();
    }

    public List<ResultsDTO> getAllResultsDTO() {
        return repository.findAll().stream()
                .map(ResultsDTO::fromEntity)
                .toList();
    }

    public List<ResultsDTO> getShopnameAndLocation() {
        return repository.findShopnameAndLocation();
    }

    public List<ResultsDTO> getApprovedShopsForMap(String approvedKeyword, Long shopId) {
        final String like = "%" + (approvedKeyword == null ? "" : approvedKeyword) + "%";
        return repository.findApprovedShopsForMap(like, shopId);
    }

    // ---------- ✅ ใหม่: DTO สำหรับ Controller /shop/{shopId} ----------
    public List<ResultsDTO> getResultsByShopDto(Long shopId) {
        return repository.findByShop_Id(shopId).stream().map(r -> {
            String certUrl = null;
            if (r.getCertificate() != null && r.getCertificate().length > 0) {
                certUrl = "http://10.32.110.29:8081/api/results/" + r.getId() + "/certificate";
            }
            return new ResultsDTO(
                    r.getId(),
                    r.getShop() != null ? r.getShop().getId() : null,
                    r.getShopName(),
                    r.getVegeName(),
                    r.getResult(),
                    r.getLocation(),
                    r.getDateInspection(),   // ✅ เปลี่ยนให้ตรง entity
                    certUrl
            );
        }).toList();
    }


    // ---------- 📄 Certificate ----------
    @Transactional
    public Results setCertificate(Long resultId, byte[] pdfBytes) {
        Results r = repository.findById(resultId)
                .orElseThrow(() -> new IllegalArgumentException("Result not found: " + resultId));
        r.setCertificate(pdfBytes);
        return repository.save(r);
    }

    public byte[] getCertificate(Long resultId) {
        return repository.findById(resultId)
                .map(Results::getCertificate)
                .orElse(null);
    }

    public Optional<Results> getByIdAndShop(Long resuId, Long shopId) {
        return repository.findByIdAndShop_Id(resuId, shopId);
    }

    @Transactional
    public Results setCertificateFor(Long resuId, Long shopId, byte[] pdfBytes) {
        Results r = repository.findByIdAndShop_Id(resuId, shopId)
                .orElseThrow(() -> new IllegalArgumentException("Result not found for this shop"));
        r.setCertificate(pdfBytes);
        return repository.save(r);
    }

    public byte[] getCertificateFor(Long resuId, Long shopId) {
        return repository.findByIdAndShop_Id(resuId, shopId)
                .map(Results::getCertificate)
                .orElse(null);
    }

    // (ออปชัน) ทางลัดด้วย reqId
    public byte[] getCertificateByRequest(Long requestId) {
        return repository.findByRequest_Id(requestId)
                .map(Results::getCertificate)
                .orElse(null);
    }
}
