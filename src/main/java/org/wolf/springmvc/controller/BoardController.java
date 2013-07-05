package org.wolf.springmvc.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.wolf.springmvc.domain.BoardVO;
import org.wolf.springmvc.service.BoardService;

@Controller
@SessionAttributes("boardVO")
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;
	
	// http://localhost:8880/springmvc/board/list
	@RequestMapping(value={"/board/list", "/board"})
	public String list(Model model) {
		model.addAttribute("boardList", boardService.list());
		return "/board/list";
	}
	
	// /board/write
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("boardVO", new BoardVO());
		return "/board/write";
	}
	
	// /board/write
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String write(@Valid @ModelAttribute BoardVO boardVO, BindingResult result) {
		if(result.hasErrors()) {
			return "/board/write";
		} else {
			boardService.write(boardVO);
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
	
	// /board/edit/1
	@RequestMapping(value="/board/edit/{seq}", method=RequestMethod.GET)
	public String edit(@PathVariable int seq, Model model) {
		BoardVO boardVO = boardService.read(seq);
		model.addAttribute("boardVO", boardVO);
		return "/board/edit";
	}
	
	// /board/edit
	@RequestMapping(value="/board/edit/{seq}", method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute BoardVO boardVO, BindingResult result, @RequestParam String pwd, ModelMap model, SessionStatus sessionStatus) {
		if(result.hasErrors()) {
			return "/board/edit";
		} else {
			if(boardVO.getPassword().equals(pwd)) {
				boardService.edit(boardVO);
				sessionStatus.setComplete();
				return "redirect:/board/list";
			}

			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "/board/edit";			
		}
	}
}