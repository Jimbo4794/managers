<<<<<<< HEAD
package dev.galasa.kubernetes;

=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2019.
 */
package dev.galasa.kubernetes;

/**
 * Abstracts a Kubernetes Resource, so that the test is not dependent of whatever Kubernetes client the 
 * Kubernetes Manager decides to use.
 * 
 * @author Michael Baylis
 *
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
public interface IResource {

    public enum TYPE {
        Deployment,
        StatefulSet,
        Secret,
        Service,
        ConfigMap,
        PersistentVolumeClaim
    }
    
<<<<<<< HEAD
    public String getName();
    public TYPE getType();
    public String getYaml();
    
=======
    /**
     * @return the name of the resource from metadata.name
     */
    public String getName();
    /**
     * @return The type of the resource as the Kubernetes Manager knows it
     */
    public TYPE getType();
    
    /**
     * @return Retrieve the raw YAML, this is populated on create resource, after the resource has been created, so should
     * have the status
     */
    public String getYaml();
    
    /**
     * refresh the raw YAML that is provided by getYaml()
     * 
     * @throws KubernetesManagerException If there is a comms problem to the Kubernetes Cluster
     */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
    public void refresh() throws KubernetesManagerException;

}
