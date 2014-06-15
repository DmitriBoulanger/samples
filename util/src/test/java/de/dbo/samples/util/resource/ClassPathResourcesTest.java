package de.dbo.samples.util.resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPathResourcesTest {
    private static final Logger log = LoggerFactory.getLogger(ClassPathResourcesTest.class);

    @Test
    public final void test() throws IOException {

        final List<File> proprtiesFiles = ClassPathResources.resourceFiles("/de/**/*.properties");
        log.info(print("Deep properties-files", "file", proprtiesFiles));

        final List<File> directProprtiesFiles = ClassPathResources.resourceFiles("/**/*.properties");
        log.info(print("Direct properties-files", "file", directProprtiesFiles));

        final List<File> xmlDeepFiles = ClassPathResources.resourceFiles("/de/**/*.xml");
        log.info(print("XML deep-files", "file", xmlDeepFiles));

        final List<File> xmlDirectFiles = ClassPathResources.resourceFiles("/**/*.xml");
        log.info(print("XML direct-files", "file", xmlDirectFiles));

        final List<String> proprtiesFilenames = ClassPathResources.resourceFilenames("/de/**/*.properties");
        log.info(print("Deep properties-files", "file", proprtiesFilenames));

        final List<String> directProprtiesFilenames = ClassPathResources.resourceFilenames("/**/*.properties");
        log.info(print("Direct properties-files", "file", directProprtiesFilenames));

        final List<String> xmlDeepFilenames = ClassPathResources.resourceFilenames("/de/**/*.xml");
        log.info(print("XML deep-files", "file", xmlDeepFilenames));

        final List<String> xmlDirectFilenames = ClassPathResources.resourceFilenames("**/**/*.xml");
        log.info(print("XML direct-files", "file", xmlDirectFilenames));

    }

    private static final String print(final String title, final String itemTitle, final List<?> items) {
        final StringBuilder sb = new StringBuilder(title + ":");
        for (final Object item : items) {
            sb.append("\n\t - " + itemTitle + ": " + item);
        }
        return sb.toString();
    }

}
