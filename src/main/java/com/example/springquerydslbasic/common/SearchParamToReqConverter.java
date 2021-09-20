package com.example.springquerydslbasic.common;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchParamToReqConverter {

  public static SearchReq convert(String searchString, Pageable pageable){
    List<SearchCondition> searchConditionList = new ArrayList<>();
    Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)(\\w+?),");
    Matcher matcher = pattern.matcher(searchString + ",");

    while (matcher.find()) {
      SearchCondition condition = new SearchCondition();
      condition.setName(matcher.group(1));
      condition.setOperation(matcher.group(2));
      condition.setValue(matcher.group(3));
      searchConditionList.add(condition);
    }

    SearchReq req = new SearchReq();
    req.setConditions(searchConditionList);
    req.setPageable(pageable);

    return req;
  }

}
