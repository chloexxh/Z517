
import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class L1_ComputeInterest
 */
public class L1_ComputeInterest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public L1_ComputeInterest() {
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
		System.out.println("command: " + request.getParameter("command"));
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		// Start to print HTML page
		out.println("<html><head>");
		out.println("<title>Help Page</title></head><body>");
		out.println("<h2>Please submit your information</h2>");
		// Create a HTML form with post method
		out.println("<form method=\"post\" action =\""
				+ request.getContextPath() + "/L1_ComputeInterest\" >");
		// First input box: Initial balance
		out.println("<table border=\"0\"><tr><td valign=\"top\">");
		out.println("Your initial balance: </td>  <td valign=\"top\">");
		out.println("<input type=\"text\" name=\"initialbalance\" size=\"20\">");
		// Second input box: Years
		out.println("</td></tr><tr><td valign=\"top\">");
		out.println("Years: </td>  <td valign=\"top\">");
		out.println("<input type=\"text\" name=\"years\" size=\"20\">");
		// Third input box: Annual Investment
		out.println("</td></tr><tr><td valign=\"top\">");
		out.println("Annual Investment: </td> <td valign=\"top\">");
		out.println("<input type=\"text\" name=\"annualinvestment\" size=\"20\">");
		// Submit button
		out.println("</td></tr><tr><td valign=\"top\">");
		out.println("<input type=\"submit\" value=\"Compute Interest\"></td></tr>");
		out.println("</table></form>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Need 3 variables to compute final balance
		double balance, yearlyinterest = 0.0, annualInvestment;
		int numyears = 0;
		// Create HTML page for results
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<title>Result</title></head><body>");
		out.println("<h2>Compute result:</h2>");
		// get savings amount and number of years from user
		balance = Double.parseDouble(request.getParameter("initialbalance"));
		numyears = Integer.parseInt(request.getParameter("years"));
		annualInvestment = Double.parseDouble(request
				.getParameter("annualinvestment"));
		// compute 5% interest each year and add to balance
		for (int i = 1; i <= numyears; i++) {
			yearlyinterest = balance * 0.05;
			balance = balance + yearlyinterest + annualInvestment;
		}
		NumberFormat currencyFormatter = NumberFormat
				.getCurrencyInstance(java.util.Locale.US);
		// Output
		out.println("User final balance: " + currencyFormatter.format(balance));
		out.println("</body></html>");
	}

}
