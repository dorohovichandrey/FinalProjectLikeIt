package by.dorohovich.site.logic.parsing;


import by.dorohovich.site.entityGem.*;

import by.dorohovich.site.exception.ParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 19.11.2016.
 */
public class GemsStAXBuilder implements AbstractGemsParser {

    static final Logger LOG= LogManager.getLogger();
    private Set<Gem> gems = new HashSet<Gem>();
    private XMLInputFactory inputFactory;

    public GemsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Gem> getGems() {
        return gems;
    }

    public void buildSetGems(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (GemEnum.parse(name) == GemEnum.PRECIOUS_GEM || GemEnum.parse(name)==GemEnum.SEMIPRECIOUS_GEM) {
                        Gem gem = buildGem(reader, GemEnum.parse(name));
                        gems.add(gem);
                    }

                }
            }
        } catch (XMLStreamException ex) {
            LOG.error(ex);
        } catch (ParsingException ex) {
            LOG.error(ex);
        } catch (FileNotFoundException ex) {
            LOG.error(ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error(e);
            }
        }
    }

    private Gem buildGem(XMLStreamReader reader, GemEnum gemType) throws ParsingException, XMLStreamException {
        Gem gem;
        if(gemType==GemEnum.PRECIOUS_GEM)
        {
            gem = new PreciousGem();
        }
        else
        {
            gem = new SemipreciousGem();
            String rarity=reader.getAttributeValue(null, GemEnum.RARITY.getValue());
            if(rarity!=null)
            {
                ((SemipreciousGem)gem).setRarity(rarity);
            }
            else
            {
                ((SemipreciousGem)gem).setRarity(SemipreciousGem.DEFAULT_RARITY);
            }
        }

        gem.setGemId(reader.getAttributeValue(null, GemEnum.GEM_ID.getValue()));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.parse(name)) {
                        case NAME:
                            gem.setName(getXMLText(reader));
                            break;
                        case ORIGIN:
                            gem.setOrigin(getXMLOrigin(reader));
                            break;
                        case VISUAL_PARAMETERS:
                            gem.setVisualParameters(getXMLVisualParameters(reader));
                            break;
                        case VALUE_IN_CARAT:
                            gem.setValueInCarat(Integer.parseInt(getXMLText(reader)));
                            break;
                        case SELLING_ONLY_ON_AUCTION:
                            ((PreciousGem)gem).setSellingOnlyOnAuction(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.parse(name) == GemEnum.PRECIOUS_GEM || GemEnum.parse(name)==GemEnum.SEMIPRECIOUS_GEM) {
                        return gem;
                    }
                    break;
            }
        }
        throw new ParsingException();
    }

    private Origin getXMLOrigin(XMLStreamReader reader) throws ParsingException, XMLStreamException {
        Origin origin=new Origin();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.parse(name)) {
                        case COUNTRY:
                            origin.setCountry(getXMLText(reader));
                            break;
                        case CITY:
                            origin.setCity(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.parse(name) == GemEnum.ORIGIN){
                        return origin;
                    }
                    break;
            }
        }
        throw new ParsingException();
    }

    private VisualParameters getXMLVisualParameters(XMLStreamReader reader) throws ParsingException, XMLStreamException {
        VisualParameters visualParameters=new VisualParameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.parse(name)) {
                        case COLOUR:
                            visualParameters.setColour(getXMLText(reader));
                            break;
                        case TRANSPARENCY_IN_PERCENTS:
                            visualParameters.setTransparencyInPercents(Integer.parseInt(getXMLText(reader)));
                            break;
                        case NUMBER_OF_VERGES:
                            visualParameters.setNumberOfVerges(Integer.parseInt(getXMLText(reader)));
                            break;

                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.parse(name) == GemEnum.VISUAL_PARAMETERS){
                        return visualParameters;
                    }
                    break;
            }
        }
        throw new ParsingException();
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException{
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
