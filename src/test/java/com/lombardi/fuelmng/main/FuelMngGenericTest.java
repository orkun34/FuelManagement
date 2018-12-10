package com.lombardi.fuelmng.main;


import com.lombardi.fuelmng.model.FuelConsumption;
import com.lombardi.fuelmng.repo.FuelMngRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FuelMngGenericTest {

    @LocalServerPort
    private int port;

    @Autowired
    FuelMngRepo fuelMngRepo;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();


    /**
     * Testing insertion
     * @throws ParseException
     */
    @Before
    public void setUp() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse("2018-01-01");

        fuelMngRepo.save(new FuelConsumption("Diesel", 23.3, 12.2, parsedDate, "234DD"));
    }

    /**
     * Testing retrieving inserted data by JPA
     */
    @Test
    public void isDataPersisted() {
        FuelConsumption fuelConsumption = fuelMngRepo.findById(1L).get();
        assertThat(fuelConsumption, notNullValue());
    }

    /**
     * Testing valid response of registration
     * @throws Exception
     */
    @Test
    public void registrationTest() throws Exception {

        HttpEntity<String> entity = new HttpEntity<>("{\"fuelType\":\"Diesel\",\"fuelType\":\"Diesel\",\"driverId\":\"234KK\",\"consumptionDate\":\"2018-02-21\",\"price\":10.3,\"volume\":77.2}", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/register"),
                HttpMethod.POST, entity, String.class);

        String expected = "{\"fuelConsumptionId\":8,\"fuelType\":\"Diesel\",\"driverId\":\"234KK\",\"consumptionDate\":\"Feb 21, 2018 12:00:00 AM\",\"price\":10.3,\"volume\":77.2}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    /**
     * Testing valid response of monthlyExpenses
     * @throws Exception
     */
    @Test
    public void monthlExpensesTest() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/monthlyExpenses/66YU"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"month\":\"12\",\"expense\":\"49.28\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }




    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}