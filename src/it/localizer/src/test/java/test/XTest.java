package test;

import org.junit.Test;
import static org.junit.Assert.*;

public class XTest {

    @Test
    public void label() throws Exception {
        assertEquals("Whatever", X.label());
    }

}
