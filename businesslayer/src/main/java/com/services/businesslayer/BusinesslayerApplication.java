package com.services.businesslayer;

import com.services.businesslayer.rmi.MortgageDetailsRMI;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;

@Slf4j
@SpringBootApplication
public class BusinesslayerApplication {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {

		MortgageDetailsRMI mortgageDetailsRMI = SpringApplication.run(BusinesslayerApplication.class, args)
				.getBean(MortgageDetailsRMI.class);

		runRMIFunctions(mortgageDetailsRMI);

	}

	public static void runRMIFunctions(MortgageDetailsRMI mortgageDetailsRMI){
		logger.info("Current Count - "+mortgageDetailsRMI.getCount());
	}

}
