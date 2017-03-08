[![Slack Status](http://slack.sumologic.com/badge.svg)](http://slack.sumologic.com)

## Introduction

Sumo Logic provides a cloud-based log management solution. It can process and analyze log files in peta-byte scale. This library provides a Java client to execute searches on the data collected by the Sumo Logic service.

## News
  * 2017-02-21: [The Search API](https://github.com/SumoLogic/sumo-api-doc/wiki/Search-API) has been deprecated and will be removed in the next release. Please migrate to the [Search Job API](https://help.sumologic.com/APIs/02Search_Job_API/About_the_Search_Job_API).
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

## Current Limitations and Known Bugs

The older Search API has currently has the following limitations:
  * A maximum of 100k log lines are returned at once. If you need more data you can issue subsequent queries covering a smaller time frame.
  * Queries need to complete in 60 seconds. Otherwise the session will timeout and no results are returned.
  * As a result, we strongly recommend you to use the Search Job API with the `SearchJobClient` class.

## License

The Sumo Logic client library is published under the Apache Software License, Version 2.0. Please visit http://www.apache.org/licenses/LICENSE-2.0.txt for details.

## Support

If you have any problems using our Java client or if you need support please do not hesitate contact us via e-mail at api@sumologic.com 

