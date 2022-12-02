package com.dammenhayn.blueprint.web.rest

import com.dammenhayn.blueprint.SpringBootBlueprintApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

@SpringBootTest(classes = [SpringBootBlueprintApplication])
@TestPropertySource(properties = ["authentication.testumgebung.basicAuth.user=user",
    "authentication.testumgebung.basicAuth.password=password"])
class TestRestControllerIntegrationTest extends Specification {

  MockMvc mockMvc

  @Autowired
  WebApplicationContext context

  def setup() {
    mockMvc = webAppContextSetup(context)
        .apply(springSecurity())
        .build()
  }

  def "Calling test endpoint with correct basic auth gives 200"() {
    given:
    def basicAuthentication = Base64.encoder.encode("user:password".bytes)

    expect:
    mockMvc.perform(get("/test")
                        .header("Authorization", "Basic ${new String(basicAuthentication)}"))
        .andExpect(status().is2xxSuccessful())
  }
}
