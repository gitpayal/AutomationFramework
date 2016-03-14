<?xml version="1.0" ?> 
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">

<xsl:script>
	  <![CDATA[
  var gMaxHeight = 0;
	var gnumPerRow = ""

  function breakRow(e) {
		if (gnumPerRow == "") {
			gnumPerRow = e.selectSingleNode("//options").getAttribute("numperrow");
		}
		return ((absoluteChildNumber(e) - 1)%gnumPerRow) == 0;
  } 

  function notPreviewOutput(e) {
		if (e.selectSingleNode("/xml[@output='preview']")) {
			return false;
		} else {
			return true;
		}
  }

  function NBSP()
  {
		return "if (navigator.appName == \"Netscape\" && parseInt(navigator.appVersion) < 5)\ndocument.write(\"<td bgcolor='#000000' width='1'>&nbsp;</td>\");";
  }
  	
  function rowEnd(e) {
		if (gnumPerRow == "") {
			gnumPerRow = e.selectSingleNode("//options").getAttribute("numperrow");
		}
		var childList = e.selectSingleNode("//pictures").childNodes;
		return ((absoluteChildNumber(e))%gnumPerRow) == 0 || childList.length == absoluteChildNumber(e);
  } 
    
	//generate a random int to identify the functions and objects in this gallery
	var rnd = Math.round(Math.random() * 10000);	

  function setMaxHeight(e) {
  var childList = e.selectSingleNode("//pictures").childNodes;
  var maxHeight = 0;
  for (i=0;i<childList.length;i++) {
    if (parseInt(childList[i].getAttribute("thumbheight")) > maxHeight) {
      maxHeight = parseInt(childList[i].getAttribute("thumbheight"));
    }
  }
  gMaxHeight = maxHeight;
  }
  
]]></xsl:script>

<!--
	the template block describes this template.
	this info is used by FP when building the photo gallery dialog
-->
<template>
	<title><!--
			_locID_text="Title"
		-->
	Horizontal Layout</title>
	<description><!-- _locID_text="Description"-->- Thumbnails of your images are created automatically.
- Thumbnail images display in multiple rows.
- Descriptive text is placed below each image.		
	</description>
	<defaults imgPerRow="7" thumbWidth="100" />
	<dependent-files />
</template>

<xsl:template><xsl:copy><xsl:apply-templates select="@* | * | comment() | pi() | text()"/></xsl:copy></xsl:template>

<xsl:template match="/">
	<xsl:eval>
		setMaxHeight(this)
	</xsl:eval>
	<xsl:apply-templates select="xml"/>
</xsl:template>

<!-- this template has identical Preview and Real output -->
<xsl:template match="xml[@output != 'subpage']">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title><xsl:value-of select="//options/@pageName"/></title>
</head>
<body>
<xsl:if expr="notPreviewOutput(this)">
<picture file-href="real_p.htm" /> 
<picture file-href="real_x.htm" />
</xsl:if>
<table border="0" cellspacing="0" cellpadding="0">
	<xsl:attribute name="id">fpGalleryTable_<xsl:eval>rnd</xsl:eval></xsl:attribute>
  <tr>
  <xsl:for-each select="//pictures">
    <xsl:apply-templates />
  </xsl:for-each>
  </tr>
  <tr>
    <td>
			<xsl:attribute name="colspan"><xsl:eval>gnumPerRow</xsl:eval></xsl:attribute>
		</td>
  </tr>
</table>
</body>
</html>
</xsl:template>


<!-- this is the output we generate for each sub page of the gallery -->
<xsl:template match="xml[@output='subpage']">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><xsl:value-of select="//picture/caption"/></title>
</head>
<body>
<div align="center">
	<table width="100%" align="center">
		<tr>
			<td width="100%" align="center" colspan="3">
			<img hspace="10" border="0">
			<xsl:attribute name="width"><xsl:value-of select="//picture/@filewidth"/></xsl:attribute>
			<xsl:attribute name="height"><xsl:value-of select="//picture/@fileheight"/></xsl:attribute>
			<xsl:attribute name="src"><xsl:value-of select="//picture/@file-href"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="//picture/caption"/></xsl:attribute>
			</img>
			</td>
		</tr>
		<tr><td colspan="3"><xsl:apply-templates select="//picture/caption"/></td></tr>
		<tr><td colspan="3"><xsl:apply-templates select="//picture/desc"/></td></tr>
		<tr>
			<td><a><xsl:attribute name="href"><xsl:value-of select="//picture/@prevImgPath"/></xsl:attribute><!-- _locID_text="PrevImg" -->Previous Image</a></td>
			<td><a><xsl:attribute name="href"><xsl:value-of select="//picture/@galleryPath"/></xsl:attribute><!-- _locID_text="BackGal" -->Back To Gallery</a></td>
			<td><a><xsl:attribute name="href"><xsl:value-of select="//picture/@nextImgPath"/></xsl:attribute><!-- _locID_text="NextImg" -->Next Image</a></td>
		</tr>
	</table>
