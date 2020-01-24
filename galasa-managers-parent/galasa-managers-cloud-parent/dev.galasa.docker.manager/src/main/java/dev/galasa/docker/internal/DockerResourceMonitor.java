package dev.galasa.docker.internal;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dev.galasa.framework.spi.IConfigurationPropertyStoreService;
import dev.galasa.framework.spi.IDynamicStatusStoreService;
import dev.galasa.framework.spi.IFramework;
import dev.galasa.framework.spi.IResourceManagement;

/**
 * Docker resource monitor
 * 
 * @author James Davies
 */
public class DockerResourceMonitor implements Runnable {
    private final IFramework                    framework;
    private final IResourceManagement           resourceManagement;
    private final IDynamicStatusStoreService    dss;
    private final Log                           logger = LogFactory.getLog(DockerResourceMonitor.class);
<<<<<<< HEAD
                                                                    //dss.docker.slot.default.run.L7.SLOT_L7_0=free
    private final Pattern                       slotRunPattern = Pattern.compile("^slot\\.(\\w+)\\.run\\.(\\w+)\\.(\\w+)");

=======
    private final Pattern                       slotRunPattern = Pattern.compile("^slot\\.run\\.(\\w+)\\.engine\\.(\\w+)\\.slot\\.(\\w+)$");
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed

   /**
    * Docker resource monitor

    * @param framework
    * @param resourceManagement
    * @param dss
    * @param dockerResourceManagement
    * @param cps
    */
    public DockerResourceMonitor(IFramework framework, IResourceManagement resourceManagement, IDynamicStatusStoreService dss, 
            DockerResourceManagement dockerResourceManagement, IConfigurationPropertyStoreService cps) {
        this.framework          = framework;
        this.dss                = dss;
        this.resourceManagement = resourceManagement;

        this.logger.info("Docker slot resource monitor intialised");
    }

    /**
     * Runs the docker resource monitor.
     */
    @Override
    public void run() {
        logger.info("Starting search for run slots.");

        try {
            Map<String, String> slotRuns = dss.getPrefix("slot");
			
			Set<String> activeRunNames = this.framework.getFrameworkRuns().getActiveRunNames();

			for(String key : slotRuns.keySet()) {
				Matcher matcher = slotRunPattern.matcher(key);
				if (matcher.find()) {
					String runName = matcher.group(2);

					if (!activeRunNames.contains(runName)) {
<<<<<<< HEAD
						String dockerEngine = matcher.group(1);
=======
						String dockerEngine = matcher.group(2);
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
						String slot    = matcher.group(3);

						logger.info("Discarding slot " + slot + " on docker engine " + dockerEngine + " as run " + runName + " has gone");

						try {
<<<<<<< HEAD
							DockerEnvironment.deleteStaleDssSlot(runName, dockerEngine, slot, dss);
=======
							DockerEnvironment.deleteDss(runName, dockerEngine, slot, dss);
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
						} catch(Exception e) {
							logger.error("Failed to discard slot " + slot + " on image " + dockerEngine + " as run " + runName);
						}
					}
				}
			}
        } catch (Exception e) {
            logger.error("Problem when trying run the docker resource monitor.", e);
        }
    }

    /**
     * 
     * @param runName
     */
    public void runFinishedOrDeleted(String runName) {
        try {
            Map<String,String> slotRuns = dss.getPrefix("slot.run." + runName + ".");
            for(String key : slotRuns.keySet()) {
				Matcher matcher = slotRunPattern.matcher(key);
				if (matcher.find()) {
					String dockerEngine = matcher.group(2);
					String slot    = matcher.group(3);

					logger.info("Discarding slot " + slot + " on image " + dockerEngine + " as run " + runName + " has gone");

					try {
<<<<<<< HEAD
						DockerEnvironment.deleteStaleDssSlot(runName, dockerEngine, slot, dss);
=======
						DockerEnvironment.deleteDss(runName, dockerEngine, slot, dss);
>>>>>>> 71f0c491713d22bdaae4c92c34e8aceddb5145ed
					} catch(Exception e) {
						logger.error("Failed to discard slot " + slot + " on image " + dockerEngine + " as run " + runName);
					}
				}
			}
        } catch (Exception e) {
            logger.error("Failed to delete stale dss properties for runtName: " + runName, e);
        }
    }
    
}