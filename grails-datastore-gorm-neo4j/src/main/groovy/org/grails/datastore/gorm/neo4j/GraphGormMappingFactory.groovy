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
package org.grails.datastore.gorm.neo4j

import org.grails.datastore.mapping.config.AbstractGormMappingFactory
import org.grails.datastore.mapping.config.Property
import org.grails.datastore.mapping.keyvalue.mapping.config.Family
import org.grails.datastore.mapping.keyvalue.mapping.config.KeyValue
import org.grails.datastore.mapping.model.PersistentProperty
import org.grails.datastore.mapping.reflect.ClassPropertyFetcher
import java.beans.PropertyDescriptor
import org.grails.datastore.mapping.annotation.Index
import org.springframework.core.annotation.AnnotationUtils
import java.lang.reflect.Field
import org.springframework.util.ReflectionUtils

/**
 * @author Stefan Armbruster <stefan@armbruster-it.de>
 */
class GraphGormMappingFactory extends AbstractGormMappingFactory {

    @Override
    protected Class getPropertyMappedFormType() {
        Property
    }

    @Override
    protected Class getEntityMappedFormType() {
        Family
    }

/*
    // copied from AnnotationKeyValueMappingFactory
    @Override
    public Property createMappedForm(PersistentProperty mpp) {

        final Class javaClass = mpp.getOwner().getJavaClass();
        final ClassPropertyFetcher cpf = ClassPropertyFetcher.forClass(javaClass);

        final PropertyDescriptor pd = cpf.getPropertyDescriptor(mpp.getName());
        final Property property = super.createMappedForm(mpp);
        Index index = AnnotationUtils.getAnnotation(pd.getReadMethod(), Index.class);

        if (index == null) {
            final Field field = ReflectionUtils.findField(javaClass, mpp.getName());
            if (field != null) {
                ReflectionUtils.makeAccessible(field);
                index = field.getAnnotation(Index.class);
            }
        }
        if (index != null) {
            property.setIndex(true);
        }

        return property;
    }
*/

}


