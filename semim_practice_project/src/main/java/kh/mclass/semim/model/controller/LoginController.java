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
	
	private MemberService service = new MemberService();
       
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
		//글쓰기 하려다가 로그인이 되어있지 않다면 prePage 값을 가지고 로그인 페이지로 이동했을 거임
		String prePage = request.getParameter("prePage");
		//session에 prePage값 넣어서 다시 전달
		request.getSession().setAttribute("prePage", prePage);
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

		// 1 성공 0 실패
		int result = 0;
		MemberInfoDto resultInfo = service.loginGetInfo(dto);   //회원정보를 저장하기 위해 MemberInfoDto에 담음
		//아이디와 비번이 일치하는 회원을 찾아 있다면 로그인 성공으로 간주, session에 로그인 정보 저장
		if(resultInfo != null) {
			//성공
			//request.getSession().setAttribute("loginId", id);
			request.getSession().setAttribute("loginInfo", resultInfo);
			result = 1;
		}
		response.getWriter().append(String.valueOf(result)); //일반 form 태그로 접근시 이렇게 반환함
	}

}
