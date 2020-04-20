package com.soft.srms;

import com.soft.srms.controller.DashboardController;
import com.soft.srms.controller.HomeController;
import com.soft.srms.controller.RegistrationController;
import com.soft.srms.repository.UserRepository;
import com.soft.srms.service.ApplicationService;
import com.soft.srms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SrmsApplicationTests {

	/**
	 * @Mock, @Spy, @Captor, @InjectMocks
	 * Given(Arrange), When(Act), Then(Assert)
	 *
	 */

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private HomeController homeController;

	@Autowired
	private DashboardController dashboardController;

	@Autowired
	private RegistrationController registrationController;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationService applicationService;


	/**
	 * Provjera da li se context aplikacije moze pokrenut
	 * @throws Exception
	 */
	@Test
	public void contextLoads() throws Exception {
		assertThat(homeController).isNotNull();
		assertThat(dashboardController).isNotNull();
		assertThat(registrationController).isNotNull();
	}

	/**
	 * Listener osluškuje konekciju, šalje se HTTP zahtjev i potvrđuje response
	 * @throws Exception
	 */
	@Test
	public void testPort() throws Exception {
		assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/", String.class))
				.contains("/");
	}

	@Test
	public void loadHome() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Studentski dom")));
	}

	@Test
	public void loadRegistration() throws Exception{
		this.mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("registration")));
	}

	@Test
	public void loadLogin() throws Exception{
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("login")));
	}













}
