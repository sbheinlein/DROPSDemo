package com.drops.demo.model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ConnectedPage extends ConnectedServlet {
	private static final long serialVersionUID = 1277983348037631179L;
	protected static final String PAGES_ROOT = "/pages/"; 

	@Override
	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getTargetPage());
		dispatcher.forward(req, resp);
	}
	
	//Implement this method to return the target page
	protected abstract String getTargetPage();

}
