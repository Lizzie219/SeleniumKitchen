package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {
	private static Config INSTANCE;
	private final Properties properties = new Properties();

	private Config() {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				throw new RuntimeException("Unable to find config.properties");
			}
			properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}
	}

	public static Config getConfig() {
		if (INSTANCE == null) {
			INSTANCE = new Config();
		}
		return INSTANCE;
	}

	public String getUrl() {
		return properties.getProperty("url");
	}

	public String getEmail() {
		return properties.getProperty("email");
	}

	public String getPassword() {
		return properties.getProperty("password");
	}

	public AbstractMap.SimpleEntry<String, String> getValuesForRecipeSearching() {
		return new AbstractMap.SimpleEntry<>(properties.getProperty("recipeToSearchFor"), properties.getProperty("recipeToSearchForResult"));
	}

	public Map<String, String> getStaticPages() {
		return getMapProperty("staticPages");
	}

	private Map<String, String> getMapProperty(String key) {
		Map<String, String> map = new HashMap<>();
		String value = properties.getProperty(key);
		if (value != null && !value.isEmpty()) {
			String[] entries = value.split(",");
			for (String entry : entries) {
				String[] pair = entry.split(";", 2);
				if (pair.length == 2) {
					map.put(pair[0].trim(), pair[1].trim());
				}
			}
		}
		return map;
	}
}
