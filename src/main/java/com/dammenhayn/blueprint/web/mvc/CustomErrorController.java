package com.dammenhayn.blueprint.web.mvc;

import com.dammenhayn.blueprint.config.Endpoints;
import com.dammenhayn.blueprint.config.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

  @RequestMapping(Endpoints.ERROR)
  public ModelAndView handleError(HttpServletRequest request) {
    Object statusCodeObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    Object requestURIObj = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

    int statusCode    = (statusCodeObj != null)? Integer.parseInt(statusCodeObj.toString()) : -1;
    String requestURI = (requestURIObj != null)? (String) requestURIObj : "";

    if(statusCode == HttpStatus.NOT_FOUND.value()) {
      log.warn("Could not found any content for '" + requestURI + "'.");
      return new ModelAndView(ViewNames.ERROR_404, "requestedURI", requestURI);
    }
    else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
      log.error("Internal server error while requesting '" + requestURI + "'.");
      return new ModelAndView(ViewNames.ERROR_500, "requestedURI", requestURI);
    }

    return new ModelAndView(ViewNames.ERROR, "requestedURI", requestURI);
  }
}
