package by.dorohovich.site.logic.parsing;


import by.dorohovich.site.entityGem.*;

import by.dorohovich.site.exception.NoSuchElemInEnumException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 17.11.2016.
 */
public class GemHandler extends DefaultHandler {
    static final Logger LOG= LogManager.getLogger();
    private Set<Gem> gems;
    private Gem current = null;
    private GemEnum currentEnum = null;
    private EnumSet<GemEnum> withText;

    public GemHandler() {
        gems = new HashSet<Gem>();
        withText = EnumSet.range(GemEnum.NAME, GemEnum.VISUAL_PARAMETERS);
    }

    public Set<Gem> getGems() {
        return gems;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if(GemEnum.SEMIPRECIOUS_GEM.getValue().equals(localName))
        {
            current=new SemipreciousGem();
            current.setGemId(attrs.getValue(0));
            if(attrs.getLength()==2)
            {
                ((SemipreciousGem)current).setRarity(attrs.getValue(1));
            }
            else
            {
                ((SemipreciousGem)current).setRarity(SemipreciousGem.DEFAULT_RARITY);
            }
        }
        else if(GemEnum.PRECIOUS_GEM.getValue().equals(localName))
        {
            current=new PreciousGem();
            current.setGemId(attrs.getValue(0));
        }
        else
        {
            GemEnum temp=GemEnum.parse(localName);
            if(withText.contains(temp))
            {
                currentEnum=temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if(GemEnum.SEMIPRECIOUS_GEM.getValue().equals(localName)||GemEnum.PRECIOUS_GEM.getValue().equals(localName))
        {
            gems.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {

        String s = new String(ch, start, length).trim();
        if(currentEnum!=null)
        {
            switch (currentEnum){
                case NAME:
                    current.setName(s);
                    break;
                case COUNTRY:
                    current.getOrigin().setCountry(s);
                    break;
                case CITY:
                    current.getOrigin().setCity(s);
                    break;
                case COLOUR:
                    current.getVisualParameters().setColour(s);
                    break;
                case TRANSPARENCY_IN_PERCENTS:
                    current.getVisualParameters().setTransparencyInPercents(Integer.parseInt(s));
                    break;
                case NUMBER_OF_VERGES:
                    current.getVisualParameters().setNumberOfVerges(Integer.parseInt(s));
                    break;
                case VALUE_IN_CARAT:
                    current.setValueInCarat(Integer.parseInt(s));
                    break;
                case SELLING_ONLY_ON_AUCTION:
                    ((PreciousGem)current).setSellingOnlyOnAuction(Boolean.parseBoolean(s));
                    break;
                case ORIGIN:
                    current.setOrigin(new Origin());
                    break;
                case VISUAL_PARAMETERS:
                    current.setVisualParameters(new VisualParameters());
                    break;
                default:
                    LOG.fatal("No such element in GemEnum");
                    throw new NoSuchElemInEnumException();
            }
        }
        currentEnum=null;
    }
}

