package io.github.sefiraat.charmtech;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import io.github.sefiraat.charmtech.commands.Commands;
import io.github.sefiraat.charmtech.listeners.RightClickListener;
import io.github.sefiraat.charmtech.timers.InventoryCheck;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Timer;

import static co.aikar.commands.PaperCommandManager.*;

public class CharmTech extends JavaPlugin {

    private CharmTech instance;

    private CommandManager commandManager;
    private final Timer repeater = new Timer();

    private InventoryCheck inventoryCheckTask;

    public CommandManager getCommandManager() {
        return commandManager;
    }
    public CharmTech getInstance() {
        return instance;
    }



    @Override
    public void onEnable() {

        getLogger().info("########################################");
        getLogger().info("");
        getLogger().info("               Charm Tech               ");
        getLogger().info("            Version 1.0.0.R1            ");
        getLogger().info("");
        getLogger().info("          Created by Sefiraat           ");
        getLogger().info("");
        getLogger().info("########################################");

        instance = this;

        saveDefaultConfig();
        registerCommands();

        inventoryCheckTask = new InventoryCheck(this.instance);
        inventoryCheckTask.runTaskTimer(this.instance, 0, 100L);

        new RightClickListener(this.instance);

        int pluginId = 11209;
        Metrics metrics = new Metrics(this, pluginId);

    }

    private void registerCommands() {

        commandManager = new PaperCommandManager(this.getInstance());
        commandManager.registerCommand(new Commands(this.getInstance()));

        commandManager.getCommandCompletions().registerCompletion("PotEff", c -> {
            return ImmutableList.of(
                    "ABSORPTION",
                    "BAD_OMEN",
                    "BLINDNESS",
                    "CONDUIT_POWER",
                    "CONFUSION",
                    "DAMAGE_RESISTANCE",
                    "DOLPHINS_GRACE",
                    "FAST_DIGGING",
                    "FIRE_RESISTANCE",
                    "GLOWING",
                    "HARM",
                    "HEAL",
                    "HEALTH_BOOST",
                    "HERO_OF_THE_VILLAGE",
                    "HUNGER",
                    "INCREASE_DAMAGE",
                    "INVISIBILITY",
                    "JUMP",
                    "LEVITATION",
                    "LUCK",
                    "NIGHT_VISION",
                    "POISON",
                    "REGENERATION",
                    "SATURATION",
                    "SLOW",
                    "SLOW_DIGGING",
                    "SLOW_FALLING",
                    "SPEED",
                    "UNLUCK",
                    "WATER_BREATHING",
                    "WEAKNESS",
                    "WITHER"
            );
        });

        commandManager.getCommandCompletions().registerCompletion("EffReq", c -> {
            return ImmutableList.of(
                "ALL",
                "MAIN_HAND",
                "OFF_HAND",
                "ARMOUR_ALL",
                "ARMOUR_HELM",
                "ARMOUR_CHEST",
                "ARMOUR_LEGS",
                "ARMOUR_FEET"
            );
        });

    }

    public void setUpSchedules() {
        Bukkit.getScheduler().runTaskTimer(instance, new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("This message is shown immediately and then repeated every second");
            }
        }, 0L, 200L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }


}
