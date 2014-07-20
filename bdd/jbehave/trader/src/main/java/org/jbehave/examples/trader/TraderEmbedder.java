package org.jbehave.examples.trader;

import java.text.SimpleDateFormat;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.examples.trader.service.TradingService;
import org.jbehave.examples.trader.steps.BeforeAfterSteps;
import org.jbehave.examples.trader.steps.TraderSteps;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

/**
 * Specifies the Embedder for the Trader example, providing the
 * Configuration and the CandidateSteps, using classpath story loading.
 */
public class TraderEmbedder extends Embedder {

    @Override
    public EmbedderControls embedderControls() {
        return new EmbedderControls().doIgnoreFailureInStories(true).doIgnoreFailureInView(true);
    }

    @Override
	public Configuration configuration() {
		Class<? extends TraderEmbedder> embedderClass = this.getClass();
		return new MostUsefulConfiguration()
			.useStoryLoader(new LoadFromClasspath(embedderClass.getClassLoader()))
			.useStoryReporterBuilder(new StoryReporterBuilder()
        		.withCodeLocation(CodeLocations.codeLocationFromClass(embedderClass))
        		.withDefaultFormats()
				.withFormats(CONSOLE, TXT, HTML, XML)
				.withCrossReference(new CrossReference()))
            .useParameterConverters(new ParameterConverters()
                	.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")))) // use custom date pattern
            .useStepPatternParser(new RegexPrefixCapturingPatternParser(
							"%")) // use '%' instead of '$' to identify parameters
			.useStepMonitor(new SilentStepMonitor());								
	}

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new TraderSteps(new TradingService()), new BeforeAfterSteps())
                .createCandidateSteps();
    }

}