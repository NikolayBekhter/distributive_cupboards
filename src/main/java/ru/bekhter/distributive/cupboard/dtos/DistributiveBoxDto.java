package ru.bekhter.distributive.cupboard.dtos;


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
    private String house;
    private String houseBlock;
    private String entrance;
    private String enterCode;
    private String description;
    private String author;
}
