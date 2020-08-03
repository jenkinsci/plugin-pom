package test;

import jenkins.model.Jenkins;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jenkins.class)
public class XTest {

    @Mock
    private Jenkins jenkins;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Jenkins.class);
        PowerMockito.when(Jenkins.get()).thenReturn(jenkins);
        PowerMockito.when(jenkins.getSystemMessage()).thenReturn("mocked");
    }

    @Test
    public void smokes() {
        assertEquals("mocked", Jenkins.get().getSystemMessage());
    }

}
