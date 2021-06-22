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
    public static int leadingTeams;

    private final String name;
    private final String prefix;
    private ChatColor color;
    private UUID leader;
    private Team owner;
    private Set<Team> ownedTeams;

    public Team(String name, String prefix, String colorCode) {
        ownedTeams = new HashSet<Team>();
        this.name = name;
        this.prefix = prefix.replace("&", "§");
        this.color = ChatColor.getByChar(colorCode.charAt(1));
        this.owner = null;
        this.leader = null;
    }

    public boolean join(Team teamToJoin) {
        this.leaveAll();
        this.setPlayersName(teamToJoin.color + "[" + teamToJoin.prefix +"]");
        this.owner = teamToJoin;
        leadingTeams --;
        return teamToJoin.ownedTeams.add(this);
    }

    private boolean leave(Team teamToLeave) {
        if(this.ownedTeams.contains(this)) {
            this.removePlayersName();
            teamToLeave.ownedTeams.remove(this);
            leadingTeams ++;
            return true;
        }
        return false;
    }

    private void leaveAll() {
        for(Team t : Team.teams) {
            t.leave(this);
        }
        this.owner = null;
    }

    private void setPlayersName(String prefix) {
        Player player = Bukkit.getPlayer(this.leader);
        player.setCustomName(prefix + " " + player.getName());
        player.setCustomNameVisible(true);
        for(Team team : this.ownedTeams) {
            team.setPlayersName(prefix);
        }
    }

    private void removePlayersName() {
        Player player = Bukkit.getPlayer(this.leader);
        player.setCustomNameVisible(true);
        for(Team team : this.ownedTeams) {
            team.removePlayersName();
        }
    }

    public int getPlayerAmount() {
        int size = 1;
        for(Team team : this.ownedTeams) {
            size += team.getPlayerAmount();
        }
        return size;
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

    public Player getLeader() {
        return Bukkit.getPlayer(this.leader);
    }

    public void setLeader(Player player) {
        this.leader = player.getUniqueId();
    }

    public Team getOwner() {
        return this.owner;
    }

    public Set<Team> getOwnTeams() {
        return this.ownedTeams;
    }

    public static Team getTeamOf(Player player) {
        UUID uuid = player.getUniqueId();
        for(Team team : Team.teams) {
            if(team.leader.equals(uuid)) return team;
        }
        return null;
    }

    public static Team getWinner() {
        Team winner = Team.teams.get(0);
        while(winner.getOwner() != null) {
            winner = winner.getOwner();
        }
        return winner;
    }

    public static boolean isPlayerLeading(Player owner, Player target) {
        Team team = getTeamOf(target);
        while(team.getOwner() != null) {
            team = team.getOwner();
            if(team.leader.equals(owner.getUniqueId())) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((leader == null) ? 0 : leader.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Team other = (Team) obj;
        if (leader == null) {
            if (other.leader != null) return false;
        } else if (!leader.equals(other.leader))
            return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name))
            return false;
        if (prefix == null) {
            if (other.prefix != null) return false;
        } else if (!prefix.equals(other.prefix))
            return false;
        return true;
    }
}