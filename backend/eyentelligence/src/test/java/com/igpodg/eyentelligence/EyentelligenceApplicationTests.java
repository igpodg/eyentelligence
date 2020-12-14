package com.igpodg.eyentelligence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.jdbc.Sql;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.sql.DataSource;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EyentelligenceApplicationTests {
    private TestRestTemplate restTemplate;

    private final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ALPHABET_LENGTH = 62;

    private final SecureRandom random = new SecureRandom();

    private final String apiPrefix = "https://localhost:9090";

    private final String initialContents =
            "[{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}," +
                    "{\"id\":2,\"name\":\"Rank\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}}," +
                    "{\"id\":3,\"name\":\"Tresom\",\"type\":\"T\",\"parentTeam\":null}," +
                    "{\"id\":4,\"name\":\"Tres-Zap\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":3,\"name\":\"Tresom\",\"type\":\"T\",\"parentTeam\":null}}," +
                    "{\"id\":5,\"name\":\"Hatity\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":4,\"name\":\"Tres-Zap\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":3,\"name\":\"Tresom\",\"type\":\"T\",\"parentTeam\":null}}}," +
                    "{\"id\":6,\"name\":\"Toughjoyfax\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":5,\"name\":\"Hatity\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":4,\"name\":\"Tres-Zap\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":3,\"name\":\"Tresom\",\"type\":\"T\",\"parentTeam\":null}}}}," +
                    "{\"id\":7,\"name\":\"Ventosanzap\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":4,\"name\":\"Tres-Zap\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":3,\"name\":\"Tresom\",\"type\":\"T\",\"parentTeam\":null}}}," +
                    "{\"id\":8,\"name\":\"Keylex\",\"type\":\"T\",\"parentTeam\":null}," +
                    "{\"id\":9,\"name\":\"Vagram\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":5,\"name\":\"Hatity\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":4,\"name\":\"Tres-Zap\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":3,\"name\":\"Tresom\",\"type\":\"T\",\"parentTeam\":null}}}}," +
                    "{\"id\":10,\"name\":\"Daltfresh\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":2,\"name\":\"Rank\",\"type\":\"O\",\"parentTeam\":" +
                    "{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}}}";

    @BeforeAll
    static void contextLoads() throws NoSuchAlgorithmException, KeyManagementException {
        // enable unsafe SSL for testing purposes
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, new TrustManager[] { new X509TrustManager() {
            @Override public void checkClientTrusted(X509Certificate[] c, String s) { }
            @Override public void checkServerTrusted(X509Certificate[] c, String s) { }
            @Override public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        } }, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    EyentelligenceApplicationTests() {
        this.restTemplate = new TestRestTemplate();

        // no errors on unsuccessful HTTP status codes
        //this.restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
        //	@Override
        //	public boolean hasError(HttpStatus statusCode) {
        //		return false;
        //	}
        //});

        // add Content-Type on each request
        this.restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders().setContentType(
                            MediaType.parseMediaType("application/json;charset=utf-8"));
                    return execution.execute(request, body);
                })
        );
    }

    private String getRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            stringBuilder.append(this.alphabet.charAt(
                    this.random.nextInt(this.ALPHABET_LENGTH)));
        return stringBuilder.toString();
    }

    @AfterAll
    static void contextUnloads(@Autowired DataSource dataSource) throws SQLException {
        // clean up the database
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("schema.sql"));
        }
    }

    // ------------------------------------------------------------
    // ------------------------ BEGIN TEST ------------------------
    // ------------------------------------------------------------

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamAdd() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team", String.class);
        Assertions.assertThat(response.getBody())
                .isEqualTo(this.initialContents + "]");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        String teamName = this.getRandomString(this.random.nextInt(32) + 1);
        String teamType = this.random.nextBoolean() ? "T" : "O";
        String parentTeam = this.random.nextBoolean() ? "null" : "{\"id\":2}";
        String postBody = "{\"name\":\"" + teamName + "\",\"type\":\"" +
                teamType + "\",\"parentTeam\":" + parentTeam + "}";
        response = this.restTemplate.postForEntity(this.apiPrefix + "/team", postBody, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/team/11");
        if (parentTeam.equals("null"))
            Assertions.assertThat(response.getBody()).isEqualTo(
                    "{\"id\":11,\"name\":\"" + teamName + "\",\"type\":\"" + teamType +
                            "\",\"parentTeam\":null}");
        else
            Assertions.assertThat(response.getBody()).isEqualTo(
                    "{\"id\":11,\"name\":\"" + teamName + "\",\"type\":\"" + teamType +
                            "\",\"parentTeam\":" +
                            "{\"id\":2,\"name\":\"Rank\",\"type\":\"O\",\"parentTeam\":" +
                            "{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}}}");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamAddAnother() {
        this.testTeamAdd();

        String expectedResponse = "{\"id\":12,\"name\":\"NAME\",\"type\":\"T\",\"parentTeam\":null}";
        String expectedAll = this.restTemplate.getForObject(this.apiPrefix + "/team", String.class);
        expectedAll = expectedAll.substring(0, expectedAll.length() - 1);
        expectedAll += "," + expectedResponse + "]";

        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/team",
                "{\"name\":\"NAME\",\"type\":\"T\",\"parentTeam\":null}", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/team/12");
        Assertions.assertThat(response.getBody()).isEqualTo(expectedResponse);

        response = this.restTemplate.getForEntity(this.apiPrefix + "/team", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(expectedAll);
    }
}
