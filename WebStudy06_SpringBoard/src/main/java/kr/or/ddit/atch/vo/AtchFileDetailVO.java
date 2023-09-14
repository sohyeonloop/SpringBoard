package kr.or.ddit.atch.vo;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of= {"atchFileId", "fileSn"})
public class AtchFileDetailVO {
	private MultipartFile uploadFile;
	
	
	// 생성자를 잘 설계를 하면 이런식으로도 활용 가능 
	public AtchFileDetailVO(MultipartFile uploadFile) { //multipart 타입의 adapter로 사용 
		super();
		this.uploadFile = uploadFile;
		
		this.orignlFileNm = uploadFile.getOriginalFilename();
		this.fileExtsn = FilenameUtils.getExtension(orignlFileNm);
		this.fileSize = uploadFile.getSize();
		this.fileMime = uploadFile.getContentType();
		
		this.streFileNm = UUID.randomUUID().toString(); //저장명은 이미 결정 
	}
	
	@NotBlank
	private int	atchFileId;
	@NotBlank
	private int fileSn;
	@NotBlank
	private String fileStreCours;
	@NotBlank
	private String streFileNm; // 저장파일명 - 원본이름 안쓰기로함 
	private String orignlFileNm;
	@NotBlank
	private String fileExtsn;
	@ToString.Exclude
	private String fileCn; // db 타입 : clob 
	private long fileSize;
	private String fileMime;
}
