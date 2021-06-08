package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.IF.CategoryIF;
import Model.Model.Category;
import Model.Model.MessagesEnum;

public class CategoryDB implements CategoryIF{
	
	@Override
	public Category getCategory(long id) throws SQLException {
		Category res = null;
		String sqlCategory = ("SELECT * FROM Category WHERE id = ?");
		
		Connection con = DBConnection.getInstance().getConnection();

    try {
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
		
		Connection con = DBConnection.getInstance().getConnection();

    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCategory);
			
				ResultSet rsCategory = preparedStmt.executeQuery();
				
			while (rsCategory.next()) {
				Category res = buildCategory(rsCategory);
				categories.add(res);
			}	
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		return categories;
	}

	public Category createCategory(Category category) throws SQLException {
		String sqlCreate = "INSERT INTO Category (name) VALUES (?)";
		
	    Connection con = DBConnection.getInstance().getConnection();
	
	    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
	
			preparedStmt.setString(1, category.getName());
			preparedStmt.executeUpdate();
			
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
            	category.setId(rs.getLong(1));
            }
            else {
            	throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
            
		} catch (SQLException e) {
			throw e;
		}

		return category;
	}
}
