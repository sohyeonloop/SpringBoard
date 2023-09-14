package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.FreeboardVO;
import kr.or.ddit.board.vo.PaginationInfo;

@Mapper
public interface FreeboardDAO {

   public int insertBoard(FreeboardVO freeBoard);
  
   public long selectTotalRecord(PaginationInfo paging);
   
   public List<FreeboardVO> selectBoardList(PaginationInfo paging);
   
   public int updateBoard(FreeboardVO freeBoard);
   public FreeboardVO fredBoard(int boNo);
   public int deleteBoard(FreeboardVO board);
}