package com.lombardi.fuelmng.model.internal;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class InnerFuelConsumption {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fuelType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String driverId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date consumptionDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double volume;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double totalPrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double averagePrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String consumptionMonth;

    private InnerFuelConsumption(InnerFuelConsumptionBuilder builder) {
        this.fuelType = builder.fuelType;
        this.driverId = builder.driverId;
        this.averagePrice = builder.averagePrice;
        this.totalPrice = builder.totalPrice;
        this.volume = builder.volume;
        this.price = builder.price;
        this.consumptionDate = builder.consumptionDate;
        this.consumptionMonth =builder.consumptionMonth;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getDriverId() {
        return driverId;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public Double getPrice() {
        return price;
    }

    public Double getVolume() {
        return volume;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public String getConsumptionMonth(){ return consumptionMonth;}

    public static class InnerFuelConsumptionBuilder {

        private String fuelType;
        private String driverId;
        private Date consumptionDate;
        private Double price;
        private Double volume;
        private Double totalPrice;
        private Double averagePrice;
        private String consumptionMonth;

        public InnerFuelConsumptionBuilder(String fuelType, Double volume, Double totalPrice) {
            this.fuelType = fuelType;
            this.volume = volume;
            this.totalPrice = totalPrice;
        }

        public InnerFuelConsumptionBuilder setDriverId(String driverId) {
            this.driverId = driverId;
            return this;
        }

        public InnerFuelConsumptionBuilder setConsumptionDate(Date consumptionDate){
            this.consumptionDate = consumptionDate;
            return this;
        }

        public InnerFuelConsumptionBuilder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public InnerFuelConsumptionBuilder setAveragePrice(Double averagePrice) {
            this.averagePrice = averagePrice;
            return this;
        }

        public InnerFuelConsumptionBuilder setConsumptionMonth(String consumptionMonth) {
            this.consumptionMonth = consumptionMonth;
            return this;
        }

        public InnerFuelConsumption build() {
            return new InnerFuelConsumption(this);
        }
    }
}
