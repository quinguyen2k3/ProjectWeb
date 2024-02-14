package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UsersDAO;

/**
 * Servlet implementation class SignUp_Severlet
 */
@WebServlet("/signup")
public class SignUp_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp_Severlet() {
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
				if(!usersdao.checkEmail(email)) {
					jsonResponse.put("mess", "Your email already exists !");
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
		}
		else{
			usersdao.signUp(email, password);
			request.setAttribute("mess","Account registered successfully");
			RequestDispatcher rd = request.getRequestDispatcher("/login-signup.jsp");
			rd.forward(request, response);
		}		
	}
}
