package com.baselogic.tutorials.domain.test;

import org.dbunit.dataset.Column;
import org.dbunit.dataset.filter.IColumnFilter;

/**
 * Null Primary Key Filter Class
 * @author Mick Knutson
 * @see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * @see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * @see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * @see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 *
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 6 Cookbook Packt</a>
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 6 Cookbook Amazon</a>
 *
 * @since 2012
 */
public class NullPrimaryKeyFilter implements IColumnFilter {
    private String[] keys = null;

    public NullPrimaryKeyFilter(String... keys) {
        this.keys = keys;
    }

    public boolean accept(String tableName, Column column) {
        for(String key: keys){
            if(column.getColumnName().equalsIgnoreCase(key)){
                return true;
            }
        }
        return false;
    }
}
