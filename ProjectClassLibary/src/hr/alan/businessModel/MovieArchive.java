/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.businessModel;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alan
 */
@XmlRootElement(name = "moviearchive")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieArchive {
    
    @XmlElementWrapper
    @XmlElement(name = "moviecast")
    private List<MovieCast> movieCasts;

    public MovieArchive() {
    }

    public MovieArchive(List<MovieCast> movieCasts) {
        this.movieCasts = movieCasts;
    }

    public List<MovieCast> getMovieCasts() {
        return movieCasts;
    }
}
