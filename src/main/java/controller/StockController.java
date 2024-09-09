package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;
}
