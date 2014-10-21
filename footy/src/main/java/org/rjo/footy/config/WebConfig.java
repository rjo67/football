package org.rjo.footy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = { "org.rjo.footy.web.controller" })
public class WebConfig {

}
