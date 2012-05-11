
import static org.junit.Assert.*;
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
  private static String userEmail;
  private static String userPassword;

  @Before
  public void initialize() {
    userEmail = "daphy@demo.com";
    userPassword = "Silver12";
    sumoClient = new SumoClient();
  }

  @Test
  public void testSearch() throws Exception {
    SumoSearchQuery searchQuery = new SumoSearchQuery("error");
    sumoClient.setEmailAndPassword(userEmail, userPassword);
    sumoClient.search(searchQuery);


  }

}
