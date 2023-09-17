package com.fletchplugins.customdeathevents;

import com.fletchplugins.customdeathevents.files.CustomConfigClass;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class DeathEventListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player=event.getEntity().getPlayer();
        String worldName=player.getWorld().getName();

        EntityDamageEvent damageEvent = player.getLastDamageCause();
        EntityDamageEvent.DamageCause lastDamageCause = damageEvent.getCause();

        String effectPath="DeathEvents."+worldName+"."+lastDamageCause;
        String effect="";
        if(CustomConfigClass.get().get(effectPath)!=null){
            effect= CustomConfigClass.get().get(effectPath).toString();
        }

        if(effect=="" || effect==null){
            effect= CustomConfigClass.get().get("DeathEvents."+worldName+".GENERIC").toString();
        }

        //player.getKiller()

        if(effect.equalsIgnoreCase("lightning")){
            player.getWorld().strikeLightningEffect(player.getLocation());
        }
        else if(effect.equalsIgnoreCase("firework")){
            Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            FireworkEffect.Type type= FireworkEffect.Type.BALL;
            Color c1 = Color.BLUE;
            Color c2 = Color.WHITE;
            FireworkEffect fireworkEffect = FireworkEffect.builder().flicker(true).withColor(c1).withColor(c2).with(type).trail(true).build();
            fwm.addEffect(fireworkEffect);
            fwm.setPower(0);
            fw.setFireworkMeta(fwm);
        }
        else if(effect.equalsIgnoreCase("explosion")){
            player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,player.getLocation(),1);
            //player.getWorld().play;
            player.getWorld().createExplosion(player.getLocation().getX(),player.getLocation().getY()-1,player.getLocation().getZ(),0);
        }
        else if(effect.equalsIgnoreCase("fire")){
            player.getWorld().spawnParticle(Particle.FLAME,player.getLocation(),200);
        }
        else if(effect.equalsIgnoreCase("dragon")){
            player.getWorld().playEffect(player.getLocation(),Effect.DRAGON_BREATH,0);
        }
    }
}
