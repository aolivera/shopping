package ie.cit.aolivera.data.dao;

import ie.cit.aolivera.Shopping;

import java.util.List;

public interface ShoppingRepo {
	List<Shopping> getAll();

	Shopping findById(String id);

	void add(Shopping shopping);

	void delete(String id);

	void update(Shopping shopping);

}
