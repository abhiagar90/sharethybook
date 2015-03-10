/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdemo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author abhishek
 */
public class URLImage {

    public static void main(String[] args) throws MalformedURLException, IOException, SQLException {
        URLImage urli = new URLImage();
        byte[][] barr = urli.getAllImages();
        String[][] isbn = urli.readAndConvert2dArrayCsv("ISBN_Image.csv");
        System.out.println("HURRAAAAAAAAAAAAAAAHhhh");
        System.out.println(barr.length);
        System.out.println(isbn.length);
        urli.setup();
        for(int i=0;i<isbn.length;i++)
        {
            urli.insertIntoDB(isbn[i][0], barr[i]);
        }
        urli.closeConnection();
    }

    public Connection conn = null;

    public Statement statement = null;

    public PreparedStatement preparedStatement = null;

    public void openConnection() {
        String drivername = Constants.driver;
        String dburl = Constants.dburl;
        String uname = Constants.uname;
        String password = Constants.password;

        try {
            Class.forName(drivername);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            conn = DriverManager
                    .getConnection(dburl,
                            uname, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void closeConnection() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setup() throws SQLException
    {
        openConnection();

        String prepQuery = "insert into masterbooksimages values(?,?)";
        preparedStatement = conn.prepareStatement(prepQuery);
    }

    public boolean insertIntoDB(String ISBN, byte[] image) throws SQLException {
       
        preparedStatement.setString(1, ISBN);
        preparedStatement.setBytes(2, image);
        preparedStatement.executeUpdate();

        
        return false;
    }

    public byte[][] getAllImages() throws IOException {
        URLImage ui = new URLImage();
        String[][] csv = ui.readAndConvert2dArrayCsv("ISBN_Image.csv");
        int count = 0;
        byte[][] finalArr = new byte[271379][2];

        for (String[] line : csv) {

            byte[] arr = ui.getBytearrayFromImage(line[1]);
            while (arr.length <= 0) {
                System.out.println("problem!!");
                arr = ui.getBytearrayFromImage(line[1]);
            }
            finalArr[count] = arr;

            count++;
            if (count > 10) {
                break;
            }
        }
        return finalArr;
    }

    public String[][] readAndConvert2dArrayCsv(String csvFile) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\\|";
        String[][] finalStr = new String[271379][2];

        int count = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();//first one is header

            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                finalStr[count][0] = country[0];
                finalStr[count][1] = country[1];
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //checking by printing
        for (int i = 0; i < 10; i++) {
            System.out.println(finalStr[i][0]);
        }
        System.out.println("Done");
        return finalStr;
    }

    public byte[] getBytearrayFromImage(String imageurl) throws MalformedURLException, IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.10.78.62", 3128));

        URL url = new URL(imageurl);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        InputStream is = url.openConnection(proxy).getInputStream();

        System.out.println("Available: " + is.available());

        byte[] b = new byte[4096];
        int length;

        while ((length = is.read(b)) != -1) {

            baos.write(b, 0, length);
        }

        byte[] arr = baos.toByteArray();

        System.out.println(arr.length);

        return arr;
    }
}
