<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes.internal.resources;

import dev.galasa.kubernetes.ISecret;
import dev.galasa.kubernetes.KubernetesManagerException;
import dev.galasa.kubernetes.internal.KubernetesNamespaceImpl;
import io.kubernetes.client.openapi.models.V1Secret;
import io.kubernetes.client.util.Yaml;

<<<<<<< HEAD
=======
/**
 * Secret implementation
 * 
 * @author Michael Baylis
 *
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
public class SecretImpl implements ISecret {
    
    private final V1Secret secret;

    public SecretImpl(KubernetesNamespaceImpl namespace, V1Secret secret) {
        this.secret = secret;
    }

    @Override
    public String getName() {
        return secret.getMetadata().getName();
    }

    @Override
    public TYPE getType() {
        return TYPE.Secret;
    }

    @Override
    public String getYaml() {
        return Yaml.dump(this.secret);
    }
    
    @Override
    public void refresh() throws KubernetesManagerException {
       throw new UnsupportedOperationException("Not developed yet"); //TODO
    }

}
