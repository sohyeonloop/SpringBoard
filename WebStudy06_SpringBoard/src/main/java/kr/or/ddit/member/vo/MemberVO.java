package kr.or.ddit.member.vo;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data // 게터, 세터 생성해줌 
@EqualsAndHashCode(of = "memId") //Equal : 뭘포함시킬지가 중요! 프라이머리 키 조건 꼭 넣어주기! VO 만들 때 많이 간과!
@ToString(exclude = {"memPass", "memRegno1", "memRegno2"}) //String : 뭘 배제시킬지 -toString 할때 비밀번호 노출되면 안돼!  
public class MemberVO implements Serializable{ // 직렬화 시킬때도 보호해야될 존재 있음 
	private long rnum;	
	@NotBlank(groups = {InsertGroup.class, DeleteGroup.class}) //insert 그룹으로 묶인단 뜻 = insert그룹 하나 defalt 그룹 하나 이렇게 있는거 
	private String memId;
	@NotBlank(groups = {Default.class, DeleteGroup.class}) //3가지 클래스 다 검증
	@Size(min = 4, max = 12, groups = {Default.class, DeleteGroup.class} )
	@JsonIgnore // 마샬링에서 제외! '마샬링할때 이거 무시해' 라는 뜻 
	private transient String memPass; // transient : 직렬화해서 제외시킴 마샬링에서 제외한건 아님 
	@NotBlank(groups = InsertGroup.class) 
	private String memName; 
	@NotBlank(groups = InsertGroup.class) 
	private transient String memRegno1; // 주민번호 
	@NotBlank(groups = InsertGroup.class) 
	private transient String memRegno2;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime memBir;
	
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	@NotBlank
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate memMemorialday;
	
	private Integer memMileage;
	private boolean memDelete; // 이게 소문자 대문자 
	
	
	private String memRole;
	
	private byte[] memImg; // DB랑 대화할때
	
	private MultipartFile memImage; // Client랑 대화할때 
	
	// 여기서 해주는 이유가 왠만해서 controller는 건들이고 싶지 않아서! 
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(!memImage.isEmpty()) {
			this.memImage = memImage;
			this.memImg = memImage.getBytes();
		}
	}
	
	
	
	public String getBase64Img() {
		if(memImg==null) {
			return null;
		}else {
			return Base64.getEncoder().encodeToString(memImg);
		}
	}
}
