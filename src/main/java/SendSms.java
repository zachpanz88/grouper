package hello;

import java.util.Map;
import java.util.HashMap;
 
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
 
public class SendSms {
 
    /* Find your sid and token at twilio.com/user/account */
    private static final String ACCOUNT_SID = "ACc4ab89366663b78cf901888a5d5351b5";
    private static final String AUTH_TOKEN = "5fe391e2735fa528e082710994f38812";
    private static final String FROM_NUMBER = "+19082743307"; //TODO import constants from another file
    private String toNumber;
    private String body;
    private TwilioRestClient client;
    private Account account;
    private  MessageFactory messageFactory;
    
    /**
     * @author Jake Sylvestre
     * @param phonenumber - number with country code before it, example: +1-908-752-7625
     * @param body - body of the sms
     */
    public SendSms(String phoneNumber, String messageBody){
        toNumber = phoneNumber;
        body = messageBody;
        client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        account = client.getAccount();
        messageFactory = account.getMessageFactory();
    }
 
    /**
     * @author Jake Sylvestre
     * Send SMS given phoneNumber and body given in original code
     */
    public void sendSms(){
        try{
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("To", toNumber));
            params.add(new BasicNameValuePair("From", FROM_NUMBER));
            params.add(new BasicNameValuePair("Body", body));
            Message sms = messageFactory.create(params);
        }
        catch(TwilioRestException error){
                System.err.println("Caught TwilioRestClient: " + error.getMessage());
        }
    }
}
