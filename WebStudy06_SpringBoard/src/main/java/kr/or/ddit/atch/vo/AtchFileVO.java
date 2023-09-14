package kr.or.ddit.atch.vo;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="atchFileId")
public class AtchFileVO {
	@NotBlank
	private int atchFileId; // pk값이라서 null일수 없어서 int로 줌 
	@NotBlank
	private LocalDate creatDt; 
	private boolean useAt; // 사용이 중단된 녀석들은 이 값을 지워줘야됌 
	
	private List<AtchFileDetailVO> detailList; // has many 관계 
}
