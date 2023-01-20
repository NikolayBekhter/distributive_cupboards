package ru.gentle.distributive.cupboard.converters;

import ru.gentle.distributive.cupboard.dtos.DistributiveBoxDto;
import ru.gentle.distributive.cupboard.entities.DistributiveBox;

public class DistributiveBoxConverter {
    public DistributiveBoxDto entityToDto(DistributiveBox box) {
        return new DistributiveBoxDto(
                box.getId(),
                box.getBoxNumber(),
                box.getStreet(),
                box.getHouse(),
                box.getHouseBlock(),
                box.getEntrance(),
                box.getEnterCode(),
                box.getDescription()
        );
    }

    public DistributiveBox dtoToEntity(DistributiveBoxDto boxDto) {
        DistributiveBox box = new DistributiveBox();
        box.setId(boxDto.getId());
        box.setBoxNumber(boxDto.getBoxNumber());
        box.setStreet(boxDto.getStreet());
        box.setHouse(boxDto.getHouse());
        box.setHouseBlock(boxDto.getHouseBlock());
        box.setEntrance(boxDto.getEntrance());
        box.setEnterCode(boxDto.getEnterCode());
        box.setDescription(boxDto.getDescription());
        return box;
    }
}
