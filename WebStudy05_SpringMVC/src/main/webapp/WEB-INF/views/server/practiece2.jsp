<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<style>
body {
   justify-content: center; /* 가로 중앙 정렬 */
}

.custom-card {
   flex-direction: column;
   margin: 0 auto;
}

#adList, #jobList {
   width: 100%; /* 가로 길이 100%로 설정 */
}
</style>
<div class="custom-card">
   <div id="adList" class="col-lg-10 stretch-card">
      <div class="card-body">
         <!-- 광고 목록 내용 -->
      </div>
   </div>
   <div id="searchUI" class="row justify-content">
      <div class="col-auto">
         <form:select path="simpleCondition.searchType" class="form-select">
            <form:option value="" label="전체" />
            <form:option value="company" label="회사명" />
            <form:option value="location" label="지역" />
         </form:select>
      </div>
      <div class="col-auto">
         <form:input path="simpleCondition.searchWord" class="form-control" />
      </div>
      <div class="col-auto">
         <input type="button" value="검색" id="searchBtn"
            class="btn btn-success" />
      </div>
   </div>
   <div id="jobList" class="col-lg-8 stretch-card">
      <div class="card-body">
         <!-- 공고 목록 내용 -->
      </div>
   </div>
</div>

<form:form id="searchForm" modelAttribute="simpleCondition" method="get">
   <form:hidden path="searchType" />
   <form:hidden path="searchWord" />
   <input type="hidden" name="page" value="1">
</form:form>
<script>
<!-- pagination -->
   function fn_paging(page) {
      searchForm.page.value = page;
      fetchJob(page);
   }
   
   $(searchBtn).on("click", function(event) {
      $(searchUI).find(":input[name]").each(function(idx, input) {
         let name = input.name;
         let value = $(input).val();
         $(searchForm).find(`[name=\${name}]`).val(value);
         fetchJob();
      });
   })
   
   
   $(document).ready(function() {
      var page = '1';
      fetchAd();
      fetchJob(page);
   });

   // 광고 목록을 불러오는 펑션
   function fetchAd() {
      $.ajax({
         url : "/oryuUniversity/job/jobAdList.do",
         method : "get",
         dataType : "html",
         success : function(data) {
            $('#adList').html(data);
         },
         error : function(jqXHR, status, error) {
            console.log(jqXHR);
            console.log(status);
            console.log(error);
         }
      });
   }

   // 공고 목록을 불러오는 펑션
   function fetchJob(page) {
      $(searchForm).find("input[name='page']").val(page);

	  var data = $(searchForm).serializeArray();
      
      $.ajax({
         url : "/oryuUniversity/job/jobAdList2.do",
         method : "get",
         data : data,
         dataType : "html",
         success : function(data) {
            $('#jobList').html(data);
         },
         error : function(jqXHR, status, error) {
            console.log(jqXHR);
            console.log(status);
            console.log(error);
         }
      });
   }
</script>