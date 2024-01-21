package lk.ijse.naptune.model.transaction;

import lk.ijse.naptune.db.DbConnection;
import lk.ijse.naptune.dto.PlaceOderDto;
import lk.ijse.naptune.model.StockModel;
import lk.ijse.naptune.model.SupOrderDetailModel;
import lk.ijse.naptune.model.SupOrderModel;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceSupplierOrderTransactionModel {

    SupOrderModel supOrderModel = new SupOrderModel();
    StockModel stockModel = new StockModel();
    SupOrderDetailModel supOrderDetailModel = new SupOrderDetailModel();

    public boolean placeSupplierOrder(PlaceOderDto placeOderDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);


            boolean isOrderSaved = SupOrderModel.saveOrder(placeOderDto.getSupItemId(), placeOderDto.getSupId(), placeOderDto.getDate());
            if (isOrderSaved) {
                boolean isUpdated = stockModel.updateStock (placeOderDto.getTmList());
                if(isUpdated) {
                    boolean isOrderDetailSaved = supOrderDetailModel.saveOrderDetail(placeOderDto.getSupItemId(), placeOderDto.getTmList());
                    if(isOrderDetailSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}
