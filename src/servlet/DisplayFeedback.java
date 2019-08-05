package servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.crypto.provider.RSACipher;

import data.Candidates;
import data.Feedback;


@WebServlet("/DisplayFeedback")
public class DisplayFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DisplayFeedback() {
        super();
    }
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Candidates entry = new Candidates();
		List<Feedback> feedback=new ArrayList<Feedback>();
		int id = Integer.valueOf(request.getParameter("id"));
		
		String user=(String) request.getSession().getAttribute("user");
		String admin=(String) request.getSession().getAttribute("admin");

		
		request.setAttribute("user", user);
		request.setAttribute("admin", admin);

		
		
		Connection c = null;
        try
        {
        	
	    	String url = "jdbc:mysql://localhost:3306/HW5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "12345678";

            c = DriverManager.getConnection( url, username, password );

            String sql = "select * from Candidates where id=? ";
            String sql1 = "select * from Feedbacks where owner_id=?";

            PreparedStatement pstmt = c.prepareStatement( sql );
            PreparedStatement pstmt1 = c.prepareStatement( sql1 );

            pstmt.setInt( 1, id );
            pstmt1.setInt( 1, id );


            ResultSet rs=pstmt.executeQuery();
            ResultSet rs1=pstmt1.executeQuery();


            while( rs.next() )
            {
               entry=(new Candidates(rs.getInt("id"),rs.getString("name"),rs.getString("speciality"),rs.getString("rating"),rs.getString("presentation")));
            }
            
            while( rs1.next() )
            {
               feedback.add(new Feedback(rs1.getInt("rate"),rs1.getString("name_f"),rs1.getString("comment"),rs1.getDate("date")));

            }
            
            String name;
	        boolean flag=false; 
	        for(Feedback name_f1 :feedback) 
			{
				    name=name_f1.getName_f();
					if(name.equals(user)) 
					{
						flag=true;
					}
					
					
			}
	        
	        request.setAttribute("flag", flag);

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
		request.setAttribute("feedback",feedback );


		request.setAttribute("id",id );
         
		request.getRequestDispatcher( "/WEB-INF/DisplayFeedback.jsp" ).forward( request, response );
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Feedback> feedback=new ArrayList<Feedback>();

		
		Integer id = Integer.valueOf(request.getParameter("id"));
		int rate=Integer.parseInt(request.getParameter("rate"));
		String name_f=request.getParameter("name_f");
		String comment=request.getParameter("comment");
		
		String user=(String) request.getSession().getAttribute("user");
		String admin=(String) request.getSession().getAttribute("admin");

		
		request.setAttribute("user", user);
		request.setAttribute("admin", admin);

		Connection c = null;
	    try
	    {
	    	
	    	String url = "jdbc:mysql://localhost:3306/HW5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String username = "root";
	        String password = "12345678";

	        c = DriverManager.getConnection( url, username, password );
	        Statement stmt = c.createStatement();
	       
	        String sql1="select * from Feedbacks where owner_id=?";

	        PreparedStatement pstmt1 = c.prepareStatement( sql1 );
	        pstmt1.setInt( 1, id);

	        ResultSet rs1=pstmt1.executeQuery();
	        
	        while( rs1.next() )
            {
               feedback.add(new Feedback(rs1.getInt("rate"),rs1.getString("name_f"),rs1.getString("comment"),rs1.getDate("date")));

            }
	        
	        
	       
				    String sql = "insert into Feedbacks (name_f,comment,rate,date,owner_id) values (?, ?, ?, now(),?)";
			        
			        PreparedStatement pstmt = c.prepareStatement( sql );

			        
			        pstmt.setString( 1, name_f );
			        pstmt.setString(2, comment);
			        pstmt.setInt( 3, rate);
			        pstmt.setInt( 4,id);
			        pstmt.executeUpdate();
			        
		            feedback.add(new Feedback(rate,name_f,comment, new Date()));
		            
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
	    
	    
		doGet(request,response);


	}

}