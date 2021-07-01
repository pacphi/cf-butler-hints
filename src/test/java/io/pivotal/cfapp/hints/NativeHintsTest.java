package io.pivotal.cfapp.hints;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.nativex.type.TypeSystem;

public class NativeHintsTest {
    
    private static Logger log = LoggerFactory.getLogger(NativeHintsTest.class);

    private static final String M2_HOME = System.getProperty("user.home") + "/.m2/repository";
    private static final String CF_PATH = "/org/cloudfoundry";
    private static final String CF_JAVA_CLIENT_VERSION = "5.5.0.RELEASE";
    private static final String CF_CLIENT_PATH = M2_HOME + CF_PATH + "/cloudfoundry-client/" + CF_JAVA_CLIENT_VERSION + "/cloudfoundry-client-" + CF_JAVA_CLIENT_VERSION + ".jar";
    private static final String TARGET_CF_CLIENT_PATH = "./target/cloudfoundry-client-" + CF_JAVA_CLIENT_VERSION + ".jar";
    private static final String CLASSES = new File("./target/classes").toString();
    private static final String TEST_CLASSES = new File("./target/test-classes").toString();

    private static List<String> CLASSPATH = Arrays.asList(CLASSES, TEST_CLASSES, TARGET_CF_CLIENT_PATH);

    static {
        try {
            FileUtils.copyFile(new File(CF_CLIENT_PATH), new File(TARGET_CF_CLIENT_PATH));
        } catch (IOException ioe) {
            System.exit(1);
        }
    }

    @Test
    public void sanityCheck() {
        NativeHints hints = new NativeHints();
        TypeSystem typeSystem = new TypeSystem(CLASSPATH);
        log.debug("Hints: {}", hints.computeHints(typeSystem));
    }

}
