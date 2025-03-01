package com.dollop.app.helper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import com.dollop.app.dtos.PageableResponse;

public class Helper {

	// Contact -> C and ContactDto -> V
	public static <C, V> PageableResponse<V> getPageableResponse(Page<C> page, Class<V> type) {

		List<C> entity = page.getContent();
		List<V> dtoList = entity.stream().map(object -> new ModelMapper().map(object, type))
				.collect(Collectors.toList());
		PageableResponse<V> response = new PageableResponse<>();
		response.setContent(dtoList);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastPage(page.isLast());
		
		return response;

	}

}
