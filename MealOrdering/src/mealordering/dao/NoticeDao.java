package mealordering.dao;

import mealordering.domain.Notice;
import mealordering.domain.annotations.Permission;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

/**
 * 公告的Dao类
 */
public class NoticeDao {

	/**
	 * 添加公告。
	 */
	@Permission(Permission.P.Admin)
	public void doAdd(@NotNull Notice notice) throws SQLException {
		String sql = "insert into Notice(title,details,time) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, notice.getTitle(), notice.getDetails(), notice.getTime());
	}

	/**
	 * 编辑公告信息。
	 */
	@Permission(Permission.P.Admin)
	public void doEdit(@NotNull Notice notice) throws SQLException {
		String sql = "update Notice set title=?,details=?,time=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, notice.getTitle(), notice.getDetails(), notice.getTime(), notice.getId());
	}

	/**
	 * 删除公告。
	 */
	@Permission(Permission.P.Admin)
	public void doDelete(int id) throws SQLException {
		String sql = "delete from Notice where id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}


	/**
	 * 根据Id查询公告。
	 */
	@Permission(Permission.P.Admin)
	public Notice findById(int id) throws SQLException {
		String sql = "select * from Notice where id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Notice.class), id);
	}

	/**
	 * 查询所有公告，按照时间降序排列。
	 */
	@Permission(Permission.P.All)
	public List<Notice> findAll() throws SQLException {
		String sql = "select * from Notice order by time desc";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Notice.class));
	}

	/**
	 * 查询指定数量的最近添加或修改的公告。
	 */
	@Permission(Permission.P.All)
	public Notice findAllRecent(int count) throws SQLException {
		String sql = "select * from Notice order by time desc limit 0,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Notice.class), count);
	}

	/**
	 * 查询最近添加或修改的一条公告。
	 */
	@Permission(Permission.P.All)
	public Notice findLatest() throws SQLException {
		return findAllRecent(1);
	}
}
