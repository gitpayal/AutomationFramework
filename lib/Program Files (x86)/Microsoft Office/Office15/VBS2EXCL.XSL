<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:bs="http://schemas.microsoft.com/visio/2003/brainstorming" 				xmlns="urn:schemas-microsoft-com:office:spreadsheet"
		 xmlns:o="urn:schemas-microsoft-com:office:office"
		 xmlns:x="urn:schemas-microsoft-com:office:excel"
		 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:variable name="MinCols">2</xsl:variable>
<xsl:template match="/bs:Brainstorm">
	<Workbook xmlns:html="http://www.w3.org/TR/REC-html40">
		 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office"/>	 
		 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel"/>	
		 <Styles>
		  <Style ss:ID="Default" ss:Name="Normal">
		   <Alignment ss:Vertical="Bottom"/>
		   <Borders/>
		   <Font/>
		   <Interior/>
		   <NumberFormat/>
		   <Protection/>
		  </Style>
		  <Style ss:ID="s21" ss:Name="Hyperlink">
		   <Font ss:Color="#0000FF" ss:Underline="Single"/>
		  </Style>
		  <Style ss:ID="s22">
		   <Font x:Family="Swiss" ss:Bold="1"/>
		  </Style>
		 	</Styles>
		 	<Worksheet ss:Name="Sheet1">		 			 			 		 	
			 	<Table>	
			 			<xsl:variable name="PropCount" select="count(//bs:label[not(.=preceding::bs:label)])"/>		 			
			 			<xsl:attribute name="ss:ExpandedColumnCount">
					 		<xsl:value-of select="($PropCount + $MinCols)"/>
					 	</xsl:attribute>					 	
					 	<xsl:variable name="RowCount" select="count(//bs:topic)"/>
					 	<xsl:variable name="MinRows">1</xsl:variable>
					 	<xsl:attribute name="ss:ExpandedRowCount">	
					 		<xsl:choose>
						 		<xsl:when test="$PropCount	&gt;	0">
						 		  	<xsl:value-of select="($RowCount + $MinRows)"/>	
						 		 </xsl:when>
						 		 <xsl:otherwise>
						 		 	<xsl:value-of select="($RowCount)"/>
						 		 </xsl:otherwise>
					 		</xsl:choose> 						
					 	</xsl:attribute>			 	
			 			<Column ss:Index="1"/>			 			 			
			 			<Column ss:Index="2"/>			 			
			 			<xsl:for-each select="//bs:id[not(.=preceding::bs:id)]">
			 				<xsl:sort select ="."/>	
			 				<xsl:variable name="ColID" select="."/>		 								 				
				 				<Column>
					 				<xsl:attribute name="ss:Index">				 									 		
									  <xsl:value-of select="($ColID + $MinCols)"/>				
							 		</xsl:attribute>
				 				</Column>			 				
			 			</xsl:for-each>
			 			<xsl:if test="$PropCount	&gt;	0">			 			
						 	<Row ss:StyleID="s22">
						 	 	<Cell>
						 			<Data ss:Type="String"/>				 							 		
						 		</Cell>
						 		<Cell>
						 			<Data ss:Type="String"/>	
						 		</Cell>					 		
						 		<xsl:for-each select="//bs:label[not(.=preceding::bs:label)]">	
									<xsl:sort select ="./bs:id" data-type="number"/>
			 						 <Cell>
			 						 	<Data ss:Type="String">
					 						<xsl:value-of select="."/>
					 					</Data>
					 				</Cell>
			 					</xsl:for-each>		 										 			
						 	</Row>	
						</xsl:if>					
						<xsl:apply-templates select="parent::node()/descendant::bs:topic"/>
					</Table>				 	
		 </Worksheet>
	</Workbook>			 
	</xsl:template>
	<xsl:template match="bs:topic">		
		<Row>
			<Cell ss:Index="1">
				<Data ss:Type="String">
					<xsl:value-of select="@bs:TopicID"/>
				</Data>			
			</Cell>
			<xsl:choose>
					<xsl:when test="(not(bs:hyperlink))">
						<Cell ss:Index="2">
								<Data ss:Type="String">
									<xsl:value-of select="bs:text"/>
								</Data>	
						</Cell>	
					</xsl:when>
					<xsl:otherwise>			
					 <Cell  ss:Index="2" ss:StyleID="s21">
							<xsl:attribute name="ss:HRef">
						 		<xsl:value-of select="bs:hyperlink/bs:address"/>
						 	</xsl:attribute>		
							<Data ss:Type="String">
								<xsl:value-of select="bs:text"/>
							</Data>			
						</Cell>
					</xsl:otherwise>
				</xsl:choose>	 
			<xsl:for-each select="./bs:prop">
				<xsl:sort select ="./bs:id" data-type="number"/>
				<xsl:variable name="ID" select="./bs:id"/>
				<Cell>
					<xsl:attribute name="ss:Index">
						 <xsl:value-of select="($ID + $MinCols)"/>
					</xsl:attribute>
					<Data ss:Type="String">
						<xsl:value-of select="./bs:value"/>
					</Data>			
				</Cell>
			</xsl:for-each>
		</Row>		
	</xsl:template>
</xsl:stylesheet>
