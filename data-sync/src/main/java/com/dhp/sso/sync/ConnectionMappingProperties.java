package com.dhp.member.identify;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app-group-mapping")
public class AppGroupMappingProperties {
	private final Map<String, String> oktaGroups = new HashMap<>();

	public Map<String, String> getOktaGroups() {
		return oktaGroups;
	}
}
