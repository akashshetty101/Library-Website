

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class newMember
 */
public class newMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newMember() {
        super();
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
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String dob = req.getParameter("dob");
		
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset=\"UTF-8\">");
		pw.println("<title>New Member Added</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div style=\"text-align: center;\">");
		pw.println("<h1>Library Membership</h1>");
		pw.println("<p><strong>First Name:</strong> " + firstname + "</p>");
		pw.println("<p><strong>Last Name:</strong> " + lastname + "</p>");
		pw.println("<p><strong>Email:</strong> " + email + "</p>");
		pw.println("<p><strong>Phone Number:</strong> " + phone + "</p>");
		pw.println("<p><strong>Address:</strong> " + address + "</p>");
		pw.println("<p><strong>Date of Birth:</strong> " + dob + "</p>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		
		
		 FileWriter fw = new FileWriter("/Users/akash/eclipse-workspace/LibraryWebApplication/src/main/java/newmember.csv", true); // Append mode
	        PrintWriter fileWriter = new PrintWriter(fw);
	        fileWriter.println(firstname + "," + lastname + "," + email + "," + phone + "," + address + "," + dob);
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
				stm.execute("INSERT INTO `members` (`id`, `Fname`, `Lname`, `Email`, `Phone`, `Address` , `DOB`) VALUES (NULL, '"+firstname+"', '"+lastname+"', '"+email+"','"+phone+"','"+address+ "','"+dob+"');");
				pw.println("<p style=\"color: green;\">Member added</p>");
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				pw.println("<p style=\"color: red;\">Member not added</p>");
				// add book not added 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pw.close();
		
		

		
	}

}
