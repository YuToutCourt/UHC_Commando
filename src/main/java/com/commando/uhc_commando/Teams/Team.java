package com.commando.uhc_commando.Teams;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;

public class Team {

    public static boolean randomTeams;
    public static boolean friendFire;
    public static boolean chat;
    public static int timeBeforeSend;
    public static Set<Team> teams;
    
    private int id;
    private String name;
    private ChatColor color;
    private Set<UUID> players;

    public Team(int id, String name, String colorCode) {
        players = new HashSet<UUID>();
        this.id = id;
        this.name = name;
        this.color = ChatColor.getByChar(colorCode.charAt(1));
    }

    public boolean append(UUID player) {
        return this.players.add(player);
    }

    public boolean remove(UUID player) {
        return this.players.remove(player);
    }

    public void empty() {
        this.players.clear();
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setColor(String colorCode) {
        this.color = ChatColor.getByChar(colorCode.charAt(1));
    }

    public ChatColor getColor() {
        return this.color;
    }

    public Set<UUID> getPlayers() {
        return this.players;
    }
}
