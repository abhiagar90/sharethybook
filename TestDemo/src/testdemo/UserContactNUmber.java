/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author reshma
 */
public class UserContactNUmber {
    public static void main2(String[] args) {
    Connection c = null;
    Connection insConn = null;
       Statement stmt = null;
       try {
        Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/library",
            "postgres", "password");
         
         insConn = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/library",
            "postgres", "password");
         
         c.setAutoCommit(true);
         System.out.println("Opened database successfully");
int count=0;
         stmt = c.createStatement();
         System.out.println("EntryNumber");
         ResultSet rs = stmt.executeQuery( "Select EntryNumber from UserTable;" );
         
         String prepQuery="Insert into ContactNumbers Values(?,?,?)";
         
         while ( rs.next() ) {
            String  entryNumber = rs.getString("EntryNumber");
            String phoneNumber=RandomGeneratePhoneNumber();
            
             count++;
             try{
             PreparedStatement ps = c.prepareStatement(prepQuery);
             ps.setString(1, entryNumber);
             ps.setString(2, phoneNumber);
             ps.setString(3, "W");
             
             ps.executeUpdate();
             ps.close();
             }
             catch(Exception e)
             {
                 System.out.println("Exception while inserting  :: "+e.getMessage()+" "+entryNumber+" "+phoneNumber);
             }
            //System.out.println(  entryNumber );
               
         }
         
         System.out.println( "Total NUmber of records fetched = "+count );
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Operation done successfully");
    }

    private static String RandomGeneratePhoneNumber() {
        String AB = "789";
         Random rnd = new Random();

            StringBuilder sb = new StringBuilder();
            sb.append( AB.charAt(rnd.nextInt(AB.length()) ) );
            sb.append( AB.charAt(rnd.nextInt(AB.length()) ) );
            
             for( int i = 0; i < 8; i++ ) 
               sb.append(  rnd.nextInt(10));
             
            return sb.toString();
    }
}
