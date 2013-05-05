package ie.cit.aolivera.data.dao;

import ie.cit.aolivera.Shopping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Secured("ROLE_USER")
public class JdbcShoppingRepo implements ShoppingRepo {

	private JdbcTemplate jdbcTemplate;

	public JdbcShoppingRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Shopping> getAll() {
		return jdbcTemplate.query("SELECT * FROM SHOPPING WHERE SHOPPER=?",
				new ShoppingRowMapper(), getCurrentUser());
	}

	public Shopping findById(String id) {
		return jdbcTemplate
				.queryForObject(
						"SELECT ID, SHOP, PRODUCT, PRICE, BESTPRICE FROM SHOPPING WHERE ID=?",
						new ShoppingRowMapper(), id);
	}

	public void add(Shopping shopping) {
		jdbcTemplate.update("INSERT INTO SHOPPING VALUES (?,?,?,?,?,?)",
				shopping.getId(), shopping.getShop(), shopping.getProduct(),
				shopping.getPrice(), shopping.isBestPrice(), getCurrentUser());

	}

	public void delete(String id) {
		jdbcTemplate.update("DELETE FROM SHOPPING WHERE ID=? AND SHOPPER=?",
				id, getCurrentUser());

	}

	public void update(Shopping shopping) {
		jdbcTemplate
				.update("UPDATE SHOPPING SET SHOP=?, PRODUCT=?, PRICE=?, BESTPRICE=? WHERE ID=? AND SHOPPER=?",
						shopping.getShop(), shopping.getProduct(),
						shopping.getPrice(), shopping.isBestPrice(),
						shopping.getId(), getCurrentUser());
	}

	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}

class ShoppingRowMapper implements RowMapper<Shopping> {

	public Shopping mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shopping shopping = new Shopping();
		shopping.setId(rs.getString("ID"));
		shopping.setShop(rs.getString("SHOP"));
		shopping.setProduct(rs.getString("PRODUCT"));
		shopping.setPrice(rs.getInt("PRICE"));
		shopping.setBestPrice(rs.getBoolean("BESTPRICE"));
		return shopping;
	}
}