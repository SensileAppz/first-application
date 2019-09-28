package com.sensileappz.platform.firstapplication.webresource.v1;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sensileappz.platform.firstapplication.model.Greeting;

@RestController
@RequestMapping("/firstapplication/v1")
public class FirstApplicationController {

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Janardhan") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
	
}
