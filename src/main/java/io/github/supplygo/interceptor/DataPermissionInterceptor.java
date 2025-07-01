package io.github.supplygo.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class DataPermissionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");
        
        // 假设你有一个方法可以获取当前用户的数据权限
        String dataPermissionSql = getDataPermissionSql();
        
        // 添加数据权限过滤条件
        String newSql = originalSql + " AND " + dataPermissionSql;
        
        // 设置新的 SQL
        metaObject.setValue("delegate.boundSql.sql", newSql);
        
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 设置属性
    }

    private String getDataPermissionSql() {
        // 实现获取数据权限的逻辑
        return "dept_id IN (SELECT dept_id FROM user_dept WHERE user_id = CURRENT_USER_ID)";
    }
} 