import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Xiaoxu Hu
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
	 * Xiaoxu Hu
	 * This program is designed for an online pizza restaurant.
	 * Customers can order and then see the order summary.
	 * doGet() will get the information about the number of each items
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
		out.println("<html><head><title>Pizzeria</title>");
		out.println("<style>" 
		+ "#background {width:500px;height:650px;background-color:#EEE;margin: 50px auto;}"
		+ "#welcome {position:relative;top:50px;text-align:center;}"
		+ "#todayspecial {position:relative;top:70px;color:#00F;text-align:center;}"
		+ "#menu {position:relative;top:90px;text-align:center;}"
		+ ".menu {margin:0 auto;line-height:250%;border:1px solid #000;}"
		+ "#submit {position:relative;top:110px;text-align:center;}"
		+ "#submit input {width:200px;height:200px;font-size:150%;}"
		+ "</style></head>");
		out.println("<body><div id=\"background\"><div id=\"welcome\"><h1>Welcome to Pizzeria</h1></div>");
		out.println("<div id=\"todayspecial\"><h3><p>Today's Special:</p><p>Get 4$ off for any 3 or more medium pizzas of any type!</p></h3></div>");
		// Create a HTML form with post method
		out.println("<div id=\"menu\"><h3>MENU</h3>");
		out.println("<form method=\"post\" action =\""
				+ request.getContextPath() + "/A1_Pizzeria\" >");
		out.println("<table class=\"menu\"><thead><tr><th>Type</th><th>Size</th><th>Price</th><th>Quantity</th></tr>");
		// input box: number of large cheese pizza
		out.println("<tbody><tr><td>Cheese Pizza</td><td>Large</td><td>$9.00</td>");
		out.println("<td><input type=\"text\" name=\"numoflarche\" size=\"3\"></td></tr>");
		// input box: number of medium cheese pizza
		out.println("<tr><td></td><td>Medium</td><td>$7.50</td>");
		out.println("<td><input type=\"text\" name=\"numofmedche\" size=\"3\"></td></tr>");
		// input box: number of large pepperoni pizza
		out.println("<tr><td>Pepperoni Pizza</td><td>Large</td><td>$10.50</td>");
		out.println("<td><input type=\"text\" name=\"numoflarpep\" size=\"3\"></td></tr>");
		// input box: number of medium pepperoni pizza
		out.println("<tr><td></td><td>Medium</td><td>$8.50</td>");
		out.println("<td><input type=\"text\" name=\"numofmedpep\" size=\"3\"></td></tr>");
		// input box: number of medium buffalo wings
		out.println("<tr><td>Buffalo Wings</td><td>Dozen</td><td>$6.00</td>");
		out.println("<td><input type=\"text\" name=\"numofbuf\" size=\"3\"></td></tr></tbody></table></div>");
		// Submit button
		out.println("<div id=\"submit\"><input type=\"submit\" value=\"Submit your order\"></div>");
		out.println("</form></div></body></html>");

	}

	/**
	 * method: apply discount or not
	 * it is easy to use if the today's special is changed
	 */
	public static boolean discount(String name[], int number[]) {
		String todayspecial = "Medium";
		int specialnumber = 3, ordernumber = 0;
		boolean discount = false;
		for (int i = 0; i < name.length; i++) {
			if (name[i].contains(todayspecial)) {
				ordernumber += number[i];
			}
			if (ordernumber >= specialnumber) {
				discount = true;
				break;
			}
		}
		return discount;
	}

	/**
	 * Xiaoxu Hu
	 * doPost() compute the final price.
	 * If customers do not type in the number or negative number, there is a warning information.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// need 10 variables to compute the price
		int numoflarche = 0, numofmedche = 0, numoflarpep = 0, numofmedpep = 0, numofbuf = 0, ordernum=0;
		double discount, totalprice = 0, tax, totalpricewtax;
		// Create HTML page for results
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html><head><title>Thank you for your order</title></head>");
		out.println("<style>"
				+ "#background{width:500px;height:650px;background-color:#EEE;margin: 50px auto;}"
				+ "#ordertitle{position:relative;top:50px;text-align:center;}"
				+ ".undertitle{position:relative;top:70px;text-align:center;}"
				+ ".ordertable{margin:0 auto;line-height:250%;border:1px solid #000;border-collapse:collapse;}"
				+ ".blue{color:#00F;}"
				+ ".bordertop{border-top:1px solid #000;}"
				+ "</style></head>");
		out.println("<body><div id=\"background\"><div id=\"ordertitle\"><h2>Order Summary</h2></div>");
		// get number of items from user and calculate order number
		String numbername[] = { "numoflarche", "numofmedche", "numoflarpep","numofmedpep", "numofbuf" };
		int itemnumber[] = { numoflarche, numofmedche, numoflarpep, numofmedpep, numofbuf };
		for (int i=0;i<numbername.length;i++) {
			if (request.getParameter(numbername[i]).equals("")||request.getParameter(numbername[i]).equals("0")){
				itemnumber[i] = 0;
				ordernum+=0;
			} else {
				itemnumber[i] = Integer.parseInt(request.getParameter(numbername[i]));
				if (itemnumber[i]<0) {
					ordernum=999;
					break;
				}
				else ordernum+=1;
			}
		}
		// check order number
		if (ordernum == 0) out.println("<div class=\"undertitle\"><h3 class=\"blue\">Sorry, you do not order anything.</h3><h3 class=\"blue\">Please check your order.</h3></div>");
		else if (ordernum >= 999) out.println("<div class=\"undertitle\"><h3 class=\"blue\">Sorry, you must type in a positive integer.</h3><h3 class=\"blue\">Please check your order.</h3></div>");
		else {
		out.println("<div class=\"undertitle\"><table class=\"ordertable\"><thead><tr><th>Item</th><th>Price</th><th>Quantity</th><th>Subtotal</th></tr></thead>");
		out.println("<tbody>");
		//only show the items which the number > 0
		String itemname[] = { "Large cheese pizza", "Medium cheese pizza","Large pepperoni pizza", "Medium pepperoni pizza",
				"Buffalo Wings" };
		double itemprice[] = { 9, 7.5, 10.5, 8.5, 6 };
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
		for (int i = 0; i < numbername.length; i++) {
			if (itemnumber[i]>0) {
				out.println("<tr><td>" + itemname[i] + "</td><td>" + currencyFormatter.format(itemprice[i])
						+ "</td><td align=\"center\">" + itemnumber[i] + "</td><td>" + currencyFormatter.format(itemnumber[i] * itemprice[i])
						+ "</td></tr>");
				totalprice += itemnumber[i] * itemprice[i];
			}
		}
		// apply the discount only when it is qualified
		if (discount(itemname, itemnumber)) {
			discount = 4;
			out.println("<tr><td colspan =\"3\" class=\"blue\">Discount:Today's Special</td><td class=\"blue\">-"
					+ currencyFormatter.format(discount) + "</td></tr>");
		} else discount = 0;
		out.println("</tbody>");
		// output
		totalprice = totalprice - discount;
		tax = totalprice * 0.08;
		totalpricewtax = totalprice + tax;
		out.println("<tfoot><tr><td colspan =\"3\" class=\"bordertop\">Total before tax</td><td class=\"bordertop\">"
				+ currencyFormatter.format(totalprice) + "</td></tr>");
		out.println("<tr><td colspan = \"3\">Tax</td><td>" + currencyFormatter.format(tax) + "</td></tr>");
		out.println("<tr><td colspan = \"3\"><b>Order total</b></td><td><b>" 
		+ currencyFormatter.format(totalpricewtax) + "</b></td></tr>");
		out.println("</tfoot></table><h3>Thank you for your order!</h3></div></body></html>");
	}
}
}
