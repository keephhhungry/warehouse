package main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/saveMoney")
public class SaveMoneyServlet extends HttpServlet {

	public SaveMoneyServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setHeader("content-type","text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String strMoney = req.getParameter("money");
		String userIdStr = req.getParameter("userId");
		if (strMoney == null || strMoney.length() == 0) {
			strMoney = "0";
		}
		Integer userId = Integer.valueOf(userIdStr);
		Double money = Double.valueOf(strMoney);
		AccountInfo user = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/atm?user=root&password=root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from account_info where user_id = " + userId);
			if (rs.next()) {
				user = new AccountInfo();
				user.setUserId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setBalance(rs.getDouble(3));
			}
			double balance = user.getBalance() + money;
			stmt.executeUpdate("update account_info set balance = " + balance + "where user_id = " + userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<html>\n" +
				"<head>\n" +
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
				"    <title>信用合作社</title>\n" +
				"</head>\n" +
				"<body  bgcolor=\"#fdf5e6\">\n" +
				"<div style=\"text-align: center\">"+
				"<h2>信用合作社</h2>\n" +
				"<p style=\"color: green\">欢迎您！ userId:" + user.getUserId() + " 姓名:" + user.getName() + "</p>" +
				"<p style=\"color: green\">您本次的存入金额为:" + money + " 总余额为:" + (user.getBalance() + money) + "</p>" +
				"<form action=\"http://localhost:8080/atm/saveMoney\">" +
				"    存款:" +
				"    <input type=\"text\" name=\"money\" placeholder=\"请输入存款金额 \"><br>" +
				"    <input type=\"text\" name=\"userId\" style=\"visibility:hidden\" value=\"" + user.getUserId() + "\"><br>" +
				"    <input type=\"submit\" value=\"存款\">" +
				"</form>\n" +
				"<br>" +
				"<form action=\"http://localhost:8080/atm/withdrawMoney\">" +
				"    取款:" +
				"    <input type=\"text\" name=\"money\" placeholder=\"请输入取款金额 \"><br>" +
				"    <input type=\"text\" name=\"userId\" style=\"visibility:hidden\" value=\"" + user.getUserId() + "\"><br>" +
				"    <input type=\"submit\" value=\"取款\">" +
				"</form>" +
				"</div>" +
				"</body>" +
				"<html>");

	}
}
