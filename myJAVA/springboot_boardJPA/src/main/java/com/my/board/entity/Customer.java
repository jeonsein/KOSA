package com.my.board.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@Column(name="id")
	String id;
	
	@Column(name="pwd")
	String pwd;
	
	@Column(name="name")
	String name;
	
	@OneToMany(mappedBy = "boardId")
	private List<Board> boards;
	
	@OneToMany(mappedBy = "replyId")
    private List<Reply> replies;
	
} // end class
