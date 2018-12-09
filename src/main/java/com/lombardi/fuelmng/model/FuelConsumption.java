package com.lombardi.fuelmng.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "FUEL_CONSUMPTION")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "mapMonthExpense",
                columns = {
                        @ColumnResult(name = "PRICE", type = Double.class),
                        @ColumnResult(name = "VOLUME", type = Double.class),
                        @ColumnResult(name = "TOTAL_PRICE", type = Double.class),
                        @ColumnResult(name = "CONSUMPTION_DATE", type = Date.class),
                        @ColumnResult(name = "DRIVER_ID", type = String.class),
                        @ColumnResult(name = "FUEL_TYPE", type = String.class)
                }),
        @SqlResultSetMapping(name = "mapMonthlyStatistic",
                columns = {
                        @ColumnResult(name = "FUEL_TYPE", type = String.class),
                        @ColumnResult(name = "VOLUME", type = Double.class),
                        @ColumnResult(name = "TOTAL_PRICE", type = Double.class),
                        @ColumnResult(name = "AVERAGE_PRICE", type = Double.class)
                })
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "FuelConsumption.retrieveByMonth",
                query = "select FUEL_TYPE,VOLUME,CONSUMPTION_DATE,PRICE,(PRICE*VOLUME) AS \"TOTAL_PRICE\",DRIVER_ID from FUEL_CONSUMPTION where MONTH(CONSUMPTION_DATE) = :month and (:driverId is NULL or DRIVER_ID=:driverId)",
                resultSetMapping = "mapMonthExpense"),
        @NamedNativeQuery(name = "FuelConsumption.monthlyStatistic",
                query = "SELECT FUEL_TYPE,VOLUME,SUM(PRICE*VOLUME) AS \"TOTAL_PRICE\",SUM(PRICE*VOLUME)/COUNT(*) AS \"AVERAGE_PRICE\" FROM FUEL_CONSUMPTION WHERE (:driverId is NULL or DRIVER_ID=:driverId) GROUP BY FUEL_TYPE,MONTH(CONSUMPTION_DATE);",
                resultSetMapping = "mapMonthlyStatistic"
        )})

public class FuelConsumption extends BaseFuelConsumption{

    @NotNull
    @Column(name = "FUEL_TYPE")
    private String fuelType;
    @NotNull
    @Column(name = "PRICE")
    private Double price;
    @NotNull
    @Column(name = "VOLUME")
    private Double volume;
    @NotNull
    @Column(name = "CONSUMPTION_DATE")
    private Date consumptionDate;
    @NotNull
    @Column(name = "DRIVER_ID")
    private String driverId;

    public FuelConsumption() {
    }

    public FuelConsumption(String fuelType, Double price, Double volume, Date consumptionDate, String driverId) {
        this.fuelType = fuelType;
        this.price = price;
        this.volume = volume;
        this.consumptionDate = consumptionDate;
        this.driverId = driverId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "FuelConsumption{" +
                "fuelConsumptionId=" + super.getFuelConsumptionId() +
                ", fuelType='" + fuelType + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", consumptionDate=" + consumptionDate +
                ", driverId='" + driverId + '\'' +
                '}';
    }
}
