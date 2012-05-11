import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: daphne
 * Date: 5/10/12
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */

public class SumoSearchQuery {
  private String query = "";
  private String fromTime = "";
  private String toTime = "";
  private String timeZone = "";
  private String resultFormat = "";

  public SumoSearchQuery(String searchQuery) {
    query = searchQuery;
  }

  public String formQueryUri() {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("q", query));
    if (!fromTime.isEmpty()) {
      params.add(new BasicNameValuePair("from", fromTime));
    }

    return URLEncodedUtils.format(params, "UTF-8");
  }
}
