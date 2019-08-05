package servlet;

import java.io.IOException;
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
import data.Feedback;
import data.User;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward( request, response );

	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

		List <User> user=new ArrayList<>();
		
		String user_name=request.getParameter("username");
		String psw1=request.getParameter("password");
		
		
		Connection c = null;
	    try
	    {
	    	
	    	String url = "jdbc:mysql://localhost:3306/HW5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String username = "root";
	        String password = "12345678";

	        c = DriverManager.getConnection( url, username, password );
	        Statement stmt = c.createStatement();
	       
	        ResultSet rs = stmt.executeQuery( "select * from User");

            while( rs.next() )
            {
               user.add(new User(rs.getInt("id"),rs.getString("name"),rs.getString("password"),rs.getBoolean("admin")));
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
		
	    String n,psw;
        boolean a;
        boolean flag=false;
        for(User name :user) 
		{
				n=name.getName();
				psw=name.getPassword();
				a=name.isAdmin();

				
        	if(n.equals(user_name) && psw.equals(psw1)) 
				{
        			flag=true;
        			if(a==true) 
        			{
        				request.getSession().setAttribute("admin",user_name );
        				request.getSession().setAttribute("psw1",psw1 );
        				System.out.println("set");				
        			}
        			else if(a==false)  
        			{
        				request.getSession().setAttribute("user",user_name );
        				request.getSession().setAttribute("psw1",psw1 );
        				System.out.println(a);
        			}
        			response.sendRedirect( "DisplayCandidates");
        			
				}
        	

			}
        	

        	if(flag==false) 
        	{
        		doGet(request, response);
        	}
		
	    
		
		

//		if(user_name.equals("cysun") && psw1.equals("abcd")) {
//		
//		request.getSession().setAttribute("user",user_name );
//		request.getSession().setAttribute("psw1",psw1 );
//		}
//		
//		else if(user_name.equals("john") && psw1.equals("abcd") ){
//			request.getSession().setAttribute("user",user_name );
//			request.getSession().setAttribute("psw1",psw1 );
//		}
//		
//		else {
//			
//			doGet(request, response);
//
//
//		}

		

		
	}

}
