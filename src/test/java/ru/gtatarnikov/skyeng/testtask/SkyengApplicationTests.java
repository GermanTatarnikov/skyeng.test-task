package ru.gtatarnikov.skyeng.testtask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gtatarnikov.skyeng.testtask.model.dto.MailingDto;
import ru.gtatarnikov.skyeng.testtask.model.enumeration.MailingType;
import ru.gtatarnikov.skyeng.testtask.rest.api.MailingController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gtatarnikov.skyeng.testtask.exception.MailingExceptionMessages.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SkyengApplicationTests {
    private static final String RECIPIENT_NAME = "Герман";
    private static final String RECIPIENT_INDEX = "420048";
    private static final String RECIPIENT_ADDRESS = "Восстания 36";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MailingController controller;

    @Test
    @Order(1)
    void registeringMailing_Success() throws Exception {
        MailingDto mailing = new MailingDto();
        mailing.setType(MailingType.POSTCARD);
        mailing.setRecipientName(RECIPIENT_NAME);
        mailing.setRecipientIndex(RECIPIENT_INDEX);
        mailing.setRecipientAddress(RECIPIENT_ADDRESS);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(mailing);

        this.mockMvc.perform(post("/mailing/register").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void mailingLeftWaypoint_MailingNotArrivedFirst() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/left_waypoint", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(MAILING_NOT_ARRIVED_FIRST));
    }

    @Test
    @Order(3)
    void mailingArrivedToWaypoint_Success() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/arriving_to/{postalOfficeId}", "1", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void mailingLeftWaypoint_Success() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/left_waypoint", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void mailingLeftWaypoint_MailingAlreadyLeftWaypoint() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/left_waypoint", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(MAILING_ALREADY_LEFT_WAYPOINT));
    }

    @Test
    @Order(6)
    void mailingReceiving_MailingNotInWaypoint() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/receiving", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(MAILING_NOT_IN_WAYPOINT));
    }

    @Test
    @Order(7)
    void mailingArrivedToAnotherWaypoint_Success() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/arriving_to/{postalOfficeId}", "1", "2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(8)
    void mailingArrivedToAnotherWaypoint_AlreadyInWaypoint() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/arriving_to/{postalOfficeId}", "1", "2"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(MAILING_ALREADY_IN_WAYPOINT));
    }

    @Test
    @Order(9)
    void mailingReceiving_Success() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/receiving", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(10)
    void mailingHistory_Success() throws Exception {
        this.mockMvc.perform(get("/mailing/{mailingId}/history", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(11)
    void mailingArrivedToWaypoint_MailingAlreadyReceived() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/arriving_to/{postalOfficeId}", "1", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(MAILING_ALREADY_RECEIVED));
    }

    @Test
    @Order(12)
    void mailingLeftWaypoint_MailingAlreadyReceived() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/left_waypoint", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(MAILING_ALREADY_RECEIVED));
    }

    @Test
    @Order(13)
    void mailingReceiving_MailingAlreadyReceived() throws Exception {
        this.mockMvc.perform(put("/mailing/{mailingId}/receiving", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(MAILING_ALREADY_RECEIVED));
    }

    @Test
    void registeringMailing_NotNullId() throws Exception {
        MailingDto mailing = new MailingDto();
        mailing.setType(MailingType.POSTCARD);
        mailing.setId(1L);
        mailing.setRecipientName(RECIPIENT_NAME);
        mailing.setRecipientIndex(RECIPIENT_INDEX);
        mailing.setRecipientAddress(RECIPIENT_ADDRESS);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(mailing);

        this.mockMvc.perform(post("/mailing/register").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(NOT_NULL_ID));
    }
}
