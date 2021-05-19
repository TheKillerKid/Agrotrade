package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.DBIF.CategoryIF;
import Model.Model.Category;

public class CategoryDB implements CategoryIF{
	
	@Override
	public Category getCategory(long id) throws SQLException {
		Category res = null;
		String sqlCategory = ("SELECT * FROM Category WHERE id = ?");
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCategory);
			
			preparedStmt.setLong(1, id);
				
			ResultSet rsCategory = preparedStmt.executeQuery();
			if(rsCategory.next()) {
				res = buildCategory(rsCategory);
			}
		} catch (SQLException e) {
			throw e;
		}
		
		return res;
	}

	private Category buildCategory(ResultSet rsCategory) throws SQLException{
		return new Category(rsCategory.getLong("id"), 
							rsCategory.getString("name"));
	}

	@Override
	public ArrayList<Category> getCategories() throws SQLException {
		ArrayList<Category> categories = new ArrayList<Category>();
		String sqlCategory = ("SELECT * FROM Category" );
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCategory);
			
				ResultSet rsCategory = preparedStmt.executeQuery();
				
			while (rsCategory.next()) {
				Category res = buildCategory(rsCategory);
				categories.add(res);
			}	
		} catch (SQLException e) {
			throw e;
		}
		return categories;
	}

}
