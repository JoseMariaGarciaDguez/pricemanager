package com.josemariagarciadguez.pricemanager.infrastructure;


import com.josemariagarciadguez.pricemanager.application.PriceService;
import com.josemariagarciadguez.pricemanager.infrastructure.dto.PriceDTO;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
@AllArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/get-price")
    public Optional<PriceDTO> getPrice(
            @RequestParam Integer productId,
            @RequestParam Integer brandId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime applicationDate) {

        return priceService.getApplicablePrice(productId, brandId, applicationDate);
    }
}
