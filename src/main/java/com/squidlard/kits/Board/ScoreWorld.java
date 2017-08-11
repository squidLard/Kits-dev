package com.squidlard.kits.Board;

import java.util.ArrayList;
import java.util.List;

public class ScoreWorld
{
    private String world;
    private String title;
    private List<String> lines;

    public ScoreWorld(String world, String title, List<String> lines)
    {
        this.world = world;
        this.title = title;
        if (lines.size() <= 15)
        {
            this.lines = lines;
        }
        else
        {
            this.lines = new ArrayList();
            for (int i = 0; i < 15; i++) {
                this.lines.add((String)lines.get(i));
            }
        }
    }

    public String getWorld()
    {
        return this.world;
    }

    public String getTitle()
    {
        return this.title;
    }

    public List<String> getLines()
    {
        return this.lines;
    }
}
