package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;
import ken.bean.User;
import ken.dao.OrderDAO;

public class OrderAction extends Action {
	@Override
	public String execute(HttpServletRequest req) throws Exception {
		// 1. リクエストパラメータの値を取得し、ローカル変数に格納する
		String name = req.getParameter("name");
		String kana_name = req.getParameter("kana_name");
		String postcode = req.getParameter("postcode");
		String address = req.getParameter("address");
		String tel = req.getParameter("tel");
		String mail = req.getParameter("mail");

		// 2. User クラスのオブジェクトを生成し、セッターメソッドを用いて 1. で受けとった String 型のデータを格納
		User user = new User();
		user.setUserName(name);
		user.setUserName_kana(kana_name);
		user.setPostCode(postcode);
		user.setAddress(address);
		user.setPhoneNumber(tel);
		user.setMailAddress(mail);

		// 3. セッションオブジェクトを取得する。その際、引数には false を設定する
		HttpSession session = req.getSession(false);

		// 4. もし、3. で取得したセッションオブジェクトが null の場合
		 if (session == null) {
	            // 4.1 "/irregular_error.jsp" を戻り値として返す
			 System.out.println("OrderAction");
	            return "/irregular_error.jsp";
	        }
		 // 5. 3. で取得したセッションオブジェクトから "cart" というキーで登録されているデータを取り出して、ArrayList<Item> 型のローカル変数に格納する
		ArrayList<Item> items = (ArrayList<Item>)session.getAttribute("cart");

		// 6. OrderDAO クラスのオブジェクトを生成する
		OrderDAO odao = new OrderDAO();

		// 7. 6. で生成した OrderDAO クラスオブジェクトの orderRegistration() メソッドを呼び出し、オーダー情報をデータベースに登録する。
		//	 その際に、第一引数として 2. で生成した User クラスオブジェクトを、第二引数として 5. で取得した ArrayList<Item> クラスオブジェクトを渡す
		odao.orderRegistration(user,items);

		// 8. セッション情報を全て破棄する
		session.invalidate();

		// 9. "/finish.jsp" という文字列を戻り値として返す
		return "/finish.jsp";
	}
}