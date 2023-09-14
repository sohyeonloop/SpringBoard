package kr.or.ddit.board.vo;

import java.text.MessageFormat;
import java.util.List;

import lombok.Getter;

/**
 * 페이징 처리와 관련된 모든 속성을 가진 객체
 *
 */
//연산은 setter가 있으면 안됨
@Getter
public class PaginationInfo<T> {

	public PaginationInfo(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private long totalRecord; // DB 조회
	private int screenSize = 10; // ul에 따라 임의로 결정
	private int blockSize = 5; // 임의로 결정

	private long totalPage; // totalRecord와 screenSize으로 연산
	private long currentPage; // request parameter로 결정

	private long startRow; // screenSize와 currentPage으로 연산
	private long endRow; // screenSize와 currentPage으로 연산

	private long startPage;// currentPage blockSize으로 연산
	private long endPage;// currentPage blockSize으로 연산

	private SimpleCondition simpleCondition;

	private List<T> dataList;

	public void setSimpleCondition(SimpleCondition simpleCondition) {
		this.simpleCondition = simpleCondition;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (totalRecord + screenSize - 1) / screenSize;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;

		this.endRow = currentPage * screenSize;
		this.startRow = endRow - (screenSize - 1);

		this.endPage = ((currentPage + blockSize - 1) / blockSize) * blockSize; // n결정
		this.startPage = endPage - (blockSize - 1);
	}

	String htmlPattern = "<a href=''javascript:fn_paging({0});'' data-page=''{0}''>{1}</a>";
	String liPtrn = "<li class=''page-item {0}''>";
	String aPtrn = "<a class='page-link' href=''javascript:fn_paging({0});'' data-page=''{0}''>{1}</a>";
	String curPtrn = "<li class=''page-item active'' aria-current=''page''><a class=''page-link'' href=''#''>{0}</a></li>";

	public String getPagingHTML() {
		StringBuilder html = new StringBuilder();
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination'>");

		html.append(MessageFormat.format(liPtrn, startPage <= 1 ? "disabled" : ""));
		html.append(MessageFormat.format(aPtrn, startPage - 1, "이전"));
		html.append("</li>");

		endPage = endPage > totalPage ? totalPage : endPage;

		for (long page = startPage; page <= endPage; page++) {
			if (page == currentPage) {
				html.append(MessageFormat.format(curPtrn, page));
			} else {
				html.append(MessageFormat.format(liPtrn, ""));
				html.append(MessageFormat.format(aPtrn, page, page));
				html.append("</li>");
			}
		}

		html.append(MessageFormat.format(liPtrn, endPage >= totalPage ? "disabled" : ""));
		html.append(MessageFormat.format(aPtrn, endPage + 1, "다음"));
		html.append("</li>");

		html.append("</ul>");
		html.append("</nav>");
		return html.toString();
	}

	public String getPagingHTMLSimple() {
		StringBuilder html = new StringBuilder();

		if (startPage > 1) {
			html.append(MessageFormat.format(htmlPattern, startPage - 1, "이전"));
		}

		endPage = endPage > totalPage ? totalPage : endPage;

		for (long page = startPage; page <= endPage; page++) {
			html.append(MessageFormat.format(htmlPattern, page, page));
		}

		if (endPage < totalPage) {
			html.append(MessageFormat.format(htmlPattern, endPage + 1, "다음"));
		}
		return html.toString();
	}
}