package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.Users;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UsersRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	@Autowired
	private UsersRepository usersRepository;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UsersRepository usersRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.usersRepository = usersRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//clasesAnteriores();
		saveUsersInDataBase();
	}
	private void saveUsersInDataBase(){
		Users user1 = new Users("John", "john@mail.com", LocalDate.of(2023,05,20));
		Users user2 = new Users("user2", "user2@mail.com", LocalDate.of(2023,06,20));
		Users user3 = new Users("user3", "user3@mail.com", LocalDate.of(2023,01,20));
		Users user4 = new Users("user4", "user4@mail.com", LocalDate.of(2023,07,20));
		Users user5 = new Users("user5", "user5@mail.com", LocalDate.of(2023,01,20));
		Users user6 = new Users("user6", "user6@mail.com", LocalDate.of(2023,02,20));
		Users user7 = new Users("user7", "user7@mail.com", LocalDate.of(2023,03,20));
		Users user8 = new Users("user8", "user8@mail.com", LocalDate.of(2023,05,20));
		Users user9 = new Users("user9", "user9@mail.com", LocalDate.of(2023,06,20));
		Users user10 = new Users("user10", "user10@mail.com", LocalDate.of(2023,04,20));

		List<Users> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);

		list.stream().forEach(usersRepository::save);
	}

	private void clasesAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " Password: " + userPojo.getPassword());
		try{
			int value = 10/0;
			LOGGER.debug("Mi valor: " + value);
		} catch (Exception e){
			LOGGER.error("Esto es un error del aplicativo");
		}
	}
}
