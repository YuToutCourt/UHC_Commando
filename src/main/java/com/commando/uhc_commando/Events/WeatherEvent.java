package com.commando.uhc_commando.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener {

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        System.out.println(event.toWeatherState());
        if(event.toWeatherState()) event.setCancelled(true);
    }
    
}
