package ru.bekhter.distributive.cupboard.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "distributive_boxes")
public class DistributiveBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "box_number")
    private String boxNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "house_block")
    private String houseBlock;

    @Column(name = "entrance")
    private String entrance;

    @Column(name = "enter_code")
    private String enterCode;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;
}
