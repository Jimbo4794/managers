<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes;

import javax.validation.constraints.NotNull;

/**
<<<<<<< HEAD
 * Kubernetes Namespace 
=======
 * This is the main interface to a provisioned Kubernetes Namespace on an infrastructure cluster.
 * Access to the Object is via the {@link KubernetesNamespace} annotation or the SPI. 
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
 *  
 * @author Michael Baylis
 *
 */
public interface IKubernetesNamespace {

<<<<<<< HEAD
=======
    /**
     * Create a Resoource in Kubernetes.  The Manager will append certain information to the YAML before
     * creating the resource, like the run name and possibly storage classes.
     * 
     * @param yaml
     * @return
     * @throws KubernetesManagerException
     */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
    @NotNull
    public IResource createResource(@NotNull String yaml) throws KubernetesManagerException;

    /**
     * @return the Full ID of the namespace in the form cluserid/namespaceid
     */
    public String getFullId();
    
<<<<<<< HEAD
    public void saveNamespaceConfiguration() throws KubernetesManagerException;
=======
    /**
     * Save all the supported resources to stored artifacts in the default folder, along with pod logs
     * 
     * @throws KubernetesManagerException if there are problems saving the entire configuration to storedartifacts
     */
    public void saveNamespaceConfiguration() throws KubernetesManagerException;
    /**
     * Save all the supported resources to stored artifacts in the default folder, along with pod logs
     * 
     * @param storedArtifactPath The path in stored artifacts to save the configuration in.  If null, will default.
     * @throws KubernetesManagerException if there are problems saving the entire configuration to storedartifacts
     */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
    public void saveNamespaceConfiguration(String storedArtifactPath) throws KubernetesManagerException;



}