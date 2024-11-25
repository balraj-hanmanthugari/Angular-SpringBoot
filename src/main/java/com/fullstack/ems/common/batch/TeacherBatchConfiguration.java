package com.fullstack.ems.common.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.fullstack.ems.entity.Teacher;
import com.fullstack.ems.repository.TeacherRepository;

@Configuration
public class TeacherBatchConfiguration {

	@Autowired
	TeacherRepository teacherRepository;

	@Bean
	FlatFileItemReader<Teacher> reader() {

		FlatFileItemReader<Teacher> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("/teacher.csv"));

		reader.setLineMapper(new DefaultLineMapper<>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setDelimiter(DELIMITER_COMMA);
						setNames("name");
					}
				});

				setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
					{
						setTargetType(Teacher.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	ItemWriter<Teacher> writer() {
		return teachers -> {
			System.out.println("Saving User: " + teachers);
			teacherRepository.saveAll(teachers);
		};
	}

    @Bean
    JobExecutionListener jobExecutionListener() {
		return new TeacherJobExecutionListener();
	}

	@Bean
	Job importTeacherJob(JobRepository jobRepository, Step step1) {
		return new JobBuilder("importTeacherJob", jobRepository).incrementer(new RunIdIncrementer()).listener(jobExecutionListener())
				.start(step1).build();
	}

	@Bean
	Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1", jobRepository).<Teacher, Teacher>chunk(100, transactionManager).reader(reader())
				.processor(teacherItemProcessor()).writer(writer()).build();
	}

	@Bean
	@StepScope
	TeacherItemProcessor teacherItemProcessor() {
		return new TeacherItemProcessor();
	}
}