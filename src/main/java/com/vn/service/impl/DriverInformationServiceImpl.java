package com.vn.service.impl;

import com.vn.entities.DriverInformation;
import com.vn.repository.DriverInformationRepository;
import com.vn.service.DriverInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverInformationServiceImpl implements DriverInformationService {
    private final DriverInformationRepository repository;
    @Override
    public void save(DriverInformation driver) {
        repository.save(driver);
    }
}
