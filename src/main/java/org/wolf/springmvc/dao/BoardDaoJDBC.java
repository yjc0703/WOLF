package org.wolf.springmvc.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.wolf.springmvc.domain.BoardVO;

@Component
public class BoardDaoJDBC implements BoardDao {
	private DataSource dataSource;
	private BeanPropertyRowMapper<BoardVO> beanPropertyRowMapper;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.beanPropertyRowMapper = new BeanPropertyRowMapper<BoardVO>(BoardVO.class);
	}
	
	@Override
	public List<BoardVO> list() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from WOLF_BOARD where 1 = :nobody";
		List<BoardVO> boardList = jdbcTemplate.query(sql, this.beanPropertyRowMapper, 1);
		return boardList;
	}
	
	@Override
	public BoardVO select(int seq) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from WOLF_BOARD where seq = :seq";
		BoardVO boardVO = jdbcTemplate.queryForObject(sql, this.beanPropertyRowMapper, seq);
		return boardVO;
	}
	
	@Override
	public int updateReadCount(int seq) {
		String sql = "update WOLF_BOARD set cnt = cnt + 1 where seq = :seq";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int result = jdbcTemplate.update(sql, seq);
		return result;
	}
		
	@Override
	public void inert(BoardVO boardVO) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("WOLF_BOARD").usingGeneratedKeyColumns("seq");

		boardVO.setRegDate(new Date());
		BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(boardVO);
		
		int seq = simpleJdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource).intValue();		
		boardVO.setSeq(seq);
	}
	
	@Override
	public int update(BoardVO boardVO) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		String sql = "update WOLF_BOARD set title = :title, content = :content where seq = :seq";
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(BoardVO.class);
		int result = jdbcTemplate.update(sql, parameterSource);
		return result;
	}
	
	@Override
	public int delete(int seq) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "delete from WOLF_BOARD where seq = :seq";
		int result = jdbcTemplate.update(sql, seq);
		return result;
	}
}
