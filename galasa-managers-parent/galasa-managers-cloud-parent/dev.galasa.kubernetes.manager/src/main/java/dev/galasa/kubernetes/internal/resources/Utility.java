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
import java.util.Map;
import java.util.Map.Entry;

import dev.galasa.kubernetes.KubernetesManagerException;
import io.kubernetes.client.openapi.models.V1LabelSelector;
import io.kubernetes.client.openapi.models.V1LabelSelectorRequirement;

<<<<<<< HEAD
public class Utility {

    
=======
/**
 * Utility class for routines that are missing the kubernetes client
 * 
 * @author Michael Baylis
 *
 */
public class Utility {

    
    /**
     * Convert a label selector to a string that can be used on the api.  only supports matchlabels at the moment
     * 
     * @param labelSelector The labelselector to convert
     * @return A string conversion
     * @throws KubernetesManagerException If a matchexpression is present
     */
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
    public static String convertLabelSelector(V1LabelSelector labelSelector) throws KubernetesManagerException {
        if (labelSelector == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        
        List<V1LabelSelectorRequirement> expressions = labelSelector.getMatchExpressions();
        if (expressions != null && !expressions.isEmpty()) {
            throw new KubernetesManagerException("The V1LabelSelector converter cannot support expressions at this time");
        }
        
        Map<String, String> matchLabels = labelSelector.getMatchLabels();
        if (matchLabels == null || matchLabels.isEmpty()) {
            return null;
        }
        
        for(Entry<String, String> entry : matchLabels.entrySet()) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
        }
        
        return sb.toString();
    }
    
    
    
}
