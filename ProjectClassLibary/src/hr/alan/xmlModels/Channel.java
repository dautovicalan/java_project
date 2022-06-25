/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.xmlModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alan
 */
public class Channel {
    public String title;
    public String link;
    public String description;

    public Channel(String title, String link, String description, String lastBuildDate, String ttl) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.lastBuildDate = lastBuildDate;
        this.ttl = ttl;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public List<Item> getItem() {
        return item;
    }
    
    public String lastBuildDate;
    public String ttl;
    public final List<Item> item = new ArrayList<>();

    @Override
    public String toString() {
        return "Channel{" + "title=" + title + ", link=" + link + ", description=" + description + ", lastBuildDate=" + lastBuildDate + ", ttl=" + ttl + ", item=" + item + '}';
    }
}
