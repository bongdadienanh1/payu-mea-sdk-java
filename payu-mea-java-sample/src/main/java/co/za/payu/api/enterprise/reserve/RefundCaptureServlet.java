package co.za.payu.api.enterprise.reserve;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.za.payu.api.IResponse;
import co.za.payu.api.redirect.BaseSample;
import co.za.payu.base.soap.APIContext;
import co.za.payu.base.soap.JSONFormatter;
import co.za.payu.base.exception.PayUSOAPException;
import co.za.payu.util.SampleConstants;
import co.za.payu.ws.*;
import co.za.payu.api.Payment;
import co.za.payu.util.ResultPrinter;
import org.apache.log4j.Logger;

/**
 * Created by kenny on 4/20/17.
 */
public class RefundCaptureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RefundCaptureServlet.class);

    private ObjectFactory objectFactory = new ObjectFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    // ##Create
    // Sample showing to create a Payment using
    // CreditCard as a FundingInstrument
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        refundCapture(req, resp);
        req.getRequestDispatcher("../../response.jsp").forward(req, resp);
    }

    @SuppressWarnings("Duplicates")
    private IResponse refundCapture(HttpServletRequest req, HttpServletResponse resp) {

        APIContext apiContext = new APIContext(SampleConstants.apiUsername, SampleConstants.apiPassword,
                SampleConstants.safeKey, SampleConstants.mode, SampleConstants.account1);

        String baseUrl = BaseSample.getBaseUrl(req);

        DoTransactionResponseMessage createdPayment = (DoTransactionResponseMessage) reserveCapture(req, resp, apiContext, baseUrl);

        /// ###Basket
        // A resource representing a Basket/Cart belonging to a customer
        Basket basket = objectFactory.createBasket()
                .setCurrencyCode("ZAR")
                .setAmountInCents("10000");

        // ###AdditionalInfo
        // A resource representing AdditionalInfo about the transaction
        AdditionalInfo additionalInfo = objectFactory.createAdditionalInfo()
                .setNotificationUrl(baseUrl + "reserve/payment/return")
                .setPayUReference(createdPayment.getPayUReference())
                .setMerchantReference(createdPayment.getMerchantReference());


        // ###DoTransaction
        // A DoTransaction defines the Request payload of a
        // payment - DoTransaction is created with
        // a `Customer`, `Basket`, `Creditcard` and other types
        DoTransaction doTransaction = objectFactory.createDoTransaction()
                .setTransactionType(TransactionType.CREDIT)
                .setAdditionalInformation(additionalInfo)
                .setBasket(basket);

        // ###Payment
        // A Payment defines the contract of a
        // payment - what is the payment for and who
        // is fulfilling it. Payment is created with
        // a `DoTransaction`
        Payment payment = new Payment();
        payment.setRequest(doTransaction);

        DoTransactionResponseMessage doTransactionResponseMessage = null;

        try {

            doTransactionResponseMessage = (DoTransactionResponseMessage) payment.refund(apiContext);

            LOGGER.info("Refund captured/finalized payment with id = " + doTransactionResponseMessage.getPayUReference()
                    + " and result code = " + doTransactionResponseMessage.getResultCode());
            ResultPrinter.addResult(req, resp, "Refund Captured/Finalized Payment", JSONFormatter.toJSON(doTransaction),
                    JSONFormatter.toJSON(doTransactionResponseMessage), null);
        } catch (PayUSOAPException ex) {
            ResultPrinter.addResult(req, resp, "Refund Captured/Finalized Payment", JSONFormatter.toJSON(doTransaction),
                    JSONFormatter.toJSON(doTransactionResponseMessage), ex.getMessage());
        }

        return doTransactionResponseMessage;
    }

    @SuppressWarnings("Duplicates")
    private IResponse createReservePayment(HttpServletRequest req, HttpServletResponse resp, APIContext apiContext, String baseUrl) {

        // ###CreditCard
        // A resource representing a credit card that can be
        // used to fund a payment.
        CreditCard creditCard = objectFactory.createCreditCard()
                .setCvv("123")
                .setCardExpiry("112019")
                .setNameOnCard("Joe Shopper")
                .setCardNumber("4000019542438801")
                .setAmountInCents("10000")
                .setInformation("visa");

        // ###Basket
        // A resource representing a Basket/Cart belonging to a customer
        Basket basket = objectFactory.createBasket()
                .setDescription("My payment")
                .setCurrencyCode("ZAR")
                .setAmountInCents("10000");

        // ###Customer
        // A resource representing a Customer that funds a payment
        Customer customer = objectFactory.createCustomer()
                .setIp("127.0.0.1")
                .setFirstName("Joe")
                .setLastName("Shopper")
                .setEmail("joe.shopper@example.com")
                .setMobile("0748523695")
                .setCountryCode("27")
                .setMerchantUserId("890");

        // ###AdditionalInfo
        // A resource representing AdditionalInfo about the transaction
        AdditionalInfo additionalInfo = objectFactory.createAdditionalInfo()
                .setNotificationUrl(baseUrl+"return")
                .setMerchantReference(UUID.randomUUID().toString())
                .setStorePaymentMethod("true");

        // ###DoTransaction
        // A DoTransaction defines the Request payload of a
        // payment - DoTransaction is created with
        // a `Customer`, `Basket`, `Creditcard` and other types
        DoTransaction doTransaction = objectFactory.createDoTransaction()
                .setTransactionType(TransactionType.RESERVE)
                .setAdditionalInformation(additionalInfo)
                .setBasket(basket)
                .setCustomer(customer);
        doTransaction.getCreditcard().add(creditCard);

        // ###Payment
        // A Payment defines the contract of a
        // payment - what is the payment for and who
        // is fulfilling it. Payment is created with
        // a `DoTransaction`
        Payment payment = new Payment();
        payment.setRequest(doTransaction);
        DoTransactionResponseMessage doTransactionResponseMessage = null;

        try {
            doTransactionResponseMessage = (DoTransactionResponseMessage) payment.create(apiContext);

            LOGGER.info("Created reserve payment with id = " + doTransactionResponseMessage.getPayUReference() + " and result code = "
                    + doTransactionResponseMessage.getResultCode());

            ResultPrinter.addResult(req, resp, "Create Authorized/Reserved Payment", JSONFormatter.toJSON(doTransaction),
                    JSONFormatter.toJSON(doTransactionResponseMessage), null);
        } catch(Exception ex) {
            ResultPrinter.addResult(req, resp, "Create Authorized/Reserved Payment. If Exception, " +
                            "check response for details.", JSONFormatter.toJSON(doTransaction),
                    JSONFormatter.toJSON(doTransactionResponseMessage), ex.getMessage());
        }

        return doTransactionResponseMessage;
    }

    @SuppressWarnings("Duplicates")
    private IResponse reserveCapture(HttpServletRequest req, HttpServletResponse resp, APIContext apiContext, String baseUrl) {

        DoTransactionResponseMessage createdReserve = (DoTransactionResponseMessage) createReservePayment(req, resp, apiContext, baseUrl);

        // ###CreditCard
        // A resource representing a credit card that can be
        // used to fund a payment.
        CreditCard creditCard = objectFactory.createCreditCard()
                .setAmountInCents("10000");

        // ###Basket
        // A resource representing a Basket/Cart belonging to a customer
        Basket basket = objectFactory.createBasket()
                .setCurrencyCode("ZAR")
                .setAmountInCents("10000");

        // ###AdditionalInfo
        // A resource representing AdditionalInfo about the transaction
        AdditionalInfo additionalInfo = objectFactory.createAdditionalInfo()
                .setNotificationUrl(baseUrl+"return")
                .setPayUReference(createdReserve.getPayUReference())
                .setMerchantReference(createdReserve.getMerchantReference());

        // ###DoTransaction
        // A DoTransaction defines the Request payload of a
        // payment - DoTransaction is created with
        // a `Customer`, `Basket`, `Creditcard` and other types
        DoTransaction doTransaction = objectFactory.createDoTransaction()
                .setTransactionType(TransactionType.FINALIZE)
                .setAdditionalInformation(additionalInfo)
                .setBasket(basket);
        doTransaction.getCreditcard().add(creditCard);

        // ###Payment
        // A Payment defines the contract of a
        // payment - what is the payment for and who
        // is fulfilling it. Payment is created with
        // a `DoTransaction`
        Payment payment = new Payment();
        payment.setRequest(doTransaction);

        DoTransactionResponseMessage doTransactionResponseMessage = null;

        try {

            doTransactionResponseMessage = (DoTransactionResponseMessage) payment.capture(apiContext);

            LOGGER.info("Capture reserved payment with id = " + doTransactionResponseMessage + " and status = ");
            ResultPrinter.addResult(req, resp, "Capture/Finalize Reserved Payment", JSONFormatter.toJSON(doTransaction), JSONFormatter.toJSON(doTransactionResponseMessage), null);
        } catch (PayUSOAPException ex) {
            ResultPrinter.addResult(req, resp, "Capture/Finalize Reserved Payment", JSONFormatter.toJSON(doTransaction),
                    JSONFormatter.toJSON(doTransactionResponseMessage), ex.getMessage());
        }

        return doTransactionResponseMessage;
    }
}