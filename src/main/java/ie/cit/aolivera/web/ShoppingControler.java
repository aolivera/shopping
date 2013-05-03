package ie.cit.aolivera.web;

import ie.cit.aolivera.Shopping;
import ie.cit.aolivera.ShoppingRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("shopping")
@Controller
public class ShoppingControler {
	@Autowired
	private ShoppingRepo repo;
	
	@RequestMapping(method= RequestMethod.GET)
	public String shoppinglist(Model model){
		model.addAttribute("shoppings", repo.getShoppings());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam String shop, @RequestParam String product, @RequestParam ("price") int price , Model model) {
		Shopping shopping = new Shopping();
		shopping.setShop(shop);
		shopping.setProduct(product);
		shopping.setPrice(price);
		repo.getShoppings().add(shopping);
		model.addAttribute("shoppings", repo.getShoppings());
		return "index";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestParam("shoppingId") int id, Model model) {
		Shopping shopping = repo.getShoppings().get(id - 1);
		shopping.setBestPrice(!shopping.isBestPrice());
		model.addAttribute("shoppings", repo.getShoppings());
		return "index";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String delete(@RequestParam("shoppingId") int id, Model model) {
		repo.getShoppings().remove(id - 1);
		model.addAttribute("shoppings", repo.getShoppings());
		return "index";
	}
}
