package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UsersDAO;
import entity.Nguoidung;
import entity.Sanpham;

/**
 * Servlet implementation class Login_Severlet
 */
@WebServlet("/login")
public class Login_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/login-signup.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UsersDAO usersdao = new UsersDAO();
		String check = request.getParameter("check");
		if(check != null) {
			JSONObject jsonResponse = new JSONObject();
			try {  
				if(usersdao.checkAccount(email, password) == null) {
					jsonResponse.put("mess", "Your password or email is wrong !");
					jsonResponse.put("check", false);
					
				}else {
					jsonResponse.put("check", true);
				}
			}catch(JSONException e) {
				e.printStackTrace();
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponse.toString());
		}else {			
			HttpSession session = request.getSession();
			session.setAttribute("acc", usersdao.checkAccount(email,password));
			response.sendRedirect("index");
			
		}
	}
}
