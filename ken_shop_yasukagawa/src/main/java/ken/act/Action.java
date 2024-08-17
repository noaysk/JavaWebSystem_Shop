package ken.act;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
	abstract public String execute(HttpServletRequest req) throws Exception;
}