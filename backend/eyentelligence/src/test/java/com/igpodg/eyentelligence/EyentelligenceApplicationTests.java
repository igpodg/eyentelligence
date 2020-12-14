package com.igpodg.eyentelligence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EyentelligenceApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	private final String apiPrefix = "https://localhost:9090/";

	@BeforeAll
	static void contextLoads() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, new TrustManager[] { new X509TrustManager() {
			@Override public void checkClientTrusted(X509Certificate[] c, String s) { }
			@Override public void checkServerTrusted(X509Certificate[] c, String s) { }
			@Override public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
		} }, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	@Sql("classpath:schema.sql")
	@Test
	public void greetingShouldReturnDefaultMessage() {
		Assertions.assertThat(this.restTemplate.getForObject(this.apiPrefix, String.class))
				.contains("Hello, World");
	}
}
