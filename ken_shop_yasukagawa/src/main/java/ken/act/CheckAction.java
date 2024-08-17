package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;

public class CheckAction extends Action {
	@Override
	public String execute(HttpServletRequest req) throws Exception {
		// 1. セッションオブジェクトを取得
		HttpSession session = req.getSession(false);

		// 2. もし、セッションオブジェクトが null の場合 "/irregular_error.jsp" というデータを戻り値として返す
		if (session == null) {
			System.out.println("CheckAction");
			return "/irregular_error.jsp";
		}

		// 3. セッションオブジェクトから、"cart" というキーで登録されているデータを取得
		// ArrayList<Item> 型なので、ArrayList<Item> 型の変数で戻り値を受け取る
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		// 4. もし、 ArrayList<Item> クラスの変数が null か、もしくは要素数が 0 だった場合 "/error.jsp" というデータを戻り値として返す
		if (cart == null || cart.size() == 0) {
			return "/error.jsp";
		}

		// 5. "/cart.jsp" というデータを戻り値として返す
		return "/cart.jsp";
	}
}
