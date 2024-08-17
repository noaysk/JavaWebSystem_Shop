package ken.act;

import javax.servlet.http.HttpServletRequest;

public class TopAction extends Action {

	@Override
	public String execute(HttpServletRequest req) throws Exception {
		// 1. "/top.jsp" という文字列を戻り値として返す
		return "/top.jsp";
	}
}