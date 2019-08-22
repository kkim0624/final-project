<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ToastUI Chart load -->
<link rel="stylesheet" href="https://uicdn.toast.com/tui.chart/latest/tui-chart.min.css">
<script src="https://uicdn.toast.com/tui.chart/latest/tui-chart-all.min.js"></script>
<script src="/js/toast-ui-chart.js"></script>
<style>
	#filterFrm label { font-size:13px; font-weight: 500; }
	#filterFrm ul li label { cursor: pointer; }
</style>
<div class="sub_menu">
	<ul class="sub_menu_item">
		<li><a href="/overview/analysis">Work List</a></li>
		<li><a href="/calendarGet">Calendar</a></li>
		<li><a href="/gantt/overview">Gantt Chart</a></li>
	</ul>
	<div class="sub_btn">
	</div>
</div>
<section class="contents">
<h2>Work List</h2>
<div id="frmContainer" style="height:100%;width:200px;float:left;margin-right:0;">
	    <form id="filterFrm">
	    	<select name="wrk_is_mine" class="filter">
	    		<option value="all" selected>전체 업무</option>
	    		<option value="mine">내 업무만</option>
	    	</select>
	    	<select name="wrk_dt" class="filter">
	    		<option value="0" selected>전체</option>
	    		<option value="30">30일 이내</option>
	    		<option value="60">60일 이내</option>
	    		<option value="90">90일 이내</option>
	    	</select>
		    	<input type="hidden" name="user_email" value="${USER_INFO.user_email}">
	    </form>
</div>	
		<div id="allContainer" style="height:95%; width:1100px;float:left;">
	        <div id="resultContainer" style="width:500px;padding:15px;height:700px; float:left;">
	        </div>
	        <div id="chartContainer" style="width:600px;padding:15px;height:100%; float:left;">
	        	<div id="pieChartContainer"></div>
	        	<div id="priorChartContainer"></div>
	        	<div id="percentChartContainer"></div>
	        </div>
	       	<div class="blankContainer" style="font-size:large;width:100%;height:730px;text-align:center;padding:250px;">
	       		<p>데이터 없음</p>
	       	</div>
		</div>
</section>
<!--         <div id="work_detail" style="width:600px;padding:25px;height:100%;float:left;overflow-y:auto;"></div> -->
<script>
	var percentChart = null;
	var priorChart = null;
	var pieChart = null;
	
	function search() {
		var serial = $("#filterFrm").serialize();
		console.log(serial);
		$.ajax({
			url: "/filter/ajax",
			type: "post",
			data: serial,
			success: function(data) {
				console.log(data);
				var filterFrm = data.resultMap.filterFrm;
				var result = data.resultMap.result;
				var prjList = data.resultMap.prjList;
				var makerList = data.resultMap.makerList;
				var followerList = data.resultMap.followerList;
				
				var pieChartData = data.resultMap.pieChartData;
				var percentChartData = data.resultMap.percentChartData;
				var priorChartData = data.resultMap.priorChartData;
				var blank = $(".blankContainer");
				blank.hide();
				var isBlank = data.resultMap.chartData.isBlank;
				if(isBlank=="true") {
					hideChart();
				} else {
					showChart();
				}
				var pieChartData = data.resultMap.chartData.pieChart;
				var priorData = data.resultMap.chartData.barChart.priorChart;
				var percentData = data.resultMap.chartData.barChart.percentChart;
				var pieChartContainer = document.getElementById('pieChartContainer');
				var priorChartContainer = document.getElementById('priorChartContainer');
				var percentChartContainer = document.getElementById('percentChartContainer');
					pieChart["chartContainer"].remove();
					pieChart = loadPieChart(pieChartContainer, pieChartData, 600, 300);
					percentChart["chartContainer"].remove();
					percentChart = loadPercentChart(percentChartContainer, percentData, 600, 200);
					priorChart["chartContainer"].remove();
					priorChart = loadPriorChart(priorChartContainer, priorData, 600, 200);
// 				console.log(filterFrm);
				$("#resultContainer").html(result);
			}
		})
	}
	function searchInit() {
		var serial = $("#filterFrm").serialize();
		console.log(serial);
		$.ajax({
			url: "/filter/ajax",
			type: "post",
			data: serial,
			success: function(data) {
				console.log(data);
				var filterFrm = data.resultMap.filterFrm;
				var result = data.resultMap.result;
				var prjList = data.resultMap.prjList;
				var makerList = data.resultMap.makerList;
				var followerList = data.resultMap.followerList;
				var pieChartData = data.resultMap.chartData.pieChart;
				var priorData = data.resultMap.chartData.barChart.priorChart;
				var percentData = data.resultMap.chartData.barChart.percentChart;
				var blank = $(".blankContainer");
				blank.hide();

				var isBlank = data.resultMap.chartData.isBlank;
				if(isBlank=="true") {
					hideChart();
				} else {
					showChart();
				}
				
				$("#resultContainer").html(result);
				$("#frmContainer").html(filterFrm);
				$("#prjList").html(prjList);
				$("#makerList").html(makerList);
				$("#followerList").html(followerList);
				priorChart = loadPriorChart(priorChartContainer, priorData, 600, 200);
				percentChart = loadPercentChart(percentChartContainer, percentData, 600, 200);
				pieChart = loadPieChart(pieChartContainer, pieChartData, 600, 300);
			}
		})
	}
	
	function workDetail(wrk_id){
		console.log(wrk_id);
		$.ajax({
			url: "/filter/detail",
			type: "post",
			data: wrk_id,
			success: function(data){
				var workDetail = data.workDetail;
				console.log(workDetail);
				
				$("#work_detail").html(workDetail);
			}
		});
	}	
		$("#resultContainer").on("click", ".result", function(){
			var wrk_id = $(this).data(wrk_id);
			workDetail(wrk_id);	
		});
		
		$("#frmContainer").on("change", ".filter", function(){
			search();
		});
		
		$("#frmContainer").on("reset", "#filterFrm", function(){
			$("#filterFrm select").prop("selectedIndex", 0);
			$("#filterFrm input[type=checkbox]").prop("checked", false);	
			search();
		})
	$(function(){
		searchInit();
		$("#filterFrm p").hide();
		  // $("ul > li:first-child a").next().show();
		  $("#frmContainer").on("click", "ul li label", function(){
		    $(this).siblings().slideToggle(300);
		  });
	})
	function hideChart() {
		var blank = $(".blankContainer");
		$("#pieChartContainer").hide();		
		$("#percentChartContainer").hide();		
		$("#priorChartContainer").hide();		
		$(blank).show();
	}
	function showChart() {
		var blank = $(".blankContainer");
		$("#pieChartContainer").show();		
		$("#percentChartContainer").show();		
		$("#priorChartContainer").show();		
		$(blank).hide();
	}
</script>
<script>
</script>