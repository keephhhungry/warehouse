package servlet;

import com.alibaba.fastjson.JSON;
import model.Artist;
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

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

    public static final String GET_ALL_ARTIST = "1";
    public static final String GET_SINGLE_ARTIST = "2";
    public static final String ADD_ARTIST = "3";
    public static final String UPDATE_ARTIST = "4";
    public static final String DELETE_ARTIST = "5";

    public ArtistServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        String type = req.getParameter("type");
        if (type.equals(GET_ALL_ARTIST)) {
            getAllArtist(req, resp);
        } else if (type.equals(GET_SINGLE_ARTIST)) {
            getSingleArtist(req, resp);
        } else if (type.equals(ADD_ARTIST)) {
            addArtist(req, resp);
        } else if (type.equals(UPDATE_ARTIST)) {
            UpdateArtist(req, resp);
        } else if (type.equals(DELETE_ARTIST)) {
            deleteArtist(req, resp);
        }
    }

    /**
     * 查询全部艺术家
     */
    public List<Artist> getAllArtist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Artist> artists = new ArrayList<Artist>();
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from t_artist");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long artistId = rs.getLong(1);
                String artistName = rs.getString(2);
                String artistPhoneNumber = rs.getString(3);
                String artistGender = rs.getString(4);
                Artist artist = new Artist(artistId, artistName, artistPhoneNumber, artistGender);
                artists.add(artist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(artists));
        return artists;
    }

    /**
     * 查询单个艺术家
     */
    public Artist getSingleArtist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Artist artist = null;
		String artistId = req.getParameter("artistId");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from t_artist where artist_id = ?");
			ps.setString(1, artistId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Long artistId1 = rs.getLong(1);
                String artistName = rs.getString(2);
                String artistPhoneNumber = rs.getString(3);
                String artistGender = rs.getString(4);
                artist = new Artist(artistId1, artistName, artistPhoneNumber, artistGender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(JSON.toJSONString(artist));
        return artist;
    }

    /**
     * 添加艺术家
     */
    public void addArtist(HttpServletRequest req, HttpServletResponse resp) {
        String artistName = req.getParameter("artistName");
        String artistPhoneNumber = req.getParameter("artistPhoneNumber");
        String artistGender = req.getParameter("artistGender");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO t_artist( artist_name, artist_phone_number, artist_gender) VALUES ( ? , ? , ? )");
            ps.setString(1, artistName);
            ps.setString(2, artistPhoneNumber);
            ps.setString(3, artistGender);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新艺术家
     */
    public void UpdateArtist(HttpServletRequest req, HttpServletResponse resp) {
        String artistId = req.getParameter("artistId");
        String artistName = req.getParameter("artistName");
        String artistPhoneNumber = req.getParameter("artistPhoneNumber");
        String artistGender = req.getParameter("artistGender");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("update t_artist set artist_name = ? , artist_phone_number = ? , artist_gender = ? where artist_id = ?");
            ps.setString(1, artistName);
            ps.setString(2, artistPhoneNumber);
            ps.setString(3, artistGender);
            ps.setString(4, artistId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除艺术家
     *
     * @param req
     * @param resp
     */
    public void deleteArtist(HttpServletRequest req, HttpServletResponse resp) {
        String artistId = req.getParameter("artistId");
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("delete from t_artist where artist_id = ?");
            ps.setString(1, artistId);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
