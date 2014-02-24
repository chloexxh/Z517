
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class L2_SalesMethods
 */
public class L2_SalesMethods extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public L2_SalesMethods() {
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
		// start to print HTML page
		out.println("<html><head><title>Books</title></head>");
		// Create a HTML form with post method
		out.println("<form method=\"post\" action =\""
				+ request.getContextPath() + "/L2_SalesMethods\" >");
		// input box: number of books
		out.println("<table><tr><td>Enter Number Books at $20 (Get 10% off for 3 or more!):</td>");
		out.println("<td><input type=\"text\" name=\"numofbooks\"></td>");
		// input box: number of magazines
		out.println("<table><tr><td>Enter Number Magazines at $3 (Get 5% off for 3 or more!):</td>");
		out.println("<td><input type=\"text\" name=\"numofmag\"></td>");
		// Checkout button
		out.println("<tr><td><input type=\"submit\" value=\"Checkout\"></td></tr>");
		out.println("</table></form></body></html>");
	}

	public static double computePrice(int number, double price, double rate) {
		double subtotal;
		if (number >= 3) {
			subtotal = price * number * rate;
		} else {
			subtotal = price * number;
		}
		return subtotal;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Need 5 variables to compute final price
		int numofbooks, numofmag;
		double bookprice, magprice, totalprice;
		// Create HTML page for results
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html><head><title>Checkout</title></head><body>");
		// get number of books from user
		if (request.getParameter("numofbooks").equals("")) {
			numofbooks = 0;
		} else {
			numofbooks = Integer.parseInt(request.getParameter("numofbooks"));
		}
		// get number of magazines from user
		if (request.getParameter("numofmag").equals("")) {
			numofmag = 0;
		} else {
			numofmag = Integer.parseInt(request.getParameter("numofmag"));
		}
		// method computePrice
		bookprice = computePrice(numofbooks,20,0.9);
		magprice = computePrice(numofmag,3,0.95);
		totalprice = bookprice + magprice;
		// Output
		NumberFormat currencyFormatter = NumberFormat
				.getCurrencyInstance(Locale.US);
		out.println("<table><thead><tr><th>Catagory</th><th>Number</th><th>Price</th></tr></thead");
		out.println("<tbody><tr><td>Books</td><td>" + numofbooks + "</td><td>"
				+ currencyFormatter.format(bookprice) + "</td></tr>");
		out.println("<tbody><tr><td>Magazines</td><td>" + numofmag
				+ "</td><td>" + currencyFormatter.format(magprice)
				+ "</td></tr></tbody>");
		out.println("<tfoot><tr><td>Total</td><td></td><td><b>"
				+ currencyFormatter.format(totalprice)
				+ "</b></td></tr></tfoot></table>");
	}

}
