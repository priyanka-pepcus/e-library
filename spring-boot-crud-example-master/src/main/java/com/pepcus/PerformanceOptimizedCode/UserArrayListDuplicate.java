package com.pepcus.PerformanceOptimizedCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.mysql.cj.xdevapi.Collection;
import com.pepcus.crud.entity.UserCollection;

public class UserArrayListDuplicate {
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres",
				"pepcus123")) {

			System.out.println("Java JDBC PostgreSQL Example");

			System.out.println("Connected to PostgreSQL database!");
			Statement statement = connection.createStatement();
			System.out.println("Reading user records...");

			ResultSet resultSet = statement.executeQuery("SELECT * FROM myschema.user");

			
	       
	        ArrayList<UserCollection> dataList = new ArrayList<>();
			while (resultSet.next()) {
				dataList.add(new UserCollection(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));

			}
			dataList.add(new UserCollection(1, "hh", 34, "aadar123", "8888", "m"));
			dataList.add(new UserCollection(11, "hh", 34, "aadar123", "8888", "m"));
			dataList.add(new UserCollection(2, "ss", 34, "aadar123", "8888", "m"));
			dataList.add(new UserCollection(4, "ss", 34, "aadar123", "8888", "m"));
			Set<UserCollection> set = new HashSet<>();
			Set<UserCollection> uniqueObject = Collections.synchronizedSet(set);
			for (UserCollection userCollection : dataList) {
				  System.out.println("outside iffffffffffffffff");
				
				   if (uniqueObject.add(userCollection) == false) {
					   System.out.println("inside iffffffffffffffff");
		               System.out.println("====Duplicate Object is===="+userCollection.getName());
		            }
				   
			      
			}

		}  catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
	}
