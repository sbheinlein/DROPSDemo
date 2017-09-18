<%@page import="com.drops.demo.tools.StringTools"%>
<%@ page import="com.drops.demo.tools.BootstrapUI" %>
<div class="panel panel-success">
	<div class="panel-heading"><%= BootstrapUI.glyphIcon("star","Demo Project Home") %></div>
	<div class="panel-body">
		<div class="well">
			<h3>Random fortune cookie</h3>
			<blockquote>
				<p>
				<%=	StringTools.getFortune() %>
				</p>
			</blockquote>
		</div>
	</div>
</div>