mvn -e clean compile exec:java -Dexec.mainClass="com.sumologic.client.searchjob.SearchJobResultDumper" -Dexec.args="$@"
