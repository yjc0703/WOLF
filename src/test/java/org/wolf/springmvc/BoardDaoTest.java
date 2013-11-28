package org.wolf.springmvc;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wolf.springmvc.dao.BoardDao;
import org.wolf.springmvc.domain.BoardVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "file:src/main/webapp/WEB-INF/spring/test-context.xml")
public class BoardDaoTest {
	
	@Autowired
	BoardDao dao;
	
	@Test
	public void initTest() {
		assertThat(dao.list().size(), is(7));
	}
	
	@Test
	public void insertTest() {
		BoardVO vo = new BoardVO();
		vo.setTitle("title");
		vo.setContent("content");
		vo.setPassword("1234");
		vo.setWriter("writer");
		
		dao.inert(vo);
		
		assertThat(dao.list().size(), is(8));		
		assertThat(vo.getSeq(), is(not(0)));
	}
	
	@Test
	public void updateTest() {
		BoardVO vo = dao.select(0);
		assertThat(vo.getTitle(), is("title1"));
		
		vo.setTitle("title1-1");
		dao.update(vo);
		
		BoardVO vo2 = dao.select(0);
		assertThat(vo.getTitle(), is(vo2.getTitle()));
	}
	
	@Test
	public void deleteTest() {
		// context 가 살아있는 동안 상태관리가 되므로 
		// 먼저 실행된 insertTest로 인하여 row가 증가된 상태
		assertThat(dao.list().size(), is(8));
		dao.delete(0);
		assertThat(dao.list().size(), is(7));
	}

}
