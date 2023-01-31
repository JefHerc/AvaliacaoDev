package br.com.soc.sistema.infra;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        if (session.containsKey("user")) {
            // User is logged in, continue with the action
            return invocation.invoke();
        } else {
            // User is not logged in, redirect to login page
            return "loginError";
        }
	}

}
