package kr.or.ddit.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.FreeboardService;
import kr.or.ddit.board.vo.FreeboardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board/boardInsert.do")
public class BoardInsertController {
	
	private final FreeboardService service;

	@ModelAttribute("board")
	public FreeboardVO board() {
		return new FreeboardVO();
	}
	
	@GetMapping
	public String getHandler() {
		return "board/boardForm";
	}
	
	@PostMapping
	public String postHandler(
		@Validated(InsertGroup.class) @ModelAttribute("board") FreeboardVO board
		 , Errors errors
		 ,Model model
		) {
		String logicalViewName = null;
		
		if(!errors.hasErrors()) {
			boolean result = service.createBoard(board);
			if(result) {
				logicalViewName = "redirect:/board/boardView.do?what="+board.getBoNo();
			}else {
				logicalViewName = "board/boardForm";
			}
		}else {
			logicalViewName = "board/boardForm";
		}
		return logicalViewName;
	}
}
