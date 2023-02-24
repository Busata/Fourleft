package io.busata.fourleft.endpoints.frontend.discord_integration;

public record DiscordGuildTo(
    String id,
    String name,
    String icon,
    boolean owner,
    int permissions) {
}