<?xml version="1.0" ?> 
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">

<xsl:script>
<![CDATA[
	//generate a random int to identify the functions and objects in this gallery
	var rnd = Math.round(Math.random() * 10000);
	var rnd2 = rnd;
	var TRAY_WIDTH = 640;
	var totalLength = 20;
	
	function getRandom() {
		rnd2 = Math.round(Math.random() * 10000);
		return rnd2;
	}

	function notEmpty(e) {
		var childList = e.selectSingleNode("//pictures").childNodes;
		if (childList.length > 0)
			return true;
		else
			return false;
	}

	function getHeight(e) {
		var childList = e.selectSingleNode("//pictures").childNodes;
		var maxHeight = 0;
		var iHeight = 0;
		for (var i=0;i<childList.length;i++) {
		  iHeight = parseInt(childList[i].getAttribute("thumbheight"))
		  if ( iHeight > maxHeight) maxHeight = iHeight;
		}
		return maxHeight;
	}

	function disableRight(e) {
		var childList = e.selectNodes("//picture");
		var iWidth = 20;
		for (i=0;i<childList.length;i++) {
			iWidth += parseInt(childList[i].getAttribute("thumbwidth")) + 20;
		}

		if (iWidth > getWidth(e))
			return false;
		return true;
	}

	function previewImgChk(e) {
		//always return true if we're not generating a preview
		if (!e.selectSingleNode("/xml[@output='preview']")) return true;
		
		//figure out the most thumbnails we can show in the space defined by TRAY_WIDTH
		var childList = e.selectNodes("//picture");
		var iWidth = 20;
		for (i=0;i<childList.length;i++) {
			iWidth += parseInt(childList[i].getAttribute("thumbwidth")) + 20;
			if (iWidth > TRAY_WIDTH) {
				break;
			}
		}
		
		// return false if this element is above that number
		if (childNumber(e) <= i) {
			return true;
		} else {
			return false;
		}
	}

	function getWidth(e) {
		//figure out the most thumbnails we can show in the space defined by TRAY_WIDTH
		var childList = e.selectNodes("//picture");
		var iWidth = 20;
		for (i=0;i<childList.length;i++) {
			iWidth += parseInt(childList[i].getAttribute("thumbwidth")) + 20;
			if (iWidth > TRAY_WIDTH) {
				return iWidth - parseInt(childList[i].getAttribute("thumbwidth")) - 20;
			}
		}

		return iWidth;
	}
		
	function BreakLine(e) {
		totalLength += parseInt(e.getAttribute("thumbwidth")) + 20;
		if ( totalLength > getWidth(e))
		{
			totalLength = 40 + parseInt(e.getAttribute("thumbwidth"));
			return true;
		}
		else{
			return false;
		}
	}

	function isPreviewOutput(e) {
		if (e.selectSingleNode("/xml[@output='preview']")) {
			return true;
		} else {
			return false;
		}
	}
]]>
</xsl:script>

<!--
	the template block describes this template.
	this info is used by FP when building the photo gallery dialog
-->
<template>
	<title><!-- _locID_text="Title"-->Slide Show</title>
	<description><!-- _locID_text="Description"-->- Thumbnails of your images are created automatically.
- Thumbnail images are arranged in a row that scrolls across the top.
- The selected image is displayed full-size in the center.
- Descriptive text is placed below the full-size image.
</description>
	<defaults imgPerRow="2" thumbWidth="100" />
	<dependent-files>
		<file name="scripts" path="sldshow.js"/>
		<file name="prev" path="prev.gif"/>
		<file name="next" path="next.gif"/>
		<file name="prevdis" path="prevdis.gif"/>
		<file name="nextdis" path="nextdis.gif"/>
	</dependent-files>
</template>

<xsl:template><xsl:copy><xsl:apply-templates select="@* | * | comment() | pi() | text()"/></xsl:copy></xsl:template>

<xsl:template match="/">
	<xsl:apply-templates select="xml"/>
</xsl:template>


<!-- this is the preview HTML we generate for display inside FP -->
<xsl:template match="xml[@output = 'preview']">
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><xsl:value-of select="//options/@pageName"/></title>
</head>
<body>
<div align="center">
<center>
<table border="0" cellspacing="0" cellpadding="5" width="700" dir="ltr">
	<tr>
		<td nowrap="" align="center">
		<img border="0" align="middle">
		<xsl:attribute name="src"><xsl:value-of select="//dependent-files/file[@name='prevdis']/@path"/></xsl:attribute> 
		</img>

		<xsl:for-each select="//pictures">
			<xsl:apply-templates select="picture"/>
		</xsl:for-each>
		
		<img border="0" align="middle">
		<xsl:attribute name="src"><xsl:value-of select="//dependent-files/file[@name='nextdis']/@path"/></xsl:attribute> 
		</img>
		<hr style="height:1"/>
		</td>
	</tr>
