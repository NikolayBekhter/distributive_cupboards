package ru.gentle.distributive.cupboard.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gentle.distributive.cupboard.converters.DistributiveBoxConverter;
import ru.gentle.distributive.cupboard.dtos.DistributiveBoxDto;
import ru.gentle.distributive.cupboard.exceptions.ResourceNotFoundException;
import ru.gentle.distributive.cupboard.services.DistributiveBoxService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/box")
public class DistributiveBoxController {
    private final DistributiveBoxService boxService;
    private final DistributiveBoxConverter boxConverter;

    @GetMapping("/{boxNumber}")
    public DistributiveBoxDto findByBoxNumber(@PathVariable String boxNumber) {
        return boxConverter.entityToDto(boxService.findByBoxNumber(boxNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Распределительный шкаф с номером: " + boxNumber + " не найден!")));
    }

    @PostMapping
    public DistributiveBoxDto saveOrUpdateBox(@RequestBody DistributiveBoxDto boxDto) {
        return boxConverter.entityToDto(boxService.saveBox(boxDto));
    }

    @PostMapping("/update")
    public DistributiveBoxDto updateBox(@RequestBody DistributiveBoxDto boxDto) {
        return boxConverter.entityToDto(boxService.updateBox(boxDto));
    }
}
