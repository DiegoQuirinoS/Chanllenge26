package com.diego.n26;

import com.diego.n26.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class N26ApplicationTests {

	private Random random = new Random();

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldBe204() {
		ResponseEntity<String> responseBody = this.restTemplate
				.postForEntity("/transactions",
						new Transaction(random.nextDouble(), 1478192204000l),
						String.class);
		assertEquals(HttpServletResponse.SC_NO_CONTENT, responseBody.getStatusCode().value());

	}

	@Test
	public void shouldBe201() {

		Transaction transaction = new Transaction(random.nextDouble(), Instant.now().toEpochMilli());

		ResponseEntity<String> responseBody = this.restTemplate
				.postForEntity("/transactions", transaction, String.class);

		assertEquals(HttpServletResponse.SC_CREATED, responseBody.getStatusCode().value());

	}

    @Test
    public void shouldGetStatisticsEqualTest() {

        String current = this.restTemplate
                .getForObject("/statistics", String.class);

        assertFalse(current.isEmpty());

    }
}
