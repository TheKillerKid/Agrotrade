package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.CategoryDB;
import Model.IF.CategoryIF;
import Model.Model.Category;

public class CategoryController {
	
	private CategoryIF categoryDb = new CategoryDB();
	
	public ArrayList<Category> getCategories() throws SQLException {
		try {
			return categoryDb.getCategories();
		} catch (SQLException e) {
			throw e;
		}
	}
}
