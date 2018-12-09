package com.lombardi.fuelmng.rest;

import com.lombardi.fuelmng.aop.AspectLogger;
import com.lombardi.fuelmng.common.exception.InvalidMonthException;
import com.lombardi.fuelmng.common.exception.JsonParsingException;
import com.lombardi.fuelmng.model.internal.InnerFuelConsumption;
import com.lombardi.fuelmng.rest.util.common.ResponseGenerator;
import com.lombardi.fuelmng.service.intrface.IFuelMng;
import com.lombardi.fuelmng.util.SingletonHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class FuelMngInfoRest {

    private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);

    @Autowired
    IFuelMng fuelMng;

    @GetMapping(path = {"/monthlyStatistic", "/monthlyStatistic/{id}"}, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> retireveMonthlyStatistic(@PathVariable Optional<String> id) {
        List<InnerFuelConsumption> innerFuelConsumptionList = fuelMng.monthlyStatistic(id);
        return new ResponseEntity<>(SingletonHolder.getInstance().getGson().toJson(innerFuelConsumptionList), HttpStatus.OK);
    }

    @GetMapping(path = {"/monthlyConsumption/{month}","/monthlyConsumption/{month}/{id}"}, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> retrieveExpensesOfMonth(@PathVariable("month") String month, @PathVariable("id") Optional<String> driverId) throws InvalidMonthException {
        Integer monthValue;
        try {
            monthValue = Integer.valueOf(month);
            if (!(monthValue > 0 && monthValue < 13)) {
                throw new InvalidMonthException("Not valid month");
            }
        } catch (NumberFormatException ex) {
            logger.error("Invalid month value {}", ex);
            throw new InvalidMonthException("Not valid integer");
        }
        List<InnerFuelConsumption> innerFuelConsumptionList = fuelMng.retireveExpensesOfMonth(String.valueOf(monthValue), driverId);
        return new ResponseEntity<>(SingletonHolder.getInstance().getGson().toJson(innerFuelConsumptionList), HttpStatus.OK);

    }

    @GetMapping(path = {"monthlyExpenses","monthlyExpenses/{id}"}, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> retrieveMonthlyExpenses(@PathVariable("id") Optional<String> driverId) throws JsonParsingException {
        Map<String, String> consumptionList = fuelMng.retrieveMonthlyExpenses(driverId);
        return new ResponseEntity<>(ResponseGenerator.generateMonthlyExpensesResponse(consumptionList), HttpStatus.OK);
    }

}
