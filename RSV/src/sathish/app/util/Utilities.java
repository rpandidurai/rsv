/**
 * 
 */
package sathish.app.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author root
 * 
 */
public class Utilities {

	public InputStream responseMessage(String message) {
		InputStream responseMessage = null;
		try {
			responseMessage = new ByteArrayInputStream(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return responseMessage;
	}

	public String generateBillNo(String lastBillNo) {

		String curDateString = "";
		boolean firstBill = false;
		String recptNo = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yy MMM dd");

		curDateString = dateFormat.format(new Date());

		String[] date = curDateString.split(" ");
		
		recptNo = "SE" + date[0] + date[1].toUpperCase() + date[2];

		System.out.println(lastBillNo+" ---- lastBillNo:recptNo ------ "+recptNo);
		if (lastBillNo.equals("")) {
			firstBill = true;
			System.out.println("in 1st if >>>");
		} else if (!recptNo.equalsIgnoreCase(lastBillNo.substring(0, 9))) {
			firstBill = true;
			System.out.println("in 1st else >>> lastBillNo.substring(0, 8) "+lastBillNo.substring(0, 8));
		}

		if (firstBill) {
			recptNo += "0001";
			System.out.println("in if >>>");
		} else {
			recptNo += String.format("%04d", Integer.parseInt(lastBillNo.substring(9)) + 1);
			System.out.println("in else >>>  lastBillNo.substring(8)  "+lastBillNo.substring(9));
		}

		System.out.println("new bill no : " + recptNo);
		return recptNo;
	}
}
