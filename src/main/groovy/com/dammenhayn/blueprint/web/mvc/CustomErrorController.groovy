package com.dammenhayn.blueprint.web.mvc

import com.dammenhayn.blueprint.config.Endpoints
import com.dammenhayn.blueprint.config.ViewNames
import groovy.util.logging.Slf4j
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import static org.springframework.http.HttpStatus.NOT_FOUND

@Controller
@Slf4j
class CustomErrorController implements ErrorController {

  @RequestMapping(Endpoints.ERROR)
  ModelAndView handleError(HttpServletRequest request) {
    Object statusCodeObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
    Object requestURIObj = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI)

    int statusCode    = statusCodeObj ? Integer.parseInt(statusCodeObj.toString()) : -1
    String requestURI = requestURIObj ? requestURIObj as String : ""

    if(statusCode == NOT_FOUND.value()) {
      log.warn("Could not found any content for '${requestURI}'.")
      return new ModelAndView(ViewNames.ERROR_404, "requestedURI", requestURI)
    }
    else if(statusCode == INTERNAL_SERVER_ERROR.value()) {
      log.error("Internal server error while requesting '${requestURI}'.")
      return new ModelAndView(ViewNames.ERROR_500, "requestedURI", requestURI)
    }

    return new ModelAndView(ViewNames.ERROR, "requestedURI", requestURI)
  }
}
