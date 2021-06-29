package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.UHC_Commando;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener {

    private UHC_Commando main;

    public WeatherEvent(UHC_Commando main) {
        this.main = main;
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        if(event.toWeatherState()) this.main.WORLD.setWeatherDuration(0);
    }
    
}
