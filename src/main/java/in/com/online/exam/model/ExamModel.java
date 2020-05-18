package in.com.online.exam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.ExamBean;
import in.com.online.exam.bean.ResultBean;
import in.com.online.exam.bean.UserBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DatabaseException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.util.JDBCDataSource;

public class ExamModel {

	private static Logger log = Logger.getLogger(UserModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM EX_EXAM");
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

	public long add(ExamBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		ExamBean existbean = findByExamName(bean.getExamName());

		if (existbean != null) {
			throw new DuplicateRecordException("Exam Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO EX_EXAM VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getExamName());
			pstmt.setDate(3, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setString(8, bean.getExamCategory());
			pstmt.setInt(9, bean.getSubject_id());
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

	public ExamBean findByExamName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM EX_EXAM WHERE EXAMNAME=?");
		ExamBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ExamBean();
				bean.setId(rs.getLong(1));
				bean.setExamName(rs.getString(2));
				bean.setExamDate(rs.getDate(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				bean.setExamCategory(rs.getString(8));
				bean.setSubject_id(rs.getInt(9));
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

	public ExamBean findByPK(long id) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM EX_EXAM WHERE ID=?");
		ExamBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ExamBean();
				bean.setId(rs.getLong(1));
				bean.setExamName(rs.getString(2));
				bean.setExamDate(rs.getDate(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				bean.setExamCategory(rs.getString(8));
				bean.setSubject_id(rs.getInt(9));

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
		StringBuffer sql = new StringBuffer("select * from EX_EXAM");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("sql in list user :" + sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ExamBean bean = new ExamBean();
				bean.setId(rs.getLong(1));
				bean.setId(rs.getLong(1));
				bean.setExamName(rs.getString(2));
				bean.setExamDate(rs.getDate(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				bean.setExamCategory(rs.getString(8));
				bean.setSubject_id(rs.getInt(9));

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

	public void delete(ExamBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM EX_EXAM WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	public void update(ExamBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		ExamBean duplicataRole = findByExamName(bean.getExamName());

		// Check if updated Role already exist
		if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
			throw new DuplicateRecordException("Exam Name already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE EX_EXAM SET EXAMNAME=?,ExamDate=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,examCategory=?,SUBJECT_FK = ? WHERE ID=?");
			pstmt.setString(1, bean.getExamName());
			pstmt.setDate(2,  new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setString(7,bean.getExamCategory());
			pstmt.setLong(8, bean.getId());
			pstmt.setInt(8, bean.getSubject_id());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	 public List search(ExamBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }

	    /**
	     * Search Role with pagination
	     * 
	     * @return list : List of Roles
	     * @param bean
	     *            : Search Parameters
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * 
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public List search(ExamBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM onlineexamsystem.Ex_Exam exam join userids.subject_typology sub on sub.id = exam.subject_fk WHERE 1=1");
	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getExamName() != null && bean.getExamName().length() > 0) {
					sql.append(" AND EXAMNAME LIKE '" + bean.getExamName() + "%'");
	            }
	            if (bean.getExamCategory() != null
	                    && bean.getExamCategory().length() > 0) {
					sql.append(" AND ExamCategory LIKE '" + bean.getExamCategory()
	                        + "%'");
	            }
	        }

	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" Limit " + pageNo + ", " + pageSize);
	            // sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        ArrayList list = new ArrayList();
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new ExamBean();
	                bean.setId(rs.getLong(1));
					bean.setId(rs.getLong(1));
					bean.setExamName(rs.getString(2));
					bean.setExamDate(rs.getDate(3));
					bean.setCreatedBy(rs.getString(4));
					bean.setModifiedBy(rs.getString(5));
					bean.setCreatedDatetime(rs.getTimestamp(6));
					bean.setModifiedDatetime(rs.getTimestamp(7));
					bean.setExamCategory(rs.getString(8));
					bean.setSubject_id(rs.getInt(9));
					bean.setSubject_description(rs.getString(11));
					if(bean.getSubject_description() == null) {
						bean.setSubject_description("No subject defined");
					}
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model search End");
	        return list;
	    }

}
