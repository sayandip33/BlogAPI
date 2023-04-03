package com.myblog.myblog;

import com.myblog.myblog.entity.Post;
import com.myblog.myblog.entity.Role;
import com.myblog.myblog.repository.PostRepository;
import com.myblog.myblog.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyblogApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(MyblogApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
//		Role adminRole = new Role();
//		adminRole.setName("ROLE_ADMIN");
//		roleRepository.save(adminRole);
//		Role userRole = new Role();
//		userRole.setName("ROLE_USER");
//		roleRepository.save(userRole);
	}
}
