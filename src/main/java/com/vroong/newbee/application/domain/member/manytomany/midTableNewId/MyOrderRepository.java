package com.vroong.newbee.application.domain.member.manytomany.midTableNewId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {

}
