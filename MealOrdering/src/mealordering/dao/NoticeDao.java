package mealordering.dao;

import mealordering.domain.Notice;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

import static dk_breeze.utils.ext.StringExt.f;

/**
 * 公告的Dao类
 */
public class NoticeDao {
	NoticeDao() {}


	/**
	 * 添加公告。
	 * @param notice 公告信息
	 */
	public void doAdd(@NotNull Notice notice) throws SQLException {
		String sql = "insert into Notice(title,details,time) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, notice.getTitle(), notice.getDetails(), notice.getTime());
	}

	/**
	 * 编辑公告信息。
	 * @param notice 公告信息
	 */
	public void doEdit(@NotNull Notice notice) throws SQLException {
		String sql = "update Notice set title=?,details=?,time=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, notice.getTitle(), notice.getDetails(), notice.getTime(), notice.getId());
	}

	/**
	 * 根据Id删除公告。
	 * @param id  公告Id
	 */
	public void doDeleteById(int id) throws SQLException {
		String sql = "doDeleteById from Notice where id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}


	/**
	 * 根据Id查询公告。
	 * @param id 公告Id
	 */
	public Notice findById(int id) throws SQLException {
		String sql = "select * from Notice where id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Notice.class), id);
	}

	/**
	 * 查询指定数量的最近添加或修改的公告。
	 */
	public List<Notice> findRecent(int findCount) throws SQLException {
		String sql = "select * from Notice order by time desc limit 0,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Notice.class), findCount);
	}

	/**
	 * 查询所有公告，按照时间降序排列。
	 */
	public List<Notice> findAll() throws SQLException {
		String sql = "select * from Notice order by time desc";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Notice.class));
	}

	/**
	 * 根据公告标题进行模糊搜索。
	 */
	public List<Notice> searchByTitle(String title) throws SQLException {
		String sql = f("select * from Notice where title like '%{0}%'", title);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Notice.class));
	}
}
