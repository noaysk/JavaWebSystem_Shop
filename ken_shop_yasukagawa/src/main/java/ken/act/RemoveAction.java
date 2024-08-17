package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;

public class RemoveAction  extends Action {
	@Override
	public String execute(HttpServletRequest req) throws Exception {
		// 1. セッションオブジェクトを取得
		HttpSession session = req.getSession(false);

		// 2. セッションオブジェクトが null の場合 "/irregular_error.jsp" というデータを戻り値として返す
		if (session == null) {
			System.out.println("RemoveAction");
			return "/irregular_error.jsp";
		}

		// 3. セッションオブジェクトから、"cart"というキーで登録されているデータを取得する。
		//"cart" で登録されているデータは ArrayList<Item> 型なので、ArrayList<Item> 型の変数で戻り値を受け取る
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		// 4. リクエストオブジェクトから、"remove" というキーのリクエストパラメータを取得
		String test = req.getParameter("remove");

		// 5.  remove() メソッドを呼び出す。その際に、数値を引数として渡す
		cart.remove(Integer.parseInt(test));

		// 6. "/cart.jsp" という文字列を戻り値として返す
		return "/cart.jsp";
	}
}
