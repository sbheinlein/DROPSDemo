<%@page import="java.io.FileReader"%>
<%@page import="java.util.Properties"%>
<%@ page import="org.json.simple.*" %>
<%@ page import="com.drops.demo.tools.*" %>
<%
	String target = StringTools.coalesce(request.getParameter("target"), "home");
	JSONObject paramJSON = new JSONObject();
	paramJSON.putAll(request.getParameterMap());
	
	Properties configuration = ConfigTools.getConfiguration();
%>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Demo project</title>		
		
		<link rel="stylesheet" type="text/css" href="statics/style.css" />
		
		<!-- Bootstrap -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<link href="statics/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
		<script src="statics/bootstrap/js/bootstrap.min.js"></script>		
		
		<script>		
			function getContent(page, datas){
				var content = $("#main");
				content.fadeOut("250", function(){
					$.get(page, datas, function(data){											
						content.html(data);
						content.fadeIn("250");
					});
				});
			}
			
			function postContent(page, datas){
				var content = $("#main");
				content.fadeOut("250", function(){
					$.post(page, datas, function(data){						
						content.html(data);
						content.fadeIn("250");
					});
				});
			}
			
			$(function() {
				getContent("home", {});
			});
		</script>
	</head>
	<body>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Navigate</span> <span	class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="drops-blue navbar-brand" href="index.jsp">Demo Project</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<div class="navbar-text navbar-right">
						&nbsp;			
						<span class="label label-primary">1.0.0.0</span>
						&nbsp;
					</div>
					<ul class="nav navbar-nav">
						<li><a href="index.jsp" alt="home"><span class='glyphicon glyphicon-star' aria-hidden='true'></span>&nbsp;&nbsp;Home</a></li>
						<li><a href="javascript:getContent('config')" alt="config"><span class='glyphicon glyphicon-wrench' aria-hidden='true'></span>&nbsp;&nbsp;Configuration</a></li>
						<li><a href="javascript:getContent('gamesList')" alt="gameslist"><span class='glyphicon glyphicon-list' aria-hidden='true'></span>&nbsp;&nbsp;Games list</a></li>
						<li><a href="javascript:getContent('peopleList')" alt="peoplelist"><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;People list</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<p>&nbsp;</p>
		<div id="main" class="container">
		</div>
	</body>
</html>