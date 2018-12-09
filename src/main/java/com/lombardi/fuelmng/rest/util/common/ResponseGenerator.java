package com.lombardi.fuelmng.rest.util.common;

import com.lombardi.fuelmng.common.exception.JsonParsingException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public final class ResponseGenerator {

    public static String generateMonthlyExpensesResponse(Map<String, String> monthlyExpensesResult) throws JsonParsingException {

        JSONArray monthlyExpensesArray = new JSONArray();

        try {
            for (Map.Entry<String, String> entry : monthlyExpensesResult.entrySet()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("month", entry.getKey());
                jsonObject.put("expense", entry.getValue());
                monthlyExpensesArray.put(jsonObject);
            }
        } catch (Exception ex) {
            throw new JsonParsingException("Object could not be parsed");
        }

        return monthlyExpensesArray.toString();
    }
}
