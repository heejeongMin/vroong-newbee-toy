package com.vroong.newbee.application.domain.member.manytomany.midTableNewId;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Member5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<MyOrder> orders = new ArrayList<>();

    @Builder
    public Member5 (String name){
        this.name = name;
    }



}

