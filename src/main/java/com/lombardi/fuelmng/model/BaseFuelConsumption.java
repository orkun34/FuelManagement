package com.lombardi.fuelmng.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseFuelConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelConsumptionId;

    public Long getFuelConsumptionId() {
        return fuelConsumptionId;
    }

    public void setFuelConsumptionId(Long fuelConsumptionId) {
        this.fuelConsumptionId = fuelConsumptionId;
    }
}
