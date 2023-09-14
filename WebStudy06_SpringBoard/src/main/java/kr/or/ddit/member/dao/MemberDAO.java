package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.board.vo.PaginationInfo;
import kr.or.ddit.member.vo.MemberVO;

/**
 * 회원관리용 Persistence Layer
 *
 */

@Mapper
public interface MemberDAO {

	/**
	 * 인증에 활용한 회원 정보 조회
	 * 
	 * @param memId 회원식별자(username)
	 * @return 존재하지 않은 경우, null 반환
	 */

	public MemberVO selectMemberForAuth(String memId);

}
