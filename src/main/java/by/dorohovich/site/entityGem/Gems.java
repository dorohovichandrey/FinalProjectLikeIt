
package by.dorohovich.site.entityGem;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.example.com/gems}gem" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "gem"
})
@XmlRootElement(name = "gems", namespace = "http://www.example.com/gems")
public class Gems {

    @XmlElementRef(name = "gem", namespace = "http://www.example.com/gems", type = JAXBElement.class)
    protected List<JAXBElement<? extends Gem>> gem;

    /**
     * Gets the value of the gem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link PreciousGem }{@code >}
     * {@link JAXBElement }{@code <}{@link SemipreciousGem }{@code >}
     * {@link JAXBElement }{@code <}{@link Gem }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Gem>> getGem() {
        if (gem == null) {
            gem = new ArrayList<JAXBElement<? extends Gem>>();
        }
        return this.gem;
    }

}
