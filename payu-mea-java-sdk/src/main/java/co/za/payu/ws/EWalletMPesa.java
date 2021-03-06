
package co.za.payu.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eWalletMPesa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eWalletMPesa">
 *   &lt;complexContent>
 *     &lt;extension base="{http://soap.api.controller.web.payjar.com/}iPaymentMethod">
 *       &lt;sequence>
 *         &lt;element name="amountInCents" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentInstrumentType" type="{http://soap.api.controller.web.payjar.com/}paymentInstrumentType" minOccurs="0"/>
 *         &lt;element name="reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="settlementDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eWalletMPesa", propOrder = {
    "amountInCents",
    "businessNumber",
    "currency",
    "eventType",
    "paymentInstrumentType",
    "reference",
    "settlementDate"
})
public class EWalletMPesa
    extends IPaymentMethod
{

    protected String amountInCents;
    protected String businessNumber;
    protected String currency;
    protected String eventType;
    @XmlSchemaType(name = "string")
    protected PaymentInstrumentType paymentInstrumentType;
    protected String reference;
    protected String settlementDate;

    /**
     * Gets the value of the amountInCents property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmountInCents() {
        return amountInCents;
    }

    /**
     * Sets the value of the amountInCents property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * @return EWalletMPesa    
     */
    public EWalletMPesa setAmountInCents(String value) {
        this.amountInCents = value;

        return this;
    }

    /**
     * Gets the value of the businessNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessNumber() {
        return businessNumber;
    }

    /**
     * Sets the value of the businessNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * @return EWalletMPesa    
     */
    public EWalletMPesa setBusinessNumber(String value) {
        this.businessNumber = value;

        return this;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * @return EWalletMPesa    
     */
    public EWalletMPesa setCurrency(String value) {
        this.currency = value;

        return this;
    }

    /**
     * Gets the value of the eventType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * @return EWalletMPesa    
     */
    public EWalletMPesa setEventType(String value) {
        this.eventType = value;

        return this;
    }

    /**
     * Gets the value of the paymentInstrumentType property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentInstrumentType }
     *     
     */
    public PaymentInstrumentType getPaymentInstrumentType() {
        return paymentInstrumentType;
    }

    /**
     * Sets the value of the paymentInstrumentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInstrumentType }
     * @return EWalletMPesa    
     */
    public EWalletMPesa setPaymentInstrumentType(PaymentInstrumentType value) {
        this.paymentInstrumentType = value;

        return this;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * @return EWalletMPesa    
     */
    public EWalletMPesa setReference(String value) {
        this.reference = value;

        return this;
    }

    /**
     * Gets the value of the settlementDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementDate() {
        return settlementDate;
    }

    /**
     * Sets the value of the settlementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * @return EWalletMPesa    
     */
    public EWalletMPesa setSettlementDate(String value) {
        this.settlementDate = value;

        return this;
    }

}
