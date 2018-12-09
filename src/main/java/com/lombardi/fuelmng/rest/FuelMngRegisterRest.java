package com.lombardi.fuelmng.rest;


import com.lombardi.fuelmng.aop.AspectLogger;
import com.lombardi.fuelmng.common.exception.FileParsingException;
import com.lombardi.fuelmng.common.exception.InternalException;
import com.lombardi.fuelmng.common.exception.InvalidMonthException;
import com.lombardi.fuelmng.common.exception.JsonParsingException;
import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.model.internal.InnerFuelConsumption;
import com.lombardi.fuelmng.rest.util.common.ResponseGenerator;
import com.lombardi.fuelmng.service.intrface.IFuelMng;
import com.lombardi.fuelmng.util.SingletonHolder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
public class FuelMngRegisterRest {

    private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);

    @Autowired
    IFuelMng fuelMng;

    @PostMapping(produces = "application/json")
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity<?> bulkInsertConsumption(@Valid @RequestParam("file") MultipartFile file) {

        List<FuelConsumption> fuelConsumptionList;
        String convertedFile;

        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
            convertedFile = IOUtils.toString(stream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileParsingException("File parsing error");
        }
        FuelConsumption[] consumptionList = SingletonHolder.getInstance().getGson().fromJson(convertedFile, FuelConsumption[].class);

        fuelConsumptionList = fuelMng.bulkInsertConsumption(consumptionList);

        return new ResponseEntity<>(SingletonHolder.getInstance().getGson().toJson(fuelConsumptionList),HttpStatus.CREATED);

    }
}
