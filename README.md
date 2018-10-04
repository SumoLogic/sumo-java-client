[![Slack Status](http://slack.sumologic.com/badge.svg)](http://slack.sumologic.com)

## Introduction

Sumo Logic provides a cloud-based log management solution. It can process and analyze log files in peta-byte scale. This library provides a Java client to execute searches on the data collected by the Sumo Logic service.

| TLS Deprecation Notice |
| --- |
| In keeping with industry standard security best practices, as of May 31, 2018, the Sumo Logic service will only support TLS version 1.2 going forward. Verify that all connections to Sumo Logic endpoints are made from software that supports TLS 1.2. |

## News
  * 2017-10-24: Version 2.5 released to maven central.
  * 2017-09-27: Version 2.4 released to maven central.
  * 2017-07-11: [The Search API](https://github.com/SumoLogic/sumo-api-doc/wiki/Search-API) has been removed
  * 2017-03-28: Version 2.3 released to maven central.
  * 2017-02-21: [The Search API](https://github.com/SumoLogic/sumo-api-doc/wiki/Search-API) has been deprecated and will be removed in the next release. Please migrate to the [Search Job API](https://help.sumologic.com/APIs/02Search_Job_API/About_the_Search_Job_API) by using the `SearchJobClient` class.
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
  <version>2.5</version>
</dependency>
```

to your pom.xml

## Making changes to the code


`mvn assembly:assembly -DdescriptorId=jar-with-dependencies` to compile the code with all depedencies.

Then edit `bin/run-search-job-result-dumper.sh` to point to `target/sumo-java-client-<version>-SNAPSHOT-jar-with-dependencies.jar`

You can test out api changes with
`bin/run-search-job-result-dumper.sh` and entering the appropriate parameters to connect with and query the api.

## License

The Sumo Logic client library is published under the Apache Software License, Version 2.0. Please visit http://www.apache.org/licenses/LICENSE-2.0.txt for details.

## Support

If you have any problems using our Java client or if you need support please do not hesitate contact us via e-mail at support@sumologic.com 

