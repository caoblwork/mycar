/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.yingzhuo.mycar.task;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GrantBadgeTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrantBadgeTask.class);
	
	@Resource
	private JobLauncher jobLauncher;
	
	@Resource(name = "grantBadgeJob")
	private Job job;
	
	// ---------------------------------------------------------------------------------------------------------

	@Scheduled(cron = "0 0 4 ? * SUN")		// 每周日凌晨4点触发
	public void work() {

		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("timestamp", new Date().getTime())
				.toJobParameters();

		try {
			jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JobRestartException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
