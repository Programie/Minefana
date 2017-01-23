package com.github.games647.minefana;

import com.github.games647.minefana.common.InfluxConnector;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinefanaBukkit extends JavaPlugin {

    private InfluxConnector influxConnector;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        String dbUrl = getConfig().getString("db_url");
        String dbName = getConfig().getString("db_name");
        String dbUser = getConfig().getString("db_user");
        String dbPass = getConfig().getString("db_pass");

        influxConnector = new InfluxConnector(dbUrl, dbName, dbUser, dbPass);
        influxConnector.init();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public InfluxConnector getInfluxConnector() {
        return influxConnector;
    }

    @Override
    public void onDisable() {
        if (influxConnector != null) {
            influxConnector.close();
        }
    }
}