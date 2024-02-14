package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductsDAO;
import entity.Sanpham;

/**
 * Servlet implementation class Index_Severlet
 */
@WebServlet("/index")
public class Index_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductsDAO productsdao = new ProductsDAO();
		long men = 1;
		long women = 2;
		long kid = 3;
		List<Sanpham> list1 = productsdao.findByCategoryId(men, null, null);
		List<Sanpham> list2 = productsdao.findByCategoryId(women, null, null);
		List<Sanpham> list3 = productsdao.findByCategoryId(kid, null, null);
		request.setAttribute("ListM", list1);
		request.setAttribute("ListW", list2);
		request.setAttribute("ListK", list3);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
