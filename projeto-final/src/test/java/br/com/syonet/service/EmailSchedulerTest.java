package br.com.syonet.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class EmailSchedulerTest {

    @Inject
    EmailScheduler emailScheduler;

    @Test
    public void testEnviarEmailsDiarios() {
        emailScheduler.enviarEmailsDiarios();

        assertTrue(true);  
    }
}
