/* Copyright (C) 2010 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.datastore.gorm.finders;

import groovy.lang.GroovySystem;
import groovy.lang.MetaClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.datastore.mapping.core.Datastore;

/**
 * Finder used to return a single result
 */
public class FindOrCreateByFinder extends AbstractFindByFinder {

    private static final String METHOD_PATTERN = "(findOrCreateBy)([A-Z]\\w*)";

    public FindOrCreateByFinder(final String methodPattern, final Datastore datastore) {
        super(Pattern.compile(methodPattern), datastore);
    }
    
    public FindOrCreateByFinder(final Datastore datastore) {
        this(METHOD_PATTERN, datastore);
    }

    @Override
    protected Object doInvokeInternal(final DynamicFinderInvocation invocation) {
        if (OPERATOR_OR.equals(invocation.getOperator())) {
            throw new UnsupportedOperationException(
                    "'Or' expressions are not allowed in findOrCreateBy queries.");
        }

        Object result = super.doInvokeInternal(invocation);
        if (result == null) {
            Map m = new HashMap();
            List<MethodExpression> expressions = invocation.getExpressions();
            for (MethodExpression me : expressions) {
                String propertyName = me.propertyName;
                Object[] arguments = me.getArguments();
                m.put(propertyName, arguments[0]);
            }
            MetaClass metaClass = GroovySystem.getMetaClassRegistry().getMetaClass(invocation.getJavaClass());
            result = metaClass.invokeConstructor(new Object[]{m});
            if(shouldSaveOnCreate()) {
                metaClass.invokeMethod(result, "save", null);
            }
        }
        return result;
    }

    protected boolean shouldSaveOnCreate() {
        return false;
    }
}