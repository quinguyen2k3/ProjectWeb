package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductTypesDAO;
import dao.ProductsDAO;
import entity.Loaisp;
import entity.Sanpham;

/**
 * Servlet implementation class ProductList_Severlet
 */
@WebServlet("/product")
public class ProductList_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductsDAO productsdao = new ProductsDAO();
		ProductTypesDAO typesdao = new ProductTypesDAO();
		String indexP = request.getParameter("index");
		String tcategory = request.getParameter("category");
		Long category = null;
		if(indexP == null) 
			indexP = "1";
		if(tcategory != null)
			category = Long.parseLong(tcategory);
		
		int index = Integer.parseInt(indexP);
		List<Sanpham> list= null;
		int maxitems = 6;
		int offset = (index - 1)*maxitems;
		if(category != null) {
			list = productsdao.findByCategoryId(category, offset, maxitems);
		}else {
			list = productsdao.findAll(index, maxitems);
		}
		
		request.setAttribute("listP", list);
		List<Loaisp> types = typesdao.getType();
		request.setAttribute("listT", types);
		request.setAttribute("tag", index);
		request.setAttribute("category", category);
		int count = productsdao.getTotalItems(category, null, null);
		int totalPage = count / 6 ;
		if(count % 6 != 0) {
			totalPage ++ ;
		}
		request.setAttribute("endPage", totalPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
