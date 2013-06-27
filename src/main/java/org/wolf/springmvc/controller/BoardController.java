package org.wolf.springmvc.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wolf.springmvc.service.BoardService;

@Controller
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;
	
	// http://localhost:8880/springmvc/board/list
	@RequestMapping(value={"/board/list", "/board"})
	public String list(Model model) {
		model.addAttribute("boardList", boardService.list());
		return "/board/list";
	}
}