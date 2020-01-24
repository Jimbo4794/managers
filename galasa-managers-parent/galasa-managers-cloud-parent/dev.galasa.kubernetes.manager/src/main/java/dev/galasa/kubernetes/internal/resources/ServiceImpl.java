<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes.internal.resources;

import java.net.InetSocketAddress;

import javax.validation.constraints.NotNull;

import dev.galasa.kubernetes.IService;
import dev.galasa.kubernetes.KubernetesManagerException;
import dev.galasa.kubernetes.internal.KubernetesNamespaceImpl;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServicePort;
import io.kubernetes.client.util.Yaml;

<<<<<<< HEAD
=======
/**
 * Service implementations
 * 
 * @author Michael Baylis
 *
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
public class ServiceImpl implements IService {
    
    private final KubernetesNamespaceImpl namespace;
    private final V1Service service;

    public ServiceImpl(KubernetesNamespaceImpl namespace, V1Service service) {
        this.namespace = namespace;
        this.service   = service;
    }

    @Override
    public String getName() {
        return service.getMetadata().getName();
    }

    @Override
    public TYPE getType() {
        return TYPE.Service;
    }

    @Override
    public String getYaml() {
        return Yaml.dump(this.service);
    }
    
    @Override
    public void refresh() throws KubernetesManagerException {
       throw new UnsupportedOperationException("Not developed yet"); //TODO
    }

    @Override
    @NotNull
    public InetSocketAddress getSocketAddressForPort(int port) throws KubernetesManagerException {
        String nodePortProxyHostname = this.namespace.getCluster().getNodePortProxyHostname();

        if (this.service.getSpec() != null && this.service.getSpec().getPorts() != null) {
            for(V1ServicePort specPort : this.service.getSpec().getPorts()) {
                if (specPort.getPort() == port) {
                    Integer nodePort = specPort.getNodePort();
                    if (nodePort != null) {
                        return new InetSocketAddress(nodePortProxyHostname, nodePort);
                    }
                }
            }
        }
        
        throw new KubernetesManagerException("Missing external port number for nodeport " + Integer.toString(port));
    }
}
