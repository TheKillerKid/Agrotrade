package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Model.Supplier;

public interface SupplierIF {
		
		Supplier getSupplierByCVRNumber(long cvrNo) throws SQLException;
		
		Supplier getSupplierById(long id) throws SQLException;
		
		long createSupplier(Supplier supplier) throws SQLException;

		void updateSupplier(Supplier supplier) throws SQLException;
		
		void deleteSupplier(long cvrNo) throws SQLException;
		
		ArrayList<Supplier> getSupplierList() throws SQLException;
}

