package com.breakidea.lotus.web.controller.spu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.lotus.web.controller.AbstractController;
import com.breakidea.lotus.web.support.Utils;

@Controller
@RequestMapping("/spu/category")
public class CategoryController extends AbstractController {

	@RequestMapping("{screen}.json")
	public ModelAndView chooseCategory(@PathVariable String screen) {
		ModelAndView mv = new ModelAndView();
		response.setContentType("application/json; charset=UTF-8");
		
		mv.setViewName("spu/category/" + Utils.toCamelCase(screen));
		return mv;
	}
}
