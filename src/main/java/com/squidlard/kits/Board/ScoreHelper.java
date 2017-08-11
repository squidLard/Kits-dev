package com.squidlard.kits.Board;

import com.squidlard.kits.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dalton on 7/31/2017.
 */
public class ScoreHelper {

    private static HashMap<UUID, ScoreHelper> players = new HashMap();
    private Player player;
    private Scoreboard scoreboard;
    private Objective sidebar;

    public static ScoreHelper getByPlayer(Player player) {
        return (ScoreHelper)players.get(player.getUniqueId());
    }

    public static void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

    public ScoreHelper(Player player, boolean healthname, boolean healthtab) {
        this.player = player;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.sidebar = this.scoreboard.registerNewObjective("sidebar", "dummy");
        this.sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(this.scoreboard);
        for (int i = 1; i <= 15; i++) {
            Team team = this.scoreboard.registerNewTeam("SLOT_" + i);
            team.addEntry(genEntry(i));
        }
        if (healthname) {
            Objective hname = this.scoreboard.registerNewObjective("hname", "health");
            hname.setDisplaySlot(DisplaySlot.BELOW_NAME);
            hname.setDisplayName(ChatColor.DARK_RED + "❤");
        }
        if (healthtab) {
            Objective hTab = this.scoreboard.registerNewObjective("htab", "health");
            hTab.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        }
        players.put(player.getUniqueId(), this);
    }

    public void setTitle(String title)
    {
        title = ChatColor.translateAlternateColorCodes('&', title);
        this.sidebar.setDisplayName(title.length() > 32 ? title.substring(0, 32) : title);
    }

    public ArrayList<String> onlinestaff = new ArrayList<String>();

    public void setSlot(int slot, String text)
    {
        Team team = this.scoreboard.getTeam("SLOT_" + slot);
        String entry = genEntry(slot);
        if (!this.scoreboard.getEntries().contains(entry)) {
            this.sidebar.getScore(entry).setScore(slot);
        }

        String balance = Core.econ.format(Core.econ.getBalance(player.getName())).replace("$", "");
        double newBalance = Double.parseDouble(balance);
        int finalBalance = (int)newBalance;
        String bal = Integer.toString(finalBalance);

        int deaths2 = Core.getInstance().getConfig().getInt("Stats." + player.getUniqueId() + ".Deaths");
        int kills2 = Core.getInstance().getConfig().getInt("Stats." + player.getUniqueId() + ".Kills");
        String deaths = Integer.toString(deaths2);
        String kills = Integer.toString(kills2);
        text = ChatColor.translateAlternateColorCodes('&', text).replace("%kills%", kills).replace("%deaths%", deaths).replace("%bal%", bal);
        String pre = getFirstSplit(text);
        String suf = getFirstSplit(ChatColor.getLastColors(pre) + getSecondSplit(text));
        team.setPrefix(pre);
        team.setSuffix(suf);
    }

    public void removeSlot(int slot)
    {
        String entry = genEntry(slot);
        if (this.scoreboard.getEntries().contains(entry)) {
            this.scoreboard.resetScores(entry);
        }
    }

    public void setSlotsFromList(List<String> list)
    {
        int slot = list.size();
        if (slot < 15) {
            for (int i = slot + 1; i <= 15; i++) {
                removeSlot(i);
            }
        }
        for (String line : list)
        {
            setSlot(slot, line);
            slot--;
        }
    }

    private String genEntry(int slot)
    {
        return ChatColor.values()[slot].toString();
    }

    private String getFirstSplit(String s)
    {
        return s.length() > 16 ? s.substring(0, 16) : s;
    }

    private String getSecondSplit(String s)
    {
        if (s.length() > 32) {
            s = s.substring(0, 32);
        }
        return s.length() > 16 ? s.substring(16, s.length()) : "";
    }

}
