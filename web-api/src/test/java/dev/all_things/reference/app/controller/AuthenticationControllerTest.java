package dev.all_things.reference.app.controller;

import config.security.SecurityConfiguration;
import dev.all_things.reference.common.i18n.InternationalizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = { AuthenticationController.class , InternationalizationService.class, SecurityConfiguration.class })
public class AuthenticationControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void whenSayHello_thenReturnsHello() throws Exception
	{
		this.mockMvc.perform(get("/authentications/hello"))
					.andExpect(status().isOk())
					.andExpect(content().string(equalTo("Hello")));
	}
}