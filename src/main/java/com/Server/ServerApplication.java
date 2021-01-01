
package com.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>Server Car-Rental</h1>
 * Spring Boot server Car-Rental to serve the authorization and database operation.
 * @author  Krystian Cwioro Kamil Bieniasz Damian Mierzynski
 * @version  1.0
 * @since 2020-12-29
 */

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
//spring.jpa.hibernate.ddl-auto=create-drop
	/*
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-javadoc-plugin</artifactId>
				<version>3.2.0</version>
			</plugin>
		</plugins>
	</build>
	<reporting>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-javadoc-plugin</artifactId>
				<version>3.2.0</version>
			</plugin>
		</plugins>
	</reporting>
	 */
}
