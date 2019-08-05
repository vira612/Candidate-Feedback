package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Candidates;
import data.Feedback;


@WebServlet("/EditFeedback")
public class EditFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditFeedback() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user=(String) request.getSession().getAttribute("user");
		String admin=(String) request.getSession().getAttribute("admin");

		
		request.setAttribute("user", user);
		request.setAttribute("admin", admin);
		
		int id =Integer.valueOf(request.getParameter("id"));
		request.setAttribute("id", id);


		
		List<Feedback> feedback=new ArrayList<Feedback>();

		Connection c = null;
        try
        {
        	
	    	String url = "jdbc:mysql://localhost:3306/HW5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "12345678";

            c = DriverManager.getConnection( url, username, password );

            String sql1 = "select * from Feedbacks where name_f=? and owner_id=?";

            PreparedStatement pstmt1 = c.prepareStatement( sql1 );

            pstmt1.setString( 1, user );
            pstmt1.setInt(2, id);


            ResultSet rs1=pstmt1.executeQuery();


           
            
            while( rs1.next() )
            {
               feedback.add(new Feedback(rs1.getInt("rate"),rs1.getString("name_f"),rs1.getString("comment"),rs1.getDate("date")));

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
        
		request.setAttribute("feedback",feedback );
		
		request.getRequestDispatcher( "/WEB-INF/EditFeedback.jsp" ).forward( request, response );


	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));

		int rate=Integer.parseInt(request.getParameter("rate"));
		String name_f=request.getParameter("name_f");
		String comment=request.getParameter("comment");
		
		List<Feedback> feedback=new ArrayList<Feedback>();

		
		Connection c = null;
 
		try
		    {
		    	
	    	String url = "jdbc:mysql://localhost:3306/HW5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String username = "root";
	        String password = "12345678";

		        c = DriverManager.getConnection( url, username, password );
		        Statement stmt = c.createStatement();
		       

		             String sql = "update Feedbacks set name_f=?,comment=?,rate=?,date=now() where owner_id=? and name_f=? ";

				        
				     PreparedStatement pstmt = c.prepareStatement( sql );

				        
				        pstmt.setString( 1, name_f );
				        pstmt.setString(2, comment);
				        pstmt.setInt( 3, rate);
				        pstmt.setInt( 4,id);
				        pstmt.setString(5, name_f);
				        pstmt.executeUpdate();
				        
				        pstmt.executeUpdate();

		        


		        String sql1="select * from Feedbacks where owner_id=?";
	
		        PreparedStatement pstmt1 = c.prepareStatement( sql1 );
		        pstmt1.setInt( 1, id);
	
		        ResultSet rs1=pstmt1.executeQuery();
		        
		        while( rs1.next() )
	            {
	               feedback.add(new Feedback(rs1.getInt("rate"),rs1.getString("name_f"),rs1.getString("comment"),rs1.getDate("date")));
	
	            }
		        
		        
		        float r=0;
				int n=0;
				for(Feedback rate_f :feedback) 
				{
					
					r+=rate_f.getRate();
					n=feedback.size();
				}
				
				String avg_rate=Float.toString(r/n);
				
				String sql2 = "update Candidates set rating=? where id=?";
		        
		        PreparedStatement pstmt2 = c.prepareStatement( sql2 );
		        pstmt2.setString( 1, avg_rate );
		        pstmt2.setInt( 2, id );

		        pstmt2.executeUpdate();


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
		    
		    
		response.sendRedirect("DisplayFeedback?id="+id);

	}

}
