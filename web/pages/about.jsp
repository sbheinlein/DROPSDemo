<%@ page import="com.drops.demo.tools.ConfigTools"%>
<%@ page import="com.drops.demo.tools.StringTools"%>
<%@ page import="com.drops.demo.tools.BootstrapUI" %>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.util.Set"%>
<%
	Properties configuration = ConfigTools.getConfiguration();	
%>
<div class="panel panel-primary">
	<div class="panel-heading"><%= BootstrapUI.glyphIcon("question-sign","About") %></div>
	<div class="panel-body">
		<h2><img src="statics/logo-drops.png" /> Demo Project by DROPS Software</h2> 
	</div>
</div>