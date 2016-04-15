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
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * mysql分页查询插件
 * 
 * @author 张鹏科
 *
 */
public class MySQLPaginationPlugin extends PluginAdapter {

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType(
				"com.hanju.server.common.model.BaseModel"));
		topLevelClass.setSuperClass("BaseModel");

		return super.modelBaseRecordClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType(
				"com.hanju.server.common.model.Limit"));
		addLimit(topLevelClass, introspectedTable, "limit");

		return super.modelExampleClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		XmlElement limit = new XmlElement("if");
		limit.addAttribute(new Attribute("test", "limit != null"));
		limit.addElement(new TextElement(
				"limit #{limit.offset} , #{limit.maxRows}"));
		element.addElement(limit);

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {

		XmlElement limit = new XmlElement("if");
		limit.addAttribute(new Attribute("test", "limit != null"));
		limit.addElement(new TextElement(
				"limit #{limit.offset} , #{limit.maxRows}"));
		element.addElement(limit);

		return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element,
				introspectedTable);
	}

	/**
	 * 设置分页limit
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 * @param name
	 */
	private void addLimit(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String name) {

		CommentGenerator commentGenerator = context.getCommentGenerator();
		// exmaple声明Limit域对象
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(new FullyQualifiedJavaType("Limit"));
		field.setName(name);
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		// 设置setLimit方法
		Method setMethod = new Method();
		setMethod.setVisibility(JavaVisibility.PUBLIC);
		setMethod.setName("setLimit");
		setMethod.addParameter(new Parameter(
				new FullyQualifiedJavaType("Limit"), "limit"));
		setMethod.addBodyLine("this.limit = limit;");
		topLevelClass.addMethod(setMethod);
		// 设置getLimit方法
		Method getMethod = new Method();
		getMethod.setVisibility(JavaVisibility.PUBLIC);
		getMethod.setName("getLimit");
		getMethod.setReturnType(new FullyQualifiedJavaType("Limit"));
		getMethod.addBodyLine("return limit;");
		topLevelClass.addMethod(getMethod);
	}

	/**
	 * This plugin is always valid - no properties are required
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}

}
