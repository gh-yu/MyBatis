package member.model.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberDAO {

	public Member selectMember(SqlSession session, Member m) throws MemberException {
	/*	
		session.selectOne(arg0)
		session.selectOne(arg0, arg1)
	
		- arg0 : 어느 매퍼에 있는 어떤 쿼리(id)에 접근할 것인가
		- arg1 : 쿼리로 보낼 데이터 작성(위치홀더(?)에 채워넣을 데이터) -> 미완성 쿼리일시 필요
	*/	
		Member member = session.selectOne("memberMapper.loginMember", m);
		//              원래 반환형 <Object>(<T>)로 되어 있음 <- 어떤 Type이든 받겠다.
		//              type지정해주면 반환형도 바뀜
		
		if(member == null) { // 일치하는 회원이 없을시 null이 반환됨
			session.close(); // 예외 발생시 service에서 close가 안되기 때문에 여기서 close
			throw new MemberException("로그인에 실패하였습니다."); // 예외 강제 발생 후 throws로 예외 위임
		}
		
		return member;
	}

	public void insertMember(SqlSession session, Member m) throws MemberException {
		
		int result = session.insert("memberMapper.insertMember", m);
		
		if (result <= 0) {
			session.rollback(); // insert 실패시 rollback해줘야 함
			session.close();
			throw new MemberException("회원가입에 실패하였습니다.");
		}
		// DAO에서 예외 발생시켜 서블릿에서 처리하는 방식으로 할 것이기 때문에 result반환할 필요 없음
	}

	public void updateMember(SqlSession session, Member m) throws MemberException {
		
		int result = session.update("memberMapper.updateMember", m);
		
		if (result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("회원정보 수정에 실패하였습니다.");
		}
	}

	public void updatePwd(SqlSession session, HashMap<String, String> map) throws MemberException {
		
		int result = session.update("memberMapper.updatePwd", map);
		
		if (result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("비밀번호 수정에 실패하였습니다.");
		}
		
	}

	public void deleteMember(SqlSession session, String userId) throws MemberException {
		
		int result = session.update("memberMapper.deleteMember", userId);
		
		if (result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("회원 탈퇴에 실패하였습니다.");
		}
		
	}

}
