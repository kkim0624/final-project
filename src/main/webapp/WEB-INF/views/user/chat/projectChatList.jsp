<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style type="text/css">
</style>

<script>
	$(document).ready(function() {

		$(".tb_style_01").on("click", "td.roomNm", function() {
			//a = .text();
			var a = $(this).attr("id");
			//$(this).data("ct_id");

			console.log(a);
			$("#ct_id").val(a);

			$("#frm").submit();
		});

		

	});

	
</script>



<section class="contents">

	<form id="frm" action="/friendChat" method="get">
		<input type="hidden" id="ct_id" name="ct_id">
	</form>

	<div class="sub_menu">
		<ul class="sub_menu_item">
			<li><a href="/friendChatList">친구 채팅</a></li>
			<li><a href="/projectChatList">프로젝트 멤버 채팅</a></li>
			<li><a href="/faceChatMain">화상 회의</a></li>
		</ul>
		<div class="sub_btn">
			<ul>
			</ul>
		</div>
	</div>


	<!-- table style start -->
	<table class="tb_style_01">
		<caption>테이블 이름</caption>
		<tr>
			<th>NO</th>
			<th>프로젝트 이름</th>
			<th>채팅방 멤버</th>
		</tr>

		<!-- 향상된 for -->
		<c:forEach items="${roomlist}" var="room" varStatus="status">
			<tr>
				<td>${room.rn}</td>
				<td id="${room.ct_id }" class="roomNm">${ room.ct_nm }</td>
				<td><c:forEach items="${realRoomMap}" var="friend"
						varStatus="status">
						<c:if test="${friend.key == room.ct_id }">
							<input type="text" style="width: 300px;" value="${friend.value}">
						</c:if>
					</c:forEach></td>
				<td><a href="#layer2" class="btn-example1 btn_style_01" id="${room.ct_id}_${room.ct_nm}">채팅방 수정</a></td>
			</tr>
		</c:forEach>

	</table>


	<div class="pagination">
		<a href="" class="btn_first"></a> <span>1</span> <a href="" class="btn_last"></a>
	</div>

</section>





</html>

