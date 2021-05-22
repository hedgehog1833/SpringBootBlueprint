package com.dammenhayn.blueprint.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@SuppressWarnings({"unused", "WeakerAccess"}) // used as view model in html
public class PaginationResponseDto {

  private static final String PAGE_QUERY_PARAM_NAME = "page";

  private int currentPage;
  private int numberOfPages;
  private int itemsPerPage;

  public String getPreviousPageUrlQueryParam() {
    return currentPage > 1? getPageParamUrlPart() + (currentPage - 1) : "";
  }

  public String getNextPageUrlQueryParam() {
    return currentPage < numberOfPages ? getPageParamUrlPart() + (currentPage + 1) : "";
  }

  public boolean getPreviousPageDisabled() {
    return currentPage <= 1;
  }

  public boolean getNextPageDisabled() {
    return currentPage >= numberOfPages;
  }

  public static String getPageParamUrlPart() {
    return "?" + PAGE_QUERY_PARAM_NAME + "=";
  }

}
