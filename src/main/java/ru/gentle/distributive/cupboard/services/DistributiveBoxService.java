package ru.gentle.distributive.cupboard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gentle.distributive.cupboard.converters.DistributiveBoxConverter;
import ru.gentle.distributive.cupboard.dtos.DistributiveBoxDto;
import ru.gentle.distributive.cupboard.entities.DistributiveBox;
import ru.gentle.distributive.cupboard.repositories.DistributiveBoxRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistributiveBoxService {
    private final DistributiveBoxRepository boxRepository;
    private final DistributiveBoxConverter boxConverter;

    public Optional<DistributiveBox> findByBoxNumber(String boxNumber) {
        return boxRepository.findByBoxNumber(boxNumber);
    }

    public DistributiveBox saveOrUpdateBox(DistributiveBoxDto boxDto) {
        return boxRepository.save(boxConverter.dtoToEntity(boxDto));
    }
}
