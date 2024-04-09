package kh.mclass.semim.board.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.semim.board.model.dto.BoardInsertDto;
import kh.mclass.semim.board.model.service.BoardService;
import kh.mclass.semim.member.model.dto.MemberInfoDto;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();

    public BoardWriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prePage = (String)request.getSession().getAttribute("prePage");
		
		
		if(prePage != null && prePage.equals("write")) {
			request.getSession().removeAttribute("prePage");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/boardwrite.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/board/write doPost()");
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		MemberInfoDto boardWriter = (MemberInfoDto)request.getSession().getAttribute("loginInfo");
		
		System.out.println(subject);
		System.out.println(content);
		BoardInsertDto dto = new BoardInsertDto(subject, content, boardWriter.getMemId());
		int result = service.insert(dto);
		response.sendRedirect(request.getContextPath()+"/board/list");
	}

}
