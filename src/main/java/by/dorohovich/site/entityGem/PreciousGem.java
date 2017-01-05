
package by.dorohovich.site.entityGem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PreciousGem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PreciousGem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/gems}Gem">
 *       &lt;sequence>
 *         &lt;element name="selling-only-on-auction" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreciousGem", namespace = "http://www.example.com/gems", propOrder = {
    "sellingOnlyOnAuction"
})
public class PreciousGem
    extends Gem
{

    @XmlElement(name = "selling-only-on-auction", namespace = "http://www.example.com/gems", defaultValue = "false")
    protected boolean sellingOnlyOnAuction;

    /**
     * Gets the value of the sellingOnlyOnAuction property.
     * 
     */
    public boolean isSellingOnlyOnAuction() {
        return sellingOnlyOnAuction;
    }

    /**
     * Sets the value of the sellingOnlyOnAuction property.
     * 
     */
    public void setSellingOnlyOnAuction(boolean value) {
        this.sellingOnlyOnAuction = value;
    }


    @Override
    public String toString() {
        return super.toString()+"PreciousGem{" +
                "sellingOnlyOnAuction=" + sellingOnlyOnAuction +
                '}';
    }
}
