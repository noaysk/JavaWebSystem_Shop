package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;
import ken.dao.SearchDAO;

public class SearchAction extends Action{
    @Override
	public String execute(HttpServletRequest req) throws Exception {
		// 1. 引数で受け取ったリクエストオブジェクトから、"keyword" というキーのリクエストパラメータを受け取る
		String keyword = req.getParameter("keyword");

		// 2. 引数で受け取ったリクエストオブジェクトから、"genre" というキーのリクエストパラメータを受け取る
		String genre = req.getParameter("genre");

		// 3. SearchDAO クラスのオブジェクトを生成する
		SearchDAO dao = new SearchDAO();

		// 4. SearchDAO の search_table メソッドを呼び出し、検索
		// 1. 2. で取得した文字列を引数に渡し、戻り値として、検索結果が格納された ArrayList<Item> クラスオブジェクトを受け取る
		ArrayList<Item> items = dao.search_table(keyword, genre);

		// 5. セッションオブジェクトを生成する
		HttpSession session = req.getSession();

		// 6.  setAttribute() メソッドを呼び出し、"table_items" というキーで、4. の戻り値で受け取った ArrayList<Item> クラスオブジェクトを登録
		session.setAttribute("table_items", items);

		// 7. の要素数が0の場合
		if (items.isEmpty()) {
			// 7.1  setAttribute() メソッドを呼び出し、"no_item" というキーで ""(空文字)を値として登録する
			req.setAttribute("no_item", "");
		}

		// 8. "/top.jsp" という文字列を戻り値として返す
		return "/top.jsp";
	}
}
