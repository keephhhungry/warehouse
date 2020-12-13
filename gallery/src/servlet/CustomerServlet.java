package servlet;

import com.alibaba.fastjson.JSON;
import model.Customer;
import utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    public static final String GET_ALL_CUSTOMER = "1";
    public static final String GET_SINGLE_CUSTOMER = "2";
    public static final String ADD_CUSTOMER = "3";
    public static final String UPDATE_CUSTOMER = "4";
    public static final String DELETE_CUSTOMER = "5";

    public CustomerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        String type = req.getParameter("type");
        if (type.equals(GET_ALL_CUSTOMER)) {
            getAllCustomer(req, resp);
        } else if (type.equals(GET_SINGLE_CUSTOMER)) {
            getSingleCustomer(req, resp);
        } else if (type.equals(ADD_CUSTOMER)) {
            addCustomer(req, resp);
        } else if (type.equals(UPDATE_CUSTOMER)) {
            UpdateCustomer(req, resp);
        } else if (type.equals(DELETE_CUSTOMER)) {
            deleteCustomer(req, resp);
        }
    }

    /**
     * 查询全部收藏家
     */
    public List<Customer> getAllCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Customer> customers = new ArrayList<Customer>();
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from t_customer");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long customerId = rs.getLong(1);
                String customerName = rs.getString(2);
                String customerGender = rs.getString(3);
                String customerPhoneNumber = rs.getString(4);
                String customerAddress = rs.getString(5);
                Customer customer = new Customer(customerId, customerName, customerGender, customerPhoneNumber , customerAddress);
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(customers));
        return customers;
    }

    /**
     * 查询单个收藏家
     */
    public Customer getSingleCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Customer customer = null;
        String customerId = req.getParameter("customerId");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from t_customer where customer_id = ?");
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Long customerId1 = rs.getLong(1);
                String customerName = rs.getString(2);
                String customerGender = rs.getString(3);
                String customerPhoneNumber = rs.getString(4);
                String customerAddress = rs.getString(5);
                customer = new Customer(customerId1, customerName, customerGender, customerPhoneNumber, customerAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(customer));
        return customer;
    }

    /**
     * 添加收藏家
     */
    public void addCustomer(HttpServletRequest req, HttpServletResponse resp) {
        String customerName = req.getParameter("customerName");
        String customerGender = req.getParameter("customerGender");
        String customerPhoneNumber = req.getParameter("customerPhoneNumber");
        String customerAddress = req.getParameter("customerAddress");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO t_customer(customer_name, customer_gender, customer_phone_number, customer_address) VALUES ( ? , ? , ? , ? )");
            ps.setString(1, customerName);
            ps.setString(2, customerGender);
            ps.setString(3, customerPhoneNumber);
            ps.setString(4, customerAddress);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新收藏家
     */
    public void UpdateCustomer(HttpServletRequest req, HttpServletResponse resp) {
        String customerId = req.getParameter("customerId");
        String customerName = req.getParameter("customerName");
        String customerGender = req.getParameter("customerGender");
        String customerPhoneNumber = req.getParameter("customerPhoneNumber");
        String customerAddress = req.getParameter("customerAddress");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("update t_customer set customer_name = ? , customer_gender = ? , customer_phone_number = ? , customer_address = ? where customer_id = ?");
            ps.setString(1, customerName);
            ps.setString(2, customerGender);
            ps.setString(3, customerPhoneNumber);
            ps.setString(4, customerAddress);
            ps.setString(5, customerId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除收藏家
     *
     * @param req
     * @param resp
     */
    public void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) {
        String customerId = req.getParameter("customerId");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("delete from t_customer where customer_id = ?");
            ps.setString(1, customerId);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
