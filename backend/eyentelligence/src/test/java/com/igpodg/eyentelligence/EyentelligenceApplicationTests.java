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
import org.springframework.http.*;
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

    private final String initialContentsUser =
            "{\"id\":1,\"username\":\"user\",\"passwordHash\":\"useruseruser\"," +
                    "\"title\":null,\"firstName\":\"Anderea\",\"middleName\":null," +
                    "\"lastName\":\"Carson\",\"email\":null,\"avatarLink\":null}";

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
    public void testTeamListAll() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody())
                .isEqualTo(this.initialContents + "]");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamListOne() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team/1", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody())
                .isEqualTo("{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamListOneNonExisting() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team/0", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team/11", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testTeamListOneGibberish() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team/asdasdasd", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamAdd() {
        this.testTeamListAll();

        String teamName = this.getRandomString(this.random.nextInt(32) + 1);
        String teamType = this.random.nextBoolean() ? "T" : "O";
        String parentTeam = this.random.nextBoolean() ? "null" : "{\"id\":2}";
        String postBody = "{\"name\":\"" + teamName + "\",\"type\":\"" +
                teamType + "\",\"parentTeam\":" + parentTeam + "}";
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/team", postBody, String.class);

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

    @Test
    public void testTeamAddBadName() {
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/team",
                "{\"name\":\"\",\"type\":\"O\",\"parentTeam\":null}", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = this.restTemplate.postForEntity(
                this.apiPrefix + "/team",
                "{\"name\":null,\"type\":\"O\",\"parentTeam\":null}", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testTeamAddBadType() {
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/team",
                "{\"name\":\"NAME\",\"type\":\"Z\",\"parentTeam\":null}", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamAddNoParentTeam() {
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/team",
                "{\"name\":\"NAME\",\"type\":\"T\"}", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/team/11");
        Assertions.assertThat(response.getBody()).isEqualTo(
                "{\"id\":11,\"name\":\"NAME\",\"type\":\"T\",\"parentTeam\":null}");
    }

    @Test
    public void testTeamAddMissingNameOrType() {
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/team",
                "{\"type\":\"T\"}", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = this.restTemplate.postForEntity(
                this.apiPrefix + "/team",
                "{\"name\":\"NAME\"}", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyName() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/1", HttpMethod.PUT,
                new HttpEntity<>("{\"name\":\"NAME-NEW\"}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/team/1");
        Assertions.assertThat(response.getBody()).isEqualTo(
                "{\"id\":1,\"name\":\"NAME-NEW\",\"type\":\"T\",\"parentTeam\":null}");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyUniform() {
        this.testTeamModifyName();

        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team/1", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody())
                .isEqualTo("{\"id\":1,\"name\":\"NAME-NEW\",\"type\":\"T\",\"parentTeam\":null}");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyType() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/1", HttpMethod.PUT,
                new HttpEntity<>("{\"type\":\"O\"}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/team/1");
        Assertions.assertThat(response.getBody()).isEqualTo(
                "{\"id\":1,\"name\":\"Zathin\",\"type\":\"O\",\"parentTeam\":null}");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyNameAndType() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/1", HttpMethod.PUT,
                new HttpEntity<>("{\"name\":\"NAME-NEW\",\"type\":\"O\"}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/team/1");
        Assertions.assertThat(response.getBody()).isEqualTo(
                "{\"id\":1,\"name\":\"NAME-NEW\",\"type\":\"O\",\"parentTeam\":null}");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyNonExisting() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/0", HttpMethod.PUT,
                new HttpEntity<>("{\"name\":\"NAME-NEW\"}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyParentTeam() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/1", HttpMethod.PUT,
                new HttpEntity<>("{\"parentTeam\":{\"id\":4}}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/team/1");
        Assertions.assertThat(response.getBody()).isEqualTo(
                "{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":" +
                        "{\"id\":4,\"name\":\"Tres-Zap\",\"type\":\"O\",\"parentTeam\":" +
                        "{\"id\":3,\"name\":\"Tresom\",\"type\":\"T\",\"parentTeam\":null}}}");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyParentTeamNoId() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/1", HttpMethod.PUT,
                new HttpEntity<>("{\"parentTeam\":{\"field\":1}}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyParentTeamIdTooBig() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/1", HttpMethod.PUT,
                new HttpEntity<>("{\"parentTeam\":{\"id\":9999}}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyParentTeamIdRecursive() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/10", HttpMethod.PUT,
                new HttpEntity<>("{\"parentTeam\":{\"id\":10}}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamDeleteNonExisting() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/0", HttpMethod.DELETE,
                new HttpEntity<>(""), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamDelete() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/10", HttpMethod.DELETE,
                new HttpEntity<>(""), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        response = this.restTemplate.exchange(
                this.apiPrefix + "/team/10", HttpMethod.DELETE,
                new HttpEntity<>(""), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        String expected = this.initialContents.substring(0,
                this.initialContents.lastIndexOf(",{\"id\":10,")) + "]";
        response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(expected);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamDeleteCascade() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/3", HttpMethod.DELETE,
                new HttpEntity<>(""), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        String expected =
                "[{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}," +
                "{\"id\":2,\"name\":\"Rank\",\"type\":\"O\",\"parentTeam\":" +
                "{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}}," +
                "{\"id\":8,\"name\":\"Keylex\",\"type\":\"T\",\"parentTeam\":null}," +
                "{\"id\":10,\"name\":\"Daltfresh\",\"type\":\"O\",\"parentTeam\":" +
                "{\"id\":2,\"name\":\"Rank\",\"type\":\"O\",\"parentTeam\":" +
                "{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}}}]";
        response = this.restTemplate.getForEntity(
                this.apiPrefix + "/team", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    public void testTeamDeleteGibberish() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/asdasdasd", HttpMethod.DELETE,
                new HttpEntity<>(""), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testUserListAll() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.apiPrefix + "/user", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody())
                .isEqualTo("[" + this.initialContentsUser + "]");
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testUserListOne() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.apiPrefix + "/user/1", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody())
                .isEqualTo(this.initialContentsUser);
    }

    @Test
    public void testUserAdd() {
        String firstName = this.getRandomString(this.random.nextInt(15) + 1);
        String middleName = this.getRandomString(this.random.nextInt(15) + 1);
        String lastName = this.getRandomString(this.random.nextInt(15) + 1);
        String postBody = "{\"firstName\":\"" + firstName + "\",\"middleName\":\"" +
                middleName + "\",\"lastName\":\"" + lastName + "\"}";
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/user", postBody, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/user/1");
        Assertions.assertThat(response.getBody()).isEqualTo(
                "{\"id\":1,\"username\":\"user\",\"passwordHash\":\"useruseruser\"," +
                        "\"title\":null,\"firstName\":\"" + firstName + "\"," +
                        "\"middleName\":\"" + middleName + "\",\"lastName\":\"" + lastName + "\"," +
                        "\"email\":null,\"avatarLink\":null}");
    }

    @Test
    public void testUserAddNoMiddleName() {
        String firstName = this.getRandomString(this.random.nextInt(15) + 1);
        String lastName = this.getRandomString(this.random.nextInt(15) + 1);
        String postBody = "{\"firstName\":\"" + firstName + "\"," +
                "\"middleName\":null,\"lastName\":\"" + lastName + "\"}";
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/user", postBody, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<String> locations = response.getHeaders().get("Location");
        Assertions.assertThat(locations).isNotNull();
        Assertions.assertThat(locations.get(0)).isEqualTo("/user/1");
        Assertions.assertThat(response.getBody()).isEqualTo(
                "{\"id\":1,\"username\":\"user\",\"passwordHash\":\"useruseruser\"," +
                        "\"title\":null,\"firstName\":\"" + firstName + "\"," +
                        "\"middleName\":null,\"lastName\":\"" + lastName + "\"," +
                        "\"email\":null,\"avatarLink\":null}");
    }

    @Test
    public void testUserAddMissingFirstName() {
        String middleName = this.getRandomString(this.random.nextInt(15) + 1);
        String lastName = this.getRandomString(this.random.nextInt(15) + 1);
        String postBody = "{\"middleName\":\"" + middleName + "\"," +
                "\"lastName\":\"" + lastName + "\"}";
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/user", postBody, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUserAddMissingLastName() {
        String firstName = this.getRandomString(this.random.nextInt(15) + 1);
        String postBody = "{\"firstName\":\"" + firstName + "\"}";
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/user", postBody, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUserAddMalformed() {
        String postBody = "{\"firstName\":\"    \",\"lastName\":\"LAST\"}";
        ResponseEntity<String> response = this.restTemplate.postForEntity(
                this.apiPrefix + "/user", postBody, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        postBody = "{\"firstName\":\"FIRST\",\"lastName\":null}";
        response = this.restTemplate.postForEntity(
                this.apiPrefix + "/user", postBody, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Sql("classpath:schema.sql") // remove this if you want to see the effects in DB
    @Test
    public void testDeleteParentById() {
        String expectedResponse = "{\"id\":4,\"name\":\"Tres-Zap\",\"type\":\"O\"," +
                "\"parentTeam\":null}";

        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/4/parent/3", HttpMethod.DELETE,
                new HttpEntity<>("{}"), String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        Assertions.assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    // ------------------------------------------------------------
    // ------------------------- TODO FIX -------------------------
    // ------------------------------------------------------------

    @Sql("classpath:schema.sql")
    @Test
    public void testTeamModifyNested() {
        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/3", HttpMethod.PUT,
                new HttpEntity<>("{\"parentTeam\":{\"id\":4}}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Sql("classpath:schema.sql")
    @Test
    public void testTripleOptional() {
        String expectedResponse1 = "{\"id\":2,\"name\":\"Rank\",\"type\":\"O\"," +
                "\"parentTeam\":{\"id\":1,\"name\":\"Zathin\",\"type\":\"T\",\"parentTeam\":null}}";

        String expectedResponse2 = "{\"id\":2,\"name\":\"Rank\",\"type\":\"O\"," +
                "\"parentTeam\":null}";

        ResponseEntity<String> response = this.restTemplate.exchange(
                this.apiPrefix + "/team/2", HttpMethod.PUT,
                new HttpEntity<>("{}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody()).isEqualTo(expectedResponse1);

        response = this.restTemplate.exchange(
                this.apiPrefix + "/team/2", HttpMethod.PUT,
                new HttpEntity<>("{\"parentTeam\":null}"), String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody()).isEqualTo(expectedResponse2);
    }
}
