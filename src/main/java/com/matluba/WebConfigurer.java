package com.matluba;

import com.matluba.filter.CachingHttpHeadersFilter;
import com.matluba.filter.gzip.GZipServletFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class WebConfigurer implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Autowired
    private Environment env;


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);
         if (env.acceptsProfiles("prod")) {
            initCachingHttpHeadersFilter(servletContext, disps);
//            initStaticResourcesProductionFilter(servletContext, disps);
        }
        initGzipFilter(servletContext, disps);
        log.info("Web application fully configured");
    }



    /**
     * Initializes the GZip filter.
     */
    private void initGzipFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
        log.debug("Registering GZip Filter");
        FilterRegistration.Dynamic compressingFilter = servletContext.addFilter("gzipFilter", new GZipServletFilter());
        Map<String, String> parameters = new HashMap<>();
        compressingFilter.setInitParameters(parameters);
        compressingFilter.addMappingForUrlPatterns(disps, true, "*.css");
        compressingFilter.addMappingForUrlPatterns(disps, true, "*.json");
        compressingFilter.addMappingForUrlPatterns(disps, true, "*.html");
        compressingFilter.addMappingForUrlPatterns(disps, true, "*.js");
        compressingFilter.setAsyncSupported(true);
    }

//    /**
//     * Initializes the static resources production Filter.
//     */
//    private void initStaticResourcesProductionFilter(ServletContext servletContext,
//                                                     EnumSet<DispatcherType> disps) {
//
//        log.debug("Registering static resources production Filter");
//        FilterRegistration.Dynamic staticResourcesProductionFilter =
//                servletContext.addFilter("staticResourcesProductionFilter",
//                        new StaticResourcesProductionFilter());
//
//        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/");
//        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/index.html");
//        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/images/*");
//        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/fonts/*");
//        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
//        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/styles/*");
//        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/views/*");
//        staticResourcesProductionFilter.setAsyncSupported(true);
//    }

    /**
     * Initializes the cachig HTTP Headers Filter.
     */
    private void initCachingHttpHeadersFilter(ServletContext servletContext,
                                              EnumSet<DispatcherType> disps) {
        log.debug("Registering Cachig HTTP Headers Filter");
        FilterRegistration.Dynamic cachingHttpHeadersFilter =
                servletContext.addFilter("cachingHttpHeadersFilter",
                        new CachingHttpHeadersFilter());

        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/img/*");
        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/fonts/*");
        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/css/*");
        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/js/*");
        cachingHttpHeadersFilter.setAsyncSupported(true);
    }

}
