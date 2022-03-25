package com.pado.SpringBootPractice.repository;

import com.pado.SpringBootPractice.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 저장소를 직접 왔다갔다.
@Repository
public class MemoryMemberRepository implements MemberRepository{

    // 실무에서는 동시성 문제를 고려해야하는데 일단 함
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;




    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 아이디 세팅
        store.put(member.getId(), member); // 스토어에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null 반환될 가능성이 있으면 옵셔널
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findALl() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
