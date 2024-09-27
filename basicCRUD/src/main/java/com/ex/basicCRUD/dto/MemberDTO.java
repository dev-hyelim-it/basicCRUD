package com.ex.basicCRUD.dto;

import com.ex.basicCRUD.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

//POJO(Plain Old Java Object)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
public class MemberDTO {
    private Long id;
    @Size(min = 2, message = "이름은 2자 이상으로 입력해야 합니다.")
    private String name;

    @Range(min = 0, max = 120, message = "나이는 0~120 사이 입니다.")
    private int age;

    @NotBlank(message = "주소는 꼭 입력해주세요. :)")
    private String address;

    //Entity -> DTO
    public static MemberDTO fromEntity(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getAddress()
        );
    }

    // DTO -> Entity
    public static Member fromDTO(MemberDTO dto) {
        return new Member(
                dto.getId(),
                dto.getName(),
                dto.getAge(),
                dto.getAddress()
        );
    }
}
