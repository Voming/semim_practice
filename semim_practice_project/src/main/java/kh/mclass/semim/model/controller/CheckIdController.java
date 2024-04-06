package kh.mclass.semim.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kh.mclass.semim.member.model.dto.MemberDto;
import kh.mclass.semim.member.model.service.MemberService;

@WebServlet("/checkid")
public class CheckIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckIdController() {
        super();
       
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("#####checkid 진입");
		String memid = request.getParameter("cid");
		
//		int result = new MemberService().selectCheckId(memid);
//		System.out.println(result);
//		response.getWriter().append(String.valueOf(result));
		
		List<MemberDto> result = new MemberService().selectAllList();
		System.out.println(result);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);
		System.out.println("=== json===");
		System.out.println(jsonResult);
		response.getWriter().append(jsonResult);
	}

}
