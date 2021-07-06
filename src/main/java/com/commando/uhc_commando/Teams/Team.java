package com.commando.uhc_commando.Teams;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team {

    public static List<String> teamsName = new ArrayList<String>();
    public static List<String> teamsPrefix = new ArrayList<String>();
    public static List<String> teamsColorCode = new ArrayList<String>();
    public static boolean chatEnable;
    public static List<Team> teams = new ArrayList<Team>();

    private final String name;
    private final String symbol;
    private ChatColor color;
    private UUID leader;
    private Team owner;
    private Set<Team> ownedTeams;

    public Team(String name, String symbol, String colorCode) {
        ownedTeams = new HashSet<Team>();
        this.name = name;
        this.symbol = symbol.replace("&", "§");
        this.color = ChatColor.valueOf(colorCode.toUpperCase());
        this.owner = null;
        this.leader = null;
    }

    public boolean join(Team teamToJoin,ChatColor teamColor) {
        this.leaveAll();
        this.setTeamMembersName(teamColor + "[" + teamToJoin.symbol +"]");
        this.owner = teamToJoin;
        return teamToJoin.ownedTeams.add(this);
    }

    private boolean leave(Team teamToLeave) {
        if(this.ownedTeams.contains(this)) {
            teamToLeave.ownedTeams.remove(this);
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

    public void setPlayerName(String prefix) {
        Player player = this.getLeader();
        player.setDisplayName(prefix + " " + player.getName());
        player.setPlayerListName(prefix + " " + player.getName());
    }

    private void setTeamMembersName(String prefix) {
        this.setPlayerName(prefix);
        for(Team team : this.ownedTeams) {
            team.setTeamMembersName(prefix);
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

    public String getSymbol() {
        return this.symbol;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public Player getLeader() {
        return Bukkit.getPlayer(this.leader);
    }

    public void setLeader(Player player) {
        this.leader = player.getUniqueId();
        this.setTeamMembersName(this.color + "[" + this.symbol +"]");
    }

    public Team getOwner() {
        return this.owner;
    }

    /**
     * @return a Set of team that you controle
     */
    public Set<Team> getOwnTeams() {
        return this.ownedTeams;
    }

    public static int getPlayerAmountInTeam(Team teams){
        int size = 1;
        for(Team team : teams.ownedTeams) {
            size += team.getPlayerAmount();
        }
        return size;
    }

    public static Team getTeamOf(Player player) {
        UUID uuid = player.getUniqueId();
        for(Team team : Team.teams) {
            if(team.leader == null) continue;
            if(team.leader.equals(uuid)) return team;
        }
        return null;
    }

    public static Team getLeadingTeamOf(Player player) {
        Team team = getTeamOf(player);
        if(team == null) return null;
        while(team.getOwner() != null) {
            team = team.getOwner();
        }
        return team;
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

    
    public static int getLeadingTeamsAmount() {
        int res = 0;
        for(Team team : Team.teams) {
            if(team.owner == null && team.leader != null) res ++;
        }
        return res;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((leader == null) ? 0 : leader.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
        if (symbol == null) {
            if (other.symbol != null) return false;
        } else if (!symbol.equals(other.symbol))
            return false;
        return true;
    }
}