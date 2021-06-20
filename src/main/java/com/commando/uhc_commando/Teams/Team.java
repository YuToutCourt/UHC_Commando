package com.commando.uhc_commando.Teams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        this.prefix = prefix.replace("&", "§");
        this.color = ChatColor.getByChar(colorCode.charAt(1));
    }

    public boolean join(Player player) {
        Team.leaveAll(player);
        player.setCustomName(this.color + "[" + this.prefix +"] " + player.getName());
        player.setCustomNameVisible(true);
        return this.players.add(player.getUniqueId());
    }

    public boolean leave(Player player) {
        player.setCustomNameVisible(false);
        return this.players.remove(player.getUniqueId());
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

    public static void leaveAll(Player player) {
        for(Team team : Team.teams) {
            team.leave(player);
        }
    }

    public static Team getTeamOf(Player player) {
        UUID uuid = player.getUniqueId();
        for(Team team : Team.teams) {
            if(team.players.contains(uuid)) return team;
        }
        return null;
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
