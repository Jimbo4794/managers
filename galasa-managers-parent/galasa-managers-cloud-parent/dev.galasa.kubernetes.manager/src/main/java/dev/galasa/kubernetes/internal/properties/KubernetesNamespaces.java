<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes.internal.properties;

import java.util.List;

import javax.validation.constraints.NotNull;

import dev.galasa.framework.spi.cps.CpsProperties;
import dev.galasa.kubernetes.KubernetesManagerException;
import dev.galasa.kubernetes.internal.KubernetesClusterImpl;


/**
 * Kubernetes Namespace IDs CPS Property
 * 
 * @galasa.cps.property
 * 
 * @galasa.name kubernetes.cluster.[XXXX.]namespaces
 * 
 * @galasa.description Provides a comma separated list of the namespaces that are available on the cluster
 * 
 * @galasa.required No
 * 
 * @galasa.default Will default to galasa{1-2} is not provided
 * 
 * @galasa.valid_values a comma separated list of valid Kubernetes namespaces,  with resource pooling expanders
 * 
 * @galasa.examples 
 * <code>kubernetes.cluster.K8S.namespaces=galasa1,galasa{2-9}<br>
 * kubebernetes.cluster.namespaces=bob1,bob2,bob3</code>
 * 
 */
public class KubernetesNamespaces extends CpsProperties {

    public static @NotNull List<String> get(KubernetesClusterImpl cluster) throws KubernetesManagerException {
        return getStringListWithDefault(KubernetesPropertiesSingleton.cps(), "galasa{1-2}", "cluster", "namespaces", cluster.getId()) ;
    }
}