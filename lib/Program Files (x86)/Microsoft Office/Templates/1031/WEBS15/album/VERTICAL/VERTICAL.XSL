<?xml version="1.0" ?> 
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">

<xsl:script>
<![CDATA[
	//generate a random int to identify the functions and objects in this gallery
	var rnd = Math.round(Math.random() * 10000);	
	var gnumPerRow = ""
	var count = ""
	
  function breakRow(e) {
		if (gnumPerRow == "") {
			gnumPerRow = e.selectSingleNode("//options").getAttribute("numperrow");
		}
		return ((absoluteChildNumber(e) - 1)%gnumPerRow) == 0;
  } 

  function isPreviewOutput(e) {
		if (e.selectSingleNode("/xml[@output='preview']")) {
			return true;
		} else {
			return false;
		}
  }

  function isLastCell(e) {
  		if (count == "") {
			count = e.selectNodes("//picture").length;
		}
		return (count == absoluteChildNumber(e));
  }


  function HR()
  {
		return "<hr noshade='noshade' width='95%' size='1' color='#000000' />";
  }

    function FillTableEnd(e) {
		if (gnumPerRow == "") {
			gnumPerRow = e.selectSingleNode("//options").getAttribute("numperrow");
		}
		if (count == "") {
			count = e.selectNodes("//picture").length;
		}
		
		var index = count % gnumPerRow;
		var html = "";
		var i = 0;
		if (index == 0)
		{
			index = gnumPerRow;
		}

		// only do that for more than one row situation.
		if (count > gnumPerRow)
		{
			for(i=0; i<gnumPerRow - index; i++)
			{
				if (isPreviewOutput(e))
				{
				html +="<td valign=\"top\"> \n\
					<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n\
					<tr> \n\
						<td colspan=\"2\" height=\"1\" style=\"border-top-style: solid; border-top-width: 1; border-top-color:black\">\n\
						</td>\n\
						<td width=\"10\"><font color=\"white\"> </font></td>\n\
					</tr>\n\
					</table></td>\n";
				}
				else{
				html +="<td valign=\"top\"> \n\
					<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n\
					<tr> \n\
						<td colspan=\"2\" height=\"1\" width=\"95%\">\n\
						<script language=\"JavaScript1.1\">\n\
							document.write(\"<hr noshade='noshade' width='95%' size='1' color='#000000' />\");\n\
						</script>\n\
						</td>\n\
						<td width=\"10\"><font color=\"white\"> </font></td>\n\
					</tr>\n\
					</table></td>\n";
				}
			}
		}

		html += "\n<tr>\n";
		for(i=0; i<index; i++)
		{	
			if (isPreviewOutput(e))
			{
			html +="<td valign=\"top\"> \n\
					<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n\
					<tr> \n\
						<td colspan=\"2\" height=\"1\" style=\"border-top-style: solid; border-top-width: 1; border-top-color:black\">\n\
						</td>\n\
						<td width=\"10\"><font color=\"white\"> </font></td>\n\
					</tr>\n\
					</table></td>\n";			
			}
			else{
			html +="<td valign=\"top\"> \n\
					<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n\
					<tr> \n\
						<td colspan=\"2\" height=\"1\" width=\"95%\">\n\
						<script language=\"JavaScript1.1\">\n\
							document.write(\"<hr noshade='noshade' width='95%' size='1' color='#000000' />\");\n\
						</script>\n\
						</td>\n\
						<td width=\"10\"><font color=\"white\"> </font></td>\n\
					</tr>\n\
					</table></td>\n";
			}
		}
		html += "\n</tr>\n";
		return html;
  }


  function lastTwoRow(e) {
		if (gnumPerRow == "") {
			gnumPerRow = e.selectSingleNode("//options").getAttribute("numperrow");
		}
		if (count == "") {
			count = e.selectNodes("//picture").length;
		}
		var index = count % gnumPerRow;
		return (count - absoluteChildNumber(e) - index < gnumPerRow);
  }

  function notLastRow(e) {
		if (gnumPerRow == "") {
			gnumPerRow = e.selectSingleNode("//options").getAttribute("numperrow");
		}
		if (count == "") {
			count = e.selectNodes("//picture").length;
		}
		var index = count % gnumPerRow;
		return (count - absoluteChildNumber(e) >= index);
  }
]]>
</xsl:script>

<!--
	the template block describes this template.
	this info is used by FP when building the photo galery dialog
-->
<template>
	<title><!-- _locID_text="Title"-->Vertical Layout</title>
	<description><!-- _locID_text="Description"-->- Thumbnails of your images are created automatically.
- Thumbnail images are arranged in columns.
- Descriptive text is placed to the right of the images.
</description>
	<defaults imgPerRow="2" thumbWidth="100" />
	<dependent-files/>
</template>


<xsl:template><xsl:copy><xsl:apply-templates select="@* | * | comment() | pi() | text()"/></xsl:copy></xsl:template>


<xsl:template match="/">
	<xsl:apply-templates select="xml"/>
</xsl:template>

<xsl:template match="xml[@output !='subpage']">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><xsl:value-of select="//options/@pageName"/></title>
</head>
<body>
<xsl:choose>
<xsl:when expr="isPreviewOutput(this)">
</xsl:when>
<xsl:otherwise>
<picture file-href="real_p.htm" /> 
<picture file-href="real_x.htm" />
</xsl:otherwise>
</xsl:choose>
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
	<xsl:apply-templates />
</xsl:template>

<xsl:template match="picture">
	<xsl:if expr="breakRow(this)">
	<tr/>
	</xsl:if>
		<td valign="top">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				
				<tr>
					<xsl:choose>
					<xsl:when expr="isPreviewOutput(this)">
						<td colspan="2" height="1" style="border-top-style: solid; border-top-width: 1; border-top-color:black"></td>
					</xsl:when>
					<xsl:otherwise>
						<td colspan="2" height="1">
							<xsl:eval no-entities='t'>HR()</xsl:eval>
						</td>
					</xsl:otherwise>
					</xsl:choose>
					<td width="10"><font color="white"> </font></td>
				</tr>
				
				<tr>
					<td valign="top">
						<a>
						<xsl:attribute name="href"><xsl:value-of select="@file-href"/></xsl:attribute>
						<img border="0" vspace="10" hspace="10">
						<xsl:attribute name="src"><xsl:value-of select="@thumb-href"/></xsl:attribute>
						<xsl:attribute name="width"><xsl:value-of select="@thumbwidth"/></xsl:attribute>
						<xsl:attribute name="height"><xsl:value-of select="@thumbheight"/></xsl:attribute>
						<xsl:attribute name="title"><xsl:eval>this.selectSingleNode("caption").text</xsl:eval></xsl:attribute>
						</img></a>
					</td>
					<td width="130" height="123" valign="top"> 
						<table width="130" border="0" cellspacing="0" cellpadding="0">
							<tr valign="top"> 
								<td height="10"><font color="white"> </font></td>
							</tr>
							<tr valign="top">
								<td>
									<xsl:apply-templates select="caption"/>
								</td>
							</tr>
							<tr valign="top">
								<td>
									<xsl:apply-templates select="desc"/>
								</td>
							</tr>
						</table>
					</td>
					<td width="10"><font color="white"> </font></td>
				</tr>
				
			</table>
		</td>
	<xsl:if expr="isLastCell(this)">
	<xsl:eval no-entities="t">FillTableEnd(this)</xsl:eval>
	</xsl:if>
</xsl:template>

<xsl:template match="caption | desc"><xsl:apply-templates /></xsl:template>
</xsl:stylesheet>