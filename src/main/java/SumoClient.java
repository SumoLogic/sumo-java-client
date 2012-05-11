import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ResponseHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: daphne
 * Date: 5/7/12
 */

public class SumoClient {
  private static SumoClient ourInstance = new SumoClient();
  private static DefaultHttpClient httpClient = new DefaultHttpClient();
  private static String sumoApiUrl = "nite-api.sumologic.net";
  private static String userEmail;
  private static String userPassword;


  private void setCredential() {
    httpClient.getCredentialsProvider().setCredentials(
        new AuthScope(sumoApiUrl, 443),
        new UsernamePasswordCredentials(userEmail, userPassword));
  }

  public void search(SumoSearchQuery query) throws Exception {
    HttpGet searchGetMethod = new HttpGet(URIUtils.createURI("https", sumoApiUrl, -1,
        "/api/v1/logs/search", query.formQueryUri(), null));

    try {
      System.out.println(searchGetMethod.getURI());
      ResponseHandler<String> responseHandler = new BasicResponseHandler();
      String responseBody = httpClient.execute(searchGetMethod, responseHandler);

      //System.out.println(responseBody);

    } finally {
      System.out.println("finish");
    }
  }


  public static SumoClient getInstance() {
    return ourInstance;
  }

  public SumoClient() {
    this("", "");
  }

  public SumoClient(String email, String password) {
    userEmail = email;
    userPassword = password;
    if (!userEmail.isEmpty()) {
      System.out.println("in not is empty");
      this.setCredential();
    }
  }

  public void setEmail(String email) {
    setEmailAndPassword(email, userPassword);
  }

  public void setPassword(String password) {
    setEmailAndPassword(userEmail, password);
  }

  public void setEmailAndPassword(String email, String password) {
    userEmail = email;
    userPassword = password;
    this.setCredential();
  }
}
