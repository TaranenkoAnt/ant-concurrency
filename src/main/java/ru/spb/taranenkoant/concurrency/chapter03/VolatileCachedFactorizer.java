package ru.spb.taranenkoant.concurrency.chapter03;


import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 21.10.2025
 */
public class VolatileCachedFactorizer implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null, null);
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
            encodeIntoResponse(servletResponse, factors);
        }
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return null;
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] factors) {

    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
