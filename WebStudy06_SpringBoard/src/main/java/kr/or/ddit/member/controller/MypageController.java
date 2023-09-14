package kr.or.ddit.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MypageController {
	
	@RequestMapping("/mypage")
	// 이미완성되있는 인증객체를 쓰는 방법은 알아놔야해 
	// 1. 인증객체를 쓴는 첫번째 방법 이파라미터를 넣기 2.꺼내오는 방법은 get으로 
	public String mypage(Authentication authentication) { //이 파라미터는 어디서 받아오는거야 ? 
		String username = authentication.getName();
		MemberVOWrapper wrapper = (MemberVOWrapper)authentication.getPrincipal();
		
		log.info("현재 인증 객체: {}", authentication);
		log.info("현재 인증된 사용자 아이디: {}", username);
		log.info("현재 인증된 사용자 객체 : {}", authentication.getPrincipal());
		log.info("현재 인증된 사용자의 이름 : {}", wrapper.getRealUser().getMemName());
		return "member/mypage";
	}
}
