package com.jzh.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jzh.news.entity.News_content;
import com.jzh.news.entity.News_luntan;
import com.jzh.news.entity.News_pinglun;

public class News_luntanDaoImpl extends BaseDaoImpl {
	List<News_luntan> list = new ArrayList<News_luntan>();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;

	/**
	 * ��ѯ����
	 * 
	 * @param
	 * @return
	 */
	public List<News_luntan> search(String limit) {
		conn = this.getConnection();
		try {

			pstmt = conn
					.prepareStatement("select * from news_luntan order by lid desc limit "
							+ limit + ",10");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				News_luntan content = new News_luntan();
				content.setUser(rs.getString("user"));
				content.setLid(rs.getInt("lid"));

				content.setLocation(rs.getString("location"));
				content.setTime(rs.getString("time"));
				content.setContent(rs.getString("content"));
				content.setImage(rs.getString("image"));
				list.add(content);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * ��������id��ѯ����
	 * 
	 * @param id
	 * @return
	 */
	public News_luntan search_one(int lid) {
		conn = this.getConnection();
		News_luntan content = null;
		try {

			pstmt = conn
					.prepareStatement("select * from news_luntan where lid='"
							+ lid + "'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				content = new News_luntan();
				content.setUser(rs.getString("user"));
				content.setLid(rs.getInt("lid"));
				content.setLocation(rs.getString("location"));
				content.setTime(rs.getString("time"));
				content.setContent(rs.getString("content"));
				content.setImage(rs.getString("image"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return content;

	}

	/**
	 * �����û���ѯ����
	 * 
	 * @param id
	 * @return
	 */
	public List<News_luntan> search_one(String user, String limit) {
		conn = this.getConnection();
		News_luntan content = null;
		List<News_luntan> list = null;
		try {

			pstmt = conn
					.prepareStatement("select * from news_luntan where user='"
							+ user + "'order by lid desc limit " + limit
							+ ",10");
			rs = pstmt.executeQuery();
			list = new ArrayList<News_luntan>();
			while (rs.next()) {
				content = new News_luntan();
				content.setUser(rs.getString("user"));
				content.setLid(rs.getInt("lid"));
				content.setLocation(rs.getString("location"));
				content.setTime(rs.getString("time"));
				content.setContent(rs.getString("content"));
				content.setImage(rs.getString("image"));
				list.add(content);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * ��ѯ��������
	 * 
	 * @param id
	 * @return
	 */
	public int search_total() {
		conn = this.getConnection();
		try {

			pstmt = conn.prepareStatement("select * from news_luntan");
			rs = pstmt.executeQuery();
			rs.last();
			int rowCount = rs.getRow(); // ���ResultSet��������
			return rowCount;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			this.closeAll(rs, pstmt, conn);

		}

	}

	/**
	 * �����û�����ѯ��������
	 * 
	 * @param id
	 * @return
	 */
	public int search_total(String user) {
		conn = this.getConnection();
		try {

			pstmt = conn
					.prepareStatement("select * from news_luntan where user='"
							+ user + "'");
			rs = pstmt.executeQuery();
			rs.last();
			int rowCount = rs.getRow(); // ���ResultSet��������
			return rowCount;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			this.closeAll(rs, pstmt, conn);

		}

	}

	/**
	 * �����û������״̬
	 * 
	 * @param news
	 */
	public boolean save(News_luntan news) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into news_luntan(user,location,time,content,image)values(?,?,?,?,?)");

			pstmt.setString(1, news.getUser());
			pstmt.setString(2, news.getLocation());
			pstmt.setString(3, news.getTime());
			pstmt.setString(4, news.getContent());
			pstmt.setString(5, news.getImage());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}

	/**
	 * �����û����������
	 * 
	 * @param news
	 */
	public boolean save_pinglun(News_pinglun news) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into luntan_pinglun(user,plocation,ptime,pcontent,plid,pzan)values(?,?,?,?,?,?)");

			pstmt.setString(1, news.getUser());
			pstmt.setString(2, news.getPlocation());
			pstmt.setString(3, news.getPtime());
			pstmt.setString(4, news.getPcontent());
			pstmt.setInt(5, news.getPcid());
			pstmt.setString(6, news.getPzan());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}

	/**
	 * ��������id��ѯ����
	 * 
	 * @param id
	 * @return
	 */
	public List<News_pinglun> search_pinglun(String id, String limit) {
		conn = this.getConnection();
		List<News_pinglun> list = new ArrayList<News_pinglun>();
		try {

			pstmt = conn
					.prepareStatement("select * from luntan_pinglun where plid='"
							+ id + "' order by lid asc limit " + limit + ",10");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				News_pinglun content = new News_pinglun();
				content.setPid(rs.getInt("lid"));
				content.setPcid(rs.getInt("plid"));
				content.setUser(rs.getString("user"));
				content.setPlocation(rs.getString("plocation"));
				content.setPtime(rs.getString("ptime"));
				content.setPcontent(rs.getString("pcontent"));
				content.setPzan(rs.getString("pzan"));
				list.add(content);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return list;

	}

	/**
	 * �������۵���
	 * 
	 * @param news
	 */
	public void save(int pid, String user) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into luntan_zan(plid,user,iszan)values(?,?,?)");
			pstmt.setInt(1, pid);
			pstmt.setString(2, user);
			pstmt.setString(3, "1");
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}

	/**
	 * �޸�����֮��
	 */
	public boolean update(String pid, String zan) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("update luntan_pinglun set pzan=? where lid="
							+ pid);
			pstmt.setString(1, zan);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.closeAll(null, pstmt, conn);
		}
	}

	/**
	 * ��������id��ѯ����
	 * 
	 * @param id
	 * @return
	 */
	public int search_totals(String id) {
		conn = this.getConnection();
		try {

			pstmt = conn
					.prepareStatement("select * from luntan_pinglun where plid='"
							+ id + "'");
			rs = pstmt.executeQuery();
			rs.last();
			int rowCount = rs.getRow(); // ���ResultSet��������
			return rowCount;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			this.closeAll(rs, pstmt, conn);

		}

	}

	/**
	 * ����plid��ѯ�û���������
	 * 
	 * @param id
	 * @return
	 */
	public String search_zan(String id) {
		conn = this.getConnection();
		String zan = "";
		try {

			pstmt = conn
					.prepareStatement("select * from luntan_pinglun where lid='"
							+ id + "'");
			rs = pstmt.executeQuery();
			while (rs.next()) {

				zan = rs.getString("pzan");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, pstmt, conn);

		}
		return zan;

	}

	/**
	 * ����pid���û�����ѯ�Ƿ��޹�
	 * 
	 * @param id
	 * @return
	 */
	public String search_iszan(String pid, String user) {
		conn = this.getConnection();
		try {

			pstmt = conn
					.prepareStatement("select * from luntan_zan where plid='"
							+ pid + "' and user='" + user + "'");
			rs = pstmt.executeQuery();
			String iszan = "0";
			while (rs.next()) {
				iszan = rs.getString("iszan");
			}

			return iszan;

		} catch (SQLException e) {

			e.printStackTrace();
			return "0";
		} finally {
			this.closeAll(rs, pstmt, conn);

		}

	}

}
