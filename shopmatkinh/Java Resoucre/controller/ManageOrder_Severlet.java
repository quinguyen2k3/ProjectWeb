package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrdersDAO;
import dao.ProductsDAO;
import dao.UsersDAO;
import entity.Donhang;

/**
 * Servlet implementation class ManageOrder_Severlet
 */
@WebServlet("/manageorder")
public class ManageOrder_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageOrder_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersDAO ordersdao = new OrdersDAO();
		UsersDAO usersdao = new UsersDAO();
		List<Donhang> list1 = ordersdao.getOrders();
		List<String> list2 = usersdao.getAllCustomerNameOfOrder();
		request.setAttribute("listO", list1);
		request.setAttribute("listN", list2);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manageorders.jsp");
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
