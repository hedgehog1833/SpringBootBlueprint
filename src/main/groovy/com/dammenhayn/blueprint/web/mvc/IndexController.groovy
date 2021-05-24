package com.dammenhayn.blueprint.web.mvc

import com.dammenhayn.blueprint.config.Endpoints
import com.dammenhayn.blueprint.config.ViewNames
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class IndexController {

  @GetMapping([Endpoints.INDEX, Endpoints.EMPTY_INDEX, Endpoints.SLASH_INDEX])
  ModelAndView showIndex() {
    return new ModelAndView(ViewNames.INDEX)
  }
}
