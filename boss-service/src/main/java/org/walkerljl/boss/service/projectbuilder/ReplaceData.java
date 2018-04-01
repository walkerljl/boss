package org.walkerljl.boss.service.projectbuilder;

import org.walkerljl.boss.service.projectbuilder.entity.AppInfo;
import org.walkerljl.boss.service.projectbuilder.entity.Table;

/**
 * @author lijunlin
 */
public class ReplaceData {

    public static String replace(AppInfo info, String content, Table table) {
        content = content.replace("${simple.groupId}", info.getGroupId());
        content = content.replace("${simple.artifactId}", info.getArtifactId());
        content = content.replace("${simple.author}", info.getAuthor());
        content = content.replace("${simple.date}", info.getDate());
        if (table != null) {
            content = content.replace("${table.className}", table.getClassName());
            content = content.replace("${table.varName}", table.getVarName());
            //content = content.replace("${table.primaryField.typeName}", table.getPrimaryField().getTypeName());
        }
        return content;
    }

    public static String replaceDomain(AppInfo info, String content, Table table) {
        content = replace(info, content, table);
        if (table != null) {
            content = content.replace("$!{table.remark}", table.getRemark());
            content = content.replace("$!{table.name}", table.getName());
            content = content.replace("${table.imports}", table.getImports());
            content = content.replace("${table.fields}", table.getFields());
            content = content.replace("${table.methods}", table.getMethods());
        }
        return content;
    }

    public static String replacePath(AppInfo info, String content, Table table) {
        content = content.replace(".java.vm", ".java");
        content = content.replace("${simple.groupId}", info.getGroupId().replace(".", "/"));
        content = content.replace("${simple.artifactId}", info.getArtifactId().replace(".", "/"));
        if (table != null) {
            content = content.replace("${table.className}", table.getClassName());
        }
        return content;
    }
}
