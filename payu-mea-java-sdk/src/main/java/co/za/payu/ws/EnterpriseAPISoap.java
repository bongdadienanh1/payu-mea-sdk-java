
package co.za.payu.ws;

import java.util.List;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EnterpriseAPISoap", targetNamespace = "http://soap.api.controller.web.payjar.com/")
@HandlerChain(file = "EnterpriseAPISoap_handler.xml")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EnterpriseAPISoap {
    /**
     * 
     * @param additionalInformation
     * @param customfield
     * @param api
     * @param safekey
     * @param managePaymentMethodType
     * @param customer
     * @return
     *     returns co.za.payu.ws.ManagePaymentMethodResponseMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "managePaymentMethod", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.ManagePaymentMethod")
    @ResponseWrapper(localName = "managePaymentMethodResponse", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.ManagePaymentMethodResponse")
    public ManagePaymentMethodResponseMessage managePaymentMethod(
        @WebParam(name = "Api", targetNamespace = "")
        String api,
        @WebParam(name = "Safekey", targetNamespace = "")
        String safekey,
        @WebParam(name = "managePaymentMethodType", targetNamespace = "")
        ManagePaymentMethodType managePaymentMethodType,
        @WebParam(name = "AdditionalInformation", targetNamespace = "")
        AdditionalInfo additionalInformation,
        @WebParam(name = "Customer", targetNamespace = "")
        Customer customer,
        @WebParam(name = "Customfield", targetNamespace = "")
        List<CustomField> customfield);

    /**
     * 
     * @param additionalInformation
     * @param api
     * @param safekey
     * @return
     *     returns co.za.payu.ws.GetTransactionResponseMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTransaction", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.GetTransaction")
    @ResponseWrapper(localName = "getTransactionResponse", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.GetTransactionResponse")
    public GetTransactionResponseMessage getTransaction(
        @WebParam(name = "Api", targetNamespace = "")
        String api,
        @WebParam(name = "Safekey", targetNamespace = "")
        String safekey,
        @WebParam(name = "AdditionalInformation", targetNamespace = "")
        AdditionalInfo additionalInformation);

    /**
     *
     * @param soulstace
     * @param basket
     * @param transactionRecord
     * @param bankTransfer
     * @param customfield
     * @param eftBankTransfer
     * @param sbux
     * @param safekey
     * @param debitcard
     * @param thirdParty
     * @param mobicred
     * @param api
     * @param credit
     * @param bankAccount
     * @param additionalInformation
     * @param wallet
     * @param autopay
     * @param rcs
     * @param ebucks
     * @param loyalty
     * @param discoveryMiles
     * @param eWallet
     * @param eWalletMpesa
     * @param creditcard
     * @param transactionType
     * @param eft
     * @param fraud
     * @param globalpay
     * @param authenticationType
     * @param payPal
     * @param customer
     * @return
     *     returns payu.DoTransactionResponseMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "doTransaction", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.DoTransaction")
    @ResponseWrapper(localName = "doTransactionResponse", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.DoTransactionResponse")
    public DoTransactionResponseMessage doTransaction(
            @WebParam(name = "Api", targetNamespace = "")
                    String api,
            @WebParam(name = "Safekey", targetNamespace = "")
                    String safekey,
            @WebParam(name = "TransactionType", targetNamespace = "")
                    TransactionType transactionType,
            @WebParam(name = "AuthenticationType", targetNamespace = "")
                    AuthenticationType authenticationType,
            @WebParam(name = "AdditionalInformation", targetNamespace = "")
                    AdditionalInfo additionalInformation,
            @WebParam(name = "Customer", targetNamespace = "")
                    Customer customer,
            @WebParam(name = "Basket", targetNamespace = "")
                    Basket basket,
            @WebParam(name = "Fraud", targetNamespace = "")
                    Fraud fraud,
            @WebParam(name = "Creditcard", targetNamespace = "")
                    List<CreditCard> creditcard,
            @WebParam(name = "Eft", targetNamespace = "")
                    List<Eft> eft,
            @WebParam(name = "DiscoveryMiles", targetNamespace = "")
                    List<DiscoveryMiles> discoveryMiles,
            @WebParam(name = "PayPal", targetNamespace = "")
                    List<PayPal> payPal,
            @WebParam(name = "Debitcard", targetNamespace = "")
                    List<DebitCard> debitcard,
            @WebParam(name = "Loyalty", targetNamespace = "")
                    List<LoyaltyCard> loyalty,
            @WebParam(name = "BankTransfer", targetNamespace = "")
                    List<BankTransfer> bankTransfer,
            @WebParam(name = "Wallet", targetNamespace = "")
                    List<Wallet> wallet,
            @WebParam(name = "ThirdParty", targetNamespace = "")
                    List<ThirdParty> thirdParty,
            @WebParam(name = "Ebucks", targetNamespace = "")
                    List<Ebucks> ebucks,
            @WebParam(name = "Autopay", targetNamespace = "")
                    AutoPay autopay,
            @WebParam(name = "Soulstace", targetNamespace = "")
                    Soulstace soulstace,
            @WebParam(name = "Globalpay", targetNamespace = "")
                    Globalpay globalpay,
            @WebParam(name = "Customfield", targetNamespace = "")
                    List<CustomField> customfield,
            @WebParam(name = "Credit", targetNamespace = "")
                    Credit credit,
            @WebParam(name = "TransactionRecord", targetNamespace = "")
                    TransactionRecord transactionRecord,
            @WebParam(name = "Sbux", targetNamespace = "")
                    List<Sbux> sbux,
            @WebParam(name = "RCS", targetNamespace = "")
                    Rcs rcs,
            @WebParam(name = "BankAccount", targetNamespace = "")
                    BankAccount bankAccount,
            @WebParam(name = "EWallet", targetNamespace = "")
                    EWallet eWallet,
            @WebParam(name = "Mobicred", targetNamespace = "")
                    List<Mobicred> mobicred,
            @WebParam(name = "EWalletMpesa", targetNamespace = "")
                    List<EWalletMPesa> eWalletMpesa,
            @WebParam(name = "EftBankTransfer", targetNamespace = "")
                    List<EftBankTransfer> eftBankTransfer);

    /**
     * 
     * @param customfield
     * @param deleteTransactionType
     * @param api
     * @param safekey
     * @return
     *     returns co.za.payu.ws.DeleteTransactionResponseMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "doDeleteTransaction", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.DoDeleteTransaction")
    @ResponseWrapper(localName = "doDeleteTransactionResponse", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.DoDeleteTransactionResponse")
    public DeleteTransactionResponseMessage doDeleteTransaction(
        @WebParam(name = "Api", targetNamespace = "")
        String api,
        @WebParam(name = "Safekey", targetNamespace = "")
        String safekey,
        @WebParam(name = "deleteTransactionType", targetNamespace = "")
        DeleteTransactionType deleteTransactionType,
        @WebParam(name = "Customfield", targetNamespace = "")
        List<CustomField> customfield);

    /**
     * 
     * @param soulstace
     * @param additionalInformation
     * @param basket
     * @param transactionRecord
     * @param bankTransfer
     * @param autopay
     * @param customfield
     * @param ebucks
     * @param loyalty
     * @param eWallet
     * @param safekey
     * @param creditcard
     * @param transactionType
     * @param eft
     * @param mobicred
     * @param stage
     * @param fraud
     * @param globalpay
     * @param api
     * @param customer
     * @return
     *     returns co.za.payu.ws.SetTransactionResponseMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "setTransaction", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.SetTransaction")
    @ResponseWrapper(localName = "setTransactionResponse", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.SetTransactionResponse")
    public SetTransactionResponseMessage setTransaction(
        @WebParam(name = "Api", targetNamespace = "")
        String api,
        @WebParam(name = "Safekey", targetNamespace = "")
        String safekey,
        @WebParam(name = "TransactionType", targetNamespace = "")
        TransactionType transactionType,
        @WebParam(name = "Stage", targetNamespace = "")
        Boolean stage,
        @WebParam(name = "AdditionalInformation", targetNamespace = "")
        AdditionalInfo additionalInformation,
        @WebParam(name = "Customer", targetNamespace = "")
        Customer customer,
        @WebParam(name = "Basket", targetNamespace = "")
        Basket basket,
        @WebParam(name = "Fraud", targetNamespace = "")
        Fraud fraud,
        @WebParam(name = "Creditcard", targetNamespace = "")
        List<CreditCard> creditcard,
        @WebParam(name = "Eft", targetNamespace = "")
        List<Eft> eft,
        @WebParam(name = "Loyalty", targetNamespace = "")
        List<LoyaltyCard> loyalty,
        @WebParam(name = "BankTransfer", targetNamespace = "")
        List<BankTransfer> bankTransfer,
        @WebParam(name = "Ebucks", targetNamespace = "")
        Ebucks ebucks,
        @WebParam(name = "Autopay", targetNamespace = "")
        AutoPay autopay,
        @WebParam(name = "Soulstace", targetNamespace = "")
        Soulstace soulstace,
        @WebParam(name = "Globalpay", targetNamespace = "")
        Globalpay globalpay,
        @WebParam(name = "Customfield", targetNamespace = "")
        List<CustomField> customfield,
        @WebParam(name = "TransactionRecord", targetNamespace = "")
        TransactionRecord transactionRecord,
        @WebParam(name = "EWallet", targetNamespace = "")
        EWallet eWallet,
        @WebParam(name = "Mobicred", targetNamespace = "")
        List<Mobicred> mobicred);

    /**
     * 
     * @param customfield
     * @param lookupTransactionType
     * @param api
     * @param safekey
     * @return
     *     returns co.za.payu.ws.LookupTransactionResponseMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getLookupTransaction", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.GetLookupTransaction")
    @ResponseWrapper(localName = "getLookupTransactionResponse", targetNamespace = "http://soap.api.controller.web.payjar.com/", className = "co.za.payu.ws.GetLookupTransactionResponse")
    public LookupTransactionResponseMessage getLookupTransaction(
        @WebParam(name = "Api", targetNamespace = "")
        String api,
        @WebParam(name = "Safekey", targetNamespace = "")
        String safekey,
        @WebParam(name = "lookupTransactionType", targetNamespace = "")
        LookupTransactionType lookupTransactionType,
        @WebParam(name = "Customfield", targetNamespace = "")
        List<CustomField> customfield);

    @WebMethod(exclude=true)
    public void __execute();
}
