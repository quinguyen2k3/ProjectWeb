package dao.impl;

import java.util.List;
import java.util.Map;

import entity.Sanpham;
import interfacedao.GenericDAO;

public interface IProductsDAO extends GenericDAO<Sanpham> {
	List<Sanpham> findAll(int offset, int limit);
	List<Sanpham> findAll();
	Sanpham findOne(Long id);
	List<Sanpham> findByCategoryId(Long id, Integer offset, Integer limit);
	int getTotalItems(Long category, String action, Map<String, String> conditions);
}
