package ru.gentle.distributive.cupboard.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gentle.distributive.cupboard.converters.DistributiveBoxConverter;
import ru.gentle.distributive.cupboard.dtos.DistributiveBoxDto;
import ru.gentle.distributive.cupboard.entities.DistributiveBox;
import ru.gentle.distributive.cupboard.exceptions.ResourceNotFoundException;
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

    public DistributiveBox saveBox(DistributiveBoxDto boxDto) {
        return boxRepository.save(boxConverter.dtoToEntity(boxDto));
    }

    @Transactional
    public DistributiveBox updateBox(DistributiveBoxDto boxDto) {
        DistributiveBox box = boxRepository.findById(boxDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Шкаф не найден с id " + boxDto.getId()));
        box.setId(box.getId());
        if (boxDto.getEnterCode() != null) {
            box.setEnterCode(boxDto.getEnterCode());
        }
        if (boxDto.getDescription() != null) {
            box.setDescription(box.getDescription() + "..." + boxDto.getDescription());
        }

        return boxRepository.save(box);
    }

    @Transactional
    public void deleteByNumber(String boxNumber) {
        boxRepository.deleteByBoxNumber(boxNumber);
    }
}
