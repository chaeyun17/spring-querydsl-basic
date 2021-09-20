package com.example.springquerydslbasic.member.controller;

import com.example.springquerydslbasic.common.SearchParamToReqConverter;
import com.example.springquerydslbasic.common.SearchReq;
import com.example.springquerydslbasic.member.dto.MemberSearchRes;
import com.example.springquerydslbasic.member.entity.Member;
import com.example.springquerydslbasic.member.repo.MemberRepoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

  @Autowired
  private MemberRepoSupport memberRepoSupport;

  @GetMapping("/member/search")
  public Page<Member> searchWithPage(@RequestParam String search,
                                     @PageableDefault Pageable pageable){
    SearchReq searchReq = SearchParamToReqConverter.convert(search, pageable);
    return memberRepoSupport.searchWithPage(searchReq);
  }

  @GetMapping("/v2/member/search")
  public Page<MemberSearchRes> searchV2(@RequestParam String search,
                                        @PageableDefault Pageable pageable){
    SearchReq searchReq = SearchParamToReqConverter.convert(search, pageable);
    return memberRepoSupport.searchV2(searchReq);
  }

  @GetMapping("/v2/member/search-join")
  public Page<MemberSearchRes> searchJoin(@RequestParam(required = false) String search,
                                          @PageableDefault Pageable pageable){
    SearchReq searchReq = SearchParamToReqConverter.convert(search, pageable);
    return memberRepoSupport.searchJoinWithPage(searchReq);
  }
}
