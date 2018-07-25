

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
/**
 * Servlet implementation class Clinpath
 */
public class Clinpath extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final Scanner scan = new Scanner(System.in);
	   
	   private static String USER_NAME = "grannysmithfrank";  // GMail user name (just the part before "@gmail.com")
	   private static String PASSWORD = "aaa82719243"; // GMail password
	   private static String RECIPIENT = "jbraendl@hotmail.com";


	   public static void main(String [] args){   
	   
	      int num;
	      System.out.println("Enter number of Histology Specimens");
	      num=scan.nextInt();
	      scan.nextLine();
	      
	      
	    
	      
	      int rund = num;
	      Integer.toString(rund);
	             
	      Clinpath run = new Clinpath();
	        
	      for(int i = 0; i<=rund-1; i++){
	         run.inputName ();
	         run.inputBags();
	         run.inputPots();
	         
	         System.out.println(run.toString()); 
	         
	      } 
	      
	   }
	   
	   String names;
	   int bags, pots;
	   
	   void inputName ()
	   {
	      
	      System.out.println("Please Enter Patients Lastname, Shortening is OK if Irregular");
	      names = scan.nextLine();
	   }

	   String time ()
	   {
	   
	      Calendar calendar = GregorianCalendar.getInstance();
	      Calendar now = Calendar.getInstance();
	      String time = (now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
	   
	      return time;
	   
	   }
	   
	   
	   void inputBags ()
	   {
	      
	      System.out.println("Enter Number of Histology Specimen-Bags For This Patient ONLY");
	      bags=scan.nextInt();
	      scan.nextLine();
	   
	   
	   }

	   void inputPots()
	   {
	      
	          
	      System.out.println("Enter TOTAL Number of Histo POTS For This Particular Patient");
	      pots=scan.nextInt();
	      scan.nextLine();
	      
	   }
	   
	   int getBags()
	   {
	   
	      return bags;
	   
	   }
	   
	   int getPots()
	   {
	      return pots;
	   
	   }
	   
	   String getName()
	   {
	   
	      return names;
	   
	   }
	   
	   private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
	        Properties props = System.getProperties();
	        String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");

	        Session session = Session.getDefaultInstance(props);
	        MimeMessage message = new MimeMessage(session);

	        try {
	            message.setFrom(new InternetAddress(from));
	            InternetAddress[] toAddress = new InternetAddress[to.length];

	            // To get the array of addresses
	            for( int i = 0; i < to.length; i++ ) {
	                toAddress[i] = new InternetAddress(to[i]);
	            }

	            for( int i = 0; i < toAddress.length; i++) {
	                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	            }

	            message.setSubject(subject);
	            message.setText(body);
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, from, pass);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	        }
	        catch (AddressException ae) {
	            ae.printStackTrace();
	        }
	        catch (MessagingException me) {
	            me.printStackTrace();
	        }
	   }
	   
	   
	       
	   public String toString() {
	   
	     String from = USER_NAME;
	      String pass = PASSWORD;
	      String[] to = { RECIPIENT }; // list of recipient email addresses
	      String subject = "Histology Specimen Pickup";
	      String body = "Patient Surname : " + getName()+"\nTime of Collection : " +time()+"\nNumber of Specimen Bags for This Patient : "+getBags()+ "\nNumber of Histo Pots : "+getPots()+"\n";
	   
	      sendFromGMail(from, pass, to, subject, body);
	   
	      return "\nPatient Surname : " + getName()+"\nTime of Collection : " +time()+"\nNumber of Specimen Bags for This Patient : "+getBags()+ "\nNumber of Histo Pots : "+getPots()+"\n";
	   }
	




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