</table>
<xsl:if expr="notEmpty(this)">
<img>
	<xsl:attribute name="width"><xsl:value-of select="//pictures/picture[0]/@filewidth"/></xsl:attribute>
	<xsl:attribute name="height"><xsl:value-of select="//pictures/picture[0]/@fileheight"/></xsl:attribute>
	<xsl:attribute name="src"><xsl:value-of select="//pictures/picture[0]/@file-href"/></xsl:attribute>
</img>
</xsl:if>
<div>
	<xsl:apply-templates select="//pictures/picture[0]/caption"/>
</div>
<div>
	<xsl:apply-templates select="//pictures/picture[0]/desc"/>
</div>
</center>
</div>
</body>
</html>
</xsl:template>


<!-- this is what gets generated for the actual gallery -->
<xsl:template match="xml[@output = 'real']">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><xsl:value-of select="//options/@pageName"/></title>
</head>
<body>
<picture file-href="real_p.htm" /> 
<picture file-href="real_x.htm" />
<div align="center">
<center>
<layer visibility="hide">
<div style="display:none;">
	<xsl:attribute name="id">fpGalleryCaptions_<xsl:eval>rnd</xsl:eval></xsl:attribute>
	<xsl:for-each select="//caption"><div><xsl:apply-templates/></div></xsl:for-each>
</div>
<div style="display:none;">
	<xsl:attribute name="id">fpGalleryDescriptions_<xsl:eval>rnd</xsl:eval></xsl:attribute>
	<xsl:for-each select="//desc"><div><xsl:apply-templates/></div></xsl:for-each>	
</div>
</layer>
<script language="javascript">
<xsl:attribute name="src"><xsl:value-of select="//dependent-files/file[@name='scripts']/@path"/></xsl:attribute>
<xsl:comment/>
</script>
<table border="0" cellspacing="0" cellpadding="5" width="700" dir="ltr">
	<tr>
		<td nowrap="" align="center">	
		<layer visibility="hide">
		<img border="0" align="middle">
		<xsl:attribute name="src"><xsl:value-of select="//dependent-files/file[@name='prevdis']/@path"/></xsl:attribute> 
		<xsl:attribute name="lowsrc"><xsl:value-of select="//dependent-files/file[@name='prev']/@path"/></xsl:attribute>
		<xsl:attribute name="id">fpGalleryLeftBtn_<xsl:eval>rnd</xsl:eval></xsl:attribute>
		<xsl:attribute name="onclick">JavaScript:fp_ScrollLeft(<xsl:eval>rnd</xsl:eval>)</xsl:attribute>
		</img>
		</layer>
		
		<script language="JavaScript1.1">
		<xsl:comment>
		if (fp_ie4()) {
			document.write("&lt;span align='center' style='width:<xsl:eval>getWidth(this)</xsl:eval>;overflow:hidden' id='fpGalleryListCell_<xsl:eval>rnd</xsl:eval>'&gt;");
		}
		if (fp_ns6()) {			
			document.getElementById("fpGalleryLeftBtn_<xsl:eval>rnd</xsl:eval>").style.visibility="hidden"
		}
		</xsl:comment>
		</script>
			<xsl:for-each select="//pictures">
				<xsl:apply-templates />
			</xsl:for-each>
			<span><xsl:attribute name="style">width:0;height:<xsl:eval>getHeight(this)</xsl:eval>;visibility:hidden</xsl:attribute>.</span>
		<script language="JavaScript1.1">
		<xsl:comment>
		if (fp_ie4()) {
			document.write("&lt;/span&gt;");
		}
		</xsl:comment>
		</script>

		<layer visibility="hide">
		<xsl:choose>
		<xsl:when expr="disableRight(this)">
			<img border="0" align="middle">
			<xsl:attribute name="src"><xsl:value-of select="//dependent-files/file[@name='nextdis']/@path"/></xsl:attribute> 
			<xsl:attribute name="lowsrc"><xsl:value-of select="//dependent-files/file[@name='next']/@path"/></xsl:attribute>
			<xsl:attribute name="id">fpGalleryRightBtn_<xsl:eval>rnd</xsl:eval></xsl:attribute>
			<xsl:attribute name="onclick">JavaScript:fp_ScrollRight(<xsl:eval>rnd</xsl:eval>)</xsl:attribute>
			<script language="JavaScript1.1">
				if (fp_ns6()) {			
						document.getElementById("fpGalleryRightBtn_<xsl:eval>rnd</xsl:eval>").style.visibility="hidden"
				}

			</script>
			</img>
		</xsl:when>
		<xsl:otherwise>
			<img border="0" align="middle">
			<xsl:attribute name="src"><xsl:value-of select="//dependent-files/file[@name='next']/@path"/></xsl:attribute> 
			<xsl:attribute name="lowsrc"><xsl:value-of select="//dependent-files/file[@name='nextdis']/@path"/></xsl:attribute>
			<xsl:attribute name="id">fpGalleryRightBtn_<xsl:eval>rnd</xsl:eval></xsl:attribute>
			<xsl:attribute name="onclick">JavaScript:fp_ScrollRight(<xsl:eval>rnd</xsl:eval>)</xsl:attribute>
			</img>
			<script language="JavaScript1.1">
				rightdisabled = false
				widthLength = <xsl:eval>getWidth(this)</xsl:eval>
				if (fp_ns6()) {			
						document.getElementById("fpGalleryRightBtn_<xsl:eval>rnd</xsl:eval>").style.visibility="hidden"
				}

			</script>
		</xsl:otherwise>
		</xsl:choose>
		</layer>
		<hr width='95%' size='1' color='#000000' />
		</td>
	</tr>
