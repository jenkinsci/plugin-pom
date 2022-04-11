package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class XTest {

    @Test
    public void label() throws Exception {
        assertEquals("Whatever", X.label());
    }

}
