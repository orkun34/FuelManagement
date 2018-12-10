package com.lombardi.fuelmng.rest;


import com.google.gson.JsonParseException;
import com.lombardi.fuelmng.aop.AspectLogger;
import com.lombardi.fuelmng.common.exception.FileParsingException;
import com.lombardi.fuelmng.common.exception.InternalException;
import com.lombardi.fuelmng.common.exception.JsonParsingException;
import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.service.intrface.IFuelMng;
import com.lombardi.fuelmng.util.SingletonHolder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Endpoint for registration of consumption
 */

@RestController
public class FuelMngRegisterRest {

    private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);

    @Autowired
    IFuelMng fuelMng;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> insertConsumption(@RequestBody String request) throws InternalException {


        FuelConsumption result;
        try {

            final FuelConsumption fuelConsumption = SingletonHolder.getInstance().getGson().fromJson(request, FuelConsumption.class);

            result = fuelMng.insertConsumption(fuelConsumption);

            return new ResponseEntity<>(SingletonHolder.getInstance().getGson().toJson(result),HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error("Invalid registration", ex);
            throw new InternalException(ex.getLocalizedMessage());
        }

    }

    @PostMapping(path = "/bulkRegister", produces = "application/json")
    public ResponseEntity<?> bulkInsertConsumption(@Valid @RequestParam("file") MultipartFile file) throws JsonParsingException,FileParsingException,InternalException{

        List<FuelConsumption> fuelConsumptionList;
        String convertedFile;
        FuelConsumption[] consumptionList;
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
            convertedFile = IOUtils.toString(stream, StandardCharsets.UTF_8);
            consumptionList = SingletonHolder.getInstance().getGson().fromJson(convertedFile, FuelConsumption[].class);
            fuelConsumptionList = fuelMng.bulkInsertConsumption(consumptionList);
        }catch (IOException e) {
            throw new FileParsingException("File parsing error");
        }catch(JsonParseException jex){
            throw new JsonParsingException("Invalid JSON format");
        }catch(Exception ex){
            throw new InternalException(ex.getLocalizedMessage());
        }


        return new ResponseEntity<>(SingletonHolder.getInstance().getGson().toJson(fuelConsumptionList),HttpStatus.CREATED);

    }
}
