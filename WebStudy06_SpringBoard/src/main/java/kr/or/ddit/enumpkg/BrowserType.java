package kr.or.ddit.enumpkg;

public enum BrowserType {
	// 상수의 집합인 클래스
	// 그상수들은 일정한 순서에 따라서 정의되있다 (열거형의 순차집합 : 순서가 고정이 되어있다)
	// 이름과 똑같은 name값을 하나씩 가지고 있다
	// 웨일 엣지 크롬 기타 순으로 지정할거야 
	WHALE("웨일"), EDG("엣지"), CHROME("크롬"), OTHERS("기타");
	//map이없어도 map의 역할을 어느정도 해줌 
	//map은 순서가 없음 enum은 순서가 있음(index값을 0,1,2... 가지고 있음)
	
	
	//생성자 private은 다른곳에서 호출할수 없음 이게 생긴 이후 에러가남 => 기본생성자가 없어짐  
	private BrowserType(String browserName) {
		this.browserName = browserName;
	} // 이생성자를 사용하라고 에러가 남 ("웨일") 이렇게 해주면 에러가 없어짐

	private String browserName;
	
	public String getBrowserName() {
		return browserName;
	}
	
	public static BrowserType findBrowserType(String userAgent){
		BrowserType finded = OTHERS;
		BrowserType[] values = values(); // values값이 뭐야 ???? 
		for( BrowserType tmp : values) {
			if(userAgent.toUpperCase().contains(tmp.name())) {
				finded = tmp;
				break;
			}
		}
		return finded;
	}
}
