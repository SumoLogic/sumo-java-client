[![Slack Status](http://slack.sumologic.com/badge.svg)](http://slack.sumologic.com)

## Introduction

Sumo Logic provides a cloud-based log management solution. It can process and analyze log files in peta-byte scale. This library provides a Java client to execute searches on the data collected by the Sumo Logic service.

## News
  * 2016-09-16: Version 2.2 released to maven central.
  * 2015-11-23: Version 2.1 released to maven central.
  * 2014-10-30: Version 2.0 released to maven central.
  * 2012-09-12: Version 1.0 released to maven central.

## How to use

The Java client library is available on Maven central (http://search.maven.org/). To use it, add:

```
<dependency>
  <groupId>com.sumologic.api.client</groupId>
  <artifactId>sumo-java-client</artifactId> 
  <version>2.2</version>
</dependency>
```

to your pom.xml

## Example

The following code snippet issues a query for log line containing the word “error” and returns the results. Replace the accessId and accessKey with the credentials you use to log on to the Sumo Logic web service.

```
import com.sumologic.client.SumoException;
import com.sumologic.client.SumoLogicClient;
import com.sumologic.client.model.SearchResponse;

public class HelloWorld {
    public static void main(String[] args) {
        // Create a client
        SumoLogicClient client = new SumoLogicClient("accessId", "accessKey");

        // Search for log lines containing "error"
        SearchResponse response = client.search("error");

        // Print raw log lines
        System.out.println(response);
    }
}
```

For information how to create sophisticated queries visit (TODO: LINK). You can find the documentation of the Java API here (TODO: link to JavaDoc) (HTML JavaDoc).

## Current Limitations and Known Bugs

The search API has currently has the following limitations:
  * A maximum of 100k log lines are returned at once. If you need more data you can issue subsequent queries covering a smaller time frame.
  * Queries need to complete in 60 seconds. Otherwise the session will timeout and no results are returned.

## License

The Sumo Logic client library is published under the Apache Software License, Version 2.0. Please visit http://www.apache.org/licenses/LICENSE-2.0.txt for details.

## Support

If you have any problems using our Java client or if you need support please do not hesitate contact us via e-mail at api@sumologic.com 

