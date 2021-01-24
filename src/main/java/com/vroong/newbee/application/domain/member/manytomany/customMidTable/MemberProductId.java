package com.vroong.newbee.application.domain.member.manytomany.customMidTable;

import java.io.Serializable;
import java.util.Objects;
import lombok.NoArgsConstructor;


/*
식별자 클래스 특징
1. 복합키는 별도의 식별자 클래스로 만들어야함
2. Serializable을 구현해야 함
3. equals, hascode 메소드를 구현해야함
4. 기본 생성자가 있어야함
5. public 클래스여야함

 */
@NoArgsConstructor
public class MemberProductId implements Serializable {

  private String member;
  private String product;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MemberProductId that = (MemberProductId) o;
    return Objects.equals(member, that.member) &&
        Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(member, product);
  }
}
