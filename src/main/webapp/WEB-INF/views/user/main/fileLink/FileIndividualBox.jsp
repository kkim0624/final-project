<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
ul.tabs {
	margin: 0px;
	padding: 0px;
	list-style: none;
}

ul.tabs li {
	background: none;
	color: #222;
	display: inline-block;
	padding: 10px 15px;
	cursor: pointer;
}

ul.tabs li.current {
	color: #222;
}

.tab-content {
	display: none;
	padding: 15px;
}

.tab-content.current {
	display: inherit;
}
</style>

<script>
	$(document).ready(function() {
		individualPagination(1,10);
		
		$('#searchBtn').on('click',function(){
			if($('#searchText').val().length == 0){
				alert("검색어를 입력해주세요");
			}else{
				$('#frmSearch').submit();
			}
		});
		
		$(".sub_menu").on("click", "#individualBox",function(){
			alert("개인보관함입니다.");
			individualBox(1, 10);
		})
	})
	
		
	function individualPagination(page, pageSize) {
		$.ajax({
			url : "/individualPagination",
			method : "post",
			data : "page=" + page + "&pageSize="+ pageSize,
			success : function(data) {
				//사용자 리스트
				var hhtml = "";
				var html = "";
				
				//hhtml생성
				hhtml += "<tr>";
				hhtml += "<th>File_Num</th>";
				hhtml += "<th>파일명</th>";
				hhtml += "<th>등록일</th>";
				hhtml += "<th>삭제</th>";
				hhtml += "<th>이동</th>";
				hhtml += "</tr>";
				
				data.data.individualList.forEach(function(file, index){
					//html생성
					html += "<tr id='filetr'>";
					html += "<td>"+ file.num + "</td>";
					html += "<td><a href='#'>"+file.original_file_nm+"</a></td>";	
					html += "<td>"+ file.prjStartDtStr + "</td>";
					html += "<td><a href='javascript:updateInFile("+file.file_id+")'>삭제</a></td>";
					html += "<td><a href='javascript:moveInFile("+file.file_id+")'>이동</a></td>";
					html += "</tr>";
				});
				var pHtml = "";
				var pageVo = data.pageVo;
				
				if(pageVo.page==1)
					pHtml += "<li class='disabled'><span>«<span></li>";
				else
					pHtml += "<li><a href='javascript:individualPagination("+(pageVo.page-1)+", "+pageVo.pageSize+");'>«</a></li>";
				
				for(var i =1; i <=data.data.paginationSize; i++){
					if(pageVo.page==i)
						pHtml += "<li class='active'><span>" + i + "</span></li>";
					else
						pHtml += "<li><a href='javascript:individualPagination("+ i + ", " + pageVo.pageSize+");'>"+i+"</a></li>";
				}
				if(pageVo.page == data.data.paginationSize)
					pHtml += "<li class='disabled'><span>»<span></li>";
				else
					pHtml += "<li><a href='javascript:individualPagination("+(pageVo.page+1)+", "+pageVo.pageSize+");'>»</a></li>";
		
				$(".pagination").html(pHtml);
				$("#FileIndividualBox").html(hhtml);
				$("#fileList").html(html);
			}
		});
	}
	
	function individualBox(page, pageSize) {
		$.ajax({
			url : "/individualPagination",
			method : "post",
			data : "page=" + page + "&pageSize="+ pageSize,
			success : function(data) {
				//사용자 리스트
				var hhtml = "";
				var html = "";
				
				//hhtml생성
				hhtml += "<tr>";
				hhtml += "<th>File_Num</th>";
				hhtml += "<th>파일명</th>";
				hhtml += "<th>등록일</th>";
				hhtml += "<th>삭제</th>";
				hhtml += "<th>이동</th>";
				hhtml += "</tr>";
				
				data.data.individualList.forEach(function(file, index){
					//html생성
					html += "<tr id='filetr'>";
					html += "<td>"+ file.num + "</td>";
					html += "<td><a href='#'>"+file.original_file_nm+"</a></td>";	
					html += "<td>"+ file.prjStartDtStr + "</td>";
					html += "<td><a href='javascript:updateInFile("+file.file_id+")'>삭제</a></td>";
					html += "<td><a href='javascript:moveInFile("+file.file_id+")'>이동</a></td>";
					html += "</tr>";
				});
				var pHtml = "";
				var pageVo = data.pageVo;
				
				if(pageVo.page==1)
					pHtml += "<li class='disabled'><span>«<span></li>";
				else
					pHtml += "<li><a href='javascript:individualPagination("+(pageVo.page-1)+", "+pageVo.pageSize+");'>«</a></li>";
				
				for(var i =1; i <=data.data.paginationSize; i++){
					if(pageVo.page==i)
						pHtml += "<li class='active'><span>" + i + "</span></li>";
					else
						pHtml += "<li><a href='javascript:individualPagination("+ i + ", " + pageVo.pageSize+");'>"+i+"</a></li>";
				}
				if(pageVo.page == data.data.paginationSize)
					pHtml += "<li class='disabled'><span>»<span></li>";
				else
					pHtml += "<li><a href='javascript:individualPagination("+(pageVo.page+1)+", "+pageVo.pageSize+");'>»</a></li>";
		
				$(".pagination").html(pHtml);
				$("#FileIndividualBox").html(hhtml);
				$("#fileList").html(html);
			}
		});
	}
	
	function updateInFile(fileID){
		alert("삭제긔긔");

		$.ajax({
			url : "/updateInFile",
			method : "post",
			data : "file_id=" + fileID,
			success : function(data) {
				//사용자 리스트
				var hhtml = "";
				var html = "";
				
				//hhtml생성
				hhtml += "<tr>";
				hhtml += "<th>File_Num</th>";
				hhtml += "<th>파일명</th>";
				hhtml += "<th>등록일</th>";
				hhtml += "<th>삭제</th>";
				hhtml += "<th>이동</th>";
				hhtml += "</tr>";
				
				console.log(data);
				
				data.data.individualList.forEach(function(file, index){
					//html생성
					html += "<tr id='filetr'>";
					html += "<td>"+ file.num + "</td>";
					html += "<td><a href='#'>"+file.original_file_nm+"</a></td>";	
					html += "<td>"+ file.prjStartDtStr + "</td>";
					html += "<td><a href='javascript:updateInFile("+file.file_id+")'>삭제</a></td>";
					html += "<td><a href='javascript:moveInFile("+file.file_id+")'>이동</a></td>";
					html += "</tr>";
				});
				var pHtml = "";
				var pageVo = data.pageVo;
				
				if(pageVo.page==1)
					pHtml += "<li class='disabled'><span>«<span></li>";
				else
					pHtml += "<li><a href='javascript:individualPagination("+(pageVo.page-1)+", "+pageVo.pageSize+");'>«</a></li>";
				
				for(var i =1; i <=data.data.paginationSize; i++){
					if(pageVo.page==i)
						pHtml += "<li class='active'><span>" + i + "</span></li>";
					else
						pHtml += "<li><a href='javascript:individualPagination("+ i + ", " + pageVo.pageSize+");'>"+i+"</a></li>";
				}
				if(pageVo.page == data.data.paginationSize)
					pHtml += "<li class='disabled'><span>»<span></li>";
				else
					pHtml += "<li><a href='javascript:individualPagination("+(pageVo.page+1)+", "+pageVo.pageSize+");'>»</a></li>";
		
				$(".pagination").html(pHtml);
				$("#FileIndividualBox").html(hhtml);
				$("#fileList").html(html);
				$(".ctxt").text("해당 게시물이 삭제 되었습니다.");
	        	layer_popup("#layer2");
	            return false;
			}
		});
	}
	
	function moveInFile(fileID){
		alert("이동긔긔?");

		$.ajax({
			url : "/updateInFile",
			method : "post",
			data : "file_id=" + fileID,
			success : function(data) {
				//사용자 리스트
				var hhtml = "";
				var html = "";
				
				//hhtml생성
				hhtml += "<tr>";
				hhtml += "<th>File_Num</th>";
				hhtml += "<th>파일명</th>";
				hhtml += "<th>등록일</th>";
				hhtml += "<th>삭제</th>";
				hhtml += "<th>이동</th>";
				hhtml += "</tr>";
				
				console.log(data);
				
				data.data.individualList.forEach(function(file, index){
					//html생성
					html += "<tr id='filetr'>";
					html += "<td>"+ file.num + "</td>";
					html += "<td><a href='#'>"+file.original_file_nm+"</a></td>";	
					html += "<td>"+ file.prjStartDtStr + "</td>";
					html += "<td><a href='javascript:updateInFile("+file.file_id+")'>삭제</a></td>";
					html += "<td><a href='javascript:moveInFile("+file.file_id+")'>이동</a></td>";
					html += "</tr>";
				});
				var pHtml = "";
				var pageVo = data.pageVo;
				
				if(pageVo.page==1)
					pHtml += "<li class='disabled'><span>«<span></li>";
				else
					pHtml += "<li><a href='javascript:individualPagination("+(pageVo.page-1)+", "+pageVo.pageSize+");'>«</a></li>";
				
				for(var i =1; i <=data.data.paginationSize; i++){
					if(pageVo.page==i)
						pHtml += "<li class='active'><span>" + i + "</span></li>";
					else
						pHtml += "<li><a href='javascript:individualPagination("+ i + ", " + pageVo.pageSize+");'>"+i+"</a></li>";
				}
				if(pageVo.page == data.data.paginationSize)
					pHtml += "<li class='disabled'><span>»<span></li>";
				else
					pHtml += "<li><a href='javascript:individualPagination("+(pageVo.page+1)+", "+pageVo.pageSize+");'>»</a></li>";
		
				$(".pagination").html(pHtml);
				$("#FileIndividualBox").html(hhtml);
				$("#fileList").html(html);
				$(".ctxt").text("해당 게시물이 삭제 되었습니다.");
	        	layer_popup("#layer2");
	            return false;
			}
		});
	}
	
	
	
</script>

<section class="contents">

	<div id="container">
		
		<div class="sub_menu">
			<ul class="tabs">
				<li id="individualBox">individualBox</li>
			</ul>
		</div>
		
		<div class="searchBox">
			<div class="tb_sch_wr">
				<form id="frmSearch" action="/searchFile" method="get">
		                <select class="search" name="selectFile">
		                	<option value="fileName">파일명</option>
		                </select>
	                <input type="text" name="original_file_nm" id="searchText" maxlength="20" placeholder="검색어를 입력해주세요">
	                <button type="button" id ="searchBtn" value="검색">검색</button>
				</form>
	    	</div>
	    </div>

		<div class="tab_con">

			<div class="tab-content current">
				<div>
					<table class="tb_style_01">
						<colgroup>
							<col width="20%">
							<col width="25%">
							<col width="25%">
							<col width="15%">
							<col width="15%">
						</colgroup>
						
							<thead id="FileIndividualBox">
							<thead>
							
							<tbody id="fileList">
						</tbody>
						
					</table>
				</div>

			<div class="pagination">
				</div>
				
			</div>
		</div>
	</div>
</section>


<!-- 오류 알림창 -->
<!-- <div class="dim-layer"> -->
<!-- 	<div class="dimBg"></div> -->
<div id="layer2" class="pop-layer">
	<div class="pop-container">
		<div class="pop-conts">
			<!--content //-->
			<p class="ctxt mb20"></p>
			<div class="btn-r">
				<a href="#" class="btn-layerClose">Close</a>
			</div>
			<!--// content-->
		</div>
	</div>
</div>
<!-- </div> -->
