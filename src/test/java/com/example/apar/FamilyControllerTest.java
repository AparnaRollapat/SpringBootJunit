package com.example.apar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.apar.Family;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FamilyController.class)
public class FamilyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FamilyService familyService;

    Family mockFamily = new Family("4", "Uma", "My mother");

    @Test
    public void getFamily() throws Exception {

        Mockito.when(
                familyService.getFamily(
                        Mockito.anyString())).thenReturn(mockFamily);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/families/4").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"id\": \"4\",\"name\":\"Uma\",\"description\":\"My mother\"}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void addFamily() throws Exception {
        Family mockFamily1 = new Family("4", "Uma", "My mother");

        // familyService.addFamily to respond back with mockFamily
        Mockito.when(
                familyService.addFamily(Mockito.any(Family.class))).thenReturn(mockFamily1);

        String expected = "{\"id\": \"4\",\"name\":\"Uma\",\"description\":\"My mother\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/families")
                .accept(MediaType.APPLICATION_JSON).content(expected)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

}

