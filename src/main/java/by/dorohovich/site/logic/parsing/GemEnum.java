package by.dorohovich.site.logic.parsing;

/**
 * Created by User on 17.11.2016.
 */
public enum GemEnum {
    GEMS("gems"),
    SEMIPRECIOUS_GEM("semiprecious-gem"),
    PRECIOUS_GEM("precious-gem"),
    GEM_ID("gem-id"),
    RARITY("rarity"),
    NAME("name"),
    COUNTRY("country"),
    CITY("city"),
    COLOUR("colour"),
    TRANSPARENCY_IN_PERCENTS("transparency-in-percents"),
    NUMBER_OF_VERGES("number-of-verges"),
    VALUE_IN_CARAT("value-in-carat"),
    SELLING_ONLY_ON_AUCTION("selling-only-on-auction"),
    ORIGIN("origin"),
    VISUAL_PARAMETERS("visual-parameters");


    private String value;

    private GemEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GemEnum parse(String s)
    {
        GemEnum result=null;
        for(GemEnum elem : GemEnum.values())
        {
            if(elem.getValue().equals(s))
            {
                result=elem;
                break;
            }
        }
        return result;
    }
}
