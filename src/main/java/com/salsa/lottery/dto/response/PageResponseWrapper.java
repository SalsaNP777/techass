package com.salsa.lottery.dto.response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PageResponseWrapper <T>{

    private T data;
    private Long totalElement;
    private Integer totalPage;
    private Integer currentPage;
    private Integer size;
}
