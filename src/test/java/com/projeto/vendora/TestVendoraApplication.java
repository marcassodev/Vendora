package com.projeto.vendora;

import org.springframework.boot.SpringApplication;

public class TestVendoraApplication {

	public static void main(String[] args) {
		SpringApplication.from(VendoraApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
