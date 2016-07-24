package de.dbo.samples.springboot.rest.hello;

import org.slf4j.*;

public class Greeting {
    private static final Logger log = LoggerFactory.getLogger(Greeting.class);

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
        log.info("created");
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
