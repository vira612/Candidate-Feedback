package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.Candidates;
import data.Feedback;


@WebServlet("/DisplayCandidates")
public class DisplayCandidates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DisplayCandidates() {
        super();
    }
    
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Candidates> entries = new ArrayList<Candidates>();
		
		String user=(String) request.getSession().getAttribute("user");
		String admin=(String) request.getSession().getAttribute("admin");

		
		request.setAttribute("user", user);
		request.setAttribute("admin", admin);
		
		String usernames=request.getParameter("user_name");
		
		
		Connection c = null;
        try
        {
        	
	    	String url = "jdbc:mysql://localhost:3306/HW5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "12345678";
            
            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from Candidates");

            while( rs.next() )
            {
               entries.add(new Candidates(rs.getInt("id"),rs.getString("name"),rs.getString("speciality"),rs.getString("rating"),rs.getString("presentation")));
            }

            c.close();
        }
        catch( SQLException e)
        {
            throw new ServletException(e);
        }
        finally
        {
            try
            {
                if( c != null ) 
                   c.close();
            }
            catch( SQLException e )
            {
            	throw new ServletException(e);
            }
        }

        	
		
		request.setAttribute("entry",entries );
//		request.setAttribute("user", user);
		request.getRequestDispatcher( "/WEB-INF/DisplayCandidates.jsp" ).forward( request, response );

		 

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}