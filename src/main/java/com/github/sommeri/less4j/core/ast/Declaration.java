package com.github.sommeri.less4j.core.ast;

import java.util.List;

import com.github.sommeri.less4j.core.ast.ListExpressionOperator.Operator;
import com.github.sommeri.less4j.core.ast.annotations.NotAstProperty;
import com.github.sommeri.less4j.core.parser.HiddenTokenAwareTree;
import com.github.sommeri.less4j.utils.ArraysUtils;

public class Declaration extends ASTCssNode {

  //private String name;
  private InterpolableName name;
  private Expression expression;
  private boolean important;
  private ListExpressionOperator.Operator mergeOperator;

  public Declaration(HiddenTokenAwareTree token, InterpolableName name) {
    this(token, name, null, false, null);
  }

  public Declaration(HiddenTokenAwareTree token, InterpolableName name, Expression expression, Operator mergeOperator) {
    this(token, name, expression, false, mergeOperator);
  }

  public Declaration(HiddenTokenAwareTree token, InterpolableName name, Expression expression, boolean important, Operator mergeOperator) {
    super(token);
    this.name = name;
    this.expression = expression;
    this.important = important;
    this.mergeOperator = mergeOperator;
  }

  public String getNameAsString() {
    return name.getName();
  }

  public Expression getExpression() {
    return expression;
  }

  public boolean isImportant() {
    return important;
  }
  
  public boolean isMerging() {
    return mergeOperator!=null;
  }
  
  public void setName(InterpolableName name) {
    this.name = name;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }

  public void setImportant(boolean important) {
    this.important = important;
  }

  public ListExpressionOperator.Operator getMergeOperator() {
    return mergeOperator;
  }

  public void setMergeOperator(Operator mergeOperator) {
    this.mergeOperator = mergeOperator;
  }

  @Override
  public ASTCssNodeType getType() {
    return ASTCssNodeType.DECLARATION;
  }

  @Override
  @NotAstProperty
  public List<? extends ASTCssNode> getChilds() {
    return ArraysUtils.asNonNullList(expression, name);
  }

  public boolean isFontDeclaration() {
    return getNameAsString()!=null? getNameAsString().toLowerCase().equals("font") : false;
  }

  public boolean isFilterDeclaration() {
    return getNameAsString()!=null? getNameAsString().toLowerCase().endsWith("filter") : false;
  }
  
  @Override
  public String toString() {
    return ""+ name + ":" + expression;
  }
  
  @Override
  public Declaration clone() {
    Declaration result = (Declaration) super.clone();
    result.name = name==null?null:name.clone();
    result.expression = expression==null?null:expression.clone();
    result.configureParentToAllChilds();
    return result;
  }
}
