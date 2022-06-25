/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.xmlModels;

import java.util.Date;

/**
 *
 * @author Alan
 */
public class Item {
    public String title;
    public String pubDate;
    public String description;
    public String orignaziv;
    public String redatelj;
    public String glumci;
    public String trajanje;
    public String zanr;
    public String plakat;
    public String link;

    
    public String guid;
    public String trailer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrignaziv() {
        return orignaziv;
    }

    public void setOrignaziv(String orignaziv) {
        this.orignaziv = orignaziv;
    }

    public String getRedatelj() {
        return redatelj;
    }

    public void setRedatelj(String redatelj) {
        this.redatelj = redatelj;
    }

    public String getGlumci() {
        return glumci;
    }

    public void setGlumci(String glumci) {
        this.glumci = glumci;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getPlakat() {
        return plakat;
    }

    public void setPlakat(String plakat) {
        this.plakat = plakat;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }
    public String pocetak;
    
    @Override
    public String toString() {
        return "Item{" + "title=" + title + ", pubDate=" + pubDate + ", description=" + description + ", orignaziv=" + orignaziv + ", redatelj=" + redatelj + ", glumci=" + glumci + ", trajanje=" + trajanje + ", zanr=" + zanr + ", plakat=" + plakat + ", link=" + link + ", guid=" + guid + ", trailer=" + trailer + ", pocetak=" + pocetak + '}';
    }
}
