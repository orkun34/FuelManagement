package com.lombardi.fuelmng.model.internal;

import java.util.Date;

/**
 * In order to use for SqlResultSetMappings
 */
public interface IFuelConsumption {


    Double getPRICE();

    Double getVOLUME();

    Double getTOTAL_PRICE();

    Date getCONSUMPTION_DATE();

    String getMONTH();

    String getDRIVER_ID();

    String getFUEL_TYPE();

    Double getAVERAGE_PRICE();
}
