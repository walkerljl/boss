package org.walkerljl.boss.support.common.attribute.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.walkerljl.boss.support.common.attribute.AttributeAccessor;
import org.walkerljl.boss.support.common.util.StringUtil;
import org.walkerljl.toolkit.template.handle.service.ServiceAssertUtil;

/**
 * DefaultAttributeAccessor
 *
 * @author xingxun
 */
public class DefaultAttributeAccessor implements AttributeAccessor, Serializable {

    private static final long serialVersionUID = 4409101753242541792L;

    private final Map<String, Object> attributes = new LinkedHashMap(0);

    @Override
    public void setAttribute(String name, Object value) {
        ServiceAssertUtil.assertParam(StringUtil.isNotEmpty(name), "name");
        if (value != null) {
            this.attributes.put(name, value);
        } else {
            this.removeAttribute(name);
        }
    }

    @Override
    public Object getAttribute(String name) {
        ServiceAssertUtil.assertParam(StringUtil.isNotEmpty(name), "name");
        return this.attributes.get(name);
    }

    @Override
    public Object removeAttribute(String name) {
        ServiceAssertUtil.assertParam(StringUtil.isNotEmpty(name), "name");
        return this.attributes.remove(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        ServiceAssertUtil.assertParam(StringUtil.isNotEmpty(name), "name");
        return this.attributes.containsKey(name);
    }

    @Override
    public String[] attributeNames() {
        return this.attributes.keySet().toArray(new String[this.attributes.size()]);
    }

    @Override
    public void copy(AttributeAccessor source) {
        ServiceAssertUtil.assertParam(source != null, "source");
        String[] sourceAttributeNames = source.attributeNames();
        for (int index = 0; index < sourceAttributeNames.length; index ++) {
            String attributeName = sourceAttributeNames[index];
            this.setAttribute(attributeName, source.getAttribute(attributeName));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        DefaultAttributeAccessor that = (DefaultAttributeAccessor) o;

        return attributes != null ? attributes.equals(that.attributes) : that.attributes == null;
    }

    @Override
    public int hashCode() {
        return attributes != null ? attributes.hashCode() : 0;
    }
}