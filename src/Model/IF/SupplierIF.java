package Model.IF;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Model.Supplier;

public interface SupplierIF {
		
		Supplier getSupplierByCVRNumber(String cvrNo) throws SQLException;
		
		Supplier getSupplierById(long id) throws SQLException;
		
		Supplier createSupplier(Supplier supplier) throws SQLException;

		void updateSupplier(Supplier supplier) throws SQLException;
		
		void deleteSupplier(String cvrNo) throws SQLException;
		
		ArrayList<Supplier> getSupplierList() throws SQLException;
}

