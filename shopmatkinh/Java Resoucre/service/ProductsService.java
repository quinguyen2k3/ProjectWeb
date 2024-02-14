package service;

import java.util.List;
import javax.inject.Inject;
import dao.impl.IProductsDAO;
import entity.Sanpham;
import service.impl.IProductsService;
public class ProductsService implements IProductsService{
	
	@Inject
	private IProductsDAO productsDAO;
	
	public List<Sanpham> findAll(int offset, int limit){
		return productsDAO.findAll(offset, limit);
	}
	
	public  List<Sanpham> findAll(){
		return productsDAO.findAll();
	}
}
