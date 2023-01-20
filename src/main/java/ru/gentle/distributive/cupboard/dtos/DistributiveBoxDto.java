package ru.gentle.distributive.cupboard.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributiveBoxDto {
    private Long id;
    private String boxNumber;
    private String street;
    private int house;
    private int houseBlock;
    private int entrance;
    private String enterCode;
    private String description;
}
