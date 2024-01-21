package lk.ijse.naptune.bo;

import lk.ijse.naptune.bo.Custom.Impl.*;
import lk.ijse.naptune.dao.Custom.Impl.StockDAOImpl;
import lk.ijse.naptune.dao.Custom.Impl.SuppliersDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public  static BOFactory getBoFactory (){
        return (boFactory == null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BoTypes {
        CUSTOMER,EMPLOYEE,ORDER,PAYMENT,STOCK,SUPPLIERS
    }

    public  SuperBO getBO (BoTypes boTypes){

        switch (boTypes){
            case CUSTOMER:
                return  new CustomerBOImpl();

            case EMPLOYEE:
                return new EmployeeBOImpl();

            case ORDER:
                return new OrderBOImpl();

            case PAYMENT:
                return new PaymentBOImpl();

            case STOCK:
                return new StockBOImpl();

            case SUPPLIERS:
                return new SuppliersBOImpl();

            default:
                return null;
        }

    }

}
