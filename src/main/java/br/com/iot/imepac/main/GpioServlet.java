/**
 * 
 */
package br.com.iot.imepac.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author claudiney.viana
 *
 */
public class GpioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public GpioServlet(){
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("POST");
		System.out.println("action =>> "+action);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("===============>> responde GPIO.... ");
		System.out.println(req.getAttributeNames());
	}

}
