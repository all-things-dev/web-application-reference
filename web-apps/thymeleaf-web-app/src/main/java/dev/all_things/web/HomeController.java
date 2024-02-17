package dev.all_things.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@GetMapping("/")
	public ModelAndView index()
	{
		logger.info("Index : Virtual? {} ..", Thread.currentThread().isVirtual());

		return new ModelAndView("index");
	}

	@GetMapping("/home")
	public ModelAndView home()
	{
		logger.info("Index : Virtual? {} ..", Thread.currentThread().isVirtual());

		return new ModelAndView("home");
	}
}
