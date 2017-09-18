package com.drops.demo.servlet.list;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drops.demo.model.ConnectedServlet;
import com.drops.demo.tools.BootstrapUI;
import com.drops.demo.tools.HTMLTools;
import com.drops.demo.tools.SQLTools;
import com.drops.demo.tools.StringTools;

public class People  extends ConnectedServlet{
	private static final long serialVersionUID = 1453088676663976954L;
	
	@Override
	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out.write("<div class='panel  panel-warning'><div class='panel-heading'>"+BootstrapUI.glyphIcon("list","People list")+"</div>");
		String orderBy = StringTools.coalesce(req.getParameter("order"), "surname");
		StringBuffer query = new StringBuffer("Select surname, name, job, birth From DEMO_PROJECT ");
		query.append("Order by ").append(orderBy);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
			ResultSet resultSet = SQLTools.getResultSetFromQuery(SQLTools.getOracleConnection(), query.toString());
			StringBuffer table = new StringBuffer("<table class='table table-striped table-bordered table-condensed'>");
			table.append("<tr>")
				.append(HTMLTools.tagElement("th", "<a href=\"javascript:getContent('peopleList?order=surname')\">Surname</a>"))
				.append(HTMLTools.tagElement("th", "<a href=\"javascript:getContent('peopleList?order=name')\">Name</a>"))
				.append(HTMLTools.tagElement("th", "Job"))
				.append(HTMLTools.tagElement("th", "<a href=\"javascript:getContent('peopleList?order=birth')\">Date of birth</a>"))
				.append("</tr>");	
			while(resultSet.next()){
				table.append("<tr>")
					.append(HTMLTools.tagElement("td", resultSet.getString("surname")))
					.append(HTMLTools.tagElement("td", resultSet.getString("name")))
					.append(HTMLTools.tagElement("td", resultSet.getString("job")))
					.append(HTMLTools.tagElement("td", dateFormat.format(resultSet.getDate("birth"))))
					.append("</tr>");
			}
			table.append("</table>");
			resultSet.close();
			out.write(table.toString());			
		}
		catch (Exception e) {		
			writeError(e.getMessage());
		}
	}
}
