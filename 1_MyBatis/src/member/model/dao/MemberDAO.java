package member.model.dao;

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
		
		if(member == null) { // 일치하는 회원이 없을시 null이 반환됨
			session.close(); // 예외 발생시 service에서 close가 안되기 때문에 여기서 close
			throw new MemberException("로그인에 실패하였습니다."); // 예외 강제 발생 후 throws로 예외 위임
		}
		
		return member;
	}

	public int insertMember(SqlSession session, Member m) {
		
		int result = session.insert("memberMapper.insertMember", m);
	
		return result;
	}

}
