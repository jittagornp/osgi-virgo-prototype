/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.osgi.prototype.central.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author anonymous
 */
public class SpringUtils {

    public static <T> T getBean(Class<T> clazz) {
        ServletContext servletContext = FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getServletContext();
        return getBean(servletContext, clazz);
    }

    public static Object getBean(String bean) {
        ServletContext servletContext = FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getServletContext();
        return getBean(servletContext, bean);
    }

    public static <T> T getBean(ServletContext servletContext, Class<T> clazz) {
        return WebApplicationContextUtils
                .getWebApplicationContext(servletContext)
                .getBean(clazz);
    }

    public static Object getBean(ServletContext servletContext, String bean) {
        return WebApplicationContextUtils
                .getWebApplicationContext(servletContext)
                .getBean(bean);
    }
}
