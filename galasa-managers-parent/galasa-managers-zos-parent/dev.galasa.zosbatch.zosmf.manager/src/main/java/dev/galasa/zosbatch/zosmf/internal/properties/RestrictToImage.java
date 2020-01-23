/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2019.
 */
package dev.galasa.zosbatch.zosmf.internal.properties;

import dev.galasa.zosbatch.ZosBatchManagerException;
import dev.galasa.framework.spi.ConfigurationPropertyStoreException;
import dev.galasa.framework.spi.cps.CpsProperties;

/**
 * Restrict processing to the zOSMF server on the specified image
 * <p>
 * Use only the zOSMF server running on the image associated with the zOS Batch job
 * </p><p>
 * The property is:<br>
 * {@code zosbatch.batchjob.[imageid].restrict.to.image=true}
 * </p>
 * <p>
 * The default value is false
 * </p>
 *
 */
public class RestrictToImage extends CpsProperties {

    public static boolean get(String imageId) throws ZosBatchManagerException {
        try {
            String sysaffString = getStringNulled(ZosBatchZosmfPropertiesSingleton.cps(), "batchjob", "restrict.to.image", imageId);
            return Boolean.parseBoolean(sysaffString);
        } catch (ConfigurationPropertyStoreException e) {
            throw new ZosBatchManagerException("Problem asking the CPS for the batch job restrict to immage property for zOS image "  + imageId, e);
        }
    }

}
