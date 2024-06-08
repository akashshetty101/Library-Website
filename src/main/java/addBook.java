

import jakarta.servlet.ServletException;


import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Register
 */

@WebServlet("/addBook")


public class addBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public addBook() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(((HttpServletRequest) res).getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");String Title = req.getParameter("Title");
		String Author = req.getParameter("Author");
		String ISBN = req.getParameter("ISBN");
		String Genre = req.getParameter("Genre");
		String Summary = req.getParameter("Summary");
		PrintWriter pw = res.getWriter();
		

		
		
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset=\"UTF-8\">");
		pw.println("<title>New Book Added</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div style=\"text-align: center;\">");
		pw.println("<h1>New Book Information</h1>");
		pw.println("<p><strong>Title:</strong> " + Title + "</p>");
		pw.println("<p><strong>Author:</strong> " + Author + "</p>");
		pw.println("<p><strong>ISBN:</strong> " + ISBN + "</p>");
		pw.println("<p><strong>Genre:</strong> " + Genre + "</p>");
		pw.println("<p><strong>Summary:</strong> " + Summary + "</p>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");
		      
		
		
		//System.out.println("Genre: " + Genre);
		
		FileWriter fw = new FileWriter("/Users/akash/eclipse-workspace/LibraryWebApplication/src/main/java/books.csv", true); // Append mode
        PrintWriter fileWriter = new PrintWriter(fw);
        fileWriter.println(Title + "," + Author + "," + ISBN + "," + Genre + "," + Summary);
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
			stm.execute("INSERT INTO `Books` (`id`, `Title`, `Author`, `ISBN`, `Genre`, `Summary`) VALUES (NULL, '"+Title+"', '"+Author+"', '"+ISBN+"','"+Genre+"','"+Summary+ "');");
			pw.println("<p style=\"color: green;\">Book added</p>");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<p style=\"color: red;\">Book not added</p>");
			// add book not added 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pw.close();
	
	

}

		
		
		
	}
