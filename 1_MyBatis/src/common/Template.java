package common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		// SqlSession 생성 위해 mybatis-3.5.7.jar 라이브러리를 lib폴더에 등록
		// mybatis-config.xml(mybatis전체 설정정보)을 읽어들이는 InputStream객체 생성(InputStream stream = Resources.getResourceAsStream("/mybatis-config.xml");)
		// SqlSessionFactoryBuilder객체생성 -> ssfb.build(stream)으로 -> 
		// SqlSessionFactory객체 생성 -> ssf.openSession(false)로 ->
		// SqlSession 객체 생성
		
		try {
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			InputStream stream = Resources.getResourceAsStream("/mybatis-config.xml");
			SqlSessionFactory ssf = ssfb.build(stream); // inputStream : mybatis-config.xml(mybatis전체 설정정보)을 읽어들이는 역할
			session = ssf.openSession(false); // false : 자동 커밋하지 않겠다
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
	// commit, rollback, close 메소드 따로 작성해주지 않아도 MyBatis에서 기능 제공
}
