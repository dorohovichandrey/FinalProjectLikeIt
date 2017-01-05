package by.dorohovich.site.logic.parsing;

import by.dorohovich.site.entityGem.Gem;

import java.util.Set;

/**
 * Created by User on 26.11.2016.
 */
public interface AbstractGemsParser {

    Set<Gem> getGems();
    void buildSetGems(String fileName);
}
