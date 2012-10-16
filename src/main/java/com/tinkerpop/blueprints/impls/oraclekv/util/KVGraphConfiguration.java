package com.tinkerpop.blueprints.impls.oraclekv.util;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.oraclekv.KVGraph;
import com.tinkerpop.rexster.Tokens;
import com.tinkerpop.rexster.config.GraphConfiguration;
import com.tinkerpop.rexster.config.GraphConfigurationException;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;

/**
 * Rexster configuration for KVGraph.  Accepts configuration in rexster.xml as follows:
 *
 * <code>
 *  <graph>
 *    <graph-name>kvexample</graph-name>
 *    <graph-type>com.tinkerpop.blueprints.impls.oraclekv.util.KVGraphConfiguration</graph-type>
 *    <properties>
 *      <store-name>kvstore</store-name>
 *      <host>my-host</host>
 *      <port>5000</port>
 *    </properties>
 *  </graph>
 * </code>
 *
 * To deploy copy the KVGraph jar (with dependencies) to the Rexster ext directory.  Make sure that Oracle NoSQL
 * is running.
 *
 * java -jar lib/kvstore-1.2.123.jar kvlite
 * Created new kvlite store with args:
 * -root ./kvroot -store kvstore -host my-host -port 5000 -admin 5001
 *
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class KVGraphConfiguration implements GraphConfiguration {

    @Override
    public Graph configureGraphInstance(Configuration configuration) throws GraphConfigurationException {
        final HierarchicalConfiguration graphSectionConfig = (HierarchicalConfiguration) configuration;
        SubnodeConfiguration kvSpecificConfiguration;

        try {
            kvSpecificConfiguration = graphSectionConfig.configurationAt(Tokens.REXSTER_GRAPH_PROPERTIES);
        } catch (IllegalArgumentException iae) {
            throw new GraphConfigurationException("Check graph configuration. Missing or empty configuration element: " + Tokens.REXSTER_GRAPH_PROPERTIES);
        }

        try {

            final String host = kvSpecificConfiguration.getString("host");
            final int port = kvSpecificConfiguration.getInt("port", 5000);
            final String storeName = kvSpecificConfiguration.getString("store-name");
            final String graphName = configuration.getString("graph-name");

            return new KVGraph(graphName, storeName, host, port);

        } catch (Exception ex) {
            throw new GraphConfigurationException(ex);
        }
    }
}