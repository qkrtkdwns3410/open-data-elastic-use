package org.search.elastic.steka.testcontainer;

import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;

/**
 * packageName    : org.search.elastic.steka.testcontainer
 * fileName       : EsTestcontainerExtension
 * author         : ipeac
 * date           : 24. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 12.        ipeac       최초 생성
 */
@Configuration
public class EsTestcontainerExtension {
    public final static String ELASTICSEARCH_CONTAINER = "docker.elastic.co/elasticsearch/elasticsearch:7.12.1";
    public final static GenericContainer<?> elasticContainer = new GenericContainer<>(ELASTICSEARCH_CONTAINER)
            .withExposedPorts(9200, 9300)
            .withEnv("discovery.type", "single-node");
    
    static {
        elasticContainer.start();
    }
}