package com.greethy.account;

import com.greethy.account.common.config.WebClientConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@AutoConfigureWireMock(port = 8181)
@Import(WebClientConfig.class)
@TestPropertySource(
        properties = {
                "",
                ""
        }
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeycloakUserAdapterTests {

    @Test
    void testSave_then2xxSuccess() {

    }

}
