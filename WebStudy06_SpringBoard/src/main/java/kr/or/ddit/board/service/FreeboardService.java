package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.FreeboardVO;
import kr.or.ddit.board.vo.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;

public interface FreeboardService {
	
	public List<FreeboardVO> retrieveBoardList(PaginationInfo paging);
	
	public FreeboardVO retrieveBoard(int boNo);
	
	public boolean createBoard(FreeboardVO board);
	
	public boolean removeBoard(FreeboardVO board);

}
