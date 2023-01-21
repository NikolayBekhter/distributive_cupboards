package ru.gentle.distributive.cupboard.converters;

import org.springframework.stereotype.Service;
import ru.gentle.distributive.cupboard.dtos.DistributiveBoxDto;
import ru.gentle.distributive.cupboard.entities.DistributiveBox;

@Service
public class DistributiveBoxConverter {
    public DistributiveBoxDto entityToDto(DistributiveBox box) {
        DistributiveBoxDto boxDto = new DistributiveBoxDto();
        boxDto.setId(box.getId());
        boxDto.setBoxNumber(box.getBoxNumber());
        boxDto.setStreet(box.getStreet());
        boxDto.setHouse(box.getHouse());
        boxDto.setHouseBlock(box.getHouseBlock());
        boxDto.setEntrance(box.getEntrance());
        boxDto.setEnterCode(box.getEnterCode());
        boxDto.setDescription(box.getDescription());
        boxDto.setAuthor(box.getAuthor());

        return boxDto;
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
        box.setAuthor(boxDto.getAuthor());
        return box;
    }
}
