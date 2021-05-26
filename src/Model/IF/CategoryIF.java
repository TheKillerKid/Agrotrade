package Model.IF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Category;

public interface CategoryIF {
		Category getCategory(long id) throws SQLException;
		
		ArrayList<Category> getCategories() throws SQLException;
}