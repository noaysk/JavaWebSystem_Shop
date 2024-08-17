package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;

public class AddAction extends Action{
	@Override
	public String execute(HttpServletRequest req) throws Exception {
		// 1. リクエストオブジェクトの getParameter() メソッドを呼び出し、"id"、"title"、"create"、"price"の 4 つリクエストパラメータの情報を取得し、それぞれ String 型のローカル変数に格納する
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String create = req.getParameter("create");
		String price = req.getParameter("price");

		// 2. Item オブジェクトの生成 パラメータの格納
		Item item = new Item();

		item.setItemID(Integer.parseInt(id));
		item.setItemName(title);
		item.setItemArtist(create);
		item.setItemPrice(Integer.parseInt(price));

		// 3. セッションオブジェクトを取得
		HttpSession session = req.getSession();

		// 4. セッションから "cart" というキーを取得、 ArrayList<Item> クラスオブジェクトを変数に格納
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		// 5. "cart" が null の場合、ArrayList<Item> を生成
		if (cart == null) {
			cart = new ArrayList<>();
		}

		// 6. ArrayList<Item> に Item オブジェクトを登録
		cart.add(item);

		// 7. セッションに "cart" というキーで ArrayList<Item> を登録
		session.setAttribute("cart", cart);

		// 8. "/top.jsp" という文字列を戻り値として返す
		return "/top.jsp";
	}
}
