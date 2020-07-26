package com.services.businesslayer;

import com.services.businesslayer.controller.InsertMortgageController;
import com.services.businesslayer.dto.InsertMortgageRequestDto;
import com.services.businesslayer.dto.MortgageHighestVersionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InsertMortgageControllerUnitTestCases {

    @InjectMocks
    InsertMortgageController insertMortgageController;

    private static final String root = "http://localhost:9001/";

    @Mock
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testInsertMortgageFailureOnVersionMatch() {

        //given

        MortgageHighestVersionDto mortgageHighestVersionDto = new MortgageHighestVersionDto("M1");
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("highest_version","2");

        Mockito.when(restTemplate.postForObject(root+"find/highestVersion",mortgageHighestVersionDto,Map.class)).thenReturn(resultMap);

        //when

        InsertMortgageRequestDto insertMortgageRequestDto = new InsertMortgageRequestDto("M1",2,"OF1","P1",new Date());
        ResponseEntity<Map> responseEntity = insertMortgageController.insertMortgage(insertMortgageRequestDto);

        //then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(406);

    }

}
