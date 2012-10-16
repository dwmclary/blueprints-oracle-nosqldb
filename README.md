blueprints-oracle-nosqldb
=========================

An implementation of the Blueprints API over Oracle NoSQL DB

Installation & Testing
=======================
As with other Blueprints implementations, the Oracle NoSQL DB implementation is built with
	```mvn clean install```

However, because there is not an embedded Oracle NoSQL DB some steps must be taken if you wish to run the tests.
* Download Oracle NoSQL DB Community Edition <http://www.oracle.com/technetwork/database/nosqldb/downloads/default-495311.html>
* Uncompress the directory
* Run
		```java -jar $PATH_TO_NOSQLDB/lib/kvstore.jar kvlite```

* Make note of the host name.  Alter pom.xml to reflect this hostname.
