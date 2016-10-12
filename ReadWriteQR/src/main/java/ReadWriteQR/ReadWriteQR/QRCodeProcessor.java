package ReadWriteQR.ReadWriteQR;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;



public class QRCodeProcessor {
	
	public static void main(String[] args) {
		ByteArrayOutputStream out = QRCode.from("Hello World Test")
										.to(ImageType.PNG).stream();

		try {
			FileOutputStream fout = new FileOutputStream( new File (System.getProperty("home.dir"), "QR_Code_Avinash1.JPG"));

			fout.write(out.toByteArray());

			fout.flush();
			fout.close();
			System.out.println("QR code create at location"+System.getProperty("home.dir"));
		} catch (FileNotFoundException e) {
			// Do Logging
		} catch (IOException e) {
			// Do Logging
		}
	}
	
	public static String readQRCode(String filePath, String charset, Map hintMap)
			   throws FileNotFoundException, IOException, NotFoundException {
			  BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
			    new BufferedImageLuminanceSource(
			      ImageIO.read(new FileInputStream(filePath)))));
			  Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
			    hintMap);
			  return qrCodeResult.getText();
			 }

}
