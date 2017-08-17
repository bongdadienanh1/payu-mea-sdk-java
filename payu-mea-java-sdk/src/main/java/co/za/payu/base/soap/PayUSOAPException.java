package co.za.payu.base.soap;

import com.google.gson.Gson;
import co.za.payu.base.exception.HttpErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayUSOAPException extends Exception {
    private static final Logger log = LoggerFactory
            .getLogger(PayUSOAPException.class);

    /**
     * Serial Version ID
     */
    private static final long serialVersionUID = -3519809596605309832L;


    /**
     * If source is {@link HttpErrorException},
     * exception's response code value is copied
     */
    private int responsecode;

    /**
     * If source is {@link HttpErrorException} and response code is 400,
     * error response content is converted to {@link Error} object
     */
    private Error details;

    public PayUSOAPException(String message) {
        super(message);
    }

    public PayUSOAPException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PayUSOAPException(Throwable throwable) {
        super(throwable);
    }

    public int getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(int responsecode) {
        this.responsecode = responsecode;
    }

    public Error getDetails() {
        return details;
    }

    public void setDetails(Error details) {
        this.details = details;
    }

    /**
     * Utility method that creates a {@link PayUSOAPException} object from {@link HttpErrorException}.
     * if {@link HttpErrorException} contains 400 response code, error response is converted to {@link Error} object.
     *
     * @param httpErrorException
     * 				{@link HttpErrorException} thrown from API call
     * @return PayUSOAPException
     */
    protected static PayUSOAPException createFromHttpErrorException(HttpErrorException httpErrorException){
        PayUSOAPException ppre = new PayUSOAPException(httpErrorException.getMessage(), httpErrorException);
        ppre.setResponsecode(httpErrorException.getResponsecode());
        if( httpErrorException.getResponsecode() >= 400 &&  httpErrorException.getErrorResponse()!=null && isJSONValid(httpErrorException.getErrorResponse())) {
            try{
                Error details = JSONFormatter.fromJSON(httpErrorException.getErrorResponse(), Error.class);
                ppre.setDetails(details);
            } catch(Exception e){
                log.error("Exception thrown while parsing error response: " + httpErrorException.getErrorResponse() , e);
            }
        }
        return ppre;
    }

    public String toString() {
        return "response-code: " + this.responsecode + "\tdetails: " + this.details;
    }

    private static boolean isJSONValid(String jsonInString) {
        try {
            new Gson().fromJson(jsonInString, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
}
