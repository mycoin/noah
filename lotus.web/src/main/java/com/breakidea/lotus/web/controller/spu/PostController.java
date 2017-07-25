package com.breakidea.lotus.web.controller.spu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.lotus.web.controller.AbstractController;
import com.breakidea.lotus.web.support.Utils;

@Controller
@RequestMapping("/spu/post")
public class PostController extends AbstractController {

	@RequestMapping("{screen}.htm")
	public ModelAndView chooseCategory(@PathVariable String screen) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("spu/post/" + Utils.toCamelCase(screen));
		return mv;
	}
}
