/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.spring5.util;

import org.springframework.core.SpringVersion;
import org.thymeleaf.util.ClassLoaderUtils;

/**
 * <p>
 *   Utility class useful for determining the version of Spring that is on the classpath.
 * </p>
 * 
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.3
 *
 */
public final class SpringVersionUtils {


    private static final int SPRING_VERSION_MAJOR;
    private static final int SPRING_VERSION_MINOR;

    private static final boolean SPRING_WEB_MVC_PRESENT;
    private static final boolean SPRING_WEB_REACTIVE_PRESENT;



    static {

        String springVersion = SpringVersion.getVersion();

        // There might be times when SpringVersion cannot determine the version due to CL restrictions (see doc)
        if (springVersion != null) {

            try {

                String versionRemainder = springVersion;

                int separatorIdx = versionRemainder.indexOf('.');
                SPRING_VERSION_MAJOR = Integer.parseInt(versionRemainder.substring(0, separatorIdx));

                int separator2Idx = versionRemainder.indexOf('.', separatorIdx + 1);
                SPRING_VERSION_MINOR = Integer.parseInt(versionRemainder.substring(separatorIdx + 1, separator2Idx));

            } catch (final Exception e) {
                throw new ExceptionInInitializerError(
                        "Exception during initialization of Spring versioning utilities. Identified Spring " +
                        "version is '" + springVersion + "', which does not follow the {major}.{minor}.{...} scheme");
            }

        } else {

            if (ClassLoaderUtils.isClassPresent("org.springframework.core.io.buffer.DataBuffer")) {
                SPRING_VERSION_MAJOR = 5;
                SPRING_VERSION_MINOR = 0;
            } else if (ClassLoaderUtils.isClassPresent("org.springframework.context.annotation.ComponentScans")) {
                SPRING_VERSION_MAJOR = 4;
                SPRING_VERSION_MINOR = 3;
            } else if (ClassLoaderUtils.isClassPresent("org.springframework.core.annotation.AliasFor")) {
                SPRING_VERSION_MAJOR = 4;
                SPRING_VERSION_MINOR = 2;
            } else if (ClassLoaderUtils.isClassPresent("org.springframework.cache.annotation.CacheConfig")) {
                SPRING_VERSION_MAJOR = 4;
                SPRING_VERSION_MINOR = 1;
            } else if (ClassLoaderUtils.isClassPresent("org.springframework.core.io.PathResource")) {
                SPRING_VERSION_MAJOR = 4;
                SPRING_VERSION_MINOR = 0;
            } else if (ClassLoaderUtils.isClassPresent("org.springframework.web.context.request.async.DeferredResult")) {
                SPRING_VERSION_MAJOR = 3;
                SPRING_VERSION_MINOR = 2;
            } else if (ClassLoaderUtils.isClassPresent("org.springframework.web.servlet.support.RequestDataValueProcessor")) {
                SPRING_VERSION_MAJOR = 3;
                SPRING_VERSION_MINOR = 1;
            } else if (ClassLoaderUtils.isClassPresent("org.springframework.web.bind.annotation.RequestBody")) {
                SPRING_VERSION_MAJOR = 3;
                SPRING_VERSION_MINOR = 0;
            } else {
                // We will default to 2.5
                SPRING_VERSION_MAJOR = 2;
                SPRING_VERSION_MINOR = 5;
            }


        }

        SPRING_WEB_MVC_PRESENT = ClassLoaderUtils.isClassPresent("org.springframework.web.servlet.View");
        SPRING_WEB_REACTIVE_PRESENT =
                SPRING_VERSION_MAJOR >= 5 && ClassLoaderUtils.isClassPresent("org.springframework.web.reactive.result.view.View");

    }




    public static int getSpringVersionMajor() {
        return SPRING_VERSION_MAJOR;
    }

    public static int getSpringVersionMinor() {
        return SPRING_VERSION_MINOR;
    }



    public static boolean isSpring30AtLeast() {
        return SPRING_VERSION_MAJOR >= 3;
    }


    public static boolean isSpring31AtLeast() {
        return SPRING_VERSION_MAJOR > 3 || (SPRING_VERSION_MAJOR == 3 && SPRING_VERSION_MINOR >= 1);
    }


    public static boolean isSpring32AtLeast() {
        return SPRING_VERSION_MAJOR > 3 || (SPRING_VERSION_MAJOR == 3 && SPRING_VERSION_MINOR >= 2);
    }


    public static boolean isSpring40AtLeast() {
        return SPRING_VERSION_MAJOR >= 4;
    }


    public static boolean isSpring41AtLeast() {
        return SPRING_VERSION_MAJOR > 4 || (SPRING_VERSION_MAJOR == 4 && SPRING_VERSION_MINOR >= 1);
    }


    public static boolean isSpring42AtLeast() {
        return SPRING_VERSION_MAJOR > 4 || (SPRING_VERSION_MAJOR == 4 && SPRING_VERSION_MINOR >= 2);
    }


    public static boolean isSpring43AtLeast() {
        return SPRING_VERSION_MAJOR > 4 || (SPRING_VERSION_MAJOR == 4 && SPRING_VERSION_MINOR >= 3);
    }


    public static boolean isSpring50AtLeast() {
        return SPRING_VERSION_MAJOR >= 5;
    }


    public static boolean isSpringWebMvcPresent() {
        return SPRING_WEB_MVC_PRESENT;
    }


    public static boolean isSpringWebFluxPresent() {
        return SPRING_WEB_REACTIVE_PRESENT;
    }



    private SpringVersionUtils() {
        super();
    }


}
