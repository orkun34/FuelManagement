package com.lombardi.fuelmng.service.intrface;

import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.model.internal.InnerFuelConsumption;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IFuelMng {

    FuelConsumption insertConsumption(FuelConsumption fuelConsumption);

    List<FuelConsumption> bulkInsertConsumption(FuelConsumption[] fuelConsumptionList);

    Map<String,String> retrieveMonthlyExpenses(Optional<String> driverId);

    List<InnerFuelConsumption> retireveExpensesOfMonth(String month,Optional<String> driverId);


    List<InnerFuelConsumption> monthlyStatistic(Optional<String> driverId);

}

