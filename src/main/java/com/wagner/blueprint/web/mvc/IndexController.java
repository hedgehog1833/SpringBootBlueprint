package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

  @GetMapping({Endpoints.INDEX, Endpoints.EMPTY_INDEX, Endpoints.SLASH_INDEX})
  public ModelAndView showIndex() {
    return new ModelAndView(ViewNames.INDEX);
  }

}