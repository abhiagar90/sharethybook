package testdemo;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReadImage {

    public static void main2(String[] args) {
        Image image = null;
        try {
            URL url = new URL("http://www.mkyong.com/image/mypic.jpg");
            // This is where you'd define the proxy's host name and port.
            SocketAddress address = new InetSocketAddress("10.10.78.62", 3128);

            // Create an HTTP Proxy using the above SocketAddress.
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);

            // Open a connection to the URL using the proxy information.
            InputStream inStream = url.openConnection(proxy).getInputStream();

            ImageIO.read(url);
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }
}
