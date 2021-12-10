package member.model.service;

import static common.Template.getSqlSession;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import member.model.dao.MemberDAO;
import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberService {

	private MemberDAO mDAO = new MemberDAO();
	
	public Member selectMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		Member member = mDAO.selectMember(session, m); // DAO에서 위임받은 예외 서블릿으로 위임
		
		session.close(); 
		// close는 무조건 해줘야되는 것 
		// selectMember() 호출에서 에러 발생시 비정상 종료(로그인 실패시 위임한 예외로 인해) -> DAO에서 예외 발생시 close 코드 추가
		
		return member;
	}

	public void insertMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		
		mDAO.insertMember(session, m);
		
		session.commit(); 
		// DAO에서 insert실패시 rollback하는 코드 작성했기 때문에 여기서는 commit만
		session.close();
	}

	public void updateMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		
		mDAO.updateMember(session, m);
		
		session.commit();
		session.close();
			
	}

	public void updatePwd(HashMap<String, String> map) throws MemberException {
		SqlSession session = getSqlSession();
		
		mDAO.updatePwd(session, map);
		
		session.commit();
		session.close();		
	}

	public void deleteMember(String userId) throws MemberException {
		SqlSession session = getSqlSession();
		
		mDAO.deleteMember(session, userId);
		
		session.commit();
		session.close();
	}

}
