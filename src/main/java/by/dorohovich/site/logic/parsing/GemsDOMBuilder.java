package by.dorohovich.site.logic.parsing;


import by.dorohovich.site.entityGem.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 19.11.2016.
 */
public class GemsDOMBuilder implements AbstractGemsParser {
    static final Logger LOG= LogManager.getLogger();
    private Set<Gem> gems;
    private DocumentBuilder docBuilder;
    public GemsDOMBuilder() {
        this.gems = new HashSet<Gem>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error(e);
        }
    }
    public Set<Gem> getGems() {
        return gems;
    }

    public void buildSetGems(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList preciousGemList = root.getElementsByTagName(GemEnum.PRECIOUS_GEM.getValue());
            for (int i = 0; i < preciousGemList.getLength(); i++) {
                Element gemElement = (Element) preciousGemList.item(i);
                Gem gem = buildPreciousGem(gemElement);
                gems.add(gem);
            }

            NodeList semipreciousGemList = root.getElementsByTagName(GemEnum.SEMIPRECIOUS_GEM.getValue());
            for (int i = 0; i < semipreciousGemList.getLength(); i++) {
                Element gemElement = (Element) semipreciousGemList.item(i);
                Gem gem = buildSemipreciousGem(gemElement);
                gems.add(gem);
            }




        } catch (IOException e) {
            System.err.println(e);
        } catch (SAXException e) {
            System.err.println(e);
        }
    }

    private Gem buildPreciousGem(Element gemElement) {
        PreciousGem gem = new PreciousGem();

        fillGem(gem, gemElement);

        gem.setSellingOnlyOnAuction(Boolean.parseBoolean(getElementTextContent(gemElement, GemEnum.SELLING_ONLY_ON_AUCTION.getValue())));

        return gem;
    }

    private Gem buildSemipreciousGem(Element gemElement) {
        SemipreciousGem gem = new SemipreciousGem();

        if(gemElement.hasAttribute(GemEnum.RARITY.getValue()))
        {
            gem.setRarity(gemElement.getAttribute(GemEnum.RARITY.getValue()));
        }
        else
        {
            gem.setRarity(SemipreciousGem.DEFAULT_RARITY);
        }

        fillGem(gem, gemElement);


        return gem;
    }

    private void fillGem(Gem gem, Element gemElement)
    {
        gem.setGemId(gemElement.getAttribute(GemEnum.GEM_ID.getValue()));
        gem.setName(getElementTextContent(gemElement, GemEnum.NAME.getValue()));

        Origin origin = new Origin();
        Element originElement = (Element) gemElement.getElementsByTagName(GemEnum.ORIGIN.getValue()).item(0);
        origin.setCountry(getElementTextContent(originElement, GemEnum.COUNTRY.getValue()));
        origin.setCity(getElementTextContent(originElement, GemEnum.CITY.getValue()));
        gem.setOrigin(origin);

        VisualParameters visualParameters;
        visualParameters = new VisualParameters();
        Element visualParametersElement=(Element) gemElement.getElementsByTagName(GemEnum.VISUAL_PARAMETERS.getValue()).item(0);
        visualParameters.setColour(getElementTextContent(visualParametersElement, GemEnum.COLOUR.getValue()));
        visualParameters.setTransparencyInPercents(Integer.parseInt(getElementTextContent(visualParametersElement, GemEnum.TRANSPARENCY_IN_PERCENTS.getValue())));
        visualParameters.setNumberOfVerges(Integer.parseInt(getElementTextContent(visualParametersElement, GemEnum.NUMBER_OF_VERGES.getValue())));
        gem.setVisualParameters(visualParameters);

        gem.setValueInCarat(Integer.parseInt(getElementTextContent(gemElement, GemEnum.VALUE_IN_CARAT.getValue())));
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }


}
