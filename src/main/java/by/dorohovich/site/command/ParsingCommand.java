package by.dorohovich.site.command;

import by.dorohovich.site.entityGem.Gem;
import by.dorohovich.site.entityGem.PreciousGem;
import by.dorohovich.site.logic.parsing.AbstractGemsParser;
import by.dorohovich.site.logic.parsing.ParserFactory;
import by.dorohovich.site.utility.ConfigurationManager;


import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 25.11.2016.
 */
public class ParsingCommand implements ActionCommand {

    final static String PARSER_TYPE_PARAM="parserType";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.result");
        ParserFactory parserFactory=new ParserFactory();
        String parserType=request.getParameter(PARSER_TYPE_PARAM);
        AbstractGemsParser parser=parserFactory.createParser(parserType);
        String appPath = request.getServletContext().getRealPath("");
        String path=appPath+"/data/gems.xml";
        parser.buildSetGems(path);
        Set<Gem> gems=parser.getGems();
        Set<Gem> prGems=new HashSet<Gem>();
        Set<Gem> semiprGems=new HashSet<Gem>();

        for(Gem elem : gems)
        {
            if(elem instanceof PreciousGem)
            {
                prGems.add(elem);
            }
            else
            {
                semiprGems.add(elem);
            }
        }
        request.setAttribute("typeOfParsing", parserType);
        request.setAttribute("prGems", prGems);
        request.setAttribute("semiprGems", semiprGems);

        return page;
    }
}
