package kr.or.ddit.member.vo;

import java.security.Principal;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MemberVOWrapper extends User{
	private MemberVO realUser;

	public MemberVOWrapper(MemberVO realUser) {
		super(realUser.getMemId(), 
			  realUser.getMemPass(), 
			!realUser.isMemDelete(), //탈퇴한 안한사람 이게 true면 탈퇴한사람  
			true, // 만료되지 않았다  
			true, // 계정잠기지 않았다 
			true, //계정이 만료되지 않았다  
			AuthorityUtils.createAuthorityList(realUser.getMemRole()) //role 이 2개이상인 사람이 있을수 있으니 list로 받음 
			); 
		this.realUser = realUser;
	}

	public MemberVO getRealUser() {
		return realUser;
	}
	
}
