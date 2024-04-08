package kh.mclass.semim.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/logout dopost()");
		request.getSession().removeAttribute("loginInfo");  // 선택한 것만 지워줘
		//request.getSession().invalidate();  
		//contextPath 내부에서 걸어둔 모든 session attribute를 모두 만료시킴
		response.sendRedirect(request.getContextPath() + "/main");
	}
}
