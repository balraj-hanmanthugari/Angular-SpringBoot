package com.fullstack.ems.common.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class TeacherJobExecutionListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Job Started: " + jobExecution.getStartTime() + ", Status: " + jobExecution.getStatus());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Job Started: " + jobExecution.getStartTime() + ", Status: " + jobExecution.getStatus());
	}
}