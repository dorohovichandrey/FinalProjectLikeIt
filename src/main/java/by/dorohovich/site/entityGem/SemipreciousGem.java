
package by.dorohovich.site.entityGem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SemipreciousGem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SemipreciousGem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/gems}Gem">
 *       &lt;attribute name="rarity" default="normal">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="frequent"/>
 *             &lt;enumeration value="normal"/>
 *             &lt;enumeration value="rare"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SemipreciousGem", namespace = "http://www.example.com/gems")
public class SemipreciousGem
    extends Gem
{
    public static final String DEFAULT_RARITY="normal";

    @XmlAttribute(name = "rarity")
    protected String rarity;

    /**
     * Gets the value of the rarity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRarity() {
        if (rarity == null) {
            return "normal";
        } else {
            return rarity;
        }
    }

    /**
     * Sets the value of the rarity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRarity(String value) {
        this.rarity = value;
    }


    @Override
    public String toString() {
        return super.toString()+"SemipreciousGem{" +
                "rarity='" + rarity + '\'' +
                '}';
    }
}
