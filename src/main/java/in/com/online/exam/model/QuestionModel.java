package in.com.online.exam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.ExamBean;
import in.com.online.exam.bean.QuestionBean;
import in.com.online.exam.bean.ResultBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DatabaseException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.util.DataUtility;
import in.com.online.exam.util.JDBCDataSource;

public class QuestionModel {
	
	
private static Logger log = Logger.getLogger(QuestionModel.class);
	
	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM EX_QUESTION");
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
	
public long add(QuestionBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;
		

		QuestionBean existbean = findByQuestionName(bean.getQuestionName());

		if (existbean != null) {
			throw new DuplicateRecordException("Question Name already exists");
		}
		
		ExamModel model=new ExamModel();
		ExamBean Ebean=model.findByPK(DataUtility.getLong(bean.getExamName()));
		bean.setExamName(Ebean.getExamName());
		
		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO EX_QUESTION VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getExamName());
			pstmt.setString(3, bean.getQuestionName());
			pstmt.setString(4,bean.getOption1());
			pstmt.setString(5,bean.getOption2());
			pstmt.setString(6,bean.getOption3());
			pstmt.setString(7,bean.getOption4());
			pstmt.setString(8,bean.getCorrectAns());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
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

public QuestionBean findByQuestionName(String name) throws ApplicationException {
	log.debug("Model findByLogin Started");
	StringBuffer sql = new StringBuffer("SELECT * FROM EX_Question WHERE QuestionName=?");
	QuestionBean bean = null;
	Connection conn = null;
	System.out.println("sql" + sql);

	try {
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new QuestionBean();
			bean.setId(rs.getLong(1));
			bean.setExamName(rs.getString(2));
			bean.setQuestionName(rs.getString(3));
			bean.setOption1(rs.getString(4));
			bean.setOption2(rs.getString(5));
			bean.setOption3(rs.getString(6));
			bean.setOption4(rs.getString(7));
			bean.setCorrectAns(rs.getString(8));
			bean.setCreatedBy(rs.getString(9));
			bean.setModifiedBy(rs.getString(10));
			bean.setCreatedDatetime(rs.getTimestamp(11));
			bean.setModifiedDatetime(rs.getTimestamp(12));
		
		}
		rs.close();
	} catch (Exception e) {
		e.printStackTrace();
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in getting User by login");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}
	log.debug("Model findByLogin End");
	return bean;
}

public QuestionBean findByPK(long id) throws ApplicationException {
	log.debug("Model findByLogin Started");
	StringBuffer sql = new StringBuffer("SELECT * FROM EX_Question WHERE id=?");
	QuestionBean bean = null;
	Connection conn = null;
	System.out.println("sql" + sql);

	try {
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new QuestionBean();
			bean.setId(rs.getLong(1));
			bean.setExamName(rs.getString(2));
			bean.setQuestionName(rs.getString(3));
			bean.setOption1(rs.getString(4));
			bean.setOption2(rs.getString(5));
			bean.setOption3(rs.getString(6));
			bean.setOption4(rs.getString(7));
			bean.setCorrectAns(rs.getString(8));
			bean.setCreatedBy(rs.getString(9));
			bean.setModifiedBy(rs.getString(10));
			bean.setCreatedDatetime(rs.getTimestamp(11));
			bean.setModifiedDatetime(rs.getTimestamp(12));
		
		}
		rs.close();
	} catch (Exception e) {
		e.printStackTrace();
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in getting User by login");
	} finally {
		JDBCDataSource.closeConnection(conn);
	}
	log.debug("Model findByLogin End");
	return bean;
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
	StringBuffer sql = new StringBuffer("select * from EX_Question");
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
			QuestionBean bean = new QuestionBean();
			bean.setId(rs.getLong(1));
			bean.setExamName(rs.getString(2));
			bean.setQuestionName(rs.getString(3));
			bean.setOption1(rs.getString(4));
			bean.setOption2(rs.getString(5));
			bean.setOption3(rs.getString(6));
			bean.setOption4(rs.getString(7));
			bean.setCorrectAns(rs.getString(8));
			bean.setCreatedBy(rs.getString(9));
			bean.setModifiedBy(rs.getString(10));
			bean.setCreatedDatetime(rs.getTimestamp(11));
			bean.setModifiedDatetime(rs.getTimestamp(12));

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

public List search(QuestionBean bean) throws ApplicationException {
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

public List search(QuestionBean bean, int pageNo, int pageSize) throws ApplicationException {
	log.debug("Model search Started");
	StringBuffer sql = new StringBuffer("SELECT * FROM EX_Question WHERE 1=1");

	if (bean != null) {
		if (bean.getId() > 0) {
			sql.append(" AND id = " + bean.getId());
		}
		if(bean.getExamName()!=null && bean.getExamName().length()>0) {
			sql.append(" AND ExamName like '" + bean.getExamName() + "%'");
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
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new QuestionBean();
			bean.setId(rs.getLong(1));
			bean.setExamName(rs.getString(2));
			bean.setQuestionName(rs.getString(3));
			bean.setOption1(rs.getString(4));
			bean.setOption2(rs.getString(5));
			bean.setOption3(rs.getString(6));
			bean.setOption4(rs.getString(7));
			bean.setCorrectAns(rs.getString(8));
			bean.setCreatedBy(rs.getString(9));
			bean.setModifiedBy(rs.getString(10));
			bean.setCreatedDatetime(rs.getTimestamp(11));
			bean.setModifiedDatetime(rs.getTimestamp(12));

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
