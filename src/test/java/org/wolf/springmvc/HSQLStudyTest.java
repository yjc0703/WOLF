package org.wolf.springmvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

public class HSQLStudyTest {
	EmbeddedDatabase db;
	JdbcTemplate template;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("@Before");
		db = new EmbeddedDatabaseBuilder()
			.setType(HSQL)
			.addScript("classpath:schema.sql")
			.addScript("classpath:data.sql")
			.build();
		
		template = new JdbcTemplate(db);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After");
		db.shutdown();
	}

	@Test
	public void initData() {
		System.out.println("initData");
		
		int count = template.queryForObject("select count(*) from sqlmap", Integer.class);
		
		assertThat(count, is(2));
		
		List<Map<String, Object>> rs = template.queryForList("select * from sqlmap order by key_");
		
		assertThat((String)rs.get(0).get("key_"), is("KEY1"));
		assertThat((String)rs.get(0).get("sql_"), is("SQL1"));
		assertThat((String)rs.get(1).get("key_"), is("KEY2"));
		assertThat((String)rs.get(1).get("sql_"), is("SQL2"));
	}
	
	@Test
	public void insert() {
		System.out.println("insert");
		
		template.update("insert into sqlmap(key_, sql_) values(?, ?)", "KEY3", "SQL3");
		
		assertThat(template.queryForObject("select count(*) from sqlmap", Integer.class), is(3));
	}
}




















