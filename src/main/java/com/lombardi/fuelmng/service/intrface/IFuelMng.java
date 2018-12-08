package com.lombardi.fuelmng.service.intrface;

import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.model.internal.InnerFuelConsumption;

import java.util.List;
import java.util.Map;

public interface IFuelMng {

    FuelConsumption insertConsumption(FuelConsumption fuelConsumption);

    List<FuelConsumption> bulkInsertConsumption(FuelConsumption[] fuelConsumptionList);

    Map<String,String> retrieveMonthlyExpenses();

    List<InnerFuelConsumption> retrieveExpensesOfMonth(String month);

    List<InnerFuelConsumption> monthlyStatistic();

}

