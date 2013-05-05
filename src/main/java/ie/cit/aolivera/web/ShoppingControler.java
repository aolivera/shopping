package ie.cit.aolivera.web;

import ie.cit.aolivera.Shopping;
import ie.cit.aolivera.data.dao.ShoppingRepo;

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

	@RequestMapping(method = RequestMethod.GET)
	public String shoppinglist(Model model) {
		model.addAttribute("shoppings", repo.getAll());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam String shop,
			@RequestParam String product, @RequestParam("price") int price,
			Model model) {
		Shopping shopping = new Shopping();
		shopping.setShop(shop);
		shopping.setProduct(product);
		shopping.setPrice(price);
		repo.add(shopping);
		// repo.getShoppings().add(shopping);
		model.addAttribute("shoppings", repo.getAll());
		return "index";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestParam("shoppingId") String id, Model model) {
		Shopping shopping = repo.findById(id);
		shopping.setBestPrice(!shopping.isBestPrice());
		repo.update(shopping);
		model.addAttribute("shoppings", repo.getAll());
		return "index";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String delete(@RequestParam("shoppingId") String id, Model model) {
		repo.delete(id);
		model.addAttribute("shoppings", repo.getAll());
		return "index";
	}
}
