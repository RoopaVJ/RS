package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Tutorial;
import com.example.repository.TutorialRepository;

@SpringBootTest
class CruDexample1ApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	TutorialRepository repo;
	
	//---------
	@Test
	public void createTutorial() {
		 Tutorial t1 = new Tutorial();
		 t1.setTitle("abc");
		 t1.setDescription("tut 1");
		 t1.setPublished(true);
		 Tutorial testT1 = repo.save(t1);
		 assertNotNull(t1);
		 assertEquals(t1.getTitle(), testT1.getTitle());
		 repo.deleteAll(); 
	}
	
	@Test
	public void getAll() {
		Tutorial tut1 = new Tutorial("Springboot","tut #1 spring boot",true);
		Tutorial tut2 = new Tutorial("Hibernate","tut #1 hibernate with mapping",false);
		int size = repo.findAll().size();
		repo.save(tut1);
		repo.save(tut2);
		int expectedCount =size+ 2;
		assertEquals(expectedCount,repo.findAll().size());
		repo.deleteAll();
	}
	
	@Test
	public void updateTest() {
		Tutorial tut = new Tutorial("Hibernate","tut #1 hibernate with mapping",false);
		Tutorial saved = repo.save(tut);
		long id = saved.getId();
		String title = "Hibernate with mapping relations";
		boolean isPublished = true;
		saved.setTitle(title);
		saved.setPublished(isPublished);
		repo.save(saved);
		System.out.println("saved data");
		Tutorial updatedTut = repo.findById(id).get();
		assertEquals(updatedTut.getTitle(), title);
		//repo.deleteAll();
	}
	
	@Test
	public void deleteTutorialTest() {
		Tutorial tut = new Tutorial("Angular","Routing and navigation in angular8",true);
		Tutorial saved = repo.save(tut);
		int count = repo.findAll().size();
		System.out.println("count: "+count);
		long id = saved.getId();
		System.out.println(id);
		repo.deleteById(id);
		assertEquals(count-1, repo.findAll().size());
	}
}















