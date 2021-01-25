package com.vroong.newbee.application.domain.member.practice;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class Address {

  private String city;
  private String street;
  private String zipcode;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(city, address.city) &&
        Objects.equals(street, address.street) &&
        Objects.equals(zipcode, address.zipcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, street, zipcode);
  }
}