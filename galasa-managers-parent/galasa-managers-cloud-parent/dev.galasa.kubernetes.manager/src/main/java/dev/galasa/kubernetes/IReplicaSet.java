<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes;

import java.util.List;

<<<<<<< HEAD
=======
/**
 * Represents a resource that utilises ReplicatSets,  ie Deployment and StatefulSet
 * 
 * @author mikebyls
 *
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
public interface IReplicaSet extends IResource {

    public List<IPodLog> getPodLogs(String container) throws KubernetesManagerException;

}
