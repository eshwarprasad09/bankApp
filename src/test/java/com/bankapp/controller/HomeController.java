package com.bankapp.controller;

import com.bankapp.model.User;
import com.bankapp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value=HomeController.class,secure=false)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testOpenAccount() throws Exception{

        User mockUser = new User();
        mockUser.setName("John");
        mockUser.setEmail("john@gmail.com");
        mockUser.setPassword("123456");
        mockUser.setBalance(1000L);
        mockUser.setAccountNumber("11258963");

        String inputInJson= this.mapToJson(mockUser);

        String URI="/openaccount";
        Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(mockUser);
        RequestBuilder requestBuilder= MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=result.getResponse();

        String outputInJson=response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());


    }

    @Test
    public void testMoneyTransfer() throws Exception{


    }

}


