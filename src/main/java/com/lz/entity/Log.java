package com.lz.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	public Log(String content) {
		super();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new Date());// 格式化数据
		this.logdate = date;
		this.content = content;
	}

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column log.id
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	private Integer id;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column log.logdate
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	private String logdate;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column log.content
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	private String content;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column log.id
	 *
	 * @return the value of log.id
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column log.id
	 *
	 * @param id
	 *            the value for log.id
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column log.logdate
	 *
	 * @return the value of log.logdate
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	public String getLogdate() {
		return logdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column log.logdate
	 *
	 * @param logdate
	 *            the value for log.logdate
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	public void setLogdate(String logdate) {
		this.logdate = logdate;
		// SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd
		// HH:mm:ss");
		// String date = formatter.format(new Date());//格式化数据
		// this.logdate = date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column log.content
	 *
	 * @return the value of log.content
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column log.content
	 *
	 * @param content
	 *            the value for log.content
	 *
	 * @mbg.generated Thu Jan 04 21:32:13 CST 2018
	 */
	public void setContent(String content) {
		this.content = content;
	}
}