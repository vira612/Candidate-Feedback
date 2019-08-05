package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Candidates;

@WebServlet("/EditCandidates")
public class EditCandidates extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditCandidates() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user=(String) request.getSession().getAttribute("user");
		String admin=(String) request.getSession().getAttribute("admin");

		
		request.setAttribute("user", user);
		request.setAttribute("admin", admin);

		Candidates entry = new Candidates();

		
		int id =Integer.valueOf(request.getParameter("id"));
		Connection c = null;
        try
        {
        	
	    	String url = "jdbc:mysql://localhost:3306/HW5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "12345678";

            c = DriverManager.getConnection( url, username, password );

            String sql = "select * from Candidates where id=?";
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt( 1, id );

            ResultSet rs=pstmt.executeQuery();

            while( rs.next() )
            {
            	entry=(new Candidates(rs.getInt("id"),rs.getString("name"),rs.getString("speciality"),rs.getString("rating"),rs.getString("presentation")));
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
         
         
		request.setAttribute("entry",entry );
		request.setAttribute("id",id );
		
		request.getRequestDispatcher( "/WEB-INF/EditCandidates.jsp" ).forward( request, response );

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));

		String name=request.getParameter("name");
		String specialities=request.getParameter("speciality");
		String presentation=request.getParameter("presentation");
		
		Connection c = null;
	    try
	    {
	    	
	    	String url = "jdbc:mysql://localhost:3306/HW5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String username = "root";
	        String password = "12345678";

	        c = DriverManager.getConnection( url, username, password );
	        Statement stmt = c.createStatement();
	       
	        String sql = "update Candidates set name=?,speciality=?,presentation=? where id=? ";
	        
	        PreparedStatement pstmt = c.prepareStatement( sql );
	        pstmt.setString( 1, name );
	        pstmt.setString(2, specialities);
	        pstmt.setString( 3, presentation);
	        pstmt.setInt( 4, id );

	        pstmt.executeUpdate();
	       
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

		response.sendRedirect("DisplayCandidates");

	}

}