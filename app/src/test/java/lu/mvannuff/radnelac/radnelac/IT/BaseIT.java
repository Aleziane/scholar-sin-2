package lu.mvannuff.radnelac.radnelac.IT;


import com.fasterxml.jackson.databind.ObjectMapper;
import lu.mvannuff.radnelac.radnelac.domain.repository.ClientRepository;
import lu.mvannuff.radnelac.radnelac.domain.repository.RendezVousRepository;
import lu.mvannuff.radnelac.radnelac.security.JwtUserDetailsService;
import lu.mvannuff.radnelac.radnelac.security.model.ExtendedUserDetails;
import lu.mvannuff.radnelac.radnelac.security.service.AuthUserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIT {

    public static String TEST_USER = "testUser";

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @MockBean
    private AuthUserService authUserService;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected RendezVousRepository rendezVousRepository;

    protected MockMvc mvc;
    protected ExtendedUserDetails authenticatedUser;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        rendezVousRepository.deleteAll();
        clientRepository.deleteAll();

        authenticatedUser = (ExtendedUserDetails) userDetailsService.loadUserByUsername(TEST_USER);
        when(authUserService.getAuthenticatedUser()).thenReturn(authenticatedUser);
    }
}
