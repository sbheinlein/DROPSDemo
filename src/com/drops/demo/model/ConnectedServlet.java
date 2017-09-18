package com.drops.demo.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drops.demo.core.Connection;
import com.drops.demo.tools.HTMLTools;
import com.drops.demo.tools.StringTools;


public abstract class ConnectedServlet extends HttpServlet {
	private static final long serialVersionUID = -3592476137704320161L;
	
	protected PrintWriter out;
	protected Connection connectionInstance;
	protected HttpSession currentSession;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		this.out = resp.getWriter();
		this.currentSession = req.getSession();		
		if(requiresConnection() && !connectionIsValid(req)){			
			this.printLoginForm(req, resp);
		}
		else{
			this.handleRequest(req, resp);			
		}			
	}

	protected boolean connectionIsValid(HttpServletRequest request) {
		this.connectionInstance = (Connection) currentSession.getAttribute(Connection.IN_SESSION);
		if(connectionInstance == null || !connectionInstance.isConnected()){ 
			if(StringTools.coalesce(request.getParameter("action")).equals("doLogin")){
				String system = request.getParameter("system");
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				connectionInstance = new Connection(system, login, password);
				currentSession.setAttribute(Connection.IN_SESSION, connectionInstance);
			}
		}
		
		return connectionInstance != null && connectionInstance.isConnected();
	}

	protected void printLoginForm(HttpServletRequest req, HttpServletResponse resp) {
		String target = StringTools.coalesce(req.getParameter("target"), "home");
				
		StringBuffer loginForm = 
				new StringBuffer("<form id='infologin'>") 
				.append("<table class='login'>")				
				.append("	<tr>")
				.append("		<td>Login</td>")
				.append("		<td><input type='text' name='login' value='' /></td>")
				.append("	</tr>")
				.append("	<tr>")
				.append("		<td>Password</td>")
				.append("		<td><input type='password' name='password' /></td>")
				.append("	</tr>")
				.append("</table>")					
				.append("<input type='hidden' name='action' value='doLogin' />")
				.append("</form>")
				.append("<input type='button' value='Connexion' onclick=\"javascript:postContent('").append(target).append("', $('#infologin').serialize())\">");
		
		out.write(loginForm.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	protected void writeError(String error){
		out.write(HTMLTools.tagElement("p", "class=\"error\"", error));
	}
	
	//Override to prompt for a connection
	protected boolean requiresConnection(){
		return false;
	}
	
	//Implement this method to output some content
	protected abstract void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
