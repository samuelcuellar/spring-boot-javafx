package com.skynet.javafx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class PropertiesConfig {

	@Value("${javafx.main.tree}")
	private Boolean javafxMainTree;

	@Value("${javafx.main.toolbar}")
	private Boolean javafxMainToolbar;
}
