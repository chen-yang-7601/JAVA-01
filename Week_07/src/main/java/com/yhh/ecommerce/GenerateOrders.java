package com.yhh.ecommerce;

import com.yhh.ecommerce.entity.SimpleOrders;
import com.yhh.ecommerce.entity.Goods;
import com.yhh.jdbc.ConnectionFactory;
import com.yhh.jdbc.JdbcUtil;
import com.yhh.jdbc.hikaricp.HikariCPDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateOrders {
	
	public static int[] GetBaseDataIdArray(Connection con, String tableName, String idCol) {
		
		int[] employeeArray = null;
		String query = "select "+ idCol +" from "+ tableName +" order by " + idCol;
		PreparedStatement pst = null;
		
		try (PreparedStatement querycmd = con.prepareStatement(query)) {
			// get record count, maybe there has some more convenient method to get this value.
			String countquery = "select count(*) from " + tableName;
			pst = con.prepareStatement(countquery);
			ResultSet rs = pst.executeQuery();

			int count = 0;
			if (rs!=null && rs.next()) {
				count = rs.getInt(1);
			}
			employeeArray = new int[count];
			// execute query
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			int i=0;
			while(rs.next()) {
				employeeArray[i] = rs.getInt(1);
				i++;
			}
			
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}		
		
		return employeeArray;
	}
	
	public static void GenerateEmployee(Connection con) {

		String addString = "insert EMPLOYEE (EMP_NAME) values (?)";

		AtomicInteger increaseNum = new AtomicInteger(100000);
		try (PreparedStatement addEmp = con.prepareStatement(addString)) {
			con.setAutoCommit(false);
			for (int i = 0; i < 500; i++) {
				final int randomNum = increaseNum.getAndIncrement();
				addEmp.setString(1, String.valueOf(randomNum));
				addEmp.execute();
			}
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
	}
	
	public static void GenerateCustomer(Connection con) {

		String addString = "insert CUSTOMER (`nickname`,`name`,`cert_card`,`gender`,`phone_number`,`reg_date`) values(?,?,?,?,?,?)"; 

		int[] employeeArray = GetBaseDataIdArray(con, "EMPLOYEE", "ID");
		
		Random rand = new Random(System.currentTimeMillis());
		
		AtomicInteger increaseNum = new AtomicInteger(10001);
		AtomicInteger increasePhone = new AtomicInteger(100_000_000);
		long ctime = System.currentTimeMillis();
		try (PreparedStatement addCust = con.prepareStatement(addString)) {
			con.setAutoCommit(false);
			for (int i = 0; i < 10_000; i++) {
				final int randomNum = increaseNum.getAndIncrement();
				addCust.setString(1, "nick-"+String.valueOf(randomNum)); //nickname
				addCust.setString(2, String.valueOf(randomNum)+"-name"); //name
				int num = rand.nextInt( employeeArray.length); //cert_card
				
				addCust.setString(3, String.format("%s%s%s", employeeArray[num],"19930101",1000)); //cert_card
				
				addCust.setInt(4, rand.nextFloat()<0.5? 0:1); //gender
				addCust.setString(5, String.format("13%s", increasePhone.addAndGet(10) +rand.nextInt(100_000))); //phone_number
				
				addCust.setTimestamp(6, Timestamp.valueOf("2021-03-01 09:01:16")); //creator_time				
				addCust.addBatch();
			}
			addCust.executeBatch();
			con.commit();
			long etime = System.currentTimeMillis();
			System.out.println("used:" + (etime-ctime) +" ms");
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
	}	
	
	
	public static void GenerateCategory(Connection con) {

		String addString = "insert CATEGORY (`id`,`cate_name`,`creator`,`create_time`) values (?,?,?,?)";

		int[] employeeArray = GetBaseDataIdArray(con, "EMPLOYEE", "ID");
		
		Random rand = new Random(System.currentTimeMillis());
		
		AtomicInteger increaseNum = new AtomicInteger(1000);
		long ctime = System.currentTimeMillis();
		try (PreparedStatement addEmp = con.prepareStatement(addString)) {
			con.setAutoCommit(false);
			for (int i = 0; i < 1000; i++) {
				final int randomNum = increaseNum.getAndIncrement();
				addEmp.setString(1, String.valueOf(randomNum)); //category_id
				addEmp.setString(2, String.valueOf(randomNum)+"-name"); //cate_name
				int num = rand.nextInt( employeeArray.length);
				addEmp.setInt(3, employeeArray[num]); //creator				
				addEmp.setTimestamp(4, Timestamp.valueOf("2021-03-01 09:01:16")); //creator_time				
				addEmp.execute();
			}
			con.commit();
			long etime = System.currentTimeMillis();
			System.out.println("used:" + (etime-ctime) +" ms");
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
	}	
	
	public static void GenerateGoods(Connection con) {

		String addString = "insert GOODS (`id`,`category_id`,`store_id`,	`goods_name`,`price`,`discount`,`description`,`status`,	`creator`,	`create_time`) "
				+ " VALUES 	(?, ?,?,?,?,?,?,?,?,?)";

		int[] employeeArray = GetBaseDataIdArray(con, "employee", "id");
		int[] categoryArray = GetBaseDataIdArray(con, "category", "id");
		
		Random rand = new Random(System.currentTimeMillis());
		Random rand1 = new Random(System.currentTimeMillis());
		AtomicInteger increaseNum = new AtomicInteger(10000);
		long ctime = System.currentTimeMillis();
		
		try (PreparedStatement addEmp = con.prepareStatement(addString)) {
			con.setAutoCommit(false);
			for (int i = 0; i <= 10000; i++) {
				final long randomNum = increaseNum.getAndIncrement();
				final int cateInd = ThreadLocalRandom.current().nextInt(categoryArray.length);
				
				addEmp.setLong(1, randomNum); //id
				addEmp.setInt(2, categoryArray[cateInd]); //category_id
				addEmp.setInt(3, 1); //store_id
				addEmp.setString(4,  String.format("%s-%s-name",categoryArray[cateInd], randomNum)); //goods_name
				addEmp.setDouble(5, ThreadLocalRandom.current().nextInt(1000000)+1);
				addEmp.setDouble(6, 1.0); //discount
				addEmp.setString(7, ""); //description
				addEmp.setInt(8, 1); //status
				
				int num = rand.nextInt( employeeArray.length);
				addEmp.setInt(9, employeeArray[num]); //creator				
				addEmp.setTimestamp(10, Timestamp.valueOf("2021-03-02 09:01:16")); //creator_time				
				addEmp.addBatch();
			}
			addEmp.executeBatch();
			con.commit();
			long etime = System.currentTimeMillis();
			System.out.println("used:" + (etime-ctime) +" ms");
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
	}		
	
	public static List<ArrayList<Goods>> QueryAllGoods(Connection con, int split) {
		String query = "SELECT `id`,`category_id`, `store_id`,`goods_name`,`price`,`discount`,`description`,`status`,	`creator`,`create_time`"
				+ " FROM `goods` order by id"; 
		
		ResultSet rs = null;
		List<ArrayList<Goods>> goodsList = null;
		try (PreparedStatement pst = con.prepareStatement(query)) {
			con.setAutoCommit(false);
			rs = pst.executeQuery();
			if(rs==null || !rs.next()) {
				return null;
			}
			goodsList = new ArrayList<ArrayList<Goods>>();
			while(rs.next()) {
				ArrayList<Goods> currgoodsList = new ArrayList<Goods>();
				for (int i= 0;i< split;i++) {
					Goods goods = new Goods();
					goods.setId(rs.getLong("id"));
					goods.setCategory_id(rs.getInt("category_id"));
					goods.setStore_id(rs.getInt("store_id"));
					goods.setGoods_name(rs.getString("goods_name"));
					goods.setPrice(rs.getDouble("price"));
					goods.setDiscount(rs.getDouble("discount"));
					goods.setDescription(rs.getNString("description"));
					goods.setGoods_status(rs.getInt("status"));
					goods.setCreator(rs.getInt("creator"));		
					currgoodsList.add(goods);
				}
				goodsList.add(currgoodsList);
			}
			
			return goodsList;
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
		return goodsList;
	}
	

	public static List<Goods> QueryAllGoods(Connection con) {
		String query = "SELECT `id`,`category_id`, `store_id`,`goods_name`,`price`,`discount`,`description`,`status`,	`creator`,`create_time`"
				+ " FROM `goods` order by id"; 
		
		ResultSet rs = null;
		List<Goods> goodsList = null;
		try (PreparedStatement pst = con.prepareStatement(query)) {
			con.setAutoCommit(false);
			rs = pst.executeQuery();
			if(rs==null || !rs.next()) {
				return null;
			}
			goodsList = new ArrayList<Goods>();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getLong("id"));
				goods.setCategory_id(rs.getInt("category_id"));
				goods.setStore_id(rs.getInt("store_id"));
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setPrice(rs.getDouble("price"));
				goods.setDiscount(rs.getDouble("discount"));
				goods.setDescription(rs.getNString("description"));
				goods.setGoods_status(rs.getInt("status"));
				goods.setCreator(rs.getInt("creator"));		
				goodsList.add(goods);
			}
			
			return goodsList;
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
		return goodsList;
	}
	
	//此处可以用Guava来实现List的拆分
	public static void GenerateOrderDetails(Connection con) {

		String addString = "insert ORDER_DETAILS (`order_id`,`seq`,`store_id`,`goods_id`,`goods_name`,`price`,`payed_amount`,`customer_id`,	`create_time`,`category_id`) "
				+ " VALUES 	( ?,?,?,?,?,?,?,?,?,?)";

		int[] customerArray = GetBaseDataIdArray(con, "CUSTOMER", "ID");
		int[] goodsArray = GetBaseDataIdArray(con, "GOODS", "ID");
		Random rand = new Random(System.currentTimeMillis());
		Random rand1 = new Random(System.currentTimeMillis()+1_000_000);
		AtomicInteger increaseNum = new AtomicInteger(3000);
		long ctime = System.currentTimeMillis();
		try (PreparedStatement addEmp = con.prepareStatement(addString)) {
			con.setAutoCommit(false);
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j< 10_000; j++) {
					final int randomNum = increaseNum.getAndIncrement();
					final int custInd = ThreadLocalRandom.current().nextInt(customerArray.length);
					
					//addEmp.setInt(1, randomNum); //detail_id
					addEmp.setInt(1, randomNum); //order_id
					addEmp.setInt(2, 1); //seq
					addEmp.setInt(3, 1); //store_id
					final int goodsInd = rand.nextInt(goodsArray.length);
					addEmp.setInt(4, goodsArray[goodsInd]); //goods_id --
					addEmp.setString(5, ""); //goods_name
					addEmp.setDouble(6, 1.0); //price
					addEmp.setDouble(7, 1.0); //payed_amount
					addEmp.setInt(8, customerArray[custInd]); //customer_id
								
					addEmp.setTimestamp(9, Timestamp.valueOf("2021-03-02 09:01:16")); //creator_time
					addEmp.setInt(10, 122); //category_id
					addEmp.addBatch(); 
				}
				addEmp.executeBatch();
				con.commit();
			}
			addEmp.executeBatch();
			con.commit();
			long etime = System.currentTimeMillis();
			System.out.println("used:" + (etime-ctime) +" ms");
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
	}		
	
	
	private static SimpleOrders[] GenSimpleOrderData(Connection con, int maxcount) {
		SimpleOrders[] orderdata = null;
		
		int[] customerArray = GetBaseDataIdArray(con, "CUSTOMER", "ID");
		
		Random rand = new Random(System.currentTimeMillis());
		AtomicInteger increaseNum = new AtomicInteger(3000);
		try {
			
			List<Goods>  goodsList = GenerateOrders.QueryAllGoods(con);
			if (goodsList==null ||goodsList.size() ==0) {
				throw new DataException("No GoodsData");
			}
			
			orderdata = new SimpleOrders[maxcount];
			for (int i=0;i <maxcount; i++ ) {
				SimpleOrders order = new SimpleOrders();
				
				final int goodsInd = rand.nextInt(goodsList.size());
				final Goods selectedGoods = goodsList.get(goodsInd);
				order.setGoods_id(selectedGoods.getId());
				order.setPrice(selectedGoods.getPrice());
				order.setPayed_amount(selectedGoods.getPrice());
				order.setCategory_id(selectedGoods.getCategory_id());
				order.setStore_id(1);
				final int custInd = ThreadLocalRandom.current().nextInt(customerArray.length);
				order.setCustomer_id(customerArray[custInd]);
				order.setStatus(1);
				orderdata[i]= order;	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return orderdata;
	}
	
	public static void GenerateSimpleOrders(Connection con) {

		String addString = "insert simpleorders (`store_id`,`goods_id`,`goods_name`,`price`,`payed_amount`,`customer_id`,`create_time`,`category_id`,`status`) "
				+ " VALUES 	( ?,?,?,?,?,?,?,?,?)";

		int[] customerArray = GetBaseDataIdArray(con, "CUSTOMER", "ID");
		int[] goodsArray = GetBaseDataIdArray(con, "GOODS", "ID");
		Random rand = new Random(System.currentTimeMillis());
		Random rand1 = new Random(System.currentTimeMillis()+1_000_000);
		AtomicInteger increaseNum = new AtomicInteger(3000);
		long ctime = System.currentTimeMillis();
		try (PreparedStatement addEmp = con.prepareStatement(addString)) {
			con.setAutoCommit(false);
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j< 10_000; j++) {
					final int randomNum = increaseNum.getAndIncrement();
					final int custInd = ThreadLocalRandom.current().nextInt(customerArray.length);
					
					addEmp.setInt(1, 1); //store_id
					final int goodsInd = rand.nextInt(goodsArray.length);
					addEmp.setInt(2, goodsArray[goodsInd]); //goods_id --
					addEmp.setString(3, ""); //goods_name
					addEmp.setDouble(4, 1.0); //price
					addEmp.setDouble(5, 1.0); //payed_amount
					addEmp.setInt(6, customerArray[custInd]); //customer_id
								
					addEmp.setTimestamp(7, Timestamp.valueOf("2021-03-02 09:01:16")); //creator_time
					addEmp.setInt(8, 122); //category_id
					addEmp.setInt(9, 1); //status
					addEmp.addBatch(); 
				}
				addEmp.executeBatch();
				con.commit();
			}
			addEmp.executeBatch();
			con.commit();
			long etime = System.currentTimeMillis();
			System.out.println("used:" + (etime-ctime) +" ms");
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
	}		

	public static void GenerateSimpleOrdersByPreparedData(Connection con) {

		String addString = "insert simpleorders (`store_id`,`goods_id`,`goods_name`,`price`,`payed_amount`,`customer_id`,`create_time`,`category_id`,`status`) "
				+ " VALUES 	( ?,?,?,?,?,?,?,?,?)";

		int[] customerArray = GetBaseDataIdArray(con, "CUSTOMER", "ID");
		List<Goods> goodsList = QueryAllGoods(con);
		Random rand = new Random(System.currentTimeMillis());
		Random rand1 = new Random(System.currentTimeMillis()+1_000_000);
		AtomicInteger increaseNum = new AtomicInteger(4000);
		long ctime = System.currentTimeMillis();
		try (PreparedStatement addEmp = con.prepareStatement(addString)) {
			con.setAutoCommit(false);
  
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j< 10_000; j++) {
					//final int randomNum = increaseNum.getAndIncrement();
					final int custInd = ThreadLocalRandom.current().nextInt(customerArray.length);
					
					addEmp.setInt(1, 1); //store_id
					final int goodsInd = rand.nextInt(goodsList.size());
					Goods goods = goodsList.get(goodsInd);
					addEmp.setLong(2, goods.getId()); //goods_id --
					addEmp.setString(3, goods.getGoods_name()); //goods_name
					addEmp.setDouble(4, goods.getPrice()); //price
					
					addEmp.setDouble(5, goods.getPrice()*goods.getDiscount()); //payed_amount
					addEmp.setInt(6, customerArray[custInd]); //customer_id
								
					addEmp.setTimestamp(7, Timestamp.valueOf("2021-03-02 09:01:16")); //creator_time
					addEmp.setInt(8, goods.getCategory_id()); //category_id
					addEmp.setInt(9, 1); //status
					addEmp.addBatch(); 
				}
				addEmp.executeBatch();
				con.commit();
			}
//			addEmp.executeBatch();
//			con.commit();
			long etime = System.currentTimeMillis();
			System.out.println("used:" + (etime-ctime) +" ms");
		} catch (SQLException e) {
			JdbcUtil.printSQLException(e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					JdbcUtil.printSQLException(excep);
				}
			}
		}
	}	
	
	
	public static void main(String[] args) throws Exception {

		Connection con = ConnectionFactory.getConnection();
		//Connection con = HikariCPDataSource.getConnection();
		//GenerateOrders.GenerateEmployee(con);
		//GenerateOrders.GenerateCategory(con);
		//GenerateOrders.GenerateGoods(con);
		//GenerateOrders.GenerateCustomer(con);
		//GenerateOrders.GenerateOrderDetails(con);
	
		// GenerateOrders.GenerateSimpleOrders(con); 
		
		GenerateOrders.GenerateSimpleOrdersByPreparedData(con);
	}

}
