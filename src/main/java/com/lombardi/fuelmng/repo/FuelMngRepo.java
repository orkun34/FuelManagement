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
    ;
    @Query(value="SELECT MONTH(CONSUMPTION_DATE),SUM(PRICE*VOLUME) FROM FUEL_CONSUMPTION where (:driverId is NULL or DRIVER_ID=:driverId) GROUP BY MONTH(CONSUMPTION_DATE)",nativeQuery = true)
    List<Object[]> monthlySpending(@Param("driverId") Optional<String> driverId);

    List<IFuelConsumption> retrieveByMonth(@Param("month") String month,@Param("driverId") Optional<String> driverId);

    List<IFuelConsumption> monthlyStatistic(@Param("driverId") Optional<String> driverId);

}
