package in.com.online.exam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.ResultBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DatabaseException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.util.JDBCDataSource;

public class ResultModel {
	
private static Logger log = Logger.getLogger(UserModel.class);
	
	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM EX_RESULT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
public long add(ResultBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO EX_RESULT VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getExaminationName());
			pstmt.setDate(3, new java.sql.Date(bean.getExaminationDate().getTime()));
			pstmt.setString(4, bean.getResult());
			pstmt.setLong(5,bean.getUser_id());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User" + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

public List list() throws ApplicationException {
	return list(0, 0);
}

/**
 * Get List of User with pagination
 * 
 * @return list : List of users
 * @param pageNo
 *            : Current Page No.
 * @param pageSize
 *            : Size of Page
 * @throws DatabaseException
 */

public List list(int pageNo, int pageSize) throws ApplicationException {
	log.debug("Model list Started");
	ArrayList list = new ArrayList();
	StringBuffer sql = new StringBuffer("select * from EX_RESULT");
	// if page size is greater than zero then apply pagination
	if (pageSize > 0) {
		// Calculate start record index
		pageNo = (pageNo - 1) * pageSize;
		sql.append(" limit " + pageNo + "," + pageSize);
	}

	
	System.out.println("sql in list user :"+sql);
	Connection conn = null;

	try {
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ResultBean bean = new ResultBean();
			bean.setId(rs.getLong(1));
			bean.setExaminationName(rs.getString(2));
			bean.setExaminationDate(rs.getDate(3));
			bean.setResult(rs.getString(4));
			bean.setUser_id(rs.getLong(5));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDatetime(rs.getTimestamp(9));
			bean.setModifiedDatetime(rs.getTimestamp(10));

			list.add(bean);
		}
		rs.close();
	} catch (Exception e) {
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in getting list of users");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}

	log.debug("Model list End");
	return list;

}

public List search(ResultBean bean) throws ApplicationException {
	return search(bean, 0, 0);
}

/**
 * Search User with pagination
 * 
 * @return list : List of Users
 * @param bean
 *            : Search Parameters
 * @param pageNo
 *            : Current Page No.
 * @param pageSize
 *            : Size of Page
 * 
 * @throws DatabaseException
 */

public List search(ResultBean bean, int pageNo, int pageSize) throws ApplicationException {
	log.debug("Model search Started");
	StringBuffer sql = new StringBuffer("SELECT * FROM EX_RESULT WHERE 1=1");

	if (bean != null) {
		if (bean.getId() > 0) {
			sql.append(" AND id ="+ bean.getId());
		}
		if (bean.getUser_id() > 0) {
			sql.append(" AND  USER_ID ="+ bean.getUser_id());
		}
	

	}

	// if page size is greater than zero then apply pagination
	if (pageSize > 0) {
		// Calculate start record index
		pageNo = (pageNo - 1) * pageSize;

		sql.append(" Limit " + pageNo + ", " + pageSize);
		// sql.append(" limit " + pageNo + "," + pageSize);
	}

	System.out.println("user model search  :"+sql);
	ArrayList list = new ArrayList();
	Connection conn = null;
	try {
		conn = JDBCDataSource.getConnection();
		System.out.println("---------------------hihi-------------bjv");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new ResultBean();
			bean.setId(rs.getLong(1));
			bean.setExaminationName(rs.getString(2));
			bean.setExaminationDate(rs.getDate(3));
			bean.setResult(rs.getString(4));
			bean.setUser_id(rs.getLong(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));

			list.add(bean);
		}
		rs.close();
	} catch (Exception e) {
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in search user");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}

	log.debug("Model search End");
	return list;
}
	

	
}
