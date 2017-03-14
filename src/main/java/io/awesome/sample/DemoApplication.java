package io.awesome.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired private PersonDao personDao;
	@Autowired private DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		org.apache.tomcat.jdbc.pool.DataSource tomcat = (org.apache.tomcat.jdbc.pool.DataSource) dataSource;
		System.err.println(tomcat.getDriverClassName() + ", " + tomcat.getUrl() + ", "
				+ tomcat.getUsername() + ", " + tomcat.getPassword());

		if (null == personDao.findOne(1L)) {
			Person person = new Person();
			person.setId(1L);
			personDao.save(person);
		}
	}
}

@RestController
class PersonController {
	@Autowired private PersonDao personDao;

	@GetMapping("/")
	public String hi() {
		personDao.findAll().forEach(System.out::println);
		return "hi";
	}
}

@Repository
interface PersonDao extends CrudRepository<Person, Long> { }
