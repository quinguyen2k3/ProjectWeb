package interfacedao;

import java.util.List;
import mapper.RowMapper;


public interface GenericDAO <T> {
	<T> List<T> query(String sql, RowMapper<T> rowmapper, Object... param);
	void update (String sql, Object... param);
	Long insert (String sql, Object... param);
	int count(String sql, Object... param);
}