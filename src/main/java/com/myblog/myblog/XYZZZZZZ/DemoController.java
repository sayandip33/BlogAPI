package com.myblog.myblog.XYZZZZZZ;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/")
	public String hello() {
		return "welcome to my blog";
	}

}
