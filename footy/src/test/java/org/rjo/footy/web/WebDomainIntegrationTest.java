package org.rjo.footy.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rjo.footy.config.CoreConfig;
import org.rjo.footy.config.PersistenceConfig;
import org.rjo.footy.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class, CoreConfig.class, PersistenceConfig.class })
public class WebDomainIntegrationTest {

   private MockMvc mockMvc;

   @Autowired
   WebApplicationContext webApplicationContext;

   @Before
   public void setup() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }

   @Test
   public void thatCorrectModelReturned() throws Exception {
      mockMvc.perform(get("/")).andDo(print()).andExpect(model().attributeExists("games"));
   }

}
