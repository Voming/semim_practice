package kh.mclass.semim.board.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.semim.board.model.service.BoardService;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private BoardService service = new BoardService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지당 글수 3씩
		int pageSize = 3;
		//화면 하단 나타날 페이지 수
		int pageBlockSize = 5;
		//현제 페이지
		int currentPageNum = 1;  //기본 1
		String pageNum = request.getParameter("page");
		if(pageNum != null && pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (NumberFormatException e) {
				System.out.println("!!!!!!!!NumberFormatException!!!!!!!!!");
				//e.printStackTrace();
			}
		}
		//페이지당 글 개수, 하단에 나타날 페이지 수, 현재 페이지를 service에 전달하면 거기서 처리하고 dao에 보내고
		//다시 받은 값을 service에서 묶어줌
		request.setAttribute("map", service.selectPageList(pageSize, pageBlockSize, currentPageNum));
	
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
