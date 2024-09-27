package com.ex.basicCRUD.repository;

import com.ex.basicCRUD.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//프라이머리키로 사용된 타입이 <여기에> 들어가는 거. 지금은 Member 의 id인 Long
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT * FROM member ORDER BY id DESC", nativeQuery = true)
    List<Member> searchAll();

    @Query(value = "SELECT * FROM member WHERE name LIKE %:keyword%" + " ORDER BY name ASC", nativeQuery = true)
    List<Member> searchName(@Param("keyword")String keyword);

    @Query(value = "SELECT * FROM member WHERE address LIKE %:keyword%" + " ORDER BY id ASC", nativeQuery = true)
    List<Member> searchAddress(@Param("keyword")String keyword);
}
