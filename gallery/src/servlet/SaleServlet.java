package servlet;

import com.alibaba.fastjson.JSON;
import model.Income;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/sale")
public class SaleServlet extends HttpServlet {

    public static final String SALE = "1";
    public static final String EMPLOYEE_SALE = "2";
    public static final String GALLERY_SALE = "3";
    public static final String ARTIST_SALE = "4";

    public SaleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        String type = req.getParameter("type");
        if (type.equals(SALE)) {
            sale(req, resp);
        } else if (type.equals(EMPLOYEE_SALE)) {
            getEmployeeSale(req, resp);
        }else if (type.equals(GALLERY_SALE)) {
            getGallerySale(req, resp);
        }else if (type.equals(ARTIST_SALE)) {
            getArtistSale(req, resp);
        }
    }

    /**
     * 出售作品
     */
    public void sale(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String msg = "";
        String customerName = req.getParameter("customerName");
        String exmployeeName = req.getParameter("exmployeeName");
        String artworkId = req.getParameter("artworkId");
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Date createTime = new Date();
        try {
            conn.setAutoCommit(false);
            //检查步骤1. 查询顾客名字是否存在
            int customerId = 0;
            ps = conn.prepareStatement("select * from t_customer where customer_name = ?");
            ps.setString(1,customerName);
            rs = ps.executeQuery();
            if(rs.next()){
                customerId = rs.getInt(1);
            }else{
                msg = "Customer name does not exist, please re-enter";
            }
            //检查步骤12. 查询销售人员名字
            int exmployeeId = 0;
            ps = conn.prepareStatement("select * from t_employee where employee_name = ?");
            ps.setString(1,exmployeeName);
            rs = ps.executeQuery();
            if(rs.next()){
                exmployeeId =  rs.getInt(1);
            }else{
                msg = "The salesperson's name does not exist, please re-enter";
            }
            //准备步骤 ，通过艺术品查找艺术家
            int artistId = 0;
            Double money = 0.0;
            ps = conn.prepareStatement("SELECT artist.*, work.price FROM t_artwork work INNER JOIN t_art_owner OWNER ON WORK.artwork_id = OWNER.artwork_id  INNER JOIN t_artist artist ON OWNER.owners_id = artist.artist_id WHERE work.artwork_id = ?");
            ps.setString(1,artworkId);
            rs = ps.executeQuery();
            if(rs.next()){
                artistId =  rs.getInt(1);
                money = rs.getDouble(5);
            }
            //1.给客户开票据
            ps = conn.prepareStatement("INSERT INTO t_bill(customer_id, artist_id, artwork_id, pay_money, creat_time) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1,customerId);
            ps.setInt(2,artistId);
            ps.setString(3,artworkId);
            ps.setDouble(4,money);
            ps.setDate(5, new java.sql.Date(new Date().getTime()));
            ps.execute();
            //2.计算利润分成 艺术家90 画廊7 销售3
            Double artistMoney = money * 0.9;
            Double galleryMoney = money * 0.07;
            Double employeeMoney = money * 0.03;
            //3.给艺术家分钱
            ps = conn.prepareStatement("INSERT INTO t_income(customer_id, artist_id, artwork_id, employee_id, receive_money, creat_time, income_type) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1,customerId);
            ps.setInt(2,artistId);
            ps.setString(3,artworkId);
            ps.setInt(4,exmployeeId);
            ps.setDouble(5,artistMoney);
            ps.setDate(6, new java.sql.Date(new Date().getTime()));
            ps.setString(7, "artist");
            ps.execute();
            //4.给画廊分钱
            ps = conn.prepareStatement("INSERT INTO t_income(customer_id, artist_id, artwork_id, employee_id, receive_money, creat_time, income_type) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1,customerId);
            ps.setInt(2,artistId);
            ps.setString(3,artworkId);
            ps.setInt(4,exmployeeId);
            ps.setDouble(5,galleryMoney);
            ps.setDate(6, new java.sql.Date(new Date().getTime()));
            ps.setString(7, "gallery");
            ps.execute();
            //5.给销售人员分钱
            ps = conn.prepareStatement("INSERT INTO t_income(customer_id, artist_id, artwork_id, employee_id, receive_money, creat_time, income_type) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1,customerId);
            ps.setInt(2,artistId);
            ps.setString(3,artworkId);
            ps.setInt(4,exmployeeId);
            ps.setDouble(5,employeeMoney);
            ps.setDate(6, new java.sql.Date(new Date().getTime()));
            ps.setString(7, "seller");
            ps.execute();
            //6.把物品状态改为已售出
            ps = conn.prepareStatement("update t_artwork set status = 'sold' where artwork_id = ?");
            ps.setString(1,artworkId);
            ps.execute();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();            }
        }
    }

    /**
     * 查询销售员销售情况
     */
    public List<Income> getEmployeeSale(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<Income> incomes = new ArrayList<>();
        String artistId = req.getParameter("artistId");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM t_income income INNER JOIN t_employee employee ON income.employee_id = employee.employee_id INNER JOIN t_artwork WORK ON income.artwork_id = WORK.artwork_id WHERE income.income_type = 'seller'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Income income = new Income();
                income.setIncomeId(rs.getInt("income_id"));
                income.setEmployeeName(rs.getString("employee_name"));
                income.setArtworkName(rs.getString("artwork_name"));
                income.setReceiveMoney(rs.getDouble("receive_money"));
                income.setCreateTime(rs.getDate("creat_time"));
                incomes.add(income);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(incomes));
        return incomes;
    }
    /**
     * 查询画廊销售情况
     */
    public List<Income> getGallerySale(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<Income> incomes = new ArrayList<>();
        String incomeType = req.getParameter("incomeType");

        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM t_income income inner join t_artwork WORK ON income.artwork_id = WORK.artwork_id WHERE income.income_type = ?");
            ps.setString(1,incomeType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Income income = new Income();
                income.setIncomeId(rs.getInt("income_id"));
                income.setArtworkName(rs.getString("artwork_name"));
                income.setReceiveMoney(rs.getDouble("receive_money"));
                income.setCreateTime(rs.getDate("creat_time"));
                incomes.add(income);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(incomes));
        return incomes;
    }

    /**
     * 查询画廊销售情况
     */
    public List<Income> getArtistSale(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<Income> incomes = new ArrayList<>();
        String incomeType = req.getParameter("incomeType");

        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM t_income income inner join t_artwork WORK ON income.artwork_id = WORK.artwork_id inner join t_artist artist on artist.artist_id = income.artist_id WHERE income.income_type = ?");
            ps.setString(1,incomeType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Income income = new Income();
                income.setIncomeId(rs.getInt("income_id"));
                income.setArtworkName(rs.getString("artwork_name"));
                income.setArtistName(rs.getString("artist_name"));
                income.setReceiveMoney(rs.getDouble("receive_money"));
                income.setCreateTime(rs.getDate("creat_time"));
                incomes.add(income);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(incomes));
        return incomes;
    }
}
