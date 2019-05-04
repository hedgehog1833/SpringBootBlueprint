package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
class IndexControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testShowIndex() throws Exception {
    mockMvc.perform(get(Endpoints.SLASH_INDEX))
            .andExpect(status().isOk())
            .andExpect(view().name(ViewNames.INDEX))
            .andExpect(content().string(containsString("Home")));
  }
}