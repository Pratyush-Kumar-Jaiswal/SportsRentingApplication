package com.gl.app.entity;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Equipment {
    int equipmentId;
    String type;
    String brand;
    String model;
    public double rentAmount;
    String dateAvailability;

    public Equipment(String type, String brand, String model, double rentAmount,String availability) {
        super();
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.dateAvailability = availability;
    }

}
