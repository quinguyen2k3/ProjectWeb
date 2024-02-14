package controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import apicaller.ApiCaller;
import dao.UsersDAO;
import entity.Nguoidung;

/**
 * Servlet implementation class UpdateInforCus_Severlet
 */
@WebServlet("/updateinforcus")
public class UpdateInforCus_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInforCus_Severlet() {
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
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String t_city = request.getParameter("city");
		String t_district = request.getParameter("district");
		String t_ward = request.getParameter("ward");
		String email = request.getParameter("email");
		String street_housenumber =request.getParameter("street_housenumber");
		String decoded = URLDecoder.decode(street_housenumber, "UTF-8");
		String oldaddress = request.getParameter("oldaddress");
		String json = "";
		try {
			json = ApiCaller.getJsonFromApi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		UsersDAO usersdao = new UsersDAO();
		HttpSession session = request.getSession();
		Nguoidung user = (Nguoidung) session.getAttribute("acc");
		if(t_ward != null && !t_ward.isEmpty()) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(json);
			String address = "";
			loop:
			for (JsonNode personNode1 : rootNode) {
	            if (personNode1.path("Id").asText().equals(t_city)) {
	            	String city = personNode1.path("Name").asText();
	            	address = ", "+city + address;
	                for(JsonNode personNode2 : personNode1.path("Districts")) {
	                	if(personNode2.path("Id").asText().equals(t_district)) {
	                		 String district = personNode2.path("Name").asText();
	                		 address = ", " + district + address;
	                         for(JsonNode personNode3 : personNode2.path("Wards")) {
	                        	 if(personNode3.path("Id").asText().equals(t_ward)) {
	                        		 String ward = personNode3.path("Name").asText();
	                        		 address = ", "+ward + address;
	                        		 break loop;
	                        	 }
	                         }
	                	}
	                }
	            }
	        }
			address = decoded + address;
			usersdao.updateInforCus(name, phone, address, email);
			user.setDiachi(address);
		}else {
			usersdao.updateInforCus(name, phone, oldaddress, email);
		}
		user.setDienthoai(phone);
		user.setTenkh(name);
		session.setAttribute("acc", user);
		response.sendRedirect("accountsettings");
	}
}
