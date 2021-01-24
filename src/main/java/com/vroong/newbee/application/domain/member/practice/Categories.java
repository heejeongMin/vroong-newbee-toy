package com.vroong.newbee.application.domain.member.practice;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Categories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORIES_ID")
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(
      name = "CATEGORIES_ITEMS",
      joinColumns = @JoinColumn(name = "CATEGORIES_ID"),
      inverseJoinColumns = @JoinColumn(name = "ITEMS_ID")
  )
  private List<Items> items = new ArrayList<>();

  /* 카테고리의 계층구조 */
  @ManyToOne
  @JoinColumn(name ="PARENT_ID")
  private Categories parent;

  @OneToMany(mappedBy = "parent")
  private List<Categories> child = new ArrayList<>();

  /* 연관관계 */
  public void setItems(Items items){
    if(!this.items.contains(items)) this.items.add(items);
    if(!items.getCategories().contains(this)) items.getCategories().add(this);
  }

  public void setCategories(Categories child) {
    if(!this.child.contains(child)) this.child.add(child);
    child.setParent(parent);
  }

  public void setParent(Categories parent){
    this.parent = parent;
  }

}
