
package by.dorohovich.site.entityGem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VisualParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VisualParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="colour" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transparency-in-percents">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *               &lt;maxInclusive value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="number-of-verges">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;minInclusive value="4"/>
 *               &lt;maxInclusive value="15"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VisualParameters", namespace = "http://www.example.com/gems", propOrder = {
    "colour",
    "transparencyInPercents",
    "numberOfVerges"
})
public class VisualParameters {

    @XmlElement(namespace = "http://www.example.com/gems", required = true)
    protected String colour;
    @XmlElement(name = "transparency-in-percents", namespace = "http://www.example.com/gems")
    protected int transparencyInPercents;
    @XmlElement(name = "number-of-verges", namespace = "http://www.example.com/gems")
    protected int numberOfVerges;

    /**
     * Gets the value of the colour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColour() {
        return colour;
    }

    /**
     * Sets the value of the colour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColour(String value) {
        this.colour = value;
    }

    /**
     * Gets the value of the transparencyInPercents property.
     * 
     */
    public int getTransparencyInPercents() {
        return transparencyInPercents;
    }

    /**
     * Sets the value of the transparencyInPercents property.
     * 
     */
    public void setTransparencyInPercents(int value) {
        this.transparencyInPercents = value;
    }

    /**
     * Gets the value of the numberOfVerges property.
     * 
     */
    public int getNumberOfVerges() {
        return numberOfVerges;
    }

    /**
     * Sets the value of the numberOfVerges property.
     * 
     */
    public void setNumberOfVerges(int value) {
        this.numberOfVerges = value;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "colour='" + colour + '\'' +
                ", transparencyInPercents=" + transparencyInPercents +
                ", numberOfVerges=" + numberOfVerges +
                '}';
    }
}
