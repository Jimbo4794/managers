package dev.galasa.docker.manager.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.galasa.docker.internal.DockerContainerImpl;
import dev.galasa.docker.internal.DockerExecImpl;
import dev.galasa.docker.internal.DockerManagerImpl;
import dev.galasa.docker.internal.DockerServerImpl;
import dev.galasa.framework.spi.IFramework;

public class DockerExecTest {

    private DockerExecImpl exec;

    @Mock
    IFramework framework;

    @Mock
    DockerManagerImpl dockerManager;

    @Mock
    DockerContainerImpl dockerContainer;

    @Mock
    DockerServerImpl dockerServer;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        String[] commands = {"ls"};
        JsonParser parser = new JsonParser();
        ClassLoader classLoader = this.getClass().getClassLoader();

        when(dockerContainer.getDockerServerImpl()).thenReturn(dockerServer);
        JsonObject getContainer = (JsonObject)parser.parse(new FileReader(classLoader.getResource("ContainerJsonResponse").getFile()));
        when(dockerServer.sendExecCommands(eq(null), any(JsonObject.class))).thenReturn(getContainer);
        exec = new DockerExecImpl(framework, dockerManager, dockerContainer, 1000, commands);
    }

    // @Test
    // public void basicCommand() throws Exception {
        
    // }

    @Test
    public void waitForExec() throws Exception {
        assertFalse(exec.waitForExec());
    }

    @Test
    public void isFinished() throws Exception {
        assertFalse(exec.isFinished());
    }

    @Test
    public void getExitCode() throws Exception {
        assertEquals(-1,exec.getExitCode());
    }
}