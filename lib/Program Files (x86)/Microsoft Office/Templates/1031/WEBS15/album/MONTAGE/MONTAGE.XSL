<?xml version="1.0" ?> 
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">

<xsl:script>
<![CDATA[
	//generate a random int to identify the functions and objects in this gallery
	var rnd = Math.round(Math.random() * 10000);	

  function breakRow(e) {
		var	numPerRow = e.selectSingleNode("//options").getAttribute("numperrow");
		var index = absoluteChildNumber(e);		

		// first image needs a new row
		if (index == 1)
		{
			return true;
		}	
		else{
			// 3, 4, 4 pattern
			var remainder = index % 11;
			if (remainder == 1 || remainder == 4 || remainder == 8)
				return true;
			else
				return false;
		}	
  }

  function notPreviewOutput(e) {
		if (e.selectSingleNode("/xml[@output='preview']")) {
			return false;
		} else {
			return true;
		}
  }
  
  function getAlignment(e) {
		var numPerRow = parseInt(e.selectSingleNode("//options").getAttribute("numperrow"));
		var childNum = absoluteChildNumber(e);
		
		//find the child number of the first image in the last row
		var pict = e.selectNodes("//picture").length;
		var index = pict % 11;
		if (index >= 8)
			pict = pict - index + 8;
		else if (index >= 4 )
			pict = pict - index + 4;
		else if (index > 0)
			pict = pict - index;
		else
			pict = pict - 3;
		
		//return alignment based on where the image falls in the table
		if (childNum < numPerRow) {
			/* first row */
			return "bottom";
		} else if (childNum >= pict) {
				/* last row */
				return "top";
		} else {
			/* all other rows */
			return "middle";
		}
  } 
]]>
</xsl:script>

<!--
	the template block describes this template.
	this info is used by FP when building the photo galery dialog
-->
<template>
	<title><!-- _locID_text="Title"-->Montage Layout</title>
	<description><!-- _locID_text="Description"-->- Thumbnails of your images are created automatically.
- Thumbnail images are displayed in a pattern like a collage.
- Caption text appears when you rest the pointer on the image. 
- Descriptive text is not available with this layout.
</description>
	<defaults imgPerRow="4" thumbWidth="100" />
	<dependent-files/>
</template>

<xsl:template><xsl:copy><xsl:apply-templates select="@* | * | comment() | pi() | text()"/></xsl:copy></xsl:template>

<xsl:template match="/">
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
	<xsl:apply-templates select="//pictures"/>
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


<xsl:template match="pictures">
	<xsl:apply-templates/>
</xsl:template>


<xsl:template match="picture">
	<xsl:if expr="breakRow(this)">
		<tr/>
		  <td align="center" valign="top" nowrap=""/>
	</xsl:if>
	<a>
	<xsl:attribute name="href"><xsl:value-of select="@file-href"/></xsl:attribute>
	<img border="0" vspace="5" hspace="5">
	<xsl:attribute name="src"><xsl:value-of select="@thumb-href"/></xsl:attribute>
	<xsl:attribute name="width"><xsl:value-of select="@thumbwidth"/></xsl:attribute>
	<xsl:attribute name="height"><xsl:value-of select="@thumbheight"/></xsl:attribute>
	<xsl:attribute name="title"><xsl:eval>this.selectSingleNode("caption").text</xsl:eval></xsl:attribute>						
	<xsl:attribute name="align"><xsl:eval>getAlignment(this)</xsl:eval></xsl:attribute>
	</img></a>
</xsl:template>

</xsl:stylesheet>
