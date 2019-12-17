package dev.galasa.docker;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.FieldSetter;

import dev.galasa.docker.internal.DockerEnvironment;
import dev.galasa.docker.internal.DockerManagerImpl;
import dev.galasa.docker.internal.DockerServerImpl;
import dev.galasa.docker.internal.IDockerEnvironment;
import dev.galasa.framework.spi.IDynamicStatusStoreService;
import dev.galasa.framework.spi.IFramework;

public class DockerEnvironmentTest {

    @Mock
    IFramework framework;

    @Mock
    DockerManagerImpl dockerManager;

    @Mock
    IDynamicStatusStoreService dss;

    @Mock
    DockerServerImpl dockerServer;

    IDockerEnvironment dockerEnv;

    @Test
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(framework.getDynamicStatusStoreService("docker")).thenReturn(dss);
        dockerEnv = new DockerEnvironment(framework, dockerManager);
    }
}