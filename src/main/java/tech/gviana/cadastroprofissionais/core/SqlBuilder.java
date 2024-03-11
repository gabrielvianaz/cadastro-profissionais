package tech.gviana.cadastroprofissionais.core;

import org.apache.commons.lang3.StringUtils;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractActivableEntity;
import tech.gviana.cadastroprofissionais.core.base.entity.AbstractEntity;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class SqlBuilder<T extends AbstractEntity> {

    private final Class<T> entityClass;
    private final String selectFields;
    private final String whereClause;
    private final String ENTITY_NAME_ALIAS = "e";

    public SqlBuilder(SearchParams searchParams, Class<T> entityClass) {
        this.entityClass = entityClass;
        this.selectFields = buildSelectFields(searchParams.fields());
        this.whereClause = buildWhereClause(searchParams.q());
    }

    public String build() {
        return String.format("SELECT %s FROM %s %s %s", selectFields, entityClass.getSimpleName(), ENTITY_NAME_ALIAS, whereClause);
    }

    private String buildSelectFields(List<String> fields) {
        return fields != null && !fields.isEmpty() ? validateAndJoinSelectFields(fields) : ENTITY_NAME_ALIAS;
    }

    private String validateAndJoinSelectFields(List<String> fields) {
        Method[] methods = entityClass.getMethods();
        List<String> classAttributes = Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("get"))
                .map(method -> StringUtils.uncapitalize(method.getName().replace("get", "")))
                .toList();
        fields.removeIf(field -> !classAttributes.contains(field));
        fields = fields.stream().map(field -> String.format("%s.%s", ENTITY_NAME_ALIAS, field)).toList();

        return String.join(", ", fields);
    }

    private String buildWhereClause(String q) {
        return String.format("WHERE searchField LIKE LOWER('%%%s%%') %s", q, getAtivoCondition());
    }

    private String getAtivoCondition() {
        return entityClass.getSuperclass().equals(AbstractActivableEntity.class) ? String.format("AND %s.ativo", ENTITY_NAME_ALIAS) : "";
    }

}
