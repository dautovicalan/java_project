/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.parser;

import hr.alan.factory.UrlConnectionFactory;
import hr.alan.xmlModels.Channel;
import hr.alan.xmlModels.Item;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Alan
 */
public class RSSFeedParser {

    static final String CHANNEL = "channel";
    static final String TITLE = "title";
    static final String LINK = "link";
    static final String DESCRIPTION = "description";
    static final String LAST_BUILD_DATE = "lastBuildDate";
    static final String TTL = "ttl";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String ORIG_NAZIV = "orignaziv";
    static final String REDATELJ = "redatelj";
    static final String GLUMCI = "glumci";
    static final String TRAJANJE = "trajanje";
    static final String ZANR = "zanr";
    static final String PLAKAT = "plakat";
    static final String TRAILER = "trailer";
    static final String POCETAK = "pocetak";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String AUTHOR = "author";
    static final String GUID = "guid";

    final HttpURLConnection url;

    public RSSFeedParser(String feedUrl) throws IOException {
        try {
            this.url = UrlConnectionFactory.getHttpUrlConnection(feedUrl);            
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } 
    }

    public Channel readFeed() {
        Channel feedItems = null;
        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String lastBuildDate = "";
            String ttl = "";
            String origNaziv = "";
            String redatelj = "";
            String glumci = "";
            String trajanje = "";
            String zanr = "";
            String plakat = "";
            String trailer = "";
            String pocetak = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feedItems = new Channel(title, link, description, lastBuildDate, ttl);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case LAST_BUILD_DATE:
                            lastBuildDate = getCharacterData(event, eventReader);
                            break;
                        case "h2":
                            System.out.println(getCharacterData(event, eventReader));
                            break;
                        case "a":
                            System.out.println(getCharacterData(event, eventReader));
                            break;
                        case TTL:
                            ttl = getCharacterData(event, eventReader);
                            break;
                        case ORIG_NAZIV:
                            origNaziv = getCharacterData(event, eventReader);
                            break;
                        case REDATELJ:
                            redatelj = getCharacterData(event, eventReader);
                            break;
                        case GLUMCI:
                            glumci = getCharacterData(event, eventReader);
                            break;
                        case TRAJANJE:
                            trajanje = getCharacterData(event, eventReader);
                            break;
                        case ZANR:
                            trajanje = getCharacterData(event, eventReader);
                            break;
                        case PLAKAT:
                            plakat = getCharacterData(event, eventReader);
                            break;
                        case TRAILER:
                            trailer = getCharacterData(event, eventReader);
                            break;
                        case POCETAK:
                            pocetak = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        Item message = new Item();
                        message.setPubDate(pubdate);
                        message.setOrignaziv(origNaziv);
                        message.setRedatelj(redatelj);
                        message.setGlumci(glumci);
                        message.setZanr(zanr);
                        message.setTrailer(trailer);
                        message.setPlakat(plakat);
                        message.setTrajanje(trajanje);
                        message.setPocetak(pocetak);
                        message.setDescription(description);
                        message.setGuid(guid);
                        message.setLink(link);
                        message.setTitle(title);
                        feedItems.getItem().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feedItems;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.getInputStream();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
