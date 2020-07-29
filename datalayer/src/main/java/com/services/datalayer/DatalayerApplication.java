package com.services.datalayer;

import com.services.datalayer.rmi.MortgageDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DatalayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatalayerApplication.class, args);
	}

}
