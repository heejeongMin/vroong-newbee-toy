package com.vroong.newbee.application.web;

import com.vroong.newbee.application.service.MemberService;
import com.vroong.newbee.application.service.OrderService;
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
  private final OrderService orderService;

  @GetMapping("/")
  public String index(){
    return "test";
  }


  @GetMapping("/many-to-one")
  public void manyToOne() throws Exception{
    memberService.manyToOne();
  }

  //컨트롤러에서는 엔티티를 직접 반환하는 것이 아니라 dto를 통해서 반환해야한다고 함.
  @RequestMapping(value="/many-to-one/{teamId}", method = RequestMethod.GET)
  public List<MemberDTO> getMemberList(@PathVariable Long teamId){

    List<MemberDTO> members = memberService.getMemberList(teamId);
    System.out.println(members);

    return members;

  }


  @GetMapping("/one-to-many")
  public void oneToMany(){
    teamService.oneToMany();

  }

  @GetMapping("/many-to-many")
  public void manyToMany(){
    memberService.saveMemberAndProduct();
    memberService.findMember3();
  }
  @GetMapping("/many-to-many/find")
  public void manyToManyFind(){
    memberService.findMember3();
  }

  @GetMapping("/many-to-many/newId")
  public void manyToManyWithNewId(){
    orderService.saveOrder();
  }

}
