package com.services.datalayer;

import static org.assertj.core.api.Assertions.assertThat;

import com.services.datalayer.controller.MortgageController;
import com.services.datalayer.dto.AddUpdateMortgageDto;
import com.services.datalayer.model.Mortgage;
import com.services.datalayer.repository.MortgageRepository;
import com.services.datalayer.services.InsertMortgageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MortgageControllerUnitTest {

    @InjectMocks
    MortgageController mortgageController;

    @Mock
    MortgageRepository mortgageRepository;

    @Mock
    InsertMortgageService insertMortgageService;

    @Test
    public void testFindAll() throws ParseException {

        //given
        Mortgage m1 = new Mortgage("M1", 1, "OF-1", "P-1", new Date(), new Date() , false);
        Mortgage m2 = new Mortgage("M2", 1, "OF-1", "P-1", new Date() , new Date() , false);
        Mortgage [] mList = {m1,m2};
        when(mortgageRepository.getMortgageList()).thenReturn(mList);

        //when
        Mortgage [] fetchedList = mortgageController.findAll();

        // then
        assertThat(fetchedList.length).isEqualTo(2);
        assertThat(fetchedList[0].getMortgageId()).isEqualTo(m1.getMortgageId());
        assertThat(fetchedList[1].getVersion()).isEqualTo(m2.getVersion());

        // false case
        // assertThat(fetchedList[0].getMortgageId()).isEqualTo(m2.getMortgageId());

    }

    @Test
    public void testInsertMortgage()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mortgage m1 = new Mortgage("M1", 1, "OF-1", "P-1", new Date(), new Date() , false);
        when(insertMortgageService.insertOneMortgageRecord(any(Mortgage.class))).thenReturn(m1.getMortgageId());

        AddUpdateMortgageDto sample = new AddUpdateMortgageDto("M1",1,"OF2","P1",new Date(),new Date(),true);
        ResponseEntity<Map> responseEntity = mortgageController.insertMortgage(sample);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }


}
