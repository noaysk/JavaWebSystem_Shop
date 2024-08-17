package ken.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ken.bean.Item;

public class SearchDAO {
	// フィールド変数
	private Connection connection;
	private PreparedStatement p_statement_selectItems_no_key;
	private PreparedStatement p_statement_selectItems;

	public SearchDAO() throws ClassNotFoundException, SQLException {
		//MySQLのJDBCドライバで接続
		Class.forName("com.mysql.jdbc.Driver");

		// 1. String 型の変数を作成し、"jdbc:mysql://localhost:3306/latte_station" を代入
		String url = "jdbc:mysql://localhost:3306/latte_station";
		// 2. String 型の変数を作成し、"root" を代入する
		String user = "root";
		// 3. String 型の変数を作成し、"root" を代入する
		String password = "root";

		// 4. DriverManager クラスのクラスメソッドである getConnection() メソッドを呼び出す。
		//	その際に、第一引数として 1. で作成した変数を、第二引数として 2. で作成した変数を、第三引数として 3. で作成した変数を設定し、戻り値をフィールド変数 connection に格納
		connection = DriverManager.getConnection(url, user, password);

		// 5. String 型の変数を作成し、"SELECT * FROM latte_station.item WHERE genre_id=?" を代入する
		String sql_selectItems_no_key = "SELECT * FROM latte_station.item WHERE genre_id=?";

		// 6. String 型の変数を作成し、"SELECT * FROM latte_station.item WHERE genre_id=? and (item_name like ? or artist like ?)" を代入する
		String sql_selectItems = "SELECT * FROM latte_station.item WHERE genre_id=? and (item_name like ? or artist like ?)";

		// 7. フィールド変数 connection が参照しているオブジェクトの prepareStatement() メソッドを呼び出す。
		//	 その際に、引数として 5. で作成した変数を設定し、戻り値をフィールド変数 p_statement_selectItems_no_key に格納する
		p_statement_selectItems_no_key = connection.prepareStatement(sql_selectItems_no_key);

		// 8. フィールド変数 connection が参照しているオブジェクトの prepareStatement() メソッドを呼び出す。
		//	 その際に、引数として 6. で作成した変数を設定し、戻り値をフィールド変数 p_statement_selectItems に格納する
		p_statement_selectItems = connection.prepareStatement(sql_selectItems);
	}

	public ArrayList<Item> search_table(String key, String genre) throws SQLException {

		// 1/ ローカル変数として ResultSet 型の変数 rs_items を宣言すると同時に null で初期化する
		ResultSet rs_items = null;

		// 4. ローカル変数として ArrayList<Item> 型の変数 list を宣言すると同時にオブジェクトを生成して代入する
		ArrayList<Item> list = new ArrayList<>();

		// 2. もし、第一引数の String 型の変数が ""(空文字) ではなかった場合
		if (!key.isEmpty()) {
			// 2.1 2.1 フィールド変数 p_statement_selectItems の setString() メソッドを呼び出す。その際、第一引数として 1、第二引数としてこのメソッドの第二引数を渡する
			p_statement_selectItems.setString(1, genre);

			// 2.2 フィールド変数 p_statement_selectItems の setString() メソッドを呼び出す。その際、第一引数として 2、第二引数として、このメソッドの第一引数を渡す。第二引数の前後には "%" を文字列結合する (例： "%" + key + "%")
			p_statement_selectItems.setString(2, "%" + key + "%");

			// 2.3 フィールド変数 p_statement_selectItems の setString()メソッドを呼び出す。その際、第一引数として 3、第二引数として、このメソッドの第一引数を渡す。第二引数の前後には "%" を文字列結合する (例： "%" + key + "%")
			p_statement_selectItems.setString(3, "%" + key + "%");

			// 2.4 フィールド変数 p_statement_selectItems の executeQuery() メソッドを呼び出し、戻り値をローカル変数 rs_items に格納する
			rs_items = p_statement_selectItems.executeQuery();
		}

		// 3.  false だった場合
		else {
			// 3.1 フィールド変数 p_statement_selectItems_no_key の setString() メソッドを呼び出す。その際に、第一引数として 1、第二引数としてこのメソッドの第二引数を渡す
			p_statement_selectItems_no_key.setString(1, genre);

			// 3.2 フィールド変数 p_statement_selectItems_no_key の executeQuery() メソッドを呼び出す。戻り値をローカル変数 rs_items に格納する
			rs_items = p_statement_selectItems_no_key.executeQuery();
		}

		// 5. ローカル変数 rs_items の next() メソッドの結果が true な限り
		while (rs_items != null && rs_items.next()) {

			// 5.1 Itemクラスのオブジェクトを生成する
			Item item = new Item();

			// 5.2~5.6 5.1 で生成したオブジェクトの setter メソッドを呼び出す。その際、引数としてローカル変数rs_items の getter メソッドの戻り値を渡す
			item.setItemID(rs_items.getInt("item_id"));
			item.setItemName(rs_items.getString("item_name"));
			item.setItemArtist(rs_items.getString("artist"));
			item.setItemPrice(rs_items.getInt("price"));
			item.setItemImage(rs_items.getString("item_img"));

			// 5.7 ローカル変数 list の add() メソッドを呼び出す。その際に、引数として 5.1 で生成したオブジェクトを渡す。
			list.add(item);
		}

		// 6. ローカル変数 rs_items が null ではない場合
		if (rs_items != null) {
			// 6.1 ローカル変数 rs_items を close() メソッドを使って閉じる
			rs_items.close();
		}
		// 7. フィールド変数 connection が null ではない場合
		if (connection != null) {
			//7.1 フィールド変数 connection をclose() メソッドを使って閉じる
			connection.close();
		}

		// 8. ローカル変数 list を戻り値として返す
		return list;
	}
}
