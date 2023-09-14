package kr.or.ddit.board.vo;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.atch.vo.AtchFileVO;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "boNo")
public class FreeboardVO {
   private long rnum;
   @NotNull(groups = UpdateGroup.class) // blank랑 null 차이가 뭐야 ? 
   private Integer boNo;
   @NotBlank
   private String boTitle;
   @NotBlank
   private String boWriter;
   @NotBlank
   private String boIp;
   @Email
   private String boMail;
   @NotBlank
   private String boPass;
   private String boContent;
   @DateTimeFormat(iso = ISO.DATE) // 이게 뭐였지 ? 
   private LocalDate boDate;
   private Integer boHit;
   
   private MultipartFile[] boFiles; // []로 받아야만 1이상의 파일을 받을수 있음 
   
   private Integer atchFileId;
   
   private AtchFileVO fileGroup; // has a 관계 
}