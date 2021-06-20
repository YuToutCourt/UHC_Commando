package com.commando.uhc_commando.Teams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team {

    public static List<String> teamsName;
    public static List<String> teamsPrefix;
    public static List<String> teamsColorCode;
    public static boolean friendlyFire;
    public static boolean chatEnable;
    public static List<Team> teams;

    private final int id;
    private final String name;
    private final String prefix;
    private ChatColor color;
    private Set<UUID> players;

    public Team(int id, String name, String prefix, String colorCode) {
        players = new HashSet<UUID>();
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.color = ChatColor.getByChar(colorCode.charAt(1));
    }

    public boolean join(UUID playerId) {
        Team.leaveAll(playerId);
        Player player = Bukkit.getPlayer(playerId);
        player.setCustomName(this.color + "[" + this.prefix +"] " + player.getName());
        player.setCustomNameVisible(true);
        return this.players.add(playerId);
    }

    public boolean leave(UUID player) {
        Bukkit.getPlayer(player).setCustomNameVisible(false);
        return this.players.remove(player);
    }

    public void empty() {
        this.players.clear();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
        return this.prefix;
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

    public static void leaveAll(UUID player) {
        for(Team team : Team.teams) {
            team.leave(player);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return prime * result + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Team other = (Team) obj;
        if (id != other.id) return false;
        return true;
    }
}
