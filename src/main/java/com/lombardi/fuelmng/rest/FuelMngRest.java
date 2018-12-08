package com.lombardi.fuelmng.rest;


import com.lombardi.fuelmng.common.exception.FileParsingException;
import com.lombardi.fuelmng.common.exception.JsonParsingException;
import com.lombardi.fuelmng.common.exception.MissingElementException;
import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.model.internal.InnerFuelConsumption;
import com.lombardi.fuelmng.service.intrface.IFuelMng;
import com.lombardi.fuelmng.util.SingletonHolder;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
public class FuelMngRest {


    private static final Logger logger = LoggerFactory.getLogger(FuelMngRest.class);

    @Autowired
    IFuelMng fuelMng;

    @GetMapping(path="/monthlyStatistic")
    @ResponseBody
    public ResponseEntity<InnerFuelConsumption> retrieveExpensesOfMonth() throws JsonParsingException{
        List<InnerFuelConsumption> innerFuelConsumptionList = fuelMng.monthlyStatistic();
        return new ResponseEntity(innerFuelConsumptionList,HttpStatus.OK);

    }

    @GetMapping(path="/monthlyExpenses/{month}")
    @ResponseBody
    public ResponseEntity<InnerFuelConsumption> retrieveExpensesOfMonth(@PathVariable("month") String month) throws JsonParsingException{
        List<InnerFuelConsumption> innerFuelConsumptionList = fuelMng.retrieveExpensesOfMonth(month);
        return new ResponseEntity(innerFuelConsumptionList,HttpStatus.OK);

    }

    @GetMapping(path="/monthlyExpenses")
    @ResponseBody
    public ResponseEntity retrieveMonthlyExpenses() throws JsonParsingException{
        Map<String,String> consumptionList = fuelMng.retrieveMonthlyExpenses();
        return new ResponseEntity(generateMonthlyExpensesResponse(consumptionList),HttpStatus.OK);
    }


    @PostMapping(path = "/register",produces="application/json")
    @ResponseBody
    public String insertConsumption(@RequestBody String request) {


        logger.info("Rest request just received");

        FuelConsumption result;
        try {

            final FuelConsumption fuelConsumption = SingletonHolder.getInstance().getGson().fromJson(request, FuelConsumption.class);
            logger.info(fuelConsumption.toString());
            result = fuelMng.insertConsumption(fuelConsumption);
            return new ResponseEntity<>(result, HttpStatus.CREATED).toString();
        } catch (Exception ex) {
            logger.error("Exception occurred {]", ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR).toString();
        }

    }

    @PostMapping(path = "/bulkRegister")
    @ResponseBody
    public ResponseEntity<FuelConsumption> bulkInsertConsumption(@RequestParam("file") MultipartFile file) {

        logger.info("Bulk request just received");

        List<FuelConsumption> fuelConsumptionList;
        String convertedFile;

        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
            convertedFile = IOUtils.toString(stream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileParsingException("File parsing error");
        }
        FuelConsumption[] consumptionList = SingletonHolder.getInstance().getGson().fromJson(convertedFile, FuelConsumption[].class);

        if (consumptionList[0].getFuelType() == null) {
            throw new MissingElementException("Missing elements in file");
        }
        fuelConsumptionList = fuelMng.bulkInsertConsumption(consumptionList);

        return new ResponseEntity(fuelConsumptionList, HttpStatus.CREATED);

    }

    public String generateMonthlyExpensesResponse(Map<String,String> monthlyExpensesResult) throws JsonParsingException {

        JSONArray monthlyExpensesArray = new JSONArray();

        try {
            for (Map.Entry<String,String> entry : monthlyExpensesResult.entrySet()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("month",entry.getKey());
                jsonObject.put("expense",entry.getValue());
                monthlyExpensesArray.put(jsonObject);
            }
        }catch(Exception ex){
            throw new JsonParsingException("Object could not be parsed");
        }

        return monthlyExpensesArray.toString();
    }
}
