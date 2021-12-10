package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.exception.MemberException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/minsert.me")
public class InsertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");

		int year =  Integer.parseInt(request.getParameter("year"));
		int month =  Integer.parseInt(request.getParameter("month")) - 1; // 1월이 0이기 때문
		int date =  Integer.parseInt(request.getParameter("date"));
		
		Date birthDay = new Date(new GregorianCalendar(year, month, date).getTimeInMillis());
		
		Member m = new Member(userId, userPwd, userName, nickName, email, birthDay, gender, phone, address, null, null, null);

		try {
			new MemberService().insertMember(m); // insert한 반환값 result 필요x -> 실패시 예외 발생시키는 것으로 처리했기 때문
			
			response.sendRedirect(request.getContextPath());
		} catch (MemberException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
