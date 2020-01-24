<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes.internal.resources;

import dev.galasa.kubernetes.IPersistentVolumeClaim;
import dev.galasa.kubernetes.KubernetesManagerException;
import dev.galasa.kubernetes.internal.KubernetesNamespaceImpl;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaim;
import io.kubernetes.client.util.Yaml;

<<<<<<< HEAD
=======
/**
 * PersistentVolumeClaim implementation
 * 
 * @author Michael Baylis
 *
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
public class PersistentVolumeClaimImpl implements IPersistentVolumeClaim {
    
    private final V1PersistentVolumeClaim pvc;

    public PersistentVolumeClaimImpl(KubernetesNamespaceImpl namespace, V1PersistentVolumeClaim pvc) {
        this.pvc = pvc;
    }

    @Override
    public String getName() {
        return pvc.getMetadata().getName();
    }

    @Override
    public TYPE getType() {
        return TYPE.PersistentVolumeClaim;
    }

    @Override
    public String getYaml() {
        return Yaml.dump(this.pvc);
    }
    
    @Override
    public void refresh() throws KubernetesManagerException {
       throw new UnsupportedOperationException("Not developed yet"); //TODO
    }
}
