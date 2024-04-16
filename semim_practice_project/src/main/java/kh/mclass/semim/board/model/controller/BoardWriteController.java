package kh.mclass.semim.board.model.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.mclass.semim.board.model.dto.BoardInsertDto;
import kh.mclass.semim.board.model.dto.FileWriteDto;
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
		//경로 이동하면서 prePage값이 있는지 확인
		String prePage = (String)request.getSession().getAttribute("prePage");
		
		//prePage 값이 write면 글 작성을 하려다가 로그인이 안되어있어 로그인 후 다시 글 작성으로 진입한 것
		if(prePage != null && prePage.equals("write")) {
			//잘 글쓰기에 진입했다면 prePage값은 이제 사용안함
			request.getSession().removeAttribute("prePage"); 
		}
		
		request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/board/write doPost()");
		
		String uploadPath = request.getServletContext().getRealPath("files");
		//uploadPath:C:\workspace\Java\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\31_web_project\files
		System.out.println("uploadPath : " + uploadPath ); 
		File uploadPathFile = new File(uploadPath); //만약 필요한 파일이 만들어져있지 않다면 만들어줘
		if(!uploadPathFile.exists()) {
			uploadPathFile.mkdirs();
		}
		
		
		int uploadFileLimit = 10*1024*1024; //10M 제한
		
		//form enctype = "multipart/form-data"형태로 전달된 경우
		MultipartRequest multiReq = new MultipartRequest(request, 
				uploadPath, //서버에 저장할 디렉토리
				uploadFileLimit, //업로드 파일 크기 제한
				"UTF-8",
				new DefaultFileRenamePolicy() //was서버에 저장할 디렉토리에 동일 이름이 존재할 때 새로운 이름 부여 방식
				);
		
		List<FileWriteDto> fileList = new ArrayList<FileWriteDto>();
		//jsp -> controller file 딱 1개일 경우
//		String filePath = multiReq.getFilesystemName("uploadfiles");
//		if(filePath == null) {
//			System.out.println("첨부파일이 없었습니다.");
//		}else {
//			System.out.println("첨부파일 정보는===");
//			System.err.println(filePath);
//			System.out.println(multiReq.getOriginalFileName("uploadfiles"));
//		}
		
		Enumeration<?> fileNames = multiReq.getFileNames();
		while(fileNames.hasMoreElements()) {
			String name = (String)fileNames.nextElement();  // input type="file" name="xxx", xxx_0, xxx_1
			String fileName = multiReq.getFilesystemName(name); // 서버에 저장된 파일이름
			String orginFileName = multiReq.getOriginalFileName(name);
			String type = multiReq.getContentType(name);  // 전송된 파일의 타입
			
			File f1 = multiReq.getFile(name); // name을 이용해서 파일 객체 생성 여부 확인 작업.
			if(f1==null) {// name을 이용해서 파일 객체 생성에 실패하면
				System.out.println("파일 업로드 실패");
			}else {
				System.out.println(f1.length()); // 그 파일의 크기 
			}
			System.out.println(name + "  :  "+ fileName+"  :  "+orginFileName);

			FileWriteDto filedto = new FileWriteDto(fileName, orginFileName);
			fileList.add(filedto);
		}
		

		String subject = multiReq.getParameter("subject");
		String content = multiReq.getParameter("content");
		
		MemberInfoDto boardWriter = (MemberInfoDto)request.getSession().getAttribute("loginInfo");
		
		System.out.println(subject);
		System.out.println(content);
		
		//BoardInsertDto dto = new BoardInsertDto(subject, content, boardWriter.getMemId());
		BoardInsertDto dto = new BoardInsertDto(subject, content, "kh1", fileList);
		System.out.println(dto);
		int result = service.insert(dto);
		
		response.sendRedirect(request.getContextPath()+"/board/list");
	}

}
