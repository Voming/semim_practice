package kh.mclass.semim.board.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.semim.board.model.service.BoardService;

/**
 * Servlet implementation class BoardReadController
 */
@WebServlet("/board/read")
public class BoardReadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardIdStr = request.getParameter("id");
		
		try {
			int boardId = Integer.parseInt(boardIdStr);
			request.setAttribute("dto", service.selectOne(boardId));  //boardId 하나를 가져와서 기본 정보와, //있는 댓글들 모두 dto에 담아서 전달
			System.out.println(boardId);
			request.getRequestDispatcher("/WEB-INF/views/board/read.jsp").forward(request, response);
		}catch(NumberFormatException e) {
			System.out.println("!!! NumberFormatException ~~~~~~!");
			response.sendRedirect(request.getContextPath()+"/board/list");
		}
	}

	

}
