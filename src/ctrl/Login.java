package ctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CustomerDAO;
import bean.CustomerBean;

/**
 * Servlet implementation class LoginAndRegister
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO customerAccessor = 
				(CustomerDAO) request.getServletContext().getAttribute("customerAccessor");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//response.setContentType("application/json");
		if(username != null && password != null){
			CustomerBean customer;
			try {
				customer = customerAccessor.retrieveCustomer(username);
				response.setContentType("text/html");
				if(customer != null && password.equals(customer.getPassword().trim())){
					//System.out.println("correct user");
					response.getWriter().print("Welcome back " + username + "!");

					request.getSession().setAttribute("user", customerAccessor.retrieveCustomer(username));
				}
				else{
					//System.out.println("Wrong!");
					response.getWriter().print("Sorry, your are not logged in!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}