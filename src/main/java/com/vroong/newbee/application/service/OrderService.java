package com.vroong.newbee.application.service;

import com.vroong.newbee.application.domain.member.manytomany.midTableNewId.Member5;
import com.vroong.newbee.application.domain.member.manytomany.midTableNewId.Member5Repository;
import com.vroong.newbee.application.domain.member.manytomany.midTableNewId.MyOrder;
import com.vroong.newbee.application.domain.member.manytomany.midTableNewId.MyOrderRepository;
import com.vroong.newbee.application.domain.member.manytomany.midTableNewId.Product3;
import com.vroong.newbee.application.domain.member.manytomany.midTableNewId.Product3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
  private final Member5Repository member5Repository;
  private final Product3Repository product3Repository;
  private final MyOrderRepository orderRepository;

  public void saveOrder(){
    //회원저장
    Member5 member = new Member5().builder().name("heejeong").build();
    member5Repository.save(member);

    //상품저장
    Product3 product = new Product3().builder().name("product").build();
    product3Repository.save(product);

    //주문저장
    MyOrder order = new MyOrder().builder().member(member).product(product).orderAmount(1).build();
    orderRepository.save(order);


  }

}
