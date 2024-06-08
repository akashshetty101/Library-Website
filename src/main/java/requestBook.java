

import jakarta.servlet.ServletException;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * Servlet implementation class requestBook
 */
@WebServlet("/requestBook")
public class requestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public requestBook() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();


		String firstname  = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String ID = req.getParameter("ID");
		String Book = req.getParameter("Book");
		String AuFName = req.getParameter("AuFName");
		String AuLName = req.getParameter("AuLName");
		String message = req.getParameter("message");
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset=\"UTF-8\">");
		pw.println("<title>Book Request Information</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div style=\"text-align: center;\">");
		pw.println("<h1>Book Request Information</h1>");
		pw.println("<p><strong>First Name:</strong> " + firstname + "</p>");
		pw.println("<p><strong>Last Name:</strong> " + lastname + "</p>");
		pw.println("<p><strong>Library ID:</strong> " + ID + "</p>");
		pw.println("<p><strong>Book Title:</strong> " + Book + "</p>");
		pw.println("<p><strong>Author First Name:</strong> " + AuFName + "</p>");
		pw.println("<p><strong>Author Last Name:</strong> " + AuLName + "</p>");
		pw.println("<p><strong>Additional information:</strong> " + message + "</p>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		
		// store data 
		
		   FileWriter fw = new FileWriter("/Users/akash/eclipse-workspace/LibraryWebApplication/src/main/java/book_requests.csv", true); // Append mode
	        PrintWriter fileWriter = new PrintWriter(fw);
	        fileWriter.println(firstname + "," + lastname + "," + ID + "," + Book + "," + AuFName + "," + AuLName + "," + message);
	        fileWriter.close();
	        

	        try {
				//1. mysql driver load
							
				Class.forName("com.mysql.cj.jdbc.Driver");
				//2. Establish connection
				Connection connection = DriverManager.getConnection
						("jdbc:mysql://localhost/LibraryWebApplication", "root", "");
				//3. execute sql command
				Statement stm = connection.createStatement();
				
				//4. check result	
				stm.execute("INSERT INTO `BookReq` (`id`, `Fname`, `Lname`, `LibID`, `Title`, `AuthFname` , `AuthLname`, `Info`) VALUES (NULL, '"+firstname+"', '"+lastname+"', '"+ID+"','"+Book+"','"+AuFName+ "','"+AuLName+"','"+message+"');");
				pw.println("<p style=\"color: green;\">Book request submitted</p>");
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				pw.println("<p style=\"color: blue;\">Member not added</p>");
				// add book not added 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pw.close();
	}

}
