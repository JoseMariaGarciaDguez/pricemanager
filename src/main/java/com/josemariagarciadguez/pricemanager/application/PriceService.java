package com.josemariagarciadguez.pricemanager.application;


import com.josemariagarciadguez.pricemanager.infrastructure.dto.PriceDTO;
import com.josemariagarciadguez.pricemanager.infrastructure.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;
    private final ModelMapper modelMapper;


    public Optional<PriceDTO> getApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return priceRepository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        productId, brandId, applicationDate, applicationDate)
                .map(price -> modelMapper.map(price, PriceDTO.class));
    }
}
