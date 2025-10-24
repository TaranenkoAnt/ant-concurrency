package ru.spb.taranenkoant.concurrency.chapter05;


import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 24.10.2025
 */
public class Factorizer implements Servlet {

    private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] compute(BigInteger arg) throws InterruptedException {
            return factor(arg);
        }
    };

    private BigInteger[] factor(BigInteger arg) {
        return new BigInteger[]{new BigInteger("2")};
    }

    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(c);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            BigInteger i = extractFromRequest(servletRequest);
            encodeIntoResponse(servletResponse, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(servletResponse, "Факторизация прервана");
        }
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return new BigInteger("1");
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] compute) {

    }

    private void encodeError(ServletResponse servletResponse, String mess) {

    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
