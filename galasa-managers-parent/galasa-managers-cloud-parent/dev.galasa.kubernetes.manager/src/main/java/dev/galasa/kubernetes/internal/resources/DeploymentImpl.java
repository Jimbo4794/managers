<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes.internal.resources;

import java.util.List;

import dev.galasa.kubernetes.IDeployment;
import dev.galasa.kubernetes.IPodLog;
import dev.galasa.kubernetes.KubernetesManagerException;
import dev.galasa.kubernetes.internal.KubernetesNamespaceImpl;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.util.Yaml;

<<<<<<< HEAD
=======
/**
 * Deployment Implementation
 * 
 * @author Michael Baylis
 *
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
public class DeploymentImpl extends ReplicaSetHolder implements IDeployment {
    
    private final KubernetesNamespaceImpl namespace;
    private final V1Deployment deployment;

    public DeploymentImpl(KubernetesNamespaceImpl namespace, V1Deployment deployment) {
        this.namespace   = namespace;
        this.deployment = deployment;
    }

    @Override
    public String getName() {
        return deployment.getMetadata().getName();
    }

    @Override
    public TYPE getType() {
        return TYPE.Deployment;
    }

    @Override
    public String getYaml() {
        return Yaml.dump(this.deployment);
    }
    
    @Override
    public void refresh() throws KubernetesManagerException {
       throw new UnsupportedOperationException("Not developed yet"); //TODO
    }

    @Override
    public List<IPodLog> getPodLogs(String container) throws KubernetesManagerException {
        if (deployment.getSpec() == null || deployment.getSpec().getSelector() == null) {
            throw new KubernetesManagerException("Missing Selector");
        }

        return getPodLogs(this.namespace.getCluster().getApi(), this.deployment.getSpec().getSelector(), this.namespace.getId(), container);
    }
}
