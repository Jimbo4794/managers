package dev.galasa.docker.manager.units;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.*;

import dev.galasa.docker.DockerManagerException;
import dev.galasa.docker.DockerProvisionException;
import dev.galasa.docker.internal.DockerContainerImpl;
import dev.galasa.docker.internal.DockerExecImpl;
import dev.galasa.docker.internal.DockerImageImpl;
import dev.galasa.docker.internal.DockerManagerImpl;
import dev.galasa.docker.internal.DockerServerImpl;
import dev.galasa.docker.internal.DockerSlotImpl;
import dev.galasa.framework.spi.DynamicStatusStoreException;
import dev.galasa.framework.spi.IDynamicStatusStoreService;
import dev.galasa.framework.spi.IFramework;

public class DockerContainerTest {

    private DockerContainerImpl container;

    private final String containerID = "e5855e38b53c9c5ea60fe8545fee9957f46fa90400e61fde9b445940423163f8";

    @Mock
    IFramework framework;

    @Mock
    DockerManagerImpl dockerManager;

    @Mock
    DockerServerImpl dockerServer;

    @Mock
    DockerImageImpl image;

    @Mock
    DockerSlotImpl slot;

    @Mock
    IDynamicStatusStoreService dss;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        // dockerServer = mock(DockerServerImpl.class);
        when(framework.getDynamicStatusStoreService("docker")).thenReturn(dss);
        when(slot.getSlotName()).thenReturn("TestSlot");
        when(dockerServer.getHost()).thenReturn("Host");
        when(dss.get("server.Host.slot.TestSlot")).thenReturn("UT");

        container = new DockerContainerImpl(framework, dockerManager, "tag", dockerServer, image, true, slot);
        assertNotNull(container);
    }

    @Test
    public void testInitException() throws DynamicStatusStoreException{
        boolean caught = false;

        when(framework.getDynamicStatusStoreService("docker")).thenThrow(new DynamicStatusStoreException("Mocked Throw"));

        try{
            new DockerContainerImpl(framework, dockerManager, "tag", dockerServer, image, true, slot);
        } catch(DockerProvisionException e) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void startContainer() throws Exception {
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();

        when(dss.get("container.tag.leave.running")).thenReturn("false");
        JsonObject getContainer = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ContainerJsonResponse").getFile()));
        when(dockerServer.getContainer("GALASA_UT_tag")).thenReturn(getContainer);
        JsonObject createContainer = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ContainerJsonResponse").getFile()));
        when(dockerServer.createContainer(eq("GALASA_UT_tag"), any(JsonObject.class))).thenReturn(createContainer);
        when(dockerServer.getContainer(containerID)).thenReturn(getContainer);

       container.start();
       assertTrue(true);
    }

    @Test
    public void startContainerException() throws Exception {
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();
        boolean caught = false;

        when(dss.get("container.tag.leave.running")).thenReturn("false");
        JsonObject getContainer = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ContainerJsonResponse").getFile()));
        when(dockerServer.getContainer("GALASA_UT_tag")).thenReturn(getContainer);
        when(dockerServer.createContainer(eq("GALASA_UT_tag"), any(JsonObject.class))).thenReturn(null);

        try {
            container.start();
        } catch (DockerManagerException e) {
            caught = true;
        }
        assertTrue(caught);
    }
    @Test
    public void startContainerExceptionSecond() throws Exception {
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();
        boolean caught = false;

        when(dss.get("container.tag.leave.running")).thenReturn("false");
        JsonObject getContainer = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ContainerJsonResponse").getFile()));
        when(dockerServer.getContainer("GALASA_UT_tag")).thenReturn(getContainer);
        when(dockerServer.createContainer(eq("GALASA_UT_tag"), any(JsonObject.class))).thenThrow(new DockerManagerException());

        try {
            container.start();
        } catch (DockerManagerException e) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void stopContainer() throws Exception {
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();

        JsonObject getContainer = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ContainerJsonResponse").getFile()));
        when(dockerServer.getContainer("GALASA_UT_tag")).thenReturn(getContainer);

        container.stop();
        assertTrue(true);
    }

    @Test
    public void stopContainerException() throws Exception {
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();
        boolean caught = false;

        JsonObject getContainer = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ContainerJsonResponse").getFile()));
        when(dockerServer.getContainer("GALASA_UT_tag")).thenReturn(getContainer);
        when(dockerServer.killContainer(null)).thenThrow(new DockerManagerException());

        try{
            container.stop();
        } catch (DockerManagerException e){
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void testGetExitCode() throws Exception {
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();

        JsonObject getContainer = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ContainerJsonResponse").getFile()));
        when(dockerServer.getContainer("GALASA_UT_tag")).thenReturn(getContainer);

        container.getExitCode();
    }

    @Test
    public void testExecObject() throws Exception {
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();

        JsonObject response = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ExecResponse").getFile()));
        when(dockerServer.sendExecCommands(eq(null), any(JsonObject.class))).thenReturn(response);
        assertTrue(container.exec("ls") instanceof DockerExecImpl);
    }
    @Test
    public void testExecObjectWithTimeout() throws Exception {
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();

        JsonObject response = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ExecResponse").getFile()));
        when(dockerServer.sendExecCommands(eq(null), any(JsonObject.class))).thenReturn(response);
        assertTrue(container.exec(1000,"ls") instanceof DockerExecImpl);
    }
}