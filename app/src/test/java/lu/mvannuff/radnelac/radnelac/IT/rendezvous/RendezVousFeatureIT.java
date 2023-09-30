package lu.mvannuff.radnelac.radnelac.IT.rendezvous;

import lu.mvannuff.radnelac.generated.model.RendezVousConfirmation;
import lu.mvannuff.radnelac.generated.model.RendezVousForm;
import lu.mvannuff.radnelac.generated.model.RendezVousListItem;
import lu.mvannuff.radnelac.radnelac.IT.BaseIT;
import lu.mvannuff.radnelac.radnelac.domain.entity.RdvConfirmationStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RendezVousFeatureIT extends BaseIT {


    @Test
    @WithMockUser(roles = {"SERVICER"})
    void unknownClient_validate_ok() throws Exception {
        mvc.perform(get("/number").param("num", "123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("HONOURED"));
    }

    @Test
    @WithMockUser(roles = {"SERVICER"})
    void unknownClient_registerRdv_ok() throws Exception {
        RendezVousForm rendezVousForm = new RendezVousForm().clientName("client name").date(OffsetDateTime.now().plusDays(4))
                .prepayment(1000L)
                .note("super note").phone("+32 485/23.99.28");
        var res = mvc.perform(post("/rendez-vous")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(rendezVousForm)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        RendezVousListItem rdv = objectMapper.readValue(res, RendezVousListItem.class);
        var client = clientRepository.findByNumberAndServicer("32485239928", authenticatedUser.getUserId()).orElseThrow();
        Assertions.assertEquals(client.getName(), "client name");

        var rdvEntity = rendezVousRepository.findByIdAndServicerId(rdv.getId(), authenticatedUser.getUserId()).get();
        Assertions.assertEquals(rdvEntity.getConfirmationStatus(), RdvConfirmationStatus.PENDING_CONFIRMATION);

        mvc.perform(get("/public/rendez-vous/" + rdvEntity.getPublicId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prepayment").value("1000"));

        RendezVousConfirmation rendezVousConfirmation = new RendezVousConfirmation()
                .accept(true)
                .publicId(rdvEntity.getPublicId())
                .note("I'm ok bitch");


        mvc.perform(post("/public/rendez-vous/confirmation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(rendezVousConfirmation)))
                .andExpect(status().isOk());

        rdvEntity = rendezVousRepository.findByIdAndServicerId(rdv.getId(), authenticatedUser.getUserId()).get();

        Assertions.assertEquals(rdvEntity.getConfirmationStatus(), RdvConfirmationStatus.CONFIRMED);

    }
}
