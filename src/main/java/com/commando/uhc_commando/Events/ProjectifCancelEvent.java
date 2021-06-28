package com.commando.uhc_commando.Events;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectifCancelEvent implements Listener {

    @EventHandler
    public void projectileHit(ProjectileLaunchEvent event){
        String projectile = event.getEntity().getType().toString();
        if(projectile.equals("EGG") || projectile.equals("SNOWBALL") || projectile.equals("FISHING_HOOK")){
            event.setCancelled(true);
        }
    }

    /*
    @EventHandler
    public void onArrow(EntityDamageByEntityEvent event) {
        if((event.getEntity() instanceof Player) && (event.getDamager() instanceof Projectile) && (((Projectile) event.getDamager()).getShooter() instanceof Player)) {
            Player victim = (Player) event.getEntity();
            Player attacker = ((Player) ((Projectile) event.getDamager()).getShooter());
            if (Team.getLeadingTeamOf(victim).equals(Team.getLeadingTeamOf(attacker)) || victim.equals(attacker) && !Team.friendlyFire){
                attacker.sendMessage("§cYou can't hit your teammate !");
                event.setCancelled(true);
                return;
            }   
    }
    */
    
}
