import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class A1_Pizzeria
 */
public class A1_Pizzeria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public A1_Pizzeria() {
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
		out.println("<html><head><title>Welcome to Pizzeria</title>");
		out.println("<style>h3{color:#0000ff;}#submit{width:150px;padding:0,10px}</style></head>");
		out.println("<body><div align=\"center\"><h1>Welcome to Pizzeria</h1>");
		out.println("<h3>Today's Special: Get 4$ off for any 3 or more medium pizzas of any type!</h3>");
		// Create a HTML form with post method
		out.println("<form method=\"post\" action =\""
				+ request.getContextPath() + "/A1_Pizzeria\" >");
		out.println("<table><thead><tr><th>Type</th><th>Size</th><th>Price</th><th>Quantity</th></tr>");
		// input box: number of large cheese pizza
		out.println("<tbody><tr><td>Cheese Pizza</td><td>Large</td><td>$9.00</td>");
		out.println("<td><input type=\"text\" name=\"numoflarche\"></td></tr>");
		// input box: number of medium cheese pizza
		out.println("<tr><td></td><td>Medium</td><td>$7.50</td>");
		out.println("<td><input type=\"text\" name=\"numofmedche\"></td></tr>");
		// input box: number of large pepperoni pizza
		out.println("<tr><td>Pepperoni Pizza</td><td>Large</td><td>$10.50</td>");
		out.println("<td><input type=\"text\" name=\"numoflarpep\"></td></tr>");
		// input box: number of medium pepperoni pizza
		out.println("<tr><td></td><td>Medium</td><td>$8.50</td>");
		out.println("<td><input type=\"text\" name=\"numofmedpep\"></td></tr>");
		// input box: number of medium buffalo wings
		out.println("<tr><td>Buffalo Wings</td><td>Dozen</td><td>$6.00</td>");
		out.println("<td><input type=\"text\" name=\"numofbuf\"></td></tr></tbody>");
		// Submit button
		out.println("<tfoot><tr><td colspan=\"4\" align=\"center\"><input type=\"submit\" value=\"Submit your order\" id=\"submit\"></td></tr></tfoot>");
		out.println("</table></form></div></body></html>");

	}

	// method: apply discount or not
	public static boolean discount(String name[], int number[]) {
		String todayspecial = "Medium";
		boolean discount = false;
		for (int i = 0; i < name.length; i++) {
			if (name[i].contains(todayspecial) && number[i] >= 3) {
				discount = true;
				break;
			}
		}
		return discount;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// need 9 variables to compute the price
		int numoflarche = 0, numofmedche = 0, numoflarpep = 0, numofmedpep = 0, numofbuf = 0;
		double discount, totalprice = 0, tax, totalpricewtax;
		// get number of items from user using arrays to simplify codes
		String numbername[] = { "numoflarche", "numofmedche", "numoflarpep",
				"numofmedpep", "numofbuf" };
		int itemnumber[] = { numoflarche, numofmedche, numoflarpep,
				numofmedpep, numofbuf };
		for (int i = 0; i < numbername.length; i++) {
			if (request.getParameter(numbername[i]).equals(""))
				itemnumber[i] = 0;
			else
				itemnumber[i] = Integer.parseInt(request
						.getParameter(numbername[i]));
		}
		// Create HTML page for results
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html><head><title>Thank you for your order</title></head>");
		out.println("<body><div align=\"center\"><h2>Order Summary</h2>");
		out.println("<table><thead><tr><th>Item</th><th>Price</th><th>Quantity</th><th>Subtotal</th></tr></thead>");
		// show users' order and compute total price before tax(only show the
		// items which the number > 0 )
		out.println("<tbody>");
		String itemname[] = { "Large cheese pizza", "Medium cheese pizza",
				"Large pepperoni pizza", "Medium pepperoni pizza",
				"Buffalo Wings" };
		double itemprice[] = { 9, 7.5, 10.5, 8.5, 6 };
		NumberFormat currencyFormatter = NumberFormat
				.getCurrencyInstance(Locale.US);
		for (int j = 0; j < itemname.length; j++) {
			if (itemnumber[j] > 0) {
				out.println("<tr><td>"
						+ itemname[j]
						+ "</td><td>"
						+ currencyFormatter.format(itemprice[j])
						+ "</td><td>"
						+ itemnumber[j]
						+ "</td><td>"
						+ currencyFormatter
								.format(itemnumber[j] * itemprice[j])
						+ "</td></tr>");
				totalprice += itemnumber[j] * itemprice[j];
			}
		}
		out.println("</tbody>");
		// apply the discount only when it is qualified
		out.println("<tfoot");
		if (discount(itemname, itemnumber)) {
			discount = 4;
			out.println("<tr><td colspan =\"3\">Discount:Today's Special</td><td>"
					+ currencyFormatter.format(discount) + "</td></tr>");
		} else
			discount = 0;
		// output
		totalprice = totalprice - discount;
		tax = totalprice * 0.08;
		totalpricewtax = totalprice + tax;
		out.println("<tr><td colspan =\"3\">Total before tax</td><td>"
				+ currencyFormatter.format(totalprice) + "</td></tr>");
		out.println("<tr><td colspan = \"3\">Tax</td><td>"
				+ currencyFormatter.format(tax) + "</td></tr>");
		out.println("<tr><td colspan = \"3\">Order total</td><td>"
				+ currencyFormatter.format(totalpricewtax) + "</td></tr>");
		out.println("</tfoot></table></body></html>");

	}

}