</table>
<img>
	<xsl:attribute name="id">fpGalleryMainImg_<xsl:eval>rnd</xsl:eval></xsl:attribute>
	<xsl:attribute name="name">fpGalleryMainImg_<xsl:eval>rnd</xsl:eval></xsl:attribute>				
	<xsl:attribute name="width"><xsl:value-of select="//pictures/picture[0]/@filewidth"/></xsl:attribute>
	<xsl:attribute name="height"><xsl:value-of select="//pictures/picture[0]/@fileheight"/></xsl:attribute>
	<xsl:attribute name="src"><xsl:value-of select="//pictures/picture[0]/@file-href"/></xsl:attribute>
	<xsl:attribute name="title"><xsl:value-of select="//pictures/picture[0]/caption"/></xsl:attribute>
</img>
<layer visibility="hide">
<div>
	<xsl:attribute name="id">fpGalleryCaptionCell_<xsl:eval>rnd</xsl:eval></xsl:attribute>
	<xsl:apply-templates select="//pictures/picture[0]/caption"/>
</div>
<div>
	<xsl:attribute name="id">fpGalleryDescCell_<xsl:eval>rnd</xsl:eval></xsl:attribute>
	<xsl:apply-templates select="//pictures/picture[0]/desc"/>
</div>
</layer>
</center>
</div>
</body>
</html>
</xsl:template>

<!-- this is the output we generate for each sub page of the gallery -->
<xsl:template match="xml[@output='subpage']">
<html>
<head>
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
	<xsl:if expr="previewImgChk(this)">
	<xsl:choose>
	<xsl:when expr="isPreviewOutput(this)">
		<img hspace="10" vspace="5" border="0">
			<xsl:attribute name="src"><xsl:value-of select="@thumb-href"/></xsl:attribute>
			<xsl:attribute name="width"><xsl:value-of select="@thumbwidth"/></xsl:attribute>
			<xsl:attribute name="height"><xsl:value-of select="@thumbheight"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="./caption"/></xsl:attribute>
			<xsl:attribute name="align">absmiddle</xsl:attribute>						
		</img>
	</xsl:when>
	<xsl:otherwise>
		<xsl:if expr="BreakLine(this)">
			<script language="JavaScript1.1">
			<xsl:comment>
			if (fp_ns6()) {			
				document.write("<br />");
			}
			</xsl:comment>
			</script>
		</xsl:if>

		<a target="_self"><xsl:attribute name="href">Javascript:fp_ShowImg(document['fpphoto_<xsl:eval>getRandom()</xsl:eval>'],'<xsl:value-of select="@filewidth"/>','<xsl:value-of select="@fileheight"/>','<xsl:eval>rnd</xsl:eval>',<xsl:eval>childNumber(this) - 1</xsl:eval>);</xsl:attribute>
		<img hspace="10" vspace="5" border="0">
			<xsl:attribute name="src"><xsl:value-of select="@thumb-href"/></xsl:attribute>
			<xsl:attribute name="id">fpphoto_<xsl:eval>rnd2</xsl:eval></xsl:attribute>
			<xsl:attribute name="name">fpphoto_<xsl:eval>rnd2</xsl:eval></xsl:attribute>
			<xsl:attribute name="lowsrc"><xsl:value-of select="@file-href"/></xsl:attribute>
			<xsl:attribute name="width"><xsl:value-of select="@thumbwidth"/></xsl:attribute>
			<xsl:attribute name="height"><xsl:value-of select="@thumbheight"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="./caption"/></xsl:attribute>
			<xsl:attribute name="align">absmiddle</xsl:attribute>						
		</img></a>	
	</xsl:otherwise>
	</xsl:choose>
	</xsl:if>
</xsl:template>

<xsl:template match="caption | desc"><xsl:apply-templates /></xsl:template></xsl:stylesheet>