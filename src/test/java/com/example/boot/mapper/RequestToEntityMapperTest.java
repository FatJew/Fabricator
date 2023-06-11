package com.example.boot.mapper;

import com.example.boot.model.Printer;
import com.example.boot.model.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class RequestToEntityMapperTest {

    @InjectMocks
    private RequestToEntityMapper mapper;

    @Test
    void getEntityFromRequest() {
        Request request = Request.builder()
                .name("name")
                .information("information")
                .year("year")
                .build();
        assertEquals(mapper.getEntityFromRequest(request, "printer").getInstance(), Printer.class.getSimpleName().toLowerCase());
    }
}