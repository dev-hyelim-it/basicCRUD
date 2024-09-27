package com.ex.basicCRUD.service;

import com.ex.basicCRUD.dto.MemberDTO;
import com.ex.basicCRUD.entity.Member;
import com.ex.basicCRUD.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberDTO> findAll() {
        return memberRepository.searchAll().stream().map(entity -> MemberDTO.fromEntity(entity)).toList();

//        return memberRepository.findAll().stream().map(entity -> MemberDTO.fromEntity(entity)).toList();
    }

    public void saveMember(MemberDTO dto) {
        memberRepository.save(MemberDTO.fromDTO(dto));
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDTO findById(Long id) {
        Member member =  memberRepository.findById(id).get();
        return MemberDTO.fromEntity(member);
    }

    public void updateMember(MemberDTO dto) {
        Member member = MemberDTO.fromDTO(dto);
        memberRepository.save(member);
    }

    public List<MemberDTO> searchMembers(String type, String keyword) {
        List<MemberDTO> dtos = new ArrayList<>();
        switch (type) {
            case "name" :
                dtos = memberRepository.searchName(keyword).stream().map(entity -> MemberDTO.fromEntity(entity)).toList();
                break;
            case "address" :
                dtos = memberRepository.searchAddress(keyword).stream().map(entity -> MemberDTO.fromEntity(entity)).toList();
                break;
            default:
                dtos = memberRepository.searchAll().stream().map(entity -> MemberDTO.fromEntity(entity)).toList();
                break;
        }
        return dtos;
    }
}
