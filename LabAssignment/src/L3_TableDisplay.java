import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class L3_TableDisplay
 */
public class L3_TableDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public L3_TableDisplay() {
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
		out.println("<html><head><title>Table Display</title></head><body>");
		// Create a HTML form with post method
		out.println("<form method=\"post\" action =\""
				+ request.getContextPath() + "/L3_TableDisplay\" >");
		// input box : size of numbers
		out.println("Please input the size of numbers: ");
		out.println("<input type=\"text\" name=\"sizeofnum\" size=\"10\"><br>");
		// submit button
		out.println("<input type=\"submit\" value=\"Submit\">");
		out.println("</body></html>");

	}

	/*
	 * method to compute the square of a number
	 */
	private double computeSquare(int num) {
		double square;
		square = num * num;
		return square;
	}

	/*
	 * method to create an array of random numbers
	 */
	private static int[] randomnumber(int size) {
		int[] randomnumber = new int[size];
		Random randomGenerator = new Random();
		for (int index = 0; index < size; index++) {
			randomnumber[index] = randomGenerator.nextInt(100);
		}
		return randomnumber;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get the size of number
		int sizeofnum;
		sizeofnum = Integer.parseInt(request.getParameter("sizeofnum"));
		int number[] = randomnumber(sizeofnum);
		// Create HTML page for results
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html><head><title>Result</title></head><body>");
		out.println("<table><thead><tr><th></th><th>Numbers</th><th>Squares</th></tr></thead><tbody>");
		// output numbers and squares and compute average of these numbers and squares
		double sumofnum=0, sumofsqu=0;
		double averageofnum, averageofsqu;
		for (int i = 0; i < sizeofnum; i++) {
			out.println("<tr><td></td><td>" + number[i] + "</td><td>"
					+ computeSquare(number[i]) + "</td></tr>");
			sumofnum+=number[i];
			sumofsqu+=computeSquare(number[i]);
		}
		averageofnum = sumofnum/sizeofnum;
		averageofsqu = sumofsqu/sizeofnum;
		// output average
		out.println("<tr><td>Average</td><td>"+averageofnum+"</td><td>"+averageofsqu+"</td></tr></tfoot>");
		out.println("</tbody></table></body></html>");

	}

}
