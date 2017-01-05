package by.dorohovich.site.logic.parsing;


import by.dorohovich.site.exception.NoSuchElemInEnumException;

/**
 * Created by User on 26.11.2016.
 */
public class ParserFactory {
    public AbstractGemsParser createParser(String type)
    {
        switch (type)
        {
            case "sax":
            {
                return new GemsSAXBuilder();
            }
            case "dom":
            {
                return new GemsDOMBuilder();
            }
            case "stax":
            {
                return new GemsStAXBuilder();
            }
            default:
            {
                throw new NoSuchElemInEnumException();
            }
        }
    }
}
