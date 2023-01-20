package ru.gentle.distributive.cupboard.entities;

import jakarta.persistence.*;
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
    private int house;

    @Column(name = "house_block")
    private int houseBlock;

    @Column(name = "entrance")
    private int entrance;

    @Column(name = "enter_code")
    private String enterCode;

    @Column(name = "description")
    private String description;
}