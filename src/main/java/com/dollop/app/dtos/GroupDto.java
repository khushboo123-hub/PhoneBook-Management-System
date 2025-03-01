package com.dollop.app.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
	
	private Integer groupId;
	
	private List<Integer> contId;

}
