package org.wolf.springmvc.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.wolf.springmvc.domain.BoardVO;

@Component
public class BoardDaoJDBC implements BoardDao {
	private DataSource dataSource;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	private BeanPropertyRowMapper<BoardVO> beanPropertyRowMapper;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		this.beanPropertyRowMapper = new BeanPropertyRowMapper<BoardVO>(BoardVO.class);
	}
	
	@Override
	public List<BoardVO> list() {
		String sql = "select * from WOLF_BOARD where 1 = :nobody";
		List<BoardVO> boardList = simpleJdbcTemplate.query(sql, this.beanPropertyRowMapper, 1);
		return boardList;
	}
	
	@Override
	public BoardVO select(int seq) {
		String sql = "select * from WOLF_BOARD where seq = :seq";
		BoardVO boardVO = simpleJdbcTemplate.queryForObject(sql, this.beanPropertyRowMapper, seq);
		return boardVO;
	}
	
	@Override
	public int updateReadCount(int seq) {
		String sql = "update WOLF_BOARD set cnt = cnt + 1 where seq = :seq";
		//BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(boardVO);
		int result = simpleJdbcTemplate.update(sql, new Object[] {seq});
		return result;
	}
		
	@Override
	public void inert(BoardVO boardVO) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
													.withTableName("WOLF_BOARD")
													.usingGeneratedKeyColumns("seq");

		boardVO.setRegDate(new Date());
		BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(boardVO);
		
		int seq = simpleJdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource).intValue();
		
		boardVO.setSeq(seq);
	}	
	
	@Override
	public int update(BoardVO boardVO) {
		String sql = "update WOLF_BOARD set title = :title, content = :content where seq = :seq";
		BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(boardVO);
		int result = simpleJdbcTemplate.update(sql, beanPropertySqlParameterSource);
		return result;
	}
	
	@Override
	public int delete(int seq) {
		String sql = "delete from WOLF_BOARD where seq = :seq";
		int result = simpleJdbcTemplate.update(sql, seq);
		return result;
	}
}
