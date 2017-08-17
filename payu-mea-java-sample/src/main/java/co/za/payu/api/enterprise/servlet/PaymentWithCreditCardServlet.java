package co.za.payu.api.enterprise.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import co.za.payu.api.Payment;

/**
 * Created by kenny on 4/20/17.
 */
public class PaymentWithCreditCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger
            .getLogger(PaymentWithCreditCardServlet.class);

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
        createPayment(req, resp);
        req.getRequestDispatcher("response.jsp").forward(req, resp);
    }

    public Payment createPayment(HttpServletRequest req, HttpServletResponse resp) {

        Payment payment = new Payment();
        Payment createdPayment = null;

        return createdPayment;
    }
}
