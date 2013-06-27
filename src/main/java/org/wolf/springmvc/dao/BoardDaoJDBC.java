package org.wolf.springmvc.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class BoardDaoJDBC implements BoardDao {
	private SimpleJdbcTemplate jdbc;

	public void setDataSource(DataSource dataSource) {
		jdbc = new SimpleJdbcTemplate(dataSource);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> list() {
		String sql = "select * from WOLF_BOARD";
		return jdbc.queryForList(sql);
	}
	
//	private class BoardRowMapper implements RowMapper<BoardVO> {
//		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//			BoardVO board = new BoardVO();
//			board.setSeq(rs.getInt("seq"));
//			board.setTitle(rs.getString("title"));
//			board.setContent(rs.getString("content"));
//			board.setWriter(rs.getString("writer"));
//			board.setRegDate(rs.getDate("regDate"));
//			board.setContent(rs.getString("setContent"));
//			board.setCnt(rs.getInt("cnt"));
//			return board;
//		}
//	}
}
