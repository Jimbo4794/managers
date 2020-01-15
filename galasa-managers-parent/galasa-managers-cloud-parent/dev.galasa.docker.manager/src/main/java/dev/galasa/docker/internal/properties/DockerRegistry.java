package dev.galasa.docker.internal.properties;

import java.util.ArrayList;

import dev.galasa.docker.DockerManagerException;
import dev.galasa.framework.spi.ConfigurationPropertyStoreException;
import dev.galasa.framework.spi.cps.CpsProperties;

/**
 * Default Docker Registries CPS Property
 * 
 * @galasa.cps.property
 * 
 * @galasa.name docker.default.registries
 * 
 * @galasa.description An ordered list of Docker Registries to search for Images requested by Galasa Tests
 * 
 * @galasa.required No
 * 
 * @galasa.default If not provided, Docker Hub will be added
 * 
 * @galasa.valid_values A comma separated list of URLs.
 * 
 * @galasa.examples 
 * <code>docker.default.registries=https://docker.galasa.dev<br>
 * docker.default.registries=https://docker.galasa.dev,https://docker.galasa.dev</code>
 * 
 * @galasa.extra
 * In order to decouple Docker Registries from the Galasa Test, this property allows for the Docker Manager
 * to search for images.  The main reason being if the customer docker registry moves, only this property needs 
 * to change, instead of having to change the source code of lots of tests.
 * <br>
 * <br>
 * The registries are searched in order when looking for an image.  When the image is located, the search stops. 
 * <br>
 * <br>
 * If this property is provided in the CPS, the Docker Hub registry is not automatically appended. If it is required, then the Docker Hub URL must be included.
 * 
 */
public class DockerRegistry extends CpsProperties {

    public static String[] get() throws DockerManagerException {
        ArrayList<String> ids = new ArrayList<String>();
        try {
            String registryIds = getStringNulled(DockerPropertiesSingleton.cps(), "default", "registries");
            if (registryIds != null) {
				String[] list = registryIds.split(",");

				for (String i: list) {
					ids.add(i);
				}
			}
			String central = "DOCKERHUB";
			
			if (ids.isEmpty()) {
				ids.add(central);
			}

            return ids.toArray(new String[ids.size()]);
        } catch (ConfigurationPropertyStoreException e) {
            throw new DockerManagerException("Problem asking the CPS for the max slots for the docker server: " , e);
        }
    }
}