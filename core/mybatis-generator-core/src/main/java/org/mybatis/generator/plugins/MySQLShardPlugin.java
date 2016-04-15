/**
 * 
 */
package org.mybatis.generator.plugins;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * mysql分表插件
 * 
 * @author 张鹏科
 *
 */
public class MySQLShardPlugin extends PluginAdapter {

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType(
				"com.hanju.server.common.model.Shard"));
		addShard(topLevelClass, introspectedTable, "shard");
		return super.modelExampleClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
			Interface interfaze, IntrospectedTable introspectedTable) {
		String recordType = introspectedTable.getBaseRecordType();
		List<Parameter> parameters = method.getParameters();
		parameters.set(0, new Parameter(new FullyQualifiedJavaType(recordType),
				"record"));
		return super.clientSelectByExampleWithBLOBsMethodGenerated(method,
				interfaze, introspectedTable);
	}

	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
			Interface interfaze, IntrospectedTable introspectedTable) {
		String recordType = introspectedTable.getBaseRecordType();
		List<Parameter> parameters = method.getParameters();
		parameters.set(0, new Parameter(new FullyQualifiedJavaType(recordType),
				"record"));
		return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze,
				introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		String recordType = introspectedTable.getBaseRecordType();
		// 把parameterType转化成对应的对象
		List<Attribute> attributes = element.getAttributes();
		attributes.set(2, new Attribute("parameterType", recordType));

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapSelectByPrimaryKeyElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapDeleteByExampleElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		// 把parameterType转化成对应的对象
		String recordType = introspectedTable.getBaseRecordType();
		List<Attribute> attributes = element.getAttributes();
		attributes.set(1, new Attribute("parameterType", recordType));

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapDeleteByPrimaryKeyElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapInsertSelectiveElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapCountByExampleElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, true);

		return super.sqlMapUpdateByExampleSelectiveElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, true);

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, true);

		return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(
				element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		setTableNameWithShard(element, introspectedTable, false);

		return super.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(element,
				introspectedTable);
	}

	/**
	 * 修改表名支持shard,updateByExample类型在example设置shard
	 * 
	 * @param element
	 * @param introspectedTable
	 * @param updateExample
	 */
	private void setTableNameWithShard(XmlElement element,
			IntrospectedTable introspectedTable, boolean updateExample) {
		String originTableName = introspectedTable.getTableConfiguration()
				.getTableName();

		List<Element> elements = element.getElements();
		int index = 0;
		for (Element elem : elements) {
			if (elem instanceof TextElement) {
				String originContent = ((TextElement) elem).getContent();
				if (originContent.contains(originTableName.toLowerCase())) {
					String shardContent = null;
					if (updateExample) {
						shardContent = originContent.replace(
								originTableName.toLowerCase(), originTableName
										+ "${example.shard.tableSuffix}");
					} else {
						shardContent = originContent.replace(
								originTableName.toLowerCase(), originTableName
										+ "${shard.tableSuffix}");
					}

					TextElement subSentence = new TextElement(shardContent);
					elements.set(index, subSentence);
					break;
				}
			}
			index++;
		}

	}

	/**
	 * 设置分表shard
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 * @param name
	 */
	private void addShard(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String name) {

		CommentGenerator commentGenerator = context.getCommentGenerator();
		// exmaple声明Shard域对象
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(new FullyQualifiedJavaType("Shard"));
		field.setName(name);
		field.setInitializationString("new Shard()");
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		// 设置setShard方法
		Method setMethod = new Method();
		setMethod.setVisibility(JavaVisibility.PUBLIC);
		setMethod.setName("setShard");
		setMethod.addParameter(new Parameter(
				new FullyQualifiedJavaType("Shard"), "shard"));
		setMethod.addBodyLine("this.shard = shard;");
		topLevelClass.addMethod(setMethod);
		// 设置getShard方法
		Method getMethod = new Method();
		getMethod.setVisibility(JavaVisibility.PUBLIC);
		getMethod.setName("getShard");
		getMethod.setReturnType(new FullyQualifiedJavaType("Shard"));
		getMethod.addBodyLine("return shard;");
		topLevelClass.addMethod(getMethod);
	}

	/**
	 * This plugin is always valid - no properties are required
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}

}
