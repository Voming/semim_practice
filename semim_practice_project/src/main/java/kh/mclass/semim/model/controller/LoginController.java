package kh.mclass.semim.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.semim.member.model.dto.MemberInfoDto;
import kh.mclass.semim.member.model.dto.MemberLoginDto;
import kh.mclass.semim.member.model.service.MemberService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd= request.getParameter("pwd");
		
		MemberLoginDto dto = new MemberLoginDto(id, pwd);
		System.out.println("MemberLoginDto" + dto);
		
		//TODO login
		// 1 성공 0 실패
		int result = 0;
		MemberInfoDto resultInfo = new MemberService().loginGetInfo(dto);
		
		if(resultInfo != null) {
			//성공
			//request.getSession().setAttribute("loginId", id);
			request.getSession().setAttribute("loginInfo", resultInfo);
			result = 1;
		}
		response.getWriter().append(String.valueOf(result));
	}

}
