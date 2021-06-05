package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Tutorial;
import com.example.repository.TutorialRepository;

@Component
public class TutorialServices {
	
	@Autowired
	TutorialRepository tutorialRepository;
	
	public List<Tutorial> getAllTutorials(){
		List<Tutorial> tutorials = new ArrayList<Tutorial>();
		tutorials = tutorialRepository.findAll();
		display(tutorials);
		return tutorials;
	}
	
	public void addTutorial(Tutorial tutorial) {
		tutorialRepository.save(tutorial);
	}
	
	public void display(List<Tutorial> tutorials) {
		for (Tutorial tutorial : tutorials) {
			System.out.println(tutorial);
		}	
	}
	
	public void delete(Long id) {
		tutorialRepository.deleteById(id);
		System.out.println("the deleted entity Id is "+id);		
	}
	
	public Tutorial getTutorialById(Long id) {
		System.out.println(tutorialRepository.findById(id));
		return tutorialRepository.findById(id).get();
	}
	
	public Tutorial updateTutorial(Long id, Tutorial tutorialdetails) {
		Tutorial tutorial = tutorialRepository.findById(id).get();
		
		//tutorial.setTitle(tutorialdetails.getTitle());
		tutorial.setDescription(tutorialdetails.getDescription());
		tutorial.setPublished(tutorialdetails.isPublished());
		
		Tutorial updatedTutorial =  tutorialRepository.save(tutorial);
		return updatedTutorial;
	}

	
	public List<Tutorial> findByPublished(){
		List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
		display(tutorials);
		return tutorials;
	}
	
}
