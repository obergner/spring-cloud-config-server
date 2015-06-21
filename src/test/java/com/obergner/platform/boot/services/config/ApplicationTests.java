package com.obergner.platform.boot.services.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigServerApplication.class)
@WebIntegrationTest({"server.port=0", "consul.url=http://localhost:38500"})
public class ApplicationTests {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private SecurityProperties security;

    @Test
    public void configurationAvailable() {
        final ResponseEntity<Map> entity = new TestRestTemplate("user", this.security.getUser().getPassword()).getForEntity("http://localhost:" + this.port + "/app/cloud", Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void envPostAvailable() {
        final MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        final ResponseEntity<Map> entity = new TestRestTemplate("user", this.security.getUser().getPassword()).postForEntity("http://localhost:" + this.port + "/admin/env", form, Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void envPostSecure() {
        final MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        final ResponseEntity<Map> entity = new TestRestTemplate().postForEntity("http://localhost:" + this.port + "/admin/env", form, Map.class);
        assertEquals(HttpStatus.UNAUTHORIZED, entity.getStatusCode());
    }
}
