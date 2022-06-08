package com.pepcus.PerformanceOptimizedCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pepcus.crud.entity.UserCollection;

public class UserHashMap {
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres",
				"pepcus123")) {

			System.out.println("Java JDBC PostgreSQL Example");

			System.out.println("Connected to PostgreSQL database!");
			Statement statement = connection.createStatement();
			System.out.println("Reading user records...");

			ResultSet resultSet = statement.executeQuery("SELECT * FROM myschema.user");

			 Map<Integer, UserCollection> hashMap = new HashMap<>();

			while (resultSet.next()) {
				hashMap.put(resultSet.getInt(1),new UserCollection(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5),resultSet.getString(6)));

			}

			
			// search operation
			System.out.println("====start list===" + hashMap.get(10));
			System.out.println("====mid list===" + hashMap.get(100000));

			// insertion operation
			System.out.println("===inserting in last====" + hashMap.put(4,new UserCollection(1, "hh", 34, "aadar123", "8888", "m")));
			
			// delete operation
			System.out.println("===delete====" + hashMap.remove(10));
		

			// modified operation
			UserCollection value = hashMap.replace(2, new UserCollection(2, "hh", 34, "aadar123", "8888", "m"));
			System.out.println("=============="+value.getName());


		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}
