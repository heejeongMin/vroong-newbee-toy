package com.vroong.newbee.application.service;

import com.vroong.newbee.application.domain.member.manytomany.Member3;
import com.vroong.newbee.application.domain.member.manytomany.Member3Repository;
import com.vroong.newbee.application.domain.member.manytomany.Product;
import com.vroong.newbee.application.domain.member.manytomany.ProductRepository;
import com.vroong.newbee.application.domain.member.manytomany.customMidTable.Member4;
import com.vroong.newbee.application.domain.member.manytomany.customMidTable.Member4Repository;
import com.vroong.newbee.application.domain.member.manytomany.customMidTable.MemberProduct;
import com.vroong.newbee.application.domain.member.manytomany.customMidTable.Product2;
import com.vroong.newbee.application.domain.member.manytomany.customMidTable.Product2Repository;
import com.vroong.newbee.application.domain.member.manytoone.Member;
import com.vroong.newbee.application.domain.member.manytoone.MemberRepository;
import com.vroong.newbee.application.domain.member.manytoone.Team;
import com.vroong.newbee.application.domain.member.manytoone.TeamRepository;
import com.vroong.newbee.application.web.dto.MemberDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;
  private final TeamRepository teamRepository;
  private final ProductRepository productRepository;
  private final Member3Repository member3Repository;
  private final Member4Repository member4Repository;
  private final Product2Repository product2Repository;



  //manytoone
  @Transactional //한 트랜젝션 안에 하나의 영속성 컨텍스트만 존재.
  public void manyToOne() throws Exception{

    try {

      Team team1 = new Team("팀1");
      team1 = teamRepository.save(team1);


      //연관관계의 주인이 값을 정함
//      Member member1 = new Member("회원1");
//      member1.setTeam(team1);
//      memberRepository.save(member1);
//
//      Member member2 = new Member("회원2");
//      memberRepository.save(member2);
//      member2.setTeam(team1); //같은트랜젝션 안의 dirty checking

      //연관관계 끊기
//      member1.setTeam(null); //연관관계 끊기
//      member2.setTeam(null);
//      teamRepository.delete(team1); //delete 하기전에 꼭 null로 저장해서 연관관계를 먼저 끊어야한다.

      //연관관계의 주인이 아닌 곳에서 값을 정하면 ? - member에는 팀의 외래키가 저장되지 않는다.
//      Member member1 = new Member("회원1");
//      team1.getMembers().add(member1);
//      memberRepository.save(member1);

      //객체까지 고려한 JPA 사용 1 - 양방향 관계의 엔티티 모두에 값을 저장을 해준다.
//      Member member1 = new Member("회원1");
//      member1.setTeam(team1);
//      team1.getMembers().add(member1); //주인이 아니기 때문에 외래키에 영향을 주지 않고 저장에는 사용되지 않지만, 순수 자바 객체를 사용할때는 사용됨.
//
//      memberRepository.save(member1);
//
//      List<Member> members = team1.getMembers();
//      System.out.println("member size : " + members.size());

      //객체까지 고려한 jpa사용 2 - 1번의 경우, 위한한 점은 메서드를 따로 따로 호출하다보니 실수로 한곳을 호출하지 않으면 양방향성이 깨질 수 있다.
      // 한곳에 모은 메서드를 구현하고 사용하는 것이 안전하니 리펙토링!
      Member member1 = new Member("회원1");
      member1.setTeam(team1);
      memberRepository.save(member1);

      Member member2 = new Member("회원2");
      member2.setTeam(team1);
      memberRepository.save(member2);

    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  public List<MemberDTO> getMemberList (Long teamId) {
    return teamRepository.findById(teamId).get().getMembers()
            .stream().map(MemberDTO::new).collect(Collectors.toList());
  }

  public void saveMemberAndProduct(){

    Product product = new Product().builder().name("상품").build();
    Member3 member = new Member3().builder().username("이름").products(product).build();

    productRepository.save(product);
    member3Repository.save(member);


  }

  public void findMember3(){

    Member3 member = member3Repository.findById(1l).get();
    member.getProducts().stream().forEach(product -> System.out.println(product.getId() + " " + product.getName()));

  }

  public void identifyingRelationship(){

    //회원저장
    Member4 member = new Member4().builder().username("민희정").build();
    member4Repository.save(member);

    //상품저장
    Product2 product = new Product2().builder().name("등쿠션").build();
    product2Repository.save(product);

    //회원상품저장
    MemberProduct memberProduct = new MemberProduct().builder()
                                                      .member(member)
                                                      .product(product)
                                                      .build();





  }

}
