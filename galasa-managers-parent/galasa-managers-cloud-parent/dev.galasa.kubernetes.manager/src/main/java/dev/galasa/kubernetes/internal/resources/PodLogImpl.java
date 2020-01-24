<<<<<<< HEAD
=======
/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
package dev.galasa.kubernetes.internal.resources;

import dev.galasa.kubernetes.IPodLog;

<<<<<<< HEAD
=======
/**
 * A holder for the Pod Log
 * 
 * @author Michael Baylis
 *
 */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
public class PodLogImpl implements IPodLog {
    private final String name;
    private final String log;

    public PodLogImpl(String name, String log) {
        this.name = name;
        this.log  = log;
    }

    @Override
    public String getLog() {
        return this.log;
    }
<<<<<<< HEAD
=======
    
    public String getName() {
        return this.name;
    }
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed

}
