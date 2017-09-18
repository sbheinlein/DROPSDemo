<%@ page import="com.drops.demo.tools.ConfigTools"%>
<%@ page import="com.drops.demo.tools.StringTools"%>
<%@ page import="com.drops.demo.tools.BootstrapUI" %>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.util.Set"%>
<%
	Properties configuration = ConfigTools.getConfiguration();	
%>
<div class="panel panel-danger">
	<div class="panel-heading"><%= BootstrapUI.glyphIcon("wrench","Configuration") %></div>
	<div class="panel-body">
		<ul class="list-group">  		
		<%
			for(Entry<Object, Object> entry : configuration.entrySet()){
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				
				if(key.toString().contains(".pass"))
					value = BootstrapUI.glyphIcon("ban-circle");
					
				out.write(String.format("<li class='list-group-item'><b>%1$s: </b>%2$s</li>", key, value));
			}
		%>
		</ul>
	</div>
</div>