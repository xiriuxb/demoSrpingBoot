package com.jorgetrujillo.demo;

import static org.junit.Assert.*;
import com.jorgetrujillo.demo.dtos.ClientDto;
import com.jorgetrujillo.demo.dtos.CreateClientDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApirestApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void testClientCreationOk() {
//		Remember, idNumber is Unique so needed to change every execution
		ResponseEntity<ClientDto> response = testRestTemplate.postForEntity(
				"/clients",
				new CreateClientDto(
						"Test Uno",
						"RUC",
						"040055",
						"099999",
						"e@e.e",
						"Guayas",
						"Gyauakill",
						"Calle 1 y 2"
						),
				ClientDto.class);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals(response.getBody().getClass(),ClientDto.class);
	}

	@Test
	void testClientCreationWhitSameIdNumber(){
		ResponseEntity<ClientDto> response = testRestTemplate.postForEntity(
				"/clients",
				new CreateClientDto(
						"Test Uno",
						"RUC",
						"040055",
						"099999",
						"e@e.e",
						"Guayas",
						"Gyauakill",
						"Calle 1 y 2"
				),
				ClientDto.class);
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
	}

	@Test
	void testClientCreationWhitoutAttrib(){
		ResponseEntity<ClientDto> response = testRestTemplate.postForEntity(
				"/clients",
				new CreateClientDto(
						"Test Uno",
						"RUC",
						"040055",
						"",
						"e@e.e",
						"Guayas",
						"Gyauakill",
						"Calle 1 y 2"
				),
				ClientDto.class);
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
	}

	@Test
	void testClientCreationWhitDiffIdType(){
		ResponseEntity<ClientDto> response = testRestTemplate.postForEntity(
				"/clients",
				new CreateClientDto(
						"Test Uno",
						"RUC",
						"040055",
						"",
						"e@e.e",
						"Guayas",
						"Gyauakill",
						"Calle 1 y 2"
				),
				ClientDto.class);
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
	}

	@Test
	void getClientsBySearchFound(){
		ResponseEntity<ClientDto[]> response = testRestTemplate.getForEntity(
				"/clients-search?search=Test",
				ClientDto[].class
		);
		ClientDto[] expectedClient = {
				new ClientDto(
						Long.parseLong("1"),
						"Test Uno",
						"RUC",
						"0400",
						"099999",
						"e@e.e",
						"Guayas",
						"Guayakill",
						"Calle 1 y 2"
				),
		};
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(expectedClient, response.getBody());
	}

	@Test
	void testUpdateOk(){

	}

}
