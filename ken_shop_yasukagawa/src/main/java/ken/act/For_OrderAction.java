package ken.act;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;

public class For_OrderAction extends Action {
	@Override
	public String execute(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		// 1. セッションオブジェクトを取得する。その際、引数には false を設定する
		HttpSession session = req.getSession(false);

		// 2. もし、セッションオブジェクトが null の場合 "/irregular_error.jsp" というデータを戻り値として返す
		if (session == null) {
			System.out.println("Action");
			return "/irregular_error.jsp";
		}

		// 3. 1. で取得したセッションオブジェクトから、"cart" というキーで登録されているデータを取得する。
		//	 "cart" で登録されているデータは ArrayList<Item> 型なので、ArrayList<Item> 型の変数で戻り値を受け取る
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		// 4. もし、2. で受け取った ArrayList 型の変数が null か、もしくは要素数が 0 だった場合
		if (cart == null || cart.size() == 0) {
			// 4.1 "/error.jsp" という文字列を戻り値として返す
			return "/error.jsp";
		}
		// false の場合
		// 5.1 "/order.jsp" という文字列を戻り値として返す
		return "/order.jsp";
	}
}