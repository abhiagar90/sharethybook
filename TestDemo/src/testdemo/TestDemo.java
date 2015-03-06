/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdemo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDemo {

    public static void main(String[] args) {
           Connection c = null;
           Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/library",
            "postgres", "password");
                  
         String [] strEntryCodes = {"MCS","MEZ","PHZ","MAS","CS","CE","EEZ","ME","HUZ","BLZ","CYZ","CEP","CEV","CEC","CET","CEW","CES","SMN","SMT"};
         String [] possibleYears ={"2014","2013","2012","2011","2010","2009","2008"};
         String [] roomSlots ={"WA","WB","WC","NB","NI","SA","SB","SC","SD","EA","EI","ED","EF","WI","WF"};
         String [] hostelNames ={"Kailash","Himadri","Jwalamukhi","Udaigiri","Girinar","Satpura","Kumaoun","Shivalik"};
              
         stmt = c.createStatement();
         
         BufferedReader br1 = new BufferedReader(new FileReader("indianNames2.csv"));
         BufferedReader br2 = new BufferedReader(new FileReader("indiansurnames.csv"));
            try {
                String firstName = br1.readLine();
                String LastName = br2.readLine();
                
                File file = new File("Anonymous.png");
                FileInputStream fis = null;
             
                String prepQuery="Insert into UserTable Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                int counter=1000;
                
                while (firstName != null) {
                                                     
                    fis = new FileInputStream(file);
                    
                    String EntryNUmber=Randomfetch(possibleYears)+Randomfetch(strEntryCodes)+counter++;
                    String emailIdtemp= EntryNUmber.substring(4);
                    int yearOfentry= Integer.parseInt(EntryNUmber.substring(0,4));
                    String emailID=emailIdtemp.substring(0,emailIdtemp.length()-4)+yearOfentry%100+emailIdtemp.substring(emailIdtemp.length()-4)+"@iitd.ac.in";
                                        
                    String password="abcde";
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] digest = md.digest();
                                        
                    StringBuffer sb1 = new StringBuffer();
                    for (byte b : digest) {
                            sb1.append(String.format("%02x", b & 0xff));
                    }
                    String roomNumber =Randomfetch(roomSlots)+ new Random().nextInt(31);
                    //sb.append(sb1+"','"+firstName+"','"+LastName+"','true','"+roomNumber+"','"+Randomfetch(hostelNames)+"','Hauz khas','New Delhi','110016','"+emailID+"',3,0,?);");
                    try{
                           PreparedStatement ps = c.prepareStatement(prepQuery);
                           ps.setString(1, EntryNUmber);
                           ps.setString(2, sb1.toString());
                           ps.setString(3, firstName);
                           ps.setString(4, LastName);
                           ps.setBoolean(5,true);
                           ps.setString(6, roomNumber);
                           ps.setString(7, Randomfetch(hostelNames));
                           ps.setString(8, "Hauz khas");
                           ps.setString(9, "New Delhi");
                           ps.setString(10, "110016");
                           ps.setString(11, emailID);
                           ps.setInt(12, 3);
                           ps.setInt(13, 0);
                           ps.setInt(14, 0);
                           ps.setInt(15, 0);
                           ps.setBinaryStream(16, fis, (int)file.length());
                   
                           ps.executeUpdate();
                           fis.close();
                           ps.close();
                           
                           firstName = br1.readLine();
                           LastName = br2.readLine();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Exception");
                    }
                }
                 
                c.close();
                }
             finally {
             
            }
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
    }
    
    public static String randomIntCharacters(int n)
    {
        int maxlimit=9;
        int minlimit=1;
        for (int i=0;i<n-1;i++)
        {
            maxlimit=maxlimit*10;
            minlimit=minlimit*10;
        }
        
        int rand = minlimit + new Random().nextInt(maxlimit);
        return new String()+rand;
    }
    
    public static  String Randomfetch(String[] strData)
    {
        return strData[new Random().nextInt(strData.length)];
    }

    public static String RandomAlphanumeric(int n)
    {
         String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghighijklmnopqrstuvwxyz";
         Random rnd = new Random();

            StringBuilder sb = new StringBuilder();
            for( int i = 0; i < n; i++ ) 
               sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
            return sb.toString();
    }
    
    public static Byte[] retriveImagedata(String EntryNumber) throws ClassNotFoundException
    {
        try
        {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/library",
            "postgres", "password");
        PreparedStatement ps = c.prepareStatement("SELECT Photo FROM UserTable WHERE EntryNumber = ?");
        ps.setString(1, EntryNumber);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            byte[] imgBytes = rs.getBytes(1);
            // use the data in some way here
        }
        rs.close();
        ps.close();
        }
        catch(Exception e)
        {
            
        }
        return  new Byte[2];
    }

}
