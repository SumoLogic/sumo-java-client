import com.sumologic.client.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SumoClientTest {

  private static SumoClient sumoClient;
  private static Credential credential;
  private static String userEmail;
  private static String userPassword;


  @Test
  public void testSearch() throws Exception {
    userEmail = "daphy@demo.com";
    userPassword = "Whatever0987";
    sumoClient = new SumoClient();
    credential = new Credential(userEmail, userPassword);

    SearchQuery searchQuery = new SearchQuery("error");
    sumoClient.setSumoApiUrl("nite-api.sumologic.net");
    sumoClient.setCredential(credential);
    //Date currentTime = new Date();
    //Date oneHourBefore = new Date(currentTime.getTime() - 1000 * 60 * 60);
    //System.out.println(oneHourBefore.toString());
    //System.out.println(String.valueOf(oneHourBefore.getTime()));
    //List<NameValuePair> list = new ArrayList<NameValuePair>();
    //list.add(new BasicNameValuePair("to", "2012-05-16"));
    //list.add(new BasicNameValuePair("from", "2012-05-17"));
    SearchResponse response = sumoClient.search(
        searchQuery.setResultFormat("json"));
    assert(response.getLogMessages().size() > 0);
    for(LogMessage log: response.getLogMessages()){
      System.out.println(log.getRawMessage());
    }
  }

}
