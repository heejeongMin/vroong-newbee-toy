package com.vroong.newbee.application.domain.member.practice;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity {

  private Date createdDate;
  private Date lastModifiedDate;

}
