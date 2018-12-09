package com.lombardi.fuelmng.repo;

import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.model.internal.IFuelConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuelMngRepo extends JpaRepository<FuelConsumption,Long> {

    @Query(value="SELECT MONTH(consumption_date) as \"MONTH\", SUM(price*volume) as \"TOTAL_PRICE\" FROM fuel_consumption where (:driverId is NULL or DRIVER_ID=:driverId) Group By MONTH(consumption_date)",nativeQuery = true)
    List<Object[]> monthlySpending(@Param("driverId") Optional<String> driverId);

    List<IFuelConsumption> retrieveByMonth(@Param("month") String month,@Param("driverId") Optional<String> driverId);

    List<IFuelConsumption> monthlyStatistic(@Param("driverId") Optional<String> driverId);

}
