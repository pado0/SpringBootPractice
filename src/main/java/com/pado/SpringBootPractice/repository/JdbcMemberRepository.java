package com.pado.SpringBootPractice.repository;

import com.pado.SpringBootPractice.domain.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

// 맛보기 jdbc코드
// MemberRepository의 구현체를 다시 만든다
// 1. db 커넥션
// 2. db에서 시퀀스를 가져와야된다. (id)
public class JdbcMemberRepository { //implements MemberRepository{

/*    private final DataSource dataSource;

    // datasoure 선언하면 스프링을 통해 datasource를 주입 받을 수 있다
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";

        Connection connection = dataSource.getConnection();
        connection.prepareStatement(sql...문;)

        PrepareStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, member.getName());

        pstmt.executeUpdate();
        쭉 해주면 db 커넥션이된다.
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findALl() {
        return null;
    }


 */
}
