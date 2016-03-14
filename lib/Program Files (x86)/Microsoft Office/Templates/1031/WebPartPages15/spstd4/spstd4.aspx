﻿<!-- _lcid="1031" _version="11.0.5120" _dal="1" -->
<!-- _LocalBinding -->

<%@ Page language="C#" Inherits="_INHERITS_" %>
<%@ Register Tagprefix="SharePoint" Namespace="Microsoft.SharePoint.WebControls" Assembly="_ASSEMBLY_" %>
<%@ Register Tagprefix="Utilities" Namespace="Microsoft.SharePoint.Utilities" Assembly="_ASSEMBLY_" %>
<%@ Import Namespace="Microsoft.SharePoint" %>
<%@ Register Tagprefix="WebPartPages" Namespace="Microsoft.SharePoint.WebPartPages" Assembly="_ASSEMBLY_" %>

<html dir="ltr">
<HEAD>
<meta name="WebPartPageExpansion" content="full">
<META Name="GENERATOR" Content="Microsoft SharePoint">
<META Name="ProgId" Content="SharePoint.WebPartPage.Document">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="Expires" content="0">
<Title ID=onetidTitle><!-- _locID_text="WebPartPageTitle" _locComment="{StringCategory=TXT}"-->Web Part Page</Title>
<Link REL="stylesheet" Type="text/css" HREF="/_layouts/_lang_/styles/ows.css">
<META Name="Microsoft Border" Content="none">
<link type="text/xml" rel='alternate' href="_vti_bin/spdisco.aspx" />
</HEAD>

<body>
	<form runat="server">
		<table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
			<!-- Begin SharePoint Head Banner-->
			<tr>
				<td valign="top" width="100%">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td colspan="3" width="100%">
								<!--Top bar-->
								<table class="ms-bannerframe" border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td nowrap valign="middle"><img id="onetidHeadbnnr0" alt="Logo" src="/_layouts/images/logo.gif"></td>
										<td class=ms-banner width=99% nowrap ID="HBN100" valign="middle"><!--webbot bot="Navigation" 
											S-Type="sequence" 
											S-Orientation="horizontal" 
											S-Rendering="html" 
											S-Btn-Nml="<a ID='onettopnavbar#LABEL_ID#' href='#URL#' accesskey='J'>#LABEL#</a>"
											S-Btn-Sel="<a ID='onettopnavbar#LABEL_ID#' href='#URL#' accesskey='J'>#LABEL#</a>"
											S-Btn-Sep="&amp;nbsp;&amp;nbsp;&amp;nbsp;"
											B-Include-Home="FALSE" 
											B-Include-Up="FALSE" 
											S-Btn-Nobr="FALSE" 
											U-Page="sid:1002"
											S-Target --></td>
										<td class=ms-banner>&nbsp;&nbsp;</td>
										<td nowrap class=ms-banner style="padding-right: 7px"><SharePoint:PortalConnection runat="server"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- End SharePoint Head Banner-->
            <!-- Begin WebPartPage Title -->
            <tr>
            	<td valign="top" width="100%">
					<WebPartPages:WebPartZone runat="server" Title="loc:TitleBar" ID="TitleBar" LockLayout="true" AllowPersonalization="false">
						<WebPartPages:TitleBarWebPart runat="server" WebPart="true">
							<WebPart xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://schemas.microsoft.com/WebPart/v2">
								<Title>Webpartseiten-Titelleiste</Title>
								<FrameType>None</FrameType>
								<Description></Description>
								<IsIncluded>true</IsIncluded>
								<PartOrder>1</PartOrder>
								<FrameState>Normal</FrameState>
								<Height></Height>
								<Width></Width>
								<AllowRemove>false</AllowRemove>
								<AllowZoneChange>true</AllowZoneChange>
								<AllowMinimize>false</AllowMinimize>
								<IsVisible>true</IsVisible>
								<DetailLink></DetailLink>
								<HelpLink></HelpLink>
								<Dir>Default</Dir>
								<PartImageSmall></PartImageSmall>
								<MissingAssembly></MissingAssembly>
								<PartImageLarge></PartImageLarge>
								<IsIncludedFilter></IsIncludedFilter>
								<ExportControlledProperties>false</ExportControlledProperties>
								<ConnectionID>00000000-0000-0000-0000-000000000000</ConnectionID>
								<Image xmlns="http://schemas.microsoft.com/WebPart/v2/TitleBar">/_layouts/images/wpicon.gif</Image>
								<HeaderTitle xmlns="http://schemas.microsoft.com/WebPart/v2/TitleBar">Webpartseite</HeaderTitle>
							</WebPart>
						</WebPartPages:TitleBarWebPart>
					</WebPartPages:WebPartZone>
            	</td>
            </tr>
            <!-- End WebPartPage Title -->
            <tr>
            	<td valign="top" width="100%" height="100%" style="margin:4px" >
            		<!-- Begin Panel Layout -->
					<PlaceHolder id="MSO_ContentDiv" runat="server">
						<!-- MSTableType="layout" -->
						<table id="MSO_ContentTable" cellpadding="4" cellspacing="0" border="0" width="100%">
							<tr>
								<td id="_invisibleIfEmpty" name="_invisibleIfEmpty" colspan="2" valign="top" width="100%">
									<WebPartPages:WebPartZone runat="server" Title="loc:Header" ID="Header">
									</WebPartPages:WebPartZone>
								</td>
							</tr>
							<tr>
								<td id="_invisibleIfEmpty" name="_invisibleIfEmpty" valign="top" height="100%" width="100%">
									<WebPartPages:WebPartZone runat="server" Title="loc:Body" ID="Body">
									</WebPartPages:WebPartZone>
								</td>
								<td id="_invisibleIfEmpty" name="_invisibleIfEmpty" valign="top" height="100%">
									<WebPartPages:WebPartZone runat="server" Title="loc:RightColumn" ID="RightColumn">
									</WebPartPages:WebPartZone>
								</td>
							</tr>
							<script language="javascript">if(typeof(MSOLayout_MakeInvisibleIfEmpty) == "function") {MSOLayout_MakeInvisibleIfEmpty();}</script>
						</table>
					</PlaceHolder>
					<!-- End Panel Layout -->
				</td>
			</tr>
        </table>
    </form>
</body>

</html>
