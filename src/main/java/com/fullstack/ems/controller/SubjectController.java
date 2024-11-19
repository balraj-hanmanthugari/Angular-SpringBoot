package com.fullstack.ems.controller;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Affordance;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.ems.entity.Subject;
import com.fullstack.ems.service.SubjectServiceImpl;

@RestController
@RequestMapping("/ems/v1")
public class SubjectController {
	@Autowired
	private SubjectServiceImpl subjectServiceImpl;

	@GetMapping(value = "/subject")
	@ResponseStatus(HttpStatus.OK)
	public RepresentationModel<?> getSubjects() {
		List<Subject> subjectList = subjectServiceImpl.getSubjects();
		subjectList.forEach(subject -> {
			Link itemLink = Link.of("/ems/v1/subject/{id}")
								.expand(subject.getId());
			subject.add(itemLink);
		});
		
		Link nodeLink = Link.of("/ems/v1/subject");
		HalModelBuilder halModelBuilder = HalModelBuilder.halModelOf(CollectionModel.of(subjectList));
		halModelBuilder.link(nodeLink.withRel(IanaLinkRelations.SELF));
		return halModelBuilder.build();
	}

	@GetMapping(value = "/subject/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RepresentationModel<?> getSubject(@PathVariable Long id) {
		Subject subject = subjectServiceImpl.getSubject(id).get();
		
		Link nodeLink = Link.of("/ems/v1/subject/{id}")
							.expand(subject.getId());
		
		HalModelBuilder halModelBuilder = HalModelBuilder.halModelOf(EntityModel.of(subject));
		halModelBuilder.link(nodeLink.withRel(IanaLinkRelations.SELF));
		halModelBuilder.link(nodeLink.withRel("update"));
		halModelBuilder.link(nodeLink.withRel("delete"));
		return halModelBuilder.build();
	}

	@PostMapping(value = "/subject")
	@ResponseStatus(HttpStatus.CREATED)
	public Subject saveSubject(@RequestBody Subject subject) {
		return subjectServiceImpl.saveSubject(subject);
	}

	@PutMapping(value = "/subject/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
		subject.setId(id);
		return subjectServiceImpl.updateSubject(id, subject);
	}

	@DeleteMapping(value = "/subject/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deletSubject(@PathVariable Long id) {
		subjectServiceImpl.deleteSubject(id);
		return ResponseEntity.ok(null);
	}
}
