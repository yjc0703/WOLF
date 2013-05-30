package org.wolf.springmvc.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	// http://localhost:8880/springmvc/board/list
	@RequestMapping(value={"/board/list", "/board"})
	public String list() {
		System.out.println("나예요");
		return "/board/list";
		// /WEB-INF/views/board/list.jsp
	}
}
