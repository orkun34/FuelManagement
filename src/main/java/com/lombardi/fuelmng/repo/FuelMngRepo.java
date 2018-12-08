package com.lombardi.fuelmng.repo;

import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.model.internal.IFuelConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelMngRepo extends JpaRepository<FuelConsumption,Long> {

    @Query(value="SELECT MONTH(consumption_date) as \"MONTH\", SUM(price*volume) as \"TOTAL\" FROM fuel_consumption Group By MONTH(consumption_date)",nativeQuery = true)
    List<Object[]> retrieveMonthlyExpenses();

    @Query(value="SELECT FUEL_TYPE,VOLUME,CONSUMPTION_DATE,PRICE,(PRICE*VOLUME) AS \"TOTAL_PRICE\",DRIVER_ID from fuel_consumption where MONTH(consumption_date)=:month",nativeQuery = true)
    List<Object[]> retrieveExpensesOfMonth(@Param("month") String month);

    List<IFuelConsumption> retrieveByMonth(@Param("month") String month);

    List<IFuelConsumption> monthlyStatistic();

}
