package com.dollop.app.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse<T> {
	
	private List<T> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean lastPage;

}
