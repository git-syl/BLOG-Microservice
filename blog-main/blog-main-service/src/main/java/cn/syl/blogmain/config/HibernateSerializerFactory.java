package cn.syl.blogmain.config;

import com.alibaba.com.caucho.hessian.io.*;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.hibernate.collection.internal.PersistentMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: syl  Date: 2017/11/2 Email:nerosyl@live.com
 */
@Deprecated
public class HibernateSerializerFactory extends SerializerFactory {

    private HibernateListSerializer listSerializer = new HibernateListSerializer();
    private HibernateMapSerializer mapSerializer = new HibernateMapSerializer();
    private HibernateBeanSerializer hibernateBeanSerializer = new HibernateBeanSerializer();

    @SuppressWarnings("rawtypes")
    public Serializer getSerializer(Class cl) throws HessianProtocolException {
        if (PersistentMap.class.isAssignableFrom(cl)) {
            return mapSerializer;
        } else if (AbstractPersistentCollection.class.isAssignableFrom(cl)) {
            return listSerializer;
        } else if (cl.getSimpleName().contains("_$$_javassist_")) {
            return hibernateBeanSerializer;
        }
        return super.getSerializer(cl);
    }

    private static class HibernateBeanSerializer implements Serializer {
        @Override
        public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {
            boolean init = Hibernate.isInitialized(obj);

            out.writeObject(init ? obj : null);
            out.flush();
            return;
        }
    }

    private static class HibernateListSerializer implements Serializer {
        private CollectionSerializer delegate = new CollectionSerializer();

        @SuppressWarnings({"unchecked", "rawtypes"})
        public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {
            if (Hibernate.isInitialized(obj)) {
                delegate.writeObject(new ArrayList((Collection) obj), out);
            } else {
                delegate.writeObject(new ArrayList(), out);
            }
        }

    }

    private static class HibernateMapSerializer implements Serializer {
        private MapSerializer delegate = new MapSerializer();

        @SuppressWarnings({"unchecked", "rawtypes"})
        public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {
            if (Hibernate.isInitialized(obj)) {
                delegate.writeObject(new HashMap((Map) obj), out);
            } else {
                delegate.writeObject(new HashMap(), out);
            }
        }

    }
}