<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes.spi;

import javax.validation.constraints.NotNull;

import dev.galasa.kubernetes.IKubernetesNamespace;

/**
 * Provides the SPI for the Kubernetes Manager for Manager to Manager communication.
 * Should not be used by Test code
 * 
 * @author Michael Baylis
 *
 */
public interface IKubernetesManagerSpi {

    /**
     * Retrieve the Kubernetes Namespace that has a specific tag.
     * 
     * @param namespaceTag The tag to be used
     * @return {@link dev.galasa.kubernetes.IKubernetesNamespace} if the tag is found, or null if not
     */
    IKubernetesNamespace getNamespaceByTag(@NotNull String namespaceTag);

}
