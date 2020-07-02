package it.enaip.corso.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class DaoArticles implements ArticlesDao {
	
	private DaoArticles() {
	}

	private static class SingletonHelper {
		private static final DaoArticles INSTANCE = new DaoArticles();
	}

	public static DaoArticles getInstance() {
		return SingletonHelper.INSTANCE;
	}

	@Override
	public Optional<Articles> find(String id) throws SQLException {

		String sql = "SELECT articles_id,name, description, quantity, location FROM articles WHERE articles_id=? ";
		int id_articles = 0, quantity = 0;
		String name = "", description = "", location = "";
		Connection conn = DbConnect.getInstance().getConnection();

		PreparedStatement statement= conn.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			id_articles = resultSet.getInt("articles_id");
			name = resultSet.getString("name");
			description = resultSet.getString("description");
			quantity = resultSet.getInt("quantity");
			location = resultSet.getString("location");
		}
		return Optional.of(new Articles(id_articles, name, description, quantity, location));
	}

	@Override
	public List<Articles> findAll() throws SQLException {
		List<Articles> listArticles = new ArrayList<>();
		String sql = "SELECT articles_id,name,description,quantity,location FROM articles";

		Connection conn = DbConnect.getInstance().getConnection();
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("articles_id");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			int quantity = resultSet.getInt("quantity");
			String location = resultSet.getString("location");

			Articles articles = new Articles(id, name, description, quantity, location);
			listArticles.add(articles);
		}
		return listArticles;
	}

	@Override
	public boolean save(Articles articles) throws SQLException {

		String sql = "INSERT into articles (name, description,quantity,location) VALUES (?,?,?,?)";
		boolean rowInserted = false;
		
		Connection conn = DbConnect.getInstance().getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, articles.getName());
		statement.setString(2, articles.getDescription());
		statement.setInt(3, articles.getQuantity());
		statement.setString(4, articles.getLocation());
		rowInserted = statement.executeUpdate() > 0;
		return rowInserted;
	}

	@Override
	public boolean update(Articles articles) throws SQLException {
		String sql = "UPDATE articles SET name = ?, description= ?, quantity= ?, location= ?";
		sql += "WHERE articles_id =?";
		boolean rowUpdateted = false;
		Connection conn = DbConnect.getInstance().getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, articles.getName());
		statement.setString(2, articles.getDescription());
		statement.setInt(3, articles.getQuantity());
		statement.setString(4, articles.getLocation());
		statement.setInt(5, articles.getId());
		rowUpdateted = statement.executeUpdate() > 0;

		return rowUpdateted;
	}

	@Override
	public boolean delete(Articles articles) throws SQLException {

		String sql = "DELETE FROM articles where articles_id = ?";
		boolean rowDeleted = false;

		Connection conn = DbConnect.getInstance().getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, articles.getId());
		rowDeleted = statement.executeUpdate() > 0;

		return rowDeleted;
	}
}