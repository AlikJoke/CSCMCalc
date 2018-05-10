package ru.project.cscm.calc.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = { "classpath:application.properties" })
public class PropertiesImpl implements Properties {

	@Autowired
	private Environment env;

	@Override
	public String getProperty(@NotNullOrEmpty final String name) {
		return env.getProperty(name);
	}
}
