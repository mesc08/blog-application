package com.project.bloggingapp;

import com.project.bloggingapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BloggingAppApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
	}

	@Test
	public void TestRepoForInterface(){
		String className = this.userRepository.getClass().getName();
		String packageName = this.userRepository.getClass().getPackageName();
		System.out.print(className + " " + packageName);
	}


}
