package dev.galasa.docker.internal.properties;

<<<<<<< HEAD
=======
import java.net.URL;

>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
import dev.galasa.docker.DockerManagerException;
import dev.galasa.docker.internal.DockerRegistryImpl;
import dev.galasa.framework.spi.ConfigurationPropertyStoreException;
import dev.galasa.framework.spi.cps.CpsProperties;

/**
 * Docker Registry Credentials CPS Property
 * 
 * @galasa.cps.property
 * 
 * @galasa.name docker.registry.[ID.]credentials
 * 
 * @galasa.description Provides the credentials of a Docker Registry that is used by the Docker Manager
 * 
 * @galasa.required Yes if the registry requires authentication. 
 * 
 * @galasa.default DOCKER
 * 
 * @galasa.valid_values A valid credentials ID.
 * 
 * @galasa.examples 
 * <code>docker.registry.LOCAL.credentials=CREDSID</code>
 * 
 * @galasa.extra
 * If the <code>docker.registry.ID.credentials</code> CPS property is missing, the Docker Manager will attempt to use
 * the credentials ID that is provided in <code>docker.registry.credentials</code>, if that is missing, then the default credentials 
 * ID of <code>DOCKER</code> will be used.
 * 
 */
public class DockerRegistryCredentials extends CpsProperties {

    public static String get(DockerRegistryImpl dockerRegistry) throws DockerManagerException {
        try {
            String credentialsKey = getStringNulled(DockerPropertiesSingleton.cps(), "registry", "credentials", dockerRegistry.getId());
            if (credentialsKey == null) {
                throw new DockerManagerException("Could not find credentials for this registry: " + dockerRegistry.getId());
            }

            return credentialsKey;
        } catch (ConfigurationPropertyStoreException e) {
            throw new DockerManagerException("Failed to access the CPS to find the credential");
        } 
    }
}
