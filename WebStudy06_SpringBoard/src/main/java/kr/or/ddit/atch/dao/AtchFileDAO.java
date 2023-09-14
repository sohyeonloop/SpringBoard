package kr.or.ddit.atch.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.atch.vo.AtchFileDetailVO;
import kr.or.ddit.atch.vo.AtchFileVO;

@Mapper
public interface AtchFileDAO {
	//select
	public AtchFileDetailVO selectAtchFileDetail(AtchFileDetailVO condition);
	public AtchFileVO selectAtchFileGroup(int atchFileId);

	//insert
	// file 1개가 insert
	// 동시에 여러개 file insert but 부모부터 시작해서 자식까지 insert 해야되서 우리는 무조건 동시에
	public int insertAtchFileGroup(AtchFileVO atchFileGroup); // 파일이 몇개가 insert반환 1-> 1개그룹에 1개 / 3-> 1개 그룹에 3개 
	
	public int deleteAtchFileGroup(int atchFileId);
	public int deleteAtchFileDetails(int atchFileId);
}

