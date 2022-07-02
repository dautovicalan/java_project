/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.businessModel;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Alan
 */
public class PubDateAdapter extends XmlAdapter<String, LocalDate>{

    @Override
    public LocalDate unmarshal(String text) throws Exception {
        return LocalDate.parse(text, Movie.POCETAK_FILMA_FORMATTER);
    }

    @Override
    public String marshal(LocalDate date) throws Exception {
        return date.format(Movie.POCETAK_FILMA_FORMATTER);
    }
    
}
