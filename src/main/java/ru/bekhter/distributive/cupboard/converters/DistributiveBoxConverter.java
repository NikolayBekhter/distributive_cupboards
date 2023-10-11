package ru.bekhter.distributive.cupboard.converters;

import org.springframework.stereotype.Service;
import ru.bekhter.distributive.cupboard.dtos.DistributiveBoxDto;
import ru.bekhter.distributive.cupboard.entities.DistributiveBox;

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
        box.setStreet(boxDto.getStreet());
        box.setBoxNumber(boxDto.getBoxNumber());
        box.setHouseBlock(boxDto.getHouseBlock());
        box.setHouse(boxDto.getHouse());
        box.setEntrance(boxDto.getEntrance());
        box.setEnterCode(boxDto.getEnterCode());
        box.setDescription(boxDto.getDescription());
        box.setAuthor(boxDto.getAuthor());
        return box;
    }
}
