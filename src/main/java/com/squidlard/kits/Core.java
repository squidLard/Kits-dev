package com.squidlard.kits;

import com.squidlard.kits.Board.ScoreHelper;
import com.squidlard.kits.Board.ScoreWorld;
import com.squidlard.kits.Board.Utils;
import com.squidlard.kits.Command.KitCommand;
import com.squidlard.kits.Command.StatsCommand;
import com.squidlard.kits.Events.*;
import com.squidlard.kits.Signs.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Dalton on 4/17/2017.
 */
public class Core extends JavaPlugin implements Listener{

    private HashMap<String, ScoreWorld> scoreWorlds;
    private boolean healthName;
    private boolean healthTab;

    public int mastercd  = 5;

    public static Plugin plugin;

    public static String prefix2 = "§bKits §7» ";

    public static String prefix = "§bStreak §7> ";

    public static Economy econ = null;

    public static Core instance;
    Logger log = Logger.getLogger("Minecraft");

    public void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new DeathEvent(), this);
        pm.registerEvents(new DiamondSign(), this);
        pm.registerEvents(new SignEvents(), this);
        pm.registerEvents(new MainEvents(), this);
        pm.registerEvents(new IronSign(), this);
        pm.registerEvents(new AntiBuildEvent(), this);
        pm.registerEvents(new dropEvent(), this);
        pm.registerEvents(new joinLeaveEvent(), this);
        pm.registerEvents(new killStreakEvent(), this);
        pm.registerEvents(new mobSpawnEvent(), this);
        pm.registerEvents(new pickupEvent(), this);
        pm.registerEvents(new respawnEvent(), this);
        pm.registerEvents(new weatherEvent(), this);
        pm.registerEvents(new GoldSign(), this);
        pm.registerEvents(new ArcherSign(), this);
        pm.registerEvents(new BuilderSign(), this);
        pm.registerEvents(this, this);

        getCommand("kscoreboard").setExecutor(this);
        getCommand("skit").setExecutor(new KitCommand());
        getCommand("Stats").setExecutor(new StatsCommand());
    }

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.log.info("===========================");
        this.log.info("[Stats] has been enabled!");
        this.log.info("       Version 1.0");
        this.log.info("===========================");
        plugin = this;
        instance = this;
        setupEconomy();
        registerListeners();
        saveConfig();
        loadConfig();

        saveDefaultConfig();

        loadScoreWorlds();

        long ticks = getConfig().getLong("Options.update-ticks");
        this.healthName = getConfig().getBoolean("Options.health-name");
        this.healthTab = getConfig().getBoolean("Options.health-tab");

        loadOnlinePlayers();

        new BukkitRunnable()
        {
            public void run()
            {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    Core.this.updatePlayer(player);
                }
            }
        }.runTaskTimer(this, ticks, ticks);
    }

    @Override
    public void onDisable() {
        this.log.info("===========================");
        this.log.info("[Stats] has been Disabled!");
        this.log.info("       Version 1.0");
        this.log.info("===========================");
        instance = null;
    }

    public void loadConfig() {
        FileConfiguration cfg = getConfig();
        cfg.options().copyDefaults(true);
        cfg.options().header("Player stats have been registered successfully.");

        saveConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 0)
        {
            sender.sendMessage(ChatColor.GREEN + "Usage: " + ChatColor.WHITE + "/" + label + " reload");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload"))
        {
            reloadConfig();

            loadScoreWorlds();
            for (Player player : Utils.getOnlinePlayers()) {
                updatePlayer(player);
            }
            sender.sendMessage(ChatColor.GREEN + "The configuration has been reloaded!");
            return true;
        }
        return true;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event)
    {
        registerPlayer(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event)
    {
        ScoreHelper.removePlayer(event.getPlayer());
    }

    @EventHandler
    private void onChangeWorld(PlayerChangedWorldEvent event)
    {
        updatePlayer(event.getPlayer());
    }

    private void loadScoreWorlds()
    {
        this.scoreWorlds = new HashMap();
        for (String world : getConfig().getConfigurationSection("Worlds").getKeys(false))
        {
            String title = getConfig().getString("Worlds." + world + ".title");
            List<String> lines = getConfig().getStringList("Worlds." + world + ".lines");
            this.scoreWorlds.put(world, new ScoreWorld(world, title, lines));
        }
    }

    private void loadOnlinePlayers()
    {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            registerPlayer(player);
        }
    }

    private void registerPlayer(Player player)
    {
        new ScoreHelper(player, this.healthName, this.healthTab);
        updatePlayer(player);
    }

    private void updatePlayer(Player player)
    {
        ScoreHelper helper = ScoreHelper.getByPlayer(player);
        if (this.scoreWorlds.containsKey(player.getWorld().getName()))
        {
            ScoreWorld score = (ScoreWorld)this.scoreWorlds.get(player.getWorld().getName());
            helper.setTitle(score.getTitle());
            helper.setSlotsFromList(score.getLines());
        }
        else
        {
            helper.setSlotsFromList(Utils.EMPTY_LIST);
        }
    }

}
