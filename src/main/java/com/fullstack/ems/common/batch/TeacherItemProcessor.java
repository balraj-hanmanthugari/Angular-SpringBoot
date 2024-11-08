package com.fullstack.ems.common.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.fullstack.ems.entity.Teacher;

public class TeacherItemProcessor implements ItemProcessor<Teacher, Teacher> {
	private static final Logger log = LoggerFactory.getLogger(TeacherItemProcessor.class);

	@Override
	public Teacher process(final Teacher teacher) throws Exception {
		final String name = teacher.getName().toUpperCase();
		final Teacher transformedPerson = new Teacher();
		transformedPerson.setName(name);
		log.info("Converting (" + teacher + ") into (" + transformedPerson + ")");
		return transformedPerson;
	}
}