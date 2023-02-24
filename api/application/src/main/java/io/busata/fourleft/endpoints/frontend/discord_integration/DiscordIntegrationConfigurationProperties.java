package io.busata.fourleft.endpoints.frontend.discord_integration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "discord-integration")
@Getter
@Setter
public class DiscordIntegrationConfigurationProperties {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}