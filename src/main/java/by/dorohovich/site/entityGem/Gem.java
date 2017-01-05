
package by.dorohovich.site.entityGem;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Gem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Gem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="origin" type="{http://www.example.com/gems}Origin"/>
 *         &lt;element name="visual-parameters" type="{http://www.example.com/gems}VisualParameters"/>
 *         &lt;element name="value-in-carat">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="150"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="gem-Id" use="required" type="{http://www.example.com/gems}GemId" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gem", namespace = "http://www.example.com/gems", propOrder = {
    "name",
    "origin",
    "visualParameters",
    "valueInCarat"
})
@XmlSeeAlso({
    SemipreciousGem.class,
    PreciousGem.class
})
public class Gem {

    @XmlElement(namespace = "http://www.example.com/gems", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.example.com/gems", required = true)
    protected Origin origin;
    @XmlElement(name = "visual-parameters", namespace = "http://www.example.com/gems", required = true)
    protected VisualParameters visualParameters;
    @XmlElement(name = "value-in-carat", namespace = "http://www.example.com/gems")
    protected int valueInCarat;
    @XmlAttribute(name = "gem-Id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String gemId;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link Origin }
     *     
     */
    public Origin getOrigin() {

        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Origin }
     *     
     */
    public void setOrigin(Origin value) {
        this.origin = value;
    }

    /**
     * Gets the value of the visualParameters property.
     * 
     * @return
     *     possible object is
     *     {@link VisualParameters }
     *     
     */
    public VisualParameters getVisualParameters()
    {

        return visualParameters;
    }

    /**
     * Sets the value of the visualParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link VisualParameters }
     *     
     */
    public void setVisualParameters(VisualParameters value) {
        this.visualParameters = value;
    }

    /**
     * Gets the value of the valueInCarat property.
     * 
     */
    public int getValueInCarat() {
        return valueInCarat;
    }

    /**
     * Sets the value of the valueInCarat property.
     * 
     */
    public void setValueInCarat(int value) {
        this.valueInCarat = value;
    }

    /**
     * Gets the value of the gemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGemId() {
        return gemId;
    }

    /**
     * Sets the value of the gemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGemId(String value) {
        this.gemId = value;
    }

    @Override
    public String toString() {
        return "Gem{" +
                "name='" + name + '\'' +
                ", origin=" + origin +
                ", visualParameters=" + visualParameters +
                ", valueInCarat=" + valueInCarat +
                ", gemId='" + gemId + '\'' +
                '}';
    }
}
