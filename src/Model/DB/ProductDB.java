package Model.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.Model.Product;
import Model.DBIF.ProductDBIF;

public class ProductDB implements ProductDBIF {
	
	public Product getProduct(long barcode) throws SQLException {
		Product res = null;
		String sqlProduct = String.format("SELECT * FROM Product WHERE barcode = '%s'");
		
		try (Statement s = DBConnection.getInstance().getConnection().createStatement()){
			ResultSet rsProduct = s.executeQuery(sqlProduct);
			if (rsProduct.next()) {
				res = buildProduct(rsProduct);
			}
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}

	private Product buildProduct(ResultSet rsProduct) {
		return null;
	}
}
