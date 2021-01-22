package com.vroong.newbee.application.web;

import com.vroong.newbee.application.service.MemberService;
import com.vroong.newbee.application.service.TeamService;
import com.vroong.newbee.application.web.dto.MemberDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

  private final MemberService memberService;
  private final TeamService teamService;

  @GetMapping("/")
  public String index(){
    return "test";
  }


  @GetMapping("/test")
  public void test() throws Exception{
    memberService.testSave();
  }

  //컨트롤러에서는 엔티티를 직접 반환하는 것이 아니라 dto를 통해서 반환해야한다고 함.
  @RequestMapping(value="/test/{teamId}", method = RequestMethod.GET)
  public List<MemberDTO> getMemberList(@PathVariable String teamId){

    List<MemberDTO> members = memberService.getMemberList(teamId);
    System.out.println(members);

    return members;

  }
}
