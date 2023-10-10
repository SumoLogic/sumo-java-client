[![Slack Status](http://slack.sumologic.com/badge.svg)](http://slack.sumologic.com)

## Introduction

Sumo Logic provides a cloud-based log management solution. It can process and analyze log files in peta-byte scale. This library provides a Java client to execute searches on the data collected by the Sumo Logic service.

## How to use

The Java client library is available on Maven central (http://search.maven.org/). To use it, add:

```
<dependency>
  <groupId>com.sumologic.api.client</groupId>
  <artifactId>sumo-java-client</artifactId> 
  <version>2.11</version>
</dependency>
```

to your pom.xml

## Making changes to the code


`mvn assembly:assembly -DdescriptorId=jar-with-dependencies` to compile the code with all depedencies.

Then edit `bin/run-search-job-result-dumper.sh` to point to `target/sumo-java-client-<version>-SNAPSHOT-jar-with-dependencies.jar`

You can test out api changes with
`bin/run-search-job-result-dumper.sh` and entering the appropriate parameters to connect with and query the api.

### TLS 1.2 Requirement

Sumo Logic only accepts connections from clients using TLS version 1.2 or greater. To utilize the content of this repo, ensure that it's running in an execution environment that is configured to use TLS 1.2 or greater.

## License

The Sumo Logic client library is published under the Apache Software License, Version 2.0. Please visit http://www.apache.org/licenses/LICENSE-2.0.txt for details.

## Support

If you have any problems using our Java client or if you need support please do not hesitate contact us via e-mail at support@sumologic.com 
