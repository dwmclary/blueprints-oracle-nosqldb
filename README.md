blueprints-oracle-nosqldb
=========================

An implementation of the Blueprints API over Oracle NoSQL DB

Installation & Testing
=======================

As with other Blueprints implementations, the Oracle NoSQL DB implementation is built with
	```mvn clean install```

However, because there is not an embedded Oracle NoSQL DB some steps must be taken if you wish to run the tests.
* Download Oracle NoSQL DB Community Edition <http://www.oracle.com/technetwork/products/nosqldb/downloads/index.html>
* Uncompress the directory
* Run
		```java -jar $PATH_TO_NOSQLDB/lib/kvstore.jar kvlite```

* Make note of the host name.  Alter pom.xml to reflect this hostname.

Rexster Configuration
=====================

[Rexster](http://rexster.tinkerpop.com) is a graph server that can serve any Blueprints graph implementations.  The Oracle NoSQL DB implementation comes with a Rexster configuration class and can be deployed within Rexster.

To deploy:

* Build with `mvn clean install`
* Copy the `target/blueprints-oracle-nosqldb-x.y.z-jar-with-dependencies.jar` to the `REXSTER_HOME/ext` directory.
* Edit the `rexster.xml` file to include a configuration (see also [Rexster Configuration](https://github.com/tinkerpop/rexster/wiki/Rexster-Configuration) for Oracle NoSQL as follows:

```text
<graph>
    <graph-name>kvexample</graph-name>
    <graph-type>com.tinkerpop.blueprints.impls.oraclekv.util.KVGraphConfiguration</graph-type>
    <properties>
      <store-name>kvstore</store-name>
      <host>my-host</host>
      <port>5000</port>
    </properties>
  </graph>
```

* Start Oracle NoSQL DB
* Start Rexster (see [Getting Started](https://github.com/tinkerpop/rexster/wiki/Getting-Started))