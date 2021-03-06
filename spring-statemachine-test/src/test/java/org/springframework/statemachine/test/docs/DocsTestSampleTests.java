/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.statemachine.test.docs;

import org.junit.Test;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;

public class DocsTestSampleTests {

	@Test
	public void sample1() throws Exception {
// tag::snippetA[]
		StateMachine<String, String> machine = buildMachine();
		StateMachineTestPlan<String, String> plan =
				StateMachineTestPlanBuilder.<String, String>builder()
					.defaultAwaitTime(2)
					.stateMachine(machine)
					.step()
						.expectStates("SI")
						.and()
					.step()
						.sendEvent("E1")
						.expectStateChanged(1)
						.expectStates("S1")
						.and()
					.build();
		plan.test();
// end::snippetA[]
	}

// tag::snippetB[]
	private StateMachine<String, String> buildMachine() throws Exception {
		StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();

		builder.configureConfiguration()
			.withConfiguration()
				.taskExecutor(new SyncTaskExecutor())
				.autoStartup(true);

		builder.configureStates()
				.withStates()
					.initial("SI")
					.state("S1");

		builder.configureTransitions()
				.withExternal()
					.source("SI").target("S1")
					.event("E1");

		return builder.build();
	}
// end::snippetB[]

}
