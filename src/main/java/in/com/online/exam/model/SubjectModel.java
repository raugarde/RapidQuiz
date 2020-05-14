package in.com.online.exam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.ResultBean;
import in.com.online.exam.bean.SubjectBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DatabaseException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.util.JDBCDataSource;

public class SubjectModel {
	
private static Logger log = Logger.getLogger(UserModel.class);
	
	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM SUBJECT_TYPOLOGY");
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
	
public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SUBJECT_TYPOLOGY VALUES(?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getDescription());
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
			throw new ApplicationException("Exception : Exception in add User");
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
	StringBuffer sql = new StringBuffer("select * from userids.SUBJECT_TYPOLOGY");
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
			SubjectBean bean = new SubjectBean();
			bean.setId(rs.getLong(1));
			bean.setDescription(rs.getString(2));

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

public List search(SubjectBean bean) throws ApplicationException {
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

public List search(SubjectBean bean, int pageNo, int pageSize) throws ApplicationException {
	log.debug("Model search Started");
	StringBuffer sql = new StringBuffer("SELECT * FROM SUBJECT_TYPOLOGY WHERE 1=1");

	if (bean != null) {
		if (bean.getId() > 0) {
			sql.append(" AND id ="+ bean.getId());
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
			bean = new SubjectBean();
			bean.setId(rs.getLong(1));
			bean.setDescription(rs.getString(2));

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

public List searchByTeacher(int teacherId) throws ApplicationException {
	log.debug("Model search Started");
	StringBuffer sql = new StringBuffer("SELECT SUB.ID,SUB.DESCRIPTION FROM  USERIDS.USER_SUBJECT US JOIN USERIDS.SUBJECT_TYPOLOGY SUB ON SUB.ID = US.SUBJECT_FK WHERE US.USER_FK = "+ teacherId);

	
	System.out.println("user model search  :"+sql);
	ArrayList list = new ArrayList();
	Connection conn = null;
	SubjectBean bean = null;
	try {
		conn = JDBCDataSource.getConnection();
		System.out.println("---------------------hihi-------------bjv");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new SubjectBean();
			bean.setId(rs.getLong(1));
			bean.setDescription(rs.getString(2));

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

public SubjectBean searchByExam(int examId) throws ApplicationException {
	log.debug("Model search Started");
	StringBuffer sql = new StringBuffer("SELECT SUB.ID, SUB.DESCRIPTION  FROM  ONLINEEXAMSYSTEM.EX_EXAM EXAM JOIN userids.SUBJECT_TYPOLOGY SUB ON SUB.ID = EXAM.SUBJECT_FK WHERE EXAM.ID = "+ examId);

	
	System.out.println("user model search  :"+sql);
	Connection conn = null;
	SubjectBean bean = null;
	try {
		conn = JDBCDataSource.getConnection();
		System.out.println("---------------------hihi-------------bjv");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new SubjectBean();
			bean.setId(rs.getLong(1));
			bean.setDescription(rs.getString(2));
		}
		rs.close();
	} catch (Exception e) {
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in search user");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}

	log.debug("Model search End");
	return bean;
}
	

	
}
