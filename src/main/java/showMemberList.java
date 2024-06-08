import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

@WebServlet("/showMemberList")
public class showMemberList extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);  // Redirect GET requests to doPost method
		 //response.getWriter().println("Hello from ShowMemberList Servlet!");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>View Books</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>List of Books</h1>");

        Connection conn = connect();
        if (conn != null) {
            fetchData(out, conn);
        }

        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    private Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            return DriverManager.getConnection(
                "jdbc:mysql://localhost/LibraryWebApplication", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void fetchData(PrintWriter out, Connection conn) {
    	String query = "SELECT * FROM members";  
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            out.println("<table border=\"1\">");
            out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Phone</th><th>Address</th><th>Date of Birth</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String firstname  = rs.getString("Fname");
                String lastname = rs.getString("Lname");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String dob = rs.getString("DOB");
                
                out.println("<tr><td>" + id + "</td><td>" + firstname + "</td><td>" + lastname + "</td><td>" + email + "</td><td>" + phone + "</td><td>" + address + "</td><td>" + dob + "</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}