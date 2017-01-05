package by.dorohovich.site.logic.parsing;


import by.dorohovich.site.entityGem.Gem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Created by User on 17.11.2016.
 */
public class GemsSAXBuilder implements AbstractGemsParser {

    static final Logger LOG= LogManager.getLogger();
    private Set<Gem> gems;
    private GemHandler gh;
    private XMLReader reader;

    public GemsSAXBuilder() {
        gh = new GemHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(gh);
        } catch (SAXException e) {
            LOG.error(e);
        }
    }

    public Set<Gem> getGems() {
        return gems;
    }

    public void buildSetGems(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            LOG.error(e);
        } catch (IOException e) {
            LOG.error(e);
        }
        gems = gh.getGems();
    }
}