</div>
</body>
</html>
</xsl:template>

	
<xsl:template match="picture">
	<xsl:if expr="breakRow(this)">
	<tr><td height="10"></td>
	</tr>
	<tr/>
	</xsl:if>

	<xsl:choose>
	<xsl:when expr="rowEnd(this)">
	<xsl:if expr="notPreviewOutput(this)">
		<script language="JavaScript1.1">
			<xsl:eval no-entities='t'>NBSP()</xsl:eval>
		</script>
	</xsl:if>
    <td valign="top">
	<xsl:attribute name="style">border-left-style: solid; border-left-width: 1px; border-left-color:black;
		border-right-style: solid; border-right-width: 1px; border-right-color:black</xsl:attribute>
      <table border="0" cellpadding="2" cellspacing="0" align="center" width="120">
        <tr>
		    <td valign="top" align="center">
			<xsl:attribute name="height"><xsl:eval>gMaxHeight + 20</xsl:eval></xsl:attribute>
			<a>
            <xsl:attribute name="href"><xsl:value-of select="@file-href"/></xsl:attribute>
            <img border="0" vspace="5" hspace="12">
            <xsl:attribute name="src"><xsl:value-of select="@thumb-href"/></xsl:attribute>
						<xsl:attribute name="width"><xsl:value-of select="@thumbwidth"/></xsl:attribute>           
            <xsl:attribute name="height"><xsl:value-of select="@thumbheight"/></xsl:attribute>
            <xsl:attribute name="title"><xsl:value-of select="@caption"/></xsl:attribute>            
            </img></a>
			</td>
        </tr>
        <tr>
			<td valign="top">
			<xsl:apply-templates select="caption"/>
			<xsl:value-of select="@desc"/>
						<xsl:apply-templates select="desc"/>
			</td>
        </tr>
      </table>
    </td>
	<xsl:if expr="notPreviewOutput(this)">
		<script language="JavaScript1.1">
			<xsl:eval no-entities='t'>NBSP()</xsl:eval>
		</script>
	</xsl:if>
	</xsl:when>
	<xsl:otherwise>
	<xsl:if expr="notPreviewOutput(this)">
		<script language="JavaScript1.1">
			<xsl:eval no-entities='t'>NBSP()</xsl:eval>
		</script>
	</xsl:if>
    <td valign="top">
	<xsl:attribute name="style">border-left-style: solid; border-left-width: 1px; border-left-color:black</xsl:attribute>
      <table border="0" cellpadding="2" cellspacing="0" align="center" width="120">
        <tr>
		    <td valign="top" align="center">
			<xsl:attribute name="height"><xsl:eval>gMaxHeight + 20</xsl:eval></xsl:attribute>
			<a>
            <xsl:attribute name="href"><xsl:value-of select="@file-href"/></xsl:attribute>
            <img border="0" vspace="5" hspace="12">
            <xsl:attribute name="src"><xsl:value-of select="@thumb-href"/></xsl:attribute>
						<xsl:attribute name="width"><xsl:value-of select="@thumbwidth"/></xsl:attribute>           
            <xsl:attribute name="height"><xsl:value-of select="@thumbheight"/></xsl:attribute>
            <xsl:attribute name="title"><xsl:value-of select="@caption"/></xsl:attribute>            
            </img></a>
			</td>
        </tr>
        <tr>
			<td valign="top">
			<xsl:apply-templates select="caption"/>
			<xsl:value-of select="@desc"/>
					<xsl:apply-templates select="desc"/>
			</td>
        </tr>
      </table>
    </td>		
	</xsl:otherwise>
	</xsl:choose>
</xsl:template>


<xsl:template match="caption | desc"><xsl:apply-templates /></xsl:template>

</xsl:stylesheet>