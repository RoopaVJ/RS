package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Tutorial;
import com.example.repository.TutorialRepository;
import com.example.services.TutorialServices;

@RestController
@RequestMapping()
@CrossOrigin(origins =  "http://localhost:4200")
public class TutorialController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TutorialServices tutorialServices;
	
	@GetMapping("/tutorials")
	private List<Tutorial> getAllTutorials(){
		logger.debug("this is an debug message");
		logger.info("this is an info message");
		logger.warn("this is an warn message");
		logger.error("this is an error message");
		return tutorialServices.getAllTutorials();
	}
	
	@PostMapping("/tutorials")
	private String saveTutorial(@RequestBody Tutorial tutorial) {
		tutorialServices.addTutorial(tutorial);
		return "data saved";
	}
	
	@PutMapping("/tutorials/{tutorialid}")
	private Tutorial update(@PathVariable("tutorialid")Long tutorialid, @RequestBody Tutorial tutorial) {
		return  tutorialServices.updateTutorial(tutorialid,tutorial);	
	}
	
	@DeleteMapping("/tutorials/{tutorialid}")
	private void deleteTutorial(@PathVariable("tutorialid")Long tutorialid) {
		tutorialServices.delete(tutorialid);
	}

	@GetMapping("/tutorials/{tutorialid}")
	private Tutorial getTutorial(@PathVariable("tutorialid")Long tutorialid) {	
		return tutorialServices.getTutorialById(tutorialid);		
	}
	
	@GetMapping("/publishedTutorials")
	private List<Tutorial> getPublishedTutorials(){
		List<Tutorial> list = tutorialServices.findByPublished();
		return list;
		
	}
	
}
