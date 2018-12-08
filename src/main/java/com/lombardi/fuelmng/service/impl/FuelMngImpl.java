package com.lombardi.fuelmng.service.impl;

import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.model.internal.IFuelConsumption;
import com.lombardi.fuelmng.model.internal.InnerFuelConsumption;
import com.lombardi.fuelmng.repo.FuelMngRepo;
import com.lombardi.fuelmng.service.intrface.IFuelMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FuelMngImpl implements IFuelMng {

    @Autowired
    FuelMngRepo repo;

    @Override
    public FuelConsumption insertConsumption(FuelConsumption fuelConsumption) {
        return repo.save(fuelConsumption);
    }

    @Override
    public List<FuelConsumption> bulkInsertConsumption(FuelConsumption[] fuelConsumptionList) {
        List<FuelConsumption> consumptionList = new ArrayList<>();
        for (FuelConsumption fuelConsumption : fuelConsumptionList) {
            consumptionList.add(repo.save(fuelConsumption));
        }
        return consumptionList;
    }

    public Map<String, String> retrieveMonthlyExpenses() {
        Map<String, String> monthlyExpensesMap = new HashMap<>();

        List<Object[]> consumptionList = repo.retrieveMonthlyExpenses();
        for (Object[] elementList : consumptionList) {
            monthlyExpensesMap.put(String.valueOf(elementList[0]), String.valueOf(elementList[1]));
        }

        return monthlyExpensesMap;
    }

    @Override
    public List<InnerFuelConsumption> retrieveExpensesOfMonth(String month) {
        List<IFuelConsumption> iFuelConsumptionList = repo.retrieveByMonth(month);

        List<InnerFuelConsumption> innerFuelConsumptionList = new ArrayList<>();
        for (IFuelConsumption iFuelConsumption : iFuelConsumptionList) {
            InnerFuelConsumption innerFuelConsumption = new InnerFuelConsumption.InnerFuelConsumptionBuilder(iFuelConsumption.getFUEL_TYPE(), iFuelConsumption.getVOLUME(), iFuelConsumption.getTOTAL_PRICE()).setConsumptionDate(iFuelConsumption.getCONSUMPTION_DATE()).setDriverId(iFuelConsumption.getDRIVER_ID()).setPrice(iFuelConsumption.getPRICE()).build();
            innerFuelConsumptionList.add(innerFuelConsumption);
        }

        return innerFuelConsumptionList;

    }

    @Override
    public List<InnerFuelConsumption> monthlyStatistic() {
        List<IFuelConsumption> iFuelConsumptionList = repo.monthlyStatistic();
        List<InnerFuelConsumption> innerFuelConsumptionList = new ArrayList<>();
        for (IFuelConsumption iFuelConsumption : iFuelConsumptionList) {
            InnerFuelConsumption innerFuelConsumption = new InnerFuelConsumption.InnerFuelConsumptionBuilder(iFuelConsumption.getFUEL_TYPE(), iFuelConsumption.getVOLUME(), iFuelConsumption.getTOTAL_PRICE()).setAveragePrice(iFuelConsumption.getAVERAGE()).build();
            innerFuelConsumptionList.add(innerFuelConsumption);
        }
        return innerFuelConsumptionList;
    }

}
