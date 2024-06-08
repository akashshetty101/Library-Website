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
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/showBookList")
public class showBookList extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); 
    }
    
    @Override
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
        String query = "SELECT * FROM Books";  
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            out.println("<table border=\"1\">");
            out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>ISBN</th><th>Genre</th><th>Summary</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String isbn = rs.getString("ISBN");
                String genre = rs.getString("Genre");
                String summary = rs.getString("Summary");
                out.println("<tr><td>" + id + "</td><td>" + title + "</td><td>" + author + "</td><td>" + isbn + "</td><td>" + genre + "</td><td>" + summary + "</td></tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
