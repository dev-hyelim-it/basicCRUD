package com.ex.basicCRUD.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member") //데이터베이스 테이블 이름 지정해서 테이블 생성할 때 사용 기본은 class 이름으로 생성됨
@Data
@AllArgsConstructor //기본적인 것만 생성됨. 특정 인자를 오버라이드 하면 그건 DefaultConstructor를 만들어줘야함(@NoArgsConstructor).
@NoArgsConstructor //받아오는? DTO에도 같이 추가.
public class Member {
    //    프라이머리키 지정, 자동 증가, id이름"member_id"
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false) //default는 255 / not null
    private String name;
    private int age;
    @Column(length = 500)
    private String address;
}
