package kr.or.ddit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.FreeboardService;
import kr.or.ddit.board.vo.FreeboardVO;
import kr.or.ddit.board.vo.PaginationInfo;
import kr.or.ddit.board.vo.SimpleCondition;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardRetrieveController {	
	private final FreeboardService service;
	
	
	
	@RequestMapping("boardList.do")
	public void boardList(
			@RequestParam(name="page", required = false, defaultValue = "1" ) long currentPage
			, @ModelAttribute("simpleCondition") SimpleCondition simpleCondition
			,Model model
		) {
		
		//pagination 객체 생성 
		PaginationInfo<FreeboardVO> paging = new PaginationInfo<>(5,5);
		paging.setCurrentPage(currentPage); // 먼저 client의 요청 currentPage 넣어줌 
		paging.setSimpleCondition(simpleCondition);
		
		List<FreeboardVO> freeBoardList = service.retrieveBoardList(paging);
		
		paging.setDataList(freeBoardList);
		
//		model.addAttribute("freeBoardList" , freeBoardList);
		model.addAttribute("paging", paging);
		
//		return "board/boardList";
	}
	
	
	@RequestMapping("boardView.do")
	public void boardView(
		@RequestParam(name="what", required = true) int boNo,
		Model model
		) {
		FreeboardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		
	}
}
