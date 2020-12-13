package servlet;

import com.alibaba.fastjson.JSON;
import model.Collector;
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

@WebServlet("/collector")
public class CollectorServlet extends HttpServlet {

    public static final String GET_ALL_COLLECTOR = "1";
    public static final String GET_SINGLE_COLLECTOR = "2";
    public static final String ADD_COLLECTOR = "3";
    public static final String UPDATE_COLLECTOR = "4";
    public static final String DELETE_COLLECTOR = "5";

    public CollectorServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        String type = req.getParameter("type");
        if (type.equals(GET_ALL_COLLECTOR)) {
            getAllCollector(req, resp);
        } else if (type.equals(GET_SINGLE_COLLECTOR)) {
            getSingleCollector(req, resp);
        } else if (type.equals(ADD_COLLECTOR)) {
            addCollector(req, resp);
        } else if (type.equals(UPDATE_COLLECTOR)) {
            UpdateCollector(req, resp);
        } else if (type.equals(DELETE_COLLECTOR)) {
            deleteCollector(req, resp);
        }
    }

    /**
     * 查询全部收藏家
     */
    public List<Collector> getAllCollector(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Collector> collectors = new ArrayList<Collector>();
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from t_collector");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long collectorId = rs.getLong(1);
                String collectorName = rs.getString(2);
                String collectorGender = rs.getString(3);
                String collectorPhoneNumber = rs.getString(4);
                Collector collector = new Collector(collectorId, collectorName, collectorGender, collectorPhoneNumber);
                collectors.add(collector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(collectors));
        return collectors;
    }

    /**
     * 查询单个收藏家
     */
    public Collector getSingleCollector(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Collector collector = null;
        String collectorId = req.getParameter("collectorId");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from t_collector where collector_id = ?");
            ps.setString(1, collectorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Long collectorIdId1 = rs.getLong(1);
                String collectorIdName = rs.getString(2);
                String collectorIdGender = rs.getString(3);
                String collectorIdPhoneNumber = rs.getString(4);
                collector = new Collector(collectorIdId1,collectorIdName,collectorIdGender,collectorIdPhoneNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(collector));
        return collector;
    }

    /**
     * 添加收藏家
     */
    public void addCollector(HttpServletRequest req, HttpServletResponse resp) {
        String collectorName = req.getParameter("collectorName");
        String collectorGender = req.getParameter("collectorGender");
        String collectorPhoneNumber = req.getParameter("collectorPhoneNumber");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO t_collector(collector_name, collector_gender, collector_phone_number) VALUES ( ? , ? , ? )");
            ps.setString(1, collectorName);
            ps.setString(2, collectorGender);
            ps.setString(3, collectorPhoneNumber);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新收藏家
     */
    public void UpdateCollector(HttpServletRequest req, HttpServletResponse resp) {
        String collectorId = req.getParameter("collectorId");
        String collectorName = req.getParameter("collectorName");
        String collectorGender = req.getParameter("collectorGender");
        String collectorPhoneNumber = req.getParameter("collectorPhoneNumber");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("update t_collector set collector_name = ? , collector_gender = ? , collector_phone_number = ? where collector_id = ?");
            ps.setString(1, collectorName);
            ps.setString(2, collectorGender);
            ps.setString(3, collectorPhoneNumber);
            ps.setString(4, collectorId);
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
    public void deleteCollector(HttpServletRequest req, HttpServletResponse resp) {
        String collectorId = req.getParameter("collectorId");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("delete from t_collector where collector_id = ?");
            ps.setString(1, collectorId);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
