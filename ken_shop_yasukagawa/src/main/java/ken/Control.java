package ken;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ken.act.Action;

@WebServlet("/kenshop")
public class Control extends HttpServlet {
	private ServletConfig config = null;
	private ServletContext context = null;
    @Override
	public void init() {
		// 1. フィールドの config に、このクラスの getServletConfig() メソッドの戻り値を代入する
		this.config = getServletConfig();

		// 2. フィールドの context に、フィールドの config が参照しているオブジェクトの getServletContext()メソッドの戻り値を代入する
		this.context = config.getServletContext();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1.
		String actName = ""; //リクエストパラメータ"act"の値
		String actionClassName = ""; //Actionクラスの名前
		Action action = null; //Actionクラスのオブジェクト
		String forwardJSP = ""; //フォワード先のJSP名
		RequestDispatcher rd = null; //ディスパッチャオブジェクト

		try {
			// 2. 引数で受け取ったリクエストオブジェクトの setCharacterEncoding() メソッドを呼び出し、文字エンコーディングを行う
			req.setCharacterEncoding("UTF-8");

			// 3. フィールドの変数 contextが参照しているオブジェクトの getRealPath() メソッドを呼び出す。その際、引数に "/" という文字列を渡し、コンテキストまでの物理パスを表す文字列を戻り値として受け取る
			String realPath = this.context.getRealPath("/");

			// 4. 3. で受け取った文字列と、"/WEB-INF/action.properties" という文字列を結合する
			// 5. 4. で作成した文字列を引数に、FileInputStream クラスのオブジェクトを生成する
			FileInputStream stream = new FileInputStream(realPath + "/WEB-INF/action.properties");

			// 7. 6. で生成したProperties クラスオブジェクトの load() メソッド呼び出す。その際に、引数としてに 5. で生成した FileInputStream 型オブジェクトを渡す
			Properties props = new Properties();
			props.load(stream);

			// 8. 引数で受け取っているリクエストオブジェクトから、"act" というキーのリクエストパラメータを取得し、1. で宣言した変数 actName に代入する
			actName = req.getParameter("act");
			//System.out.println("actName : " + actName);
			// 9. もし、変数 actName が null ではない場合
			if (actName != null) {

				// 9.1 6. で生成した Properties クラスオブジェクトの getProperty() メソッドを呼び出す。その際、引数として変数 actName を渡す。呼び出したら戻り値を変数 actionClassName に格納する
				actionClassName = props.getProperty(actName);
			}

			// 10. もし、9. の結果が false の場合
			else {
				// 10.1 6. で生成した Properties クラスオブジェクトの getProperty() メソッドを呼び出す。その際、引数には "top" という文字列を渡す。呼び出したら戻り値を変数 actionClassName に格納
				actionClassName = props.getProperty("top");
			}

			//System.out.println("actionClassName : " + actionClassName);
			/*debag control */
			System.out.println("debag control act = " + actName);

			// 11. Class クラスの static メソッドである forName() メソッドを呼び出す。その際に、引数として 1. で宣言した変数 actionClassName を渡し、戻り値を Class<?> 型変数 actionClass に格納
			Class actionClass = Class.forName(actionClassName);

			// 12. 変数 actionClassが参照しているオブジェクトの newInstance() メソッドを呼び出し、戻り値を Action 型の変数に代入する (キャストが必要)
			action = (Action) actionClass.newInstance();

			// 13. 変数 action が参照しているオブジェクトの execute() メソッドを呼び出す。その際、引数で受け取ったリクエストオブジェクトを引数として渡し、1. で宣言した変数 forwardJSP に代入する
			forwardJSP = action.execute(req);
			//System.out.println("req : " + req);
			// 14. もし、変数 forwardJSP が null でなく、かつ変数 forwardJSP が参照している文字列が ""(空文字) ではない場合
			if (forwardJSP != null) {
				// 14.1 変数 context の getRequestDispatcher() メソッドを呼び出す。その際、引数に変数 forwardJSP を渡し、戻り値を 1. で宣言した変数 rd に格納
				rd = context.getRequestDispatcher(forwardJSP);
			}
			// 15. false の場合
			else {
				// 15.1 引数に文字列 "/top.jsp" を渡して変数 context の getRequestDispatcher() メソッドを呼び出し、戻り値を 1. で宣言した変数 rd に格納
				rd = context.getRequestDispatcher("/top.jsp");
			}
			// 16. 上記の処理 (3 ～ 15) を try 句で囲み、Exception 例外を受け取る。catch 句には以下の処理を記述する。
		} catch (Exception e) {
			// 16.1 例外クラスの printStackTrace() メソッドを呼び出す
			e.printStackTrace();
			// 16.2 変数 context の getRequestDispatcher() メソッドを呼び出す。その際、引数として "/irregular_error.jsp" を渡し、戻り値を 1. で宣言した変数 rd に代入
			System.out.println("Control");
			rd = context.getRequestDispatcher("/irregular_error.jsp");
		}
		// 15.変数 rd の forward() ソッドを呼び出し、JSP に遷移
		rd.forward(req, res);
	}
}