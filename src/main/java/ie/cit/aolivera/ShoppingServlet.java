package ie.cit.aolivera;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ShoppingServlet extends HttpServlet{
	
	//private ShoppingRepo repo;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}


	private ShoppingRepo getRepo() {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		return (ShoppingRepo) context.getBean("ShoppingRepo");
	}
	

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String srtId= req.getParameter("shoppingId");
		int shoppingId = Integer.parseInt(srtId);
		getRepo().getShoppings().remove(shoppingId-1);
		forward(req, resp);	
	}		
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String srtId= req.getParameter("shoppingId");
		int shoppingId = Integer.parseInt(srtId);
		Shopping shopping = getRepo().getShoppings().get(shoppingId-1);
		shopping.setBestPrice(!shopping.isBestPrice());
		forward(req, resp);	

	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Shopping shoppingItem =new Shopping();
		shoppingItem.setShop(req.getParameter("shop"));
		shoppingItem.setProduct(req.getParameter("product"));
		String srtprice = req.getParameter("price");
		shoppingItem.setPrice(Integer.parseInt(srtprice));
		getRepo().getShoppings().add(shoppingItem);		
		forward(req, resp);	

	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		forward(req, resp);	
	}
	
	private void forward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Shopping> shoppings = getRepo().getShoppings();
		req.setAttribute("shoppings", shoppings);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}
}
