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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author reshma
 */
public class BooksPopulate {

    static int count = 1000;

    public static void main2(String[] args) {
        {
            Connection c = null;
            Statement stmt = null;
            Statement stmt1 = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/library",
                                "postgres", "password");

                c.setAutoCommit(true);
                System.out.println("Opened database successfully");
                int count = 0;
                stmt = c.createStatement();
                stmt1 = c.createStatement();
                System.out.println("EntryNumber");

                String prepQuery = "Insert into Books Values(?,?,?,?,?,?)";

                int i = 0;
                int MAXCOUNT = 5000;
                while (i < MAXCOUNT) {
                    ResultSet rs = stmt.executeQuery("SELECT ISBN FROM MasterBooks ORDER BY RANDOM() LIMIT 1;");
                    String ISBN = null;
                    while (rs.next()) {
                        ISBN = rs.getString("ISBN");
                    }

                    ResultSet rs1 = stmt1.executeQuery("SELECT EntryNumber FROM UserTable ORDER BY RANDOM() LIMIT 1;");
                    String entryNumber = null;
                    while (rs1.next()) {
                        entryNumber = rs1.getString("EntryNumber");
                    }

                    String bookID = RandomGenerateBookNumber();

                    i++;
                    try {
                        PreparedStatement ps = c.prepareStatement(prepQuery);
                        ps.setString(1, bookID);
                        ps.setString(2, ISBN);
                        ps.setString(3, entryNumber);
                        ps.setString(4, entryNumber);
                        DateFormat formatter;
                        Calendar calendar = Calendar.getInstance();
                        java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
                        ps.setDate(5, ourJavaDateObject);
                        ps.setString(6, RandomGenerateBookCond());

                        ps.executeUpdate();
                        ps.close();
                    } catch (Exception e) {
                        System.out.println("Exception while inserting  :: " + e.getMessage() + " " + entryNumber + " ");
                    }
                    //System.out.println(  entryNumber );
                    rs.close();
                    rs1.close();
                }

         //System.out.println( "Total NUmber of records fetched = "+count );
                stmt.close();
                stmt1.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("Operation done successfully");

        }
    }

    private static String RandomGenerateBookNumber() {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        sb.append(count++);

        return sb.toString();
    }

    private static String Padleft(String abc, int l) {
        String temp = abc;
        for (int i = 0; i < abc.length() - l; i++) {
            temp = "0" + temp;
        }
        return temp;
    }

    private static String RandomGenerateBookCond() {
        String AB = "DOGE";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder();
        sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }

}
