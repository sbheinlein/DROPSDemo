package com.drops.demo.servlet.list;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drops.demo.model.ConnectedServlet;
import com.drops.demo.tools.BootstrapUI;
import com.drops.demo.tools.HTMLTools;
import com.drops.demo.tools.SQLTools;
import com.drops.demo.tools.StringTools;

public class Games  extends ConnectedServlet{
	private static final long serialVersionUID = 1453088676663976954L;
	
	@Override
	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out.write("<div class='panel panel-warning'><div class='panel-heading'>"+BootstrapUI.glyphIcon("list","Games list")+"</div>");
		String orderBy = StringTools.coalesce(req.getParameter("order"), "title");
		StringBuffer query = new StringBuffer("Select id, title, support, editor, release_date From games ");
		query.append("Order by ").append(orderBy);
		try {
			ResultSet resultSet = SQLTools.getResultSetFromQuery(SQLTools.getMySQLConnection(), query.toString());
			StringBuffer table = new StringBuffer("<table class='table table-striped table-bordered table-condensed'>");
			table.append("<tr>")
				.append(HTMLTools.tagElement("th", "ID"))
				.append(HTMLTools.tagElement("th", "<a href=\"javascript:getContent('gamesList?order=title')\">Title</a>"))
				.append(HTMLTools.tagElement("th", "<a href=\"javascript:getContent('gamesList?order=support')\">Support</a>"))
				.append(HTMLTools.tagElement("th", "Editor"))
				.append(HTMLTools.tagElement("th", "<a href=\"javascript:getContent('gamesList?order=release_date')\">Release Date</a>"))
				.append("</tr>");	
			while(resultSet.next()){
				table.append("<tr>")
					.append(HTMLTools.tagElement("td", resultSet.getString("id")))
					.append(HTMLTools.tagElement("td", resultSet.getString("title")))
					.append(HTMLTools.tagElement("td", resultSet.getString("support")))
					.append(HTMLTools.tagElement("td", resultSet.getString("editor")))
					.append(HTMLTools.tagElement("td", resultSet.getString("release_date")))
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
