package com.ex.basicCRUD.controller;

import com.ex.basicCRUD.dto.MemberDTO;
import com.ex.basicCRUD.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("member")
@Slf4j
public class memberController {
    @Autowired
    MemberService memberService;

    @GetMapping({"", "view"})
    public String showMembers(Model model) {
        model.addAttribute("lists", memberService.findAll());
        return "showMember";
    }

    @GetMapping("insert")
    public String insertFormView(Model model) {
        model.addAttribute("dto", new MemberDTO());
        return "insertMember";
    }

    @PostMapping("insert")
    public String insertMember(@Valid @ModelAttribute("dto") MemberDTO dto, BindingResult bindingResult) {
        log.info("############" + dto.toString());

        if (bindingResult.hasErrors()) {
            log.info("############ Validation Error");
            return "insertMember";
        }
        memberService.saveMember(dto);
        return "redirect:/member";
    }

    @PostMapping("delete")
    public String deleteMember(@RequestParam("id") Long id) {
        log.info("############ deleteID" + id);
        memberService.deleteMember(id);
        return "redirect:/member";
    }

    @GetMapping("update")
    public String updateFormView(@RequestParam("id") Long id, Model model) {
        log.info("############ updateID" + id);
        MemberDTO dto = memberService.findById(id);
        log.info("############ dto" + dto.toString());
        model.addAttribute("dto", dto);
        return "updateMember";
    }

    @PostMapping("update")
    public String updateMember(@Valid @ModelAttribute("dto") MemberDTO dto, BindingResult bindingResult) {
        log.info("############ updatedto" + dto.toString());

        if (bindingResult.hasErrors()) {
            return "updateMember";
        }
        memberService.updateMember(dto);
        return "redirect:/member";
    }

    @PostMapping("search")
    public String searchMember(@RequestParam("type")String type, @RequestParam("keyword")String keyword, Model model) {
        List<MemberDTO> dtos = memberService.searchMembers(type, keyword);
        model.addAttribute("lists", dtos);
        return "showMember";
    }

}
