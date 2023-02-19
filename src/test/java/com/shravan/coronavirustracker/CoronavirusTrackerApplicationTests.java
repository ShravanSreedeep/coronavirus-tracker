package com.shravan.coronavirustracker;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.shravan.coronavirustracker.service.CoronaVirusDataServices;

@SpringBootTest
class CoronavirusTrackerApplicationTests {

	
	private CoronaVirusDataServices cs;
	
	@BeforeEach
	void setUp() throws Exception {
		this.cs = new CoronaVirusDataServices();
	}
	@Test
	void testAllStats() {
		//List<Model> list = this.cs.getAll();
		
		assertNotNull(cs.getAll());
		
	}
	

}
