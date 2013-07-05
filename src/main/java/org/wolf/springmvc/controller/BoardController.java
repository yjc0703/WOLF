package org.wolf.springmvc.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wolf.springmvc.domain.BoardVO;
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
	
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("boardVO", new BoardVO());
		return "/board/write";
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String write(@Validated @ModelAttribute BoardVO boardVO, BindingResult result) {
		if(result.hasErrors()) {
			return "/board/write";
		} else {
			return "redirect:/board/list";
		}
	}
	
	// /board/delete/1
	@RequestMapping(value="/board/delete/{seq}")
	public String delete(@PathVariable int seq) {
		boardService.delete(seq);
		return "redirect:/board/list";
	}
	
	// /board/read/1
	@RequestMapping(value="/board/read/{seq}")
	public String read(@PathVariable int seq, Model model) {
		BoardVO boardVO = boardService.read(seq);
		model.addAttribute("boardVO", boardVO);
		return "/board/read";
	}
}