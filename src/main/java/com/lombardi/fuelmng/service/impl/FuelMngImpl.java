package com.lombardi.fuelmng.service.impl;

import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.model.internal.IFuelConsumption;
import com.lombardi.fuelmng.model.internal.InnerFuelConsumption;
import com.lombardi.fuelmng.repo.FuelMngRepo;
import com.lombardi.fuelmng.service.intrface.IFuelMng;
import com.lombardi.fuelmng.util.SingletonHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FuelMngImpl implements IFuelMng {

    @Autowired
    FuelMngRepo fuelMngRepo;

    @Override
    public FuelConsumption insertConsumption(FuelConsumption fuelConsumption) {
        return fuelMngRepo.save(fuelConsumption);
    }

    @Override
    public List<FuelConsumption> bulkInsertConsumption(FuelConsumption[] fuelConsumptionList) {
        List<FuelConsumption> consumptionList = new ArrayList<>();
        for (FuelConsumption fuelConsumption : fuelConsumptionList) {
            consumptionList.add(fuelMngRepo.save(fuelConsumption));
        }
        return consumptionList;
    }

    @Override
    public Map<String, String> retrieveMonthlyExpenses(Optional<String> driverId) {
        Map<String, String> monthlyExpensesMap = new HashMap<>();

        List<Object[]> consumptionList = fuelMngRepo.monthlySpending(driverId);
        for (Object[] elementList : consumptionList) {
            monthlyExpensesMap.put(String.valueOf(elementList[0]), String.valueOf(elementList[1]));
        }

        return monthlyExpensesMap;
    }

    @Override
    public List<InnerFuelConsumption> retireveExpensesOfMonth(String month,Optional<String> driverId) {
        List<IFuelConsumption> iFuelConsumptionList = fuelMngRepo.retrieveByMonth(month,driverId);

        List<InnerFuelConsumption> innerFuelConsumptionList = new ArrayList<>();
        for (IFuelConsumption iFuelConsumption : iFuelConsumptionList) {
            InnerFuelConsumption innerFuelConsumption = new InnerFuelConsumption.InnerFuelConsumptionBuilder(iFuelConsumption.getFUEL_TYPE(), iFuelConsumption.getVOLUME(), iFuelConsumption.getTOTAL_PRICE()).setConsumptionDate(iFuelConsumption.getCONSUMPTION_DATE()).setDriverId(iFuelConsumption.getDRIVER_ID()).setPrice(iFuelConsumption.getPRICE()).build();
            innerFuelConsumptionList.add(innerFuelConsumption);
        }

        return innerFuelConsumptionList;

    }


    @Override
    public List<InnerFuelConsumption> monthlyStatistic(Optional<String> driverId) {
        List<IFuelConsumption> iFuelConsumptionList = fuelMngRepo.monthlyStatistic(driverId);
        List<InnerFuelConsumption> innerFuelConsumptionList = new ArrayList<>();
        for (IFuelConsumption iFuelConsumption : iFuelConsumptionList) {
            InnerFuelConsumption innerFuelConsumption = new InnerFuelConsumption.InnerFuelConsumptionBuilder(iFuelConsumption.getFUEL_TYPE(), iFuelConsumption.getVOLUME(), iFuelConsumption.getTOTAL_PRICE()).setAveragePrice(iFuelConsumption.getAVERAGE()).build();
            innerFuelConsumptionList.add(innerFuelConsumption);
        }
        return innerFuelConsumptionList;
    }



}
