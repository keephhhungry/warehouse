package servlet;

import com.alibaba.fastjson.JSON;
import model.Artwork;
import utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/artwork")
public class ArtworkServlet extends HttpServlet {

    public static final String GET_ALL_ARTWORK = "1";
    public static final String ADD_ARTWORK = "2";

    public ArtworkServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        String type = req.getParameter("type");
            if (type.equals(GET_ALL_ARTWORK)) {
            getAllArtwork(req, resp);
        } else if (type.equals(ADD_ARTWORK)) {
            addArtwork(req, resp);
        }
    }

    /**
     * 查询全部艺术品
     */
    public List<Artwork> getAllArtwork(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Artwork> artworks = new ArrayList<Artwork>();
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from t_artwork");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long artworkId = rs.getLong(1);
                String artworkName = rs.getString(2);
                String price = rs.getString(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                Date dateReceived = rs.getTimestamp(6);
                String status = rs.getString(7);;
                Artwork artwork = new Artwork(artworkId, artworkName, price, description, type, dateReceived, status);
                artworks.add(artwork);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(artworks));
        return artworks;
    }

    /**
     * 添加艺术品
     */
    public void addArtwork(HttpServletRequest req, HttpServletResponse resp) {
        String ownType = req.getParameter("ownType");
        String ownersId = req.getParameter("ownersId");
        String artworkName = req.getParameter("artworkName");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        Date dateReceived = new Date();
        String status = req.getParameter("status");
        Connection conn = DBUtil.getConn();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO t_artwork(artwork_name, price, description, type, date_received, status) VALUES (? , ? , ? , ? , ? , ?)");
            ps.setString(1, artworkName);
            ps.setString(2, price);
            ps.setString(3, description);
            ps.setString(4, type);
            ps.setDate(5, new java.sql.Date(new Date().getTime()));
            ps.setString(6, status);
            ps.execute();

            int artworkId = 0;
            ps = conn.prepareStatement("select MAX(artwork_id) from t_artwork");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                artworkId = rs.getInt(1);
            }

            ps = conn.prepareStatement("INSERT INTO t_art_owner(artwork_id, owner_type, owners_id) VALUES (? , ? ,  ?)");
            ps.setString(1, artworkId+"");
            ps.setString(2, ownType);
            ps.setString(3, ownersId);
            ps.execute();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
