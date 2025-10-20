package ru.spb.taranenkoant.concurrency.chapter02;


import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 20.10.2025
 */
public class CachedFactorizer implements Servlet  {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
       BigInteger i = extractFromRequest(servletRequest);
       BigInteger[] factors = null;
       synchronized (this) {
           ++hits;
           if (i.equals(lastNumber)) {
               ++cacheHits;
               factors = lastFactors.clone();
           }
       }

       if (factors == null) {
           factors = factor(i);
           synchronized (this) {
               lastNumber = i;
               lastFactors = factors.clone();
           }
           encodeIntoResponse(servletResponse, factors);
       }
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return null;
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {

    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }



    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
