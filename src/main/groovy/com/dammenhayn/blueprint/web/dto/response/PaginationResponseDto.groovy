package com.dammenhayn.blueprint.web.dto.response

class PaginationResponseDto {

  private static final String PAGE_QUERY_PARAM_NAME = "page"

  int currentPage
  int numberOfPages
  int itemsPerPage

  String getPreviousPageUrlQueryParam() {
    return currentPage > 1 ? getPageParamUrlPart() + (currentPage - 1) : ""
  }

  String getNextPageUrlQueryParam() {
    return currentPage < numberOfPages ? getPageParamUrlPart() + (currentPage + 1) : ""
  }

  boolean getPreviousPageDisabled() {
    return currentPage <= 1
  }

  boolean getNextPageDisabled() {
    return currentPage >= numberOfPages
  }

  static String getPageParamUrlPart() {
    return "?" + PAGE_QUERY_PARAM_NAME + "="
  }
}
