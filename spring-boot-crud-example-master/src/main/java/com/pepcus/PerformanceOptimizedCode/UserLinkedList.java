package com.pepcus.PerformanceOptimizedCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.pepcus.crud.entity.UserCollection;

public class UserLinkedList {
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres",
				"pepcus123")) {

			System.out.println("Java JDBC PostgreSQL Example");

			System.out.println("Connected to PostgreSQL database!");
			Statement statement = connection.createStatement();
			System.out.println("Reading user records...");

			ResultSet resultSet = statement.executeQuery("SELECT * FROM myschema.user");

			List<UserCollection> list = new LinkedList<>();

			while (resultSet.next()) {
				list.add(new UserCollection(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5),resultSet.getString(6)));

			}

			for (UserCollection userCollection : list) {
				System.out.println("========userCollection===" + userCollection.getId());

			}

			// search operation
			System.out.println("====start list===" + list.get(10));
			System.out.println("====mid list===" + list.get(100000));

			// insertion operation
			System.out.println(
					"===inserting in last====" + list.add(new UserCollection(1, "hh", 34, "aadar123", "8888", "m")));
			list.add(100000, new UserCollection(1, "hh", 34, "aadar123", "8888", "m"));
			System.out.println("===serching====" + list.get(100000));

			// delete operation
			System.out.println("===delete====" + list.remove(10));
			System.out.println("===serching====" + list.get(10));
			System.out.println("===delete====" + list.remove(10));

			// modified operation
			System.out.println("===delete====" + list.get(1));
			System.out.println(list.set(1, new UserCollection(1, "hh", 34, "aadar123", "8888", "m")));
			System.out.println("===modified====" + list.get(1));

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}
