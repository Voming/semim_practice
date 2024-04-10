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
		System.out.println("#####checkid 진입");  //ajax로 들어옴
		String memid = request.getParameter("cid"); //data로 전달 받음
		
		int result = new MemberService().selectCheckId(memid);
		System.out.println(result);
		response.getWriter().append(String.valueOf(result));  //결과 불렀던 ajax로 반환
		
		//모든 멤버조회
//		List<MemberDto> result = new MemberService().selectAllList();
//		System.out.println(result);
//		Gson gson = new Gson();
//		String jsonResult = gson.toJson(result);  //result는 원래 MemberDto형태였다가 json으로 파싱
//		System.out.println("=== json===");
//		System.out.println(jsonResult);
//		response.getWriter().append(jsonResult);
	}

}
