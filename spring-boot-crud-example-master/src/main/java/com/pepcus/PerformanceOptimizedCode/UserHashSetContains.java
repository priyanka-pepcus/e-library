package com.pepcus.PerformanceOptimizedCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pepcus.crud.entity.UserCollection;

public class UserHashSetContains {
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres",
				"pepcus123")) {

			System.out.println("Java JDBC PostgreSQL Example");

			System.out.println("Connected to PostgreSQL database!");
			Statement statement = connection.createStatement();
			System.out.println("Reading user records...");

			ResultSet resultSet = statement.executeQuery("SELECT * FROM myschema.user");

			Set<UserCollection> set = new HashSet<>();

			while (resultSet.next()) {
				set.add(new UserCollection(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));

			}
			int setSize = set.size();
			// insert in last
			set.add(new UserCollection(setSize + 1, "hh", 34, "aadar123", "8888", "m"));

			for (UserCollection userCollection : set) {

				if (userCollection.getId() == 1000000) {
					System.out.println("==search==" + set.contains(userCollection));

				}

			}

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}
