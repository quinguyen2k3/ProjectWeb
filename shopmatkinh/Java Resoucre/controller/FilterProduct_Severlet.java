package controller;
import java.lang.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conditions.Conditions;
import dao.ProductTypesDAO;
import dao.ProductsDAO;
import entity.Sanpham;

/**
 * Servlet implementation class FilterProduct_JSP
 */
@WebServlet("/filterproduct")
public class FilterProduct_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterProduct_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ProductsDAO productsdao = new ProductsDAO();
		ProductTypesDAO typesdao = new ProductTypesDAO();
		String indexP = request.getParameter("index");
		String filter = request.getParameter("filter");
		Object pfilter = session.getAttribute("pfilter");
		if(indexP == null) {
			indexP = "1";
		}
		int index = Integer.parseInt(indexP);
		List<Sanpham> list= new ArrayList<>();
		Map<String,String> conditions = new HashMap<>();
		if(pfilter != null) {
			if(filter != null) {
				conditions = Conditions.toMapStringString(request);
				session.setAttribute("pfilter", Conditions.toMapStringString(request));
				if(request.getParameter("loai") != null) 
					conditions.put("loai", request.getParameter("loai"));
			}else {
				conditions = (Map<String, String>) pfilter;
			}
		}else {
			conditions = Conditions.toMapStringString(request);
			session.setAttribute("pfilter", Conditions.toMapStringString(request));
		}
		list = productsdao.filterProduct(conditions, index);
		String action = "filter";
		request.setAttribute("action", action);
		request.setAttribute("listP", list);
		request.setAttribute("listT", typesdao.getType());
		request.setAttribute("tag", index);
		int count = productsdao.getTotalItems(null,"filter", conditions);
		int totalPage = count/ 6 ;
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
