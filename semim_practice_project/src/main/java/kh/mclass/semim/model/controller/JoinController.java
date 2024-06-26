package kh.mclass.semim.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.semim.member.model.dto.MemberDto;
import kh.mclass.semim.member.model.service.MemberService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/join.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기여기");
		String memId = request.getParameter("id");
		String memPwd = request.getParameter("pwd");
		String memEmail = request.getParameter("email");
		
		MemberDto dto = new MemberDto(memId, memPwd, memEmail);
		int result = new MemberService().insert(dto);
		System.out.println(result);
		
		if(result <= 0) {
			//실패시
			response.sendRedirect(request.getContextPath()+"/main");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
