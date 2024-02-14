package controller;

import java.io.IOException;
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

/**
 * Servlet implementation class updatePassword_Severlet
 */
@WebServlet("/updatepassword")
public class updatePassword_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePassword_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("accountsettings");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UsersDAO usersdao = new UsersDAO();
		Object o = session.getAttribute("acc");
		Nguoidung user = (Nguoidung) o;
		String curpwd = request.getParameter("currentpwd");
		String newpassword = request.getParameter("newpassword");
		String check = request.getParameter("check");
		if(check != null) {
			JSONObject jsonResponse = new JSONObject();
			try {  
				if(!usersdao.checkPassword(user.getEmail(), curpwd)) {
					jsonResponse.put("mess", "Your password is invalid !");
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
			usersdao.updatePassword(user.getEmail(), newpassword);
			user.setMatkhau(newpassword);
			session.setAttribute("acc", user);
			response.sendRedirect("accountsettings");
		}
	}

}
