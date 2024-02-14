package service.impl;

import java.util.List;

import entity.Sanpham;

public interface IProductsService {
	List<Sanpham> findAll(int offset, int limit);
	List<Sanpham> findAll();
}
