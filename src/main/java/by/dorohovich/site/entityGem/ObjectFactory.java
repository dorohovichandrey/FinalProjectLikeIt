
package by.dorohovich.site.entityGem;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * entityGem in the by.dorohoch.fifthtask.entityGem package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SemipreciousGem_QNAME = new QName("http://www.example.com/gems", "semiprecious-gem");
    private final static QName _PreciousGem_QNAME = new QName("http://www.example.com/gems", "precious-gem");
    private final static QName _Gem_QNAME = new QName("http://www.example.com/gems", "gem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: by.dorohoch.fifthtask.entityGem
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SemipreciousGem }
     * 
     */
    public SemipreciousGem createSemipreciousGem() {
        return new SemipreciousGem();
    }

    /**
     * Create an instance of {@link Gem }
     * 
     */
    public Gem createGem() {
        return new Gem();
    }

    /**
     * Create an instance of {@link Gems }
     * 
     */
    public Gems createGems() {
        return new Gems();
    }

    /**
     * Create an instance of {@link PreciousGem }
     * 
     */
    public PreciousGem createPreciousGem() {
        return new PreciousGem();
    }

    /**
     * Create an instance of {@link Origin }
     * 
     */
    public Origin createOrigin() {
        return new Origin();
    }

    /**
     * Create an instance of {@link VisualParameters }
     * 
     */
    public VisualParameters createVisualParameters() {
        return new VisualParameters();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SemipreciousGem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/gems", name = "semiprecious-gem", substitutionHeadNamespace = "http://www.example.com/gems", substitutionHeadName = "gem")
    public JAXBElement<SemipreciousGem> createSemipreciousGem(SemipreciousGem value) {
        return new JAXBElement<SemipreciousGem>(_SemipreciousGem_QNAME, SemipreciousGem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PreciousGem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/gems", name = "precious-gem", substitutionHeadNamespace = "http://www.example.com/gems", substitutionHeadName = "gem")
    public JAXBElement<PreciousGem> createPreciousGem(PreciousGem value) {
        return new JAXBElement<PreciousGem>(_PreciousGem_QNAME, PreciousGem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Gem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/gems", name = "gem")
    public JAXBElement<Gem> createGem(Gem value) {
        return new JAXBElement<Gem>(_Gem_QNAME, Gem.class, null, value);
    }

}
