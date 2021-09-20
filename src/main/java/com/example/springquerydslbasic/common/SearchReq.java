package com.example.springquerydslbasic.common;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class SearchReq {

  List<SearchCondition> conditions;

  Pageable pageable;

}
