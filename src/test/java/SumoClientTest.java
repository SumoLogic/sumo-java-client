import com.sumologic.client.Credential;
import com.sumologic.client.SearchQuery;
import com.sumologic.client.SumoClient;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: daphne
 * Date: 5/8/12
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */

public class SumoClientTest {

  private static SumoClient sumoClient;
  private static Credential credential;
  private static String userEmail;
  private static String userPassword;

  @Before
  public void initialize() {
    userEmail = "daphy@demo.com";
    userPassword = "XXX";
    sumoClient = new SumoClient();
    credential = new Credential();
}

  @Test
  public void testSearch() throws Exception {
    SearchQuery searchQuery = new SearchQuery("error");
    credential.setEmailAndPassword(userEmail, userPassword);
    sumoClient.setCredential(credential);
    sumoClient.search(searchQuery.setResultFormat("json"));
  }

}
