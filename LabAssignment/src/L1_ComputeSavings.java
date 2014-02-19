
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class L1_ComputeSavings
 */
public class L1_ComputeSavings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public L1_ComputeSavings() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("command:" + request.getParameter("command"));
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		// Start to print HTML page
		out.println("<html> <head> <title> Compute Savings </title> </head>");
		out.println("<body> <h2> Please submit your information </h2>");
		// Create a HTML form with post method
		out.println("<form method = \"post\"  action = \""
				+ request.getContextPath() + "/L1_ComputeSavings\" >");
		// First input box : Initial balance
		out.println("<table> <tr> <td> Your initial balance: </td>");
		out.println("<td> <input type=\"text\" name=\"initialbalance\"> </td></tr>");
		// Second input box : Annual investment
		out.println("<tr> <td> Your annual investment: </td>");
		out.println("<td> <input type= \"text\" name=\"annualinvestment\"> </td></tr>");
		// Third input box : Goal savings amount
		out.println("<tr> <td> Your goal savings amount: </td>");
		out.println("<td> <input type=\"text\" name=\"goalsavings\"> </td></tr>");
		// Submit button
		out.println("<tr> <td> <input type=\"submit\" value=\"Compute Number of Years\"> </td></tr></table> ");
		out.println("</form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Need 5 variables to compute the number of years
		int numberyears = 0;
		double balance, yearlyinterest = 0.0, annualinvestment, goalsavings;
		// get initial balance, annual investment and goal savings amount from
		// user
		balance = Double.parseDouble(request.getParameter("initialbalance"));
		annualinvestment = Double.parseDouble(request
				.getParameter("annualinvestment"));
		goalsavings = Double.parseDouble(request.getParameter("goalsavings"));
		// compute
		while (balance < goalsavings) {
			yearlyinterest = balance * 0.05;
			balance = balance + yearlyinterest + annualinvestment;
			++numberyears;
		}
		// Create HTML page for results
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html> <head> <title> Result </title> </head>");
		out.println("<body> <h2> Compute result: </h2>");
		out.println("Need " + Integer.toString(numberyears)
				+ " years to achieve your goal.");
		out.println("</body> </html>");
	}

}
