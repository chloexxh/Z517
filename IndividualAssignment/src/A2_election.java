import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Xiaoxu Hu
 * Servlet implementation class A2_election
 */
public class A2_election extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public A2_election() {
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
		out.println("<html><head><title>Mock Election</title>");
		out.println("<style>"
				+ "#background {width:500px;height:650px;background-color:#EEE;margin: 50px auto;}"
				+ "#title {position:relative;top:50px;text-align:center;}"
				+ "#description {position:relative;top:70px;left:50px;color:#00F;width:400px;}"
				+ "#table {position:relative;top:90px;text-align:center;}"
				+ ".table {margin:0 auto;line-height:250%;border:1px solid #000;text-align:center}"
				+ "#submit {position:relative;top:110px;text-align:center;}"
				+ "#submit input {width:100px;height:200px;font-size:150%;}"
				+ "</style></head>");
		out.println("<body><div id=\"background\"><div id=\"title\"><h1>Mock Election</h1></div>");
		out.println("<div id=\"description\"><h4><p>Note:</p>"
				+"<p>1. Please input positive integers.</p>"
				+ "<p>2.The sum of the precentages should not exceed 100.</p>"
				+ "<p>3. Any percentage left over from 100 will be assumed</p>"
				+ "<p>to represent any third party voting.</p></h4></div>");
		// Create a HTML form with post method
		out.println("<div id=\"table\"><h3>Predicted Percentages</h3>");
		out.println("<form method=\"post\" action =\""
				+ request.getContextPath() + "/A2_election\" >");
		out.println("<table class=\"table\"><thead><tr><th>State</th><th>Democratic(%)</th><th>Republican(%)</th></tr>");
		// input box: California Democratic and Republican
		out.println("<tbody><tr><td>California</td>");
		out.println("<td><input type=\"text\" name=\"caldem\" size=\"8\"></td>"
				+ "<td><input type=\"text\" name=\"calrep\" size=\"8\"></td></tr>");
		// input box: Florida Democratic and Republican
		out.println("<tbody><tr><td>Florida</td>");
		out.println("<td><input type=\"text\" name=\"flodem\" size=\"8\"></td>"
				+ "<td><input type=\"text\" name=\"florep\" size=\"8\"></td></tr>");
		// input box: New York Democratic and Republican
		out.println("<tbody><tr><td>Indiana</td>");
		out.println("<td><input type=\"text\" name=\"nydem\" size=\"8\"></td>"
				+ "<td><input type=\"text\" name=\"nyrep\" size=\"8\"></td></tr></tbody></table></div>");
		// Submit button
		out.println("<div id=\"submit\"><input type=\"submit\" value=\"Submit\"></div>");
		out.println("</form></div></body></html>");
	}

	/**
	 * This method will generate an integer array of simulated election votes by
	 * generating random numbers according to the predicted voter rates
	 * Parameters: 
	 * 1. number of voters, 
	 * 2. predicted Democratic party support rate, and 
	 * 3. predicted Republican party support rate 
	 * Return: an array with a value for each voter 
	 * predictedvotes[0] represents the total votes for Democratic 
	 * predictedvotes[1] represents the total votes for Republican 
	 * predictedvotes[2] represents the total votes for the third party
	 */
	public static int[] getVotes(int numvoters, double democraticrate,
			 double republicanrate) {
		// declare and allocate an array to hold votes
		int[] predictedvotes = { 0, 0, 0 };
		Random random = new Random();
		for (int i = 0; i < numvoters; i++) {
			// generate a random vote between 0 and 1
			double vote = random.nextDouble();
			// if the vote is between 0 and the democratic rate, add 1 to democratic votes
			if (vote <= democraticrate) predictedvotes[0]++;
			// if the vote is between democratic rate and its sum with the republican rate,
			// add 1 to republican votes
			else if (vote > democraticrate && vote <= democraticrate + republicanrate) predictedvotes[1]++;
			// otherwise, add 1 to other parties
			else predictedvotes[2]++;
		}
		return predictedvotes;
	}

	/**
	 * This method will compute the winner party of each state 
	 * Parameters: an integer array of predicted votes, an integer of electoral votes of specific state 
	 * return: an integer array represents the electoral votes of each party in the specific state
	 * e.g. return is {55,0,0} means that in the NY democratic party wins and gets the whole electoral votes
	 */
	public static int[] computeWinnerofState(int[] predictedvotes, int electoralvotes) {
		// declare an integer array to hold electoral votes
		int[] winnerofState = {0,0,0};
		// find the largest number and its subscript in the predicted votes array
		int max = predictedvotes[0],index=0;
		for (int i = 1; i < predictedvotes.length; i++) {
			if (predictedvotes[i] > max) {
				index =i;
				max = predictedvotes[i];
			}
		}
		// the subscript of the largest number (index) represents the winner of each state
		winnerofState[index] = electoralvotes;
		return winnerofState;
	}

	/**
	 * This method will compute the final winner party of this mock election
	 * Parameters: a two-dimensional array of winners of each state
	 * return an integer array represents the final result of electoral votes
	 * e.g. {55,29,29} means that 
	 * democratic party wins 55 electoral votes
	 * republican party wins 29 electoral votes
	 * third party wins 29 electoral votes
	 */
	public static int[] finalresult(int[][] winnerofState) {
		// declare an integer array to hold electoral votes
		int[] finalresult = { 0, 0, 0 };
		// e.g. winnerofstate[][0] represents the electoral votes which are hold by democratic party 
		// and thus add them together will compute the final result of electoral votes of democratic party
		for (int i = 0; i < winnerofState.length; i++) {
			for (int j = 0; j < winnerofState.length; j++) {
				finalresult[i] += winnerofState[j][i];
			}
		}
		return finalresult;
	}
	
	/** This method will print the final winner party
	 * Parameters: an integer array of final result of electoral votes
	 * Return: String of the winner party
	 */
	public static String winnerparty (int[] finalresult) {
		// find the largest number in the array, its subscript represents the winner party
		int max = finalresult[0], index=0;
		for (int i=0;i<finalresult.length;i++) {
			if(finalresult[i]>max) {
				index=i;
				max=finalresult[i];
			}
		}
		// print the winner party
		if (index==0) return "Democratic";
		if (index==1) return "Republican";
		return "Third Party";
		
	}
	
	/**
	 * This method will compute the exceptions
	 * Parameters: an array of number, an integer of index
	 * Return a boolean represents that it contains the index or not.
	 */
	public static boolean contains(int[] number,int index) {
		for (int i=0;i<number.length;i++) {
			if (number[i]==index) return true;			
		}
		return false;
	}
	
	/**
	 * This method will check the inputs are positive integer or not
	 * Parameters: String of the inputs
	 * Return: a boolean represents the result
	 */
	public static boolean isInteger(String s) {
		for (int i=0;i<s.length();i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Create HTML page for results
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html><head><title>Results</title></head>");
		out.println("<style>"
				+ "#background{position:relative;width:600px;height:1100px;background-color:#EEE;margin: 50px auto;}"
				+ "#title{position:relative;top:50px;text-align:center;}"
				+ ".warning{position:relative;top:70px;width:450px;margin:auto;color:#00f;}"
				+ "#table{position:relative;top:70px;}"
				+ ".table{margin:0 auto;line-height:250%;border:1px solid #000;border-collapse:collapse;}"
				+ ".bordertop{border-top:1px solid #000;}"
				+ "table td {text-align:center;}"
				+ "#winnerstate{position:relative;top:90px;text-align:center;color:#00f;}"
				+ "#finalwinner{position:relative;top:110px;text-align:center;color:#00f;}"
				+ "</style></head>");
		out.println("<body><div id=\"background\"><div id=\"title\"><h2>Results of Mock Election</h2></div>");
		// declare the variables which are used to get number from users
		int caldem = 0, calrep = 0, flodem = 0, florep = 0, nydem = 0, nyrep = 0;
		String name[] = { "caldem", "calrep", "flodem", "florep", "nydem", "nyrep" };
		int[]number={caldem, calrep, flodem, florep, nydem, nyrep };
		// get number from users and count the exceptions
		for (int i = 0; i < name.length; i++) {
			// if the inputs are not positive integer, return -1
			if (!isInteger(request.getParameter(name[i])))
				number[i] = -1;
			// if the inputs are empty, return -2
			else if (request.getParameter(name[i]).equals(""))
				number[i] = -2;
			else {
				number[i] = Integer.parseInt(request.getParameter(name[i]));
			}
		}
		/**
		 * check the inputs 
		 * if it is empty, print out "Sorry, you must fill in each blank. Please check your inputs." 
		 * if it is not positive integer, print out "Sorry, you must type into positive integers. Please check your inputs." 
		 * if it exceeds 100, print out "Sorry, your sum has exceeded 100. Please check your inputs."
		 */
		if (contains(number, -1)
				|| contains(number, -2)
				|| ((number[0] + number[1]) > 100
						|| (number[2] + number[3]) > 100 || (number[4] + number[5]) > 100)) {
			out.println("<div class=\"warning\">");
			if (contains(number, -2))
				out.println("<p>Sorry, you must fill in each blank. Please check your inputs.</p>");
			if (contains(number, -1))
				out.println("<p>Sorry, you must type into positive integers. Please check your inputs.</p>");
			if ((number[0] + number[1]) > 100 || (number[2] + number[3]) > 100
					|| (number[4] + number[5]) > 100)
				out.println("Sorry, your sum has exceeded 100. Please check your inputs.");
			out.println("</div>");
		}
		else {
				// compute votes for each state using getVotes method
				int numofCalVoters = 3833252, numofFloVoters = 1955286, numofNYVoters = 1965113;
				int[] prevotesofcal = getVotes(numofCalVoters, number[0]/100.0, number[1]/100.0);
				int[] prevotesofflo = getVotes(numofFloVoters, number[2]/100.0, number[3]/100.0);
				int[] prevotesofny = getVotes(numofNYVoters, number[4]/100.0, number[5]/100.0);
				//compute winner of each state using computeWinnerofState method
				int[] winnerofCal = computeWinnerofState(prevotesofcal, 55);
				int[] winnerofFlo = computeWinnerofState(prevotesofflo,29);
				int[] winnerofNY = computeWinnerofState(prevotesofny, 29);
				// show results using for loop
				// declare required variables for the "for loop"
				int[][] winnerofState = {winnerofCal, winnerofFlo, winnerofNY};
				int[][] prevotes = { prevotesofcal, prevotesofflo, prevotesofny };
				String[] partyname = {"Democratic","Republican","Third Party"};
				String[] statename = { "CALIFORNIA", "FLORIDA", "NEW YORK" };
				int[] statevoters = { numofCalVoters, numofFloVoters,
						numofNYVoters };
				// print the result
				out.println("<div id=\"table\"><table class=\"table\">");
				for (int i = 0; i < statename.length; i++) {
					out.println("<tr><td colspan=\"4\"  class=\"bordertop\"><b>"
							+ statename[i] + "</b></td></tr>");
					out.println("<tr><td colspan=\"4\"><b>Total Voters: "
							+ new DecimalFormat(",###").format(statevoters[i])
							+ "</b></td></tr>");
					out.println("<tr><td width=\"100\">Party</td><td width=\"100\">Voters</td><td width=\"100\">Percentage</td><td width=\"100\">Electoral Votes</td></tr>");
					for (int j = 0; j < prevotes[i].length; j++) {
						double voterate = (double) prevotes[i][j]
								/ statevoters[i];
						DecimalFormat df = new DecimalFormat("0.00%");
						String svoterate = df.format(voterate);
						out.println("<tr><td>" + partyname[j] + "</td><td>"
								+ prevotes[i][j] + "</td><td>" + svoterate
								+ "</td><td>" + winnerofState[i][j]
								+ "</td></tr>");
					}
				}
				out.println("</table></div>");
				// compute and print winner of each state
				int[] finalwinner = finalresult(winnerofState);
				out.println("<div id=\"winnerstate\"");
				for (int i = 0; i < winnerofState.length; i++) {
					out.println("<p>"+partyname[i] + ": " + finalwinner[i]+"</p>");
				}
				out.println("</div>");
				// compute and print the final winner party
				out.println("<div id=\"finalwinner\">");
				out.println("<h3>Congratulations! "+winnerparty(finalwinner)+" wins!</h3>");
				out.println("</div>");
			}
		out.println("</div></body></html>");
	}
}





