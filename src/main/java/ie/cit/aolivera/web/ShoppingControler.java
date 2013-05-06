package ie.cit.aolivera.web;

import java.util.List;

import ie.cit.aolivera.Shopping;
import ie.cit.aolivera.data.dao.ShoppingRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shopping")
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
		shopping.setBestPrice(true);
		// logic to check if it is the cheapest
		List<Shopping> arg = repo.getAll();
		for (Shopping item : arg) {
			if (item.getProduct().equals(shopping.getProduct())) {
				if (item.isBestPrice()) {
					if (item.getPrice() >= price) {
						// update item to false and add new item as true
						item.setBestPrice(false);
						repo.update(item);
					} else {
						shopping.setBestPrice(false);
					}

				}

			}
		}

		repo.add(shopping);
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
		Integer base = Integer.MAX_VALUE;
		String idLowest = "";
		Shopping shopping = repo.findById(id);
		repo.delete(id);
		List<Shopping> arg = repo.getAll();
		if (shopping.isBestPrice()) {
			for (Shopping item : arg) {
				if (item.getProduct().equals(shopping.getProduct())) {
					if (item.getPrice() < base) {
						base = item.getPrice();
						idLowest = item.getId();
					}
				}
			}
		}
		if (base != Integer.MAX_VALUE) {
			Shopping shoppingToUpdate = repo.findById(idLowest);
			shoppingToUpdate.setBestPrice(true);
			repo.update(shoppingToUpdate);
		}

		model.addAttribute("shoppings", repo.getAll());
		return "index";
	}
}
