package com.jzh.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jzh.news.entity.News_content;
import com.jzh.news.entity.News_pinglun;

public class News_pinglunDaoImpl extends BaseDaoImpl {
	List<News_pinglun> list = new ArrayList<News_pinglun>();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	
	
	/**
	 * ��������id��ѯ����
	 * @param id
	 * @return
	 */
	public List<News_pinglun> search(String id,String limit) {
		conn = this.getConnection();
		try {

			pstmt = conn
					.prepareStatement("select * from news_pinglun where pcid='"
							+ id + "' order by pid desc limit "+limit+",10");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				News_pinglun content = new News_pinglun();
				content.setPid(rs.getInt("pid"));
				content.setPcid(rs.getInt("pcid"));
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
	 * ��������id��ѯ����
	 * @param id
	 * @return
	 */
	public int search_total(String id) {
		conn = this.getConnection();
		try {

			pstmt = conn
					.prepareStatement("select * from news_pinglun where pcid='"
							+ id + "'");
			rs = pstmt.executeQuery();
			rs.last();  
			int rowCount = rs.getRow(); //���ResultSet��������  
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
	 * �����û�����
	 * 
	 * @param news
	 */
	public boolean save(News_pinglun news) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into news_pinglun(pcid,user,plocation,ptime,pcontent,pzan)values(?,?,?,?,?,?)");
			pstmt.setInt(1, news.getPcid());
			pstmt.setString(2, news.getUser());
			pstmt.setString(3, news.getPlocation());
			pstmt.setString(4, news.getPtime());
			pstmt.setString(5, news.getPcontent());
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
	 * �������۵���
	 * 
	 * @param news
	 */
	public void save(int pid,String user) {
		conn = this.getConnection();
		try {
			pstmt = conn
					.prepareStatement("insert into news_zan(pid,user,iszan)values(?,?,?)");
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
					.prepareStatement("update news_pinglun set pzan=? where pid="
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
	 * ����pid��ѯ�û���������
	 * 
	 * @param id
	 * @return
	 */
	public List<News_pinglun> search_zan(String id) {
		conn = this.getConnection();
		try {

			pstmt = conn
					.prepareStatement("select * from news_pinglun where pid='"
							+ id + "'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				News_pinglun content = new News_pinglun();
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
	 * ����pid���û�����ѯ�Ƿ��޹�
	 * 
	 * @param id
	 * @return
	 */
	public String search_iszan(String pid, String user) {
		conn = this.getConnection();
		try {

			pstmt = conn.prepareStatement("select * from news_zan where pid='"
					+ pid + "' and user='" + user + "'");
			rs = pstmt.executeQuery();
			String iszan="0";
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
