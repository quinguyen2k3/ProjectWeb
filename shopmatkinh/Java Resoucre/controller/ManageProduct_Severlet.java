package controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.SystemConstant;
import dao.ProductTypesDAO;
import dao.ProductsDAO;
import entity.Loaisp;
import entity.Sanpham;
import paging.PageRequest;
import paging.Pageble;
import service.impl.IProductsService;

/**
 * Servlet implementation class ManageProduct_Severlet
 */
@WebServlet("/manageproduct")
public class ManageProduct_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	private IProductsService productsservice;
		
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Sanpham sanpham = new Sanpham();
    	String pageStr = request.getParameter("page");
    	if(pageStr != null) {
    		sanpham.setPage(Integer.parseInt(pageStr));
    	}else {
    		sanpham.setPage(1);
    	}
    	sanpham.setTotalItem(productsservice.findAll().size());
    	PageRequest paging = new PageRequest(sanpham.getPage(), sanpham.getTotalItem());
    	sanpham.setResult(productsservice.findAll(paging.getOffset(), paging.getLimit()));
		ProductTypesDAO typesdao = new ProductTypesDAO();
		List<Loaisp> list2 = typesdao.getType();
		request.setAttribute(SystemConstant.MODEL,sanpham);
		request.setAttribute("listT", list2 );
		RequestDispatcher rd = request.getRequestDispatcher("/manageproducts.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
