package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleBotApplication {

		public static void main(String[] args) {
		SpringApplication.run(ExampleBotApplication.class, args);
//		try { бенч по потреблению памяти
//			long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//
//			while (true) {
//				Thread.sleep(20000);
//				long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//				long actualMemUsed=afterUsedMem-beforeUsedMem;
//				System.out.println(actualMemUsed);
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		}

}
