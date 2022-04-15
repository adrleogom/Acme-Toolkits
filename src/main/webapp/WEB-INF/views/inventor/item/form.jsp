<%--
- list.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<%@ page language="java"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.item.form.label.itemType" path="itemType"/>
	<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.code" path ="code"/>
	<acme:input-textbox code="inventor.item.form.label.tecnology" path ="technology"/>
	<acme:input-textarea code="inventor.item.form.label.description" path ="description"/>
	<acme:input-money code="inventor.item.form.label.retailPrice" path ="retailPrice"/>
	<acme:input-url code="inventor.item.form.label.furtherInfo" path ="furtherInfo"/>
		
</acme:form>

