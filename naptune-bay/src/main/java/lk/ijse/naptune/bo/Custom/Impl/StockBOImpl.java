package lk.ijse.naptune.bo.Custom.Impl;

import lk.ijse.naptune.bo.Custom.StockBO;
import lk.ijse.naptune.dao.Custom.Impl.StockDAOImpl;
import lk.ijse.naptune.dao.Custom.StockDAO;
import lk.ijse.naptune.dto.StockDto;
import lk.ijse.naptune.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {

    StockDAO stockDAO = new StockDAOImpl();

    @Override
    public boolean Save(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Stock(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getUserName()));
    }

    @Override
    public StockDto search(String id) throws SQLException, ClassNotFoundException {
        Stock stock = stockDAO.search(id);
        StockDto stockDto = new StockDto(stock.getCode(), stock.getDescription(), stock.getUnitPrice(), stock.getQtyOnHand(), stock.getUserName());
        return stockDto;
    }

    @Override
    public boolean update(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getUserName()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(id);
    }

    @Override
    public ArrayList<StockDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> stocks = stockDAO.getAllCustomers();
        ArrayList<StockDto> stockDtos=new ArrayList<>();
        for (Stock stock:stocks) {
            stockDtos.add(new StockDto(stock.getCode(), stock.getDescription(), stock.getUnitPrice(), stock.getQtyOnHand(), stock.getUserName()));
        }
        return stockDtos;
    }
}
