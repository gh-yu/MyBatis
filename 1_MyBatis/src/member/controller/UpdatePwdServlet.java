package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.exception.MemberException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdatePwdServlet
 */
@WebServlet("/mPwdUpdate.me")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
		String userId = member.getUserId();
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userPwd", userPwd);
		map.put("newPwd", newPwd);
		// 매퍼에 데이터 보낼때 인자에 2개까지만 보낼 수 있음
		// 2개 초과하여 보낼 수 없기 때문에 HashMap을 이용해서 여러 개를 담아서 보냄
		
		MemberService mService = new MemberService();
		
		// 현재 비밀번호와 일치하지 않았을때 알림 띄우는 방식
//		if (userPwd.equals(member.getUserPwd())) {
//			member.setUserPwd(newPwd);
//			try {
//				mService.updatePwd(member);
//				Member m = mService.selectMember(member);
//				session.setAttribute("loginUser", m);
//				response.sendRedirect(request.getContextPath() + "/info.me");
//			} catch (MemberException e) {
//				request.setAttribute("message", e.getMessage());
//				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
//			}
//		} else {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("alert('현재 비밀번호가 일치하지 않습니다.')");
//			script.println("history.back()");
//			script.println("</script>");
//		}
		
		// 에러 페이지로 바로 보내는 방법
		try {
			mService.updatePwd(map);
			member.setUserPwd(newPwd);
			Member m = mService.selectMember(member);
			session.setAttribute("loginUser", m);
			response.sendRedirect(request.getContextPath() + "/info.me");
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
