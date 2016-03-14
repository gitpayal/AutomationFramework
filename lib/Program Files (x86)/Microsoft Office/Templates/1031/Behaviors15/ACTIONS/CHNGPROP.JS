
// globals

var oSelectedElement	= null;
var aoElementMatches	= new Array();

//////////////////////////////////////////////////////////

function onOk()
{
	var oItems = theForm.lvProperties.ListItems;

	if( ( null != oSelectedElement ) &&
		( 0 < oItems.Count ) )
	{
		window.returnValue = RETURN_OK;
		window.close();
	}
	else
	{
		alert( L_OkFailed_ErrorMessage );
	}
}

//////////////////////////////////////////////////////////

function onCancel()
{
	window.returnValue = RETURN_CANCEL;
	window.close();
}

//////////////////////////////////////////////////////////

function onDelete()
{
	if( !theForm.btnDelete.disabled )
	{
		deleteSelectedProperty();
	}
	else
	{
		alert( L_NoSelection_ErrorMessage );
	}			
}

//////////////////////////////////////////////////////////

function onClickElementGroup()
{
	syncSelectors();
	
	selectedElementChanged();
}

//////////////////////////////////////////////////////////

function onEdit()
{
	if( !theForm.btnEdit.disabled )
	{
		invokeEditDialog();	
	}
	else
	{
		alert( L_NoSelection_ErrorMessage );
	}	
}

//////////////////////////////////////////////////////////

function onInsert()
{
	if( !theForm.btnInsert.disabled )
	{
		invokeInsertDialog()
	}
	else
	{
		alert( L_NoSelection_ErrorMessage );
	}		
}

//////////////////////////////////////////////////////////

function onFont()
{
	invokeFontDialog();
}

//////////////////////////////////////////////////////////

function onPosition()
{
	invokePositionDialog();
}

//////////////////////////////////////////////////////////

function onBorders()
{
	invokeBordersShadingDialog();
}

//////////////////////////////////////////////////////////

function onVisibility()
{
	invokeVisibilityDialog();
}

//////////////////////////////////////////////////////////

function onResize()
{
	resizeColumnHeaders();
}

//////////////////////////////////////////////////////////

function onTagIDChange()
{
	selectedElementChanged();
}

//////////////////////////////////////////////////////////

function onTagTypeChange()
{
	populateIDSelector();
	
	selectedElementChanged();
}

//////////////////////////////////////////////////////////

function selectedElementChanged()
{		
	var oPrevSelectedElement	= oSelectedElement;	

	if( theForm.radCurrentElement.checked )
	{
		oSelectedElement = getBehaviorElement();				
	}
	else
	{
		var nSelectedTag = theForm.ddTagType.selectedIndex;
		
		if( 0 <= nSelectedTag )
		{
			var oSelectedTag = theForm.ddTagType.options[nSelectedTag];	
			var strSelectedTag = oSelectedTag.value;
			
			var nSelectedID = theForm.ddTagID.selectedIndex;
			
			if( 0 <= nSelectedID )
			{
				var oSelectedID		= theForm.ddTagID.options[nSelectedID];
				var strSelectedID	= oSelectedID.value;			

				oSelectedElement = getElementByName( FrontPage.ActiveDocument, strSelectedID, strSelectedTag );
			}
		}
	}			
	
	if( oPrevSelectedElement != oSelectedElement )
		clearPropertyLV();		
}

//////////////////////////////////////////////////////////

function deleteSelectedProperty()
{
	var oSelected = theForm.lvProperties.SelectedItem;
	
	if( oSelected )
	{	
		removePropertyLVI( oSelected.Index );
	}

	highlightPropertySelection();
}

//////////////////////////////////////////////////////////

function deleteProperty( strName )
{	
	var oProperties = theForm.lvProperties.ListItems;

	for( var nProperty = 1; nProperty <= oProperties.Count; nProperty++ )
	{
		var oItem			= oProperties.Item( nProperty )
		var strPropertyName = getPropName( oItem );
		
		if( strName == strPropertyName ) 
		{
			removePropertyLVI( oItem.Index );					
		}
	}	
}

//////////////////////////////////////////////////////////

function highlightPropertySelection()
{
	oSelected = theForm.lvProperties.SelectedItem;
	
	if( oSelected )
	{
		theForm.lvProperties.SelectedItem = oSelected;
	}
}

//////////////////////////////////////////////////////////

function syncSelectors()
{
	var bDisableTagSelectors	= false;
	
	if( theForm.radCurrentElement.checked )
	{
		bDisableTagSelectors = true;
	
		populateTagSelectors();		
			
		theForm.ddTagType.disabled	= bDisableTagSelectors;
		theForm.ddTagID.disabled	= bDisableTagSelectors;			
	}
	else
	{
		populateTagSelectors();		
	}
}

//////////////////////////////////////////////////////////

function syncEditButtons()
{
	// enable, disable Edit and Delete buttons
	
	var oSelected = theForm.lvProperties.SelectedItem;

	if( null == oSelected )
	{
		theForm.btnEdit.disabled	= true;
		theForm.btnDelete.disabled	= true;
	}
	else
	{
		theForm.btnEdit.disabled	= false;
		theForm.btnDelete.disabled	= false;
	}
}

//////////////////////////////////////////////////////////

function resizeColumnHeaders()
{
	var nColumn1Width = theForm.lvProperties.clientWidth / 2;
	var nColumn2Width = theForm.lvProperties.clientWidth - nColumn1Width - 5;
	
	nColumn2Width = 0 < nColumn2Width ? nColumn2Width : 0;
	
	theForm.lvProperties.ColumnHeaders.Item( 2 ).Width	= nColumn1Width;
	theForm.lvProperties.ColumnHeaders.Item( 3 ).Width	= nColumn2Width;
}

//////////////////////////////////////////////////////////

function initializeForm()
{			
	window.onresize = onResize;		
		
	theForm.lvProperties.ColumnHeaders.Clear();	

	var oHeader0 = theForm.lvProperties.ColumnHeaders.Add(
						theForm.lvProperties.ColumnHeaders.Count + 1,
						"", 
						"", 
						0 );									

	var oHeader1 = theForm.lvProperties.ColumnHeaders.Add(
						theForm.lvProperties.ColumnHeaders.Count + 1,
						L_strPropertiesCol1_TopicTitle, 
						L_strPropertiesCol1_TopicTitle, 
						0 );									
		
	var oHeader2 = theForm.lvProperties.ColumnHeaders.Add(
						theForm.lvProperties.ColumnHeaders.Count + 1,
						L_strPropertiesCol2_TopicTitle, 
						L_strPropertiesCol2_TopicTitle, 
						0 );
						
	var strFontSize = String( document.body.currentStyle.fontSize );
	var nPtIndex	= strFontSize.search( "pt$" );
	
	if( document.dir == "rtl" )
	{
		oHeader1.Alignment = 1;	// lvwColumnRight
		oHeader2.Alignment = 1;	// lvwColumnRight
	}	
	
	if( -1 != nPtIndex )
	{			
		theForm.lvProperties.Font.Size = Number( strFontSize.substr( 0, nPtIndex ) );
	}
	
	theForm.lvProperties.Font.Name = g_fontName;
	theForm.lvProperties.Font.Charset = g_fontCharSet;

	// define expando properties in case we're in IE5
	theForm.ddTagType.disabled	= false;
	theForm.ddTagID.disabled	= false;
				
	resizeColumnHeaders();	
	syncSelectors();
	
	highlightPropertySelection();
	
	selectedElementChanged();
	
	var oSel = getBehaviorElement();
	
	// check if we find an existing restore
	var strMouseOut = oSel.getAttribute( 'onmouseout' );
	
	if( strMouseOut )
	{
		if( -1 != strMouseOut.indexOf( "FP_changePropRestore()" ) )
			theForm.chkRestore.checked = true;
	}
	
	safeFocus( theForm.radCurrentElement );	
}

function enableFormControls( bEnable )
{	
	theForm.ddTagType.disabled		= !bEnable;
	theForm.ddTagID.disabled		= !bEnable;
	theForm.lvProperties.disabled	= !bEnable;
	theForm.btnEdit.disabled		= !bEnable;
	theForm.btnInsert.disabled		= !bEnable;
	theForm.btnDelete.disabled		= !bEnable;
	theForm.btnBorders.disabled		= !bEnable;	
	theForm.btnPosition.disabled	= !bEnable;	
	theForm.btnFont.disabled		= !bEnable;	
	theForm.btnVisibility.disabled	= !bEnable;	
}

//////////////////////////////////////////////////////////

function getElementName( oElement )
{	
	var strID = oElement.id ? String( oElement.id ) : "";
	
	if( 0 < strID.length )
	{
		return strID;
	}
		
	// these elements support "name" attributes
	if( stricmp( "a", oElement.tagName ) ||
		stricmp( "applet", oElement.tagName ) ||
		stricmp( "button", oElement.tagName ) ||
		stricmp( "embed", oElement.tagName ) ||
		stricmp( "form", oElement.tagName ) ||
		stricmp( "frame", oElement.tagName ) ||
		stricmp( "img", oElement.tagName ) ||
		stricmp( "iframe", oElement.tagName ) ||
		stricmp( "input", oElement.tagName ) ||
		stricmp( "link", oElement.tagName ) ||
		stricmp( "map", oElement.tagName ) ||
		stricmp( "object", oElement.tagName ) ||
		stricmp( "rt", oElement.tagName ) ||
		stricmp( "ruby", oElement.tagName ) ||		
		stricmp( "select", oElement.tagName ) ||
		stricmp( "textarea", oElement.tagName ) )
	{
		var strName = oElement.name ? String( oElement.name ) : "";

		if( 0 < strName.length ) 
		{
			return strName;
		}
	} 
	
	return "";
}

//////////////////////////////////////////////////////////

function setSelectedElement( strName )
{
	theForm.radSelectElement.checked = true;

	syncSelectors();
	
	var oElement	= getElementByName( FrontPage.ActiveDocument, strName );
	
	if( oElement )
	{
		var strTagName	= String( oElement.tagName );	
		var oOptions	= theForm.ddTagType.options;
				
		for( nOption = 0; nOption < oOptions.length; nOption++ )
		{
			var oOption			= oOptions[nOption];		

			if( stricmp( oOption.value, strTagName ) )
			{
				oOption.selected = true;
				
				populateIDSelector();
				
				var oOptions = theForm.ddTagID.options;
				
				for( nOption = 0; nOption < oOptions.length; nOption++ )
				{
					var oOption = oOptions[nOption];
					
					if( oOption.value == strName )
					{
						oOption.selected = true;
						
						oSelectedElement = oElement;
						
						return true;
					}
				}
						
				break;			
			}
		}	
		
		if( L_strNoElements_Text == theForm.ddTagType.value )
		{
			oOptions.length = 0;
		}
		
		// tag type not in filter drop down, add it
		var oOption = document.createElement( "OPTION" );
		
		oOption.text	= strTagName;
		oOption.value	= strTagName;		
		
		oOptions.add( oOption );	
		
		oOption.selected = true;		
		
		var oIDOptions = theForm.ddTagID.options;
		
		oIDOptions.length = 0;
		
		oOption = document.createElement( "OPTION" );
		
		oOption.text	= strName;
		oOption.value	= strName;
		
		oIDOptions.add( oOption );	
		
		oOption.selected = true;
		
		enableFormControls( true );

		return true;		
	}
	
	return false;
}

//////////////////////////////////////////////////////////

function getTagFilters()
{
	astrTags = new Array();

	astrTags[astrTags.length] = "BODY";
	astrTags[astrTags.length] = "BUTTON";
	astrTags[astrTags.length] = "DIV";
	astrTags[astrTags.length] = "FORM";
	astrTags[astrTags.length] = "IMG";
	astrTags[astrTags.length] = "INPUT";	
	astrTags[astrTags.length] = "OBJECT";
	astrTags[astrTags.length] = "P";
	astrTags[astrTags.length] = "SELECT";
	astrTags[astrTags.length] = "SPAN";
	astrTags[astrTags.length] = "TABLE";
	astrTags[astrTags.length] = "TD";
	astrTags[astrTags.length] = "TR";
	
	return astrTags;
}

//////////////////////////////////////////////////////////

function populateTagSelectors()
{
	populateTagTypeSelector();
	populateIDSelector();
}

//////////////////////////////////////////////////////////

function populateTagTypeSelector()
{	
	var oOptions = theForm.ddTagType.options;
	
	oOptions.length = 0;

	var aoMatches		= new Array();
	var aFoundTagNames	= new Array();
	var bUseCurrentElement	= theForm.radCurrentElement.checked;

	if( bUseCurrentElement )
	{
		aoMatches[0] = getBehaviorElement();
	}
	else
	{	
		aoMatches  = FrontPage.ActiveDocument.all;
	}

	for( var nMatch = 0; nMatch < aoMatches.length; nMatch++ )
	{
		var strName = getElementName( aoMatches[nMatch] );				

		if( bUseCurrentElement || ( 0 < strName.length ) )
		{
			var strTagName = aoMatches[nMatch].tagName;
			
			if( aFoundTagNames[strTagName] != true )
			{
				aFoundTagNames[strTagName] = true;
			
				var oOption = document.createElement( "OPTION" );
					
				oOption.text	= strTagName;
				oOption.value	= strTagName;
					
				oOptions.add( oOption );		
			}					
		}
	}
			
	// no named elements available
	if( 0 >= oOptions.length )
	{
		var oOption = document.createElement( "OPTION" );
		
		oOption.text	= L_strNoElements_Text;
		oOption.value	= L_strNoElements_Text;
		
		oOptions.add( oOption );			
	}
}

//////////////////////////////////////////////////////////

function populateIDSelector()
{	
	var oOptions = theForm.ddTagID.options;

	oOptions.length = 0;
	
	var	strTagFilter = String( theForm.ddTagType.value );		
	var aoElementMatches;
	
	if( theForm.radCurrentElement.checked )
	{
		aoElementMatches = new Array( getBehaviorElement() );			
	}
	else if( strTagFilter == L_strNoElements_Text )
	{
		var oOption = document.createElement( "OPTION" );
				
		oOption.text	= "";
		oOption.value	= "";
		
		oOptions.add( oOption );			
		
		aoElementMatches = new Array( 0 );	
	}
	else
	{
		aoElementMatches = getElementsByTagName( FrontPage.ActiveDocument, strTagFilter );	
	}
	
	// populate ID selector drop down
	
	for( var nElement = 0; nElement < aoElementMatches.length; nElement++ )
	{		
		var	strID 	= getElementName( aoElementMatches[nElement] );
		var strTag	= String( aoElementMatches[nElement].tagName );				

		if( 0 >= strID.length )
		{
			if( theForm.radCurrentElement.checked )
			{
				strID = L_strNoID_Text;
			}
			else			
			{
				// skip unID'd objects
				continue;
			}
		}
				
		var oOption = document.createElement( "OPTION" );
		
		oOption.text	= strID;
		oOption.value	= strID;
		
		oOptions.add( oOption );			
	}				
		
	if( strTagFilter != L_strNoElements_Text )
	{
		enableFormControls( true );
		
		theForm.ddTagID.selectedIndex = 0;		
	}
	else
	{		
		enableFormControls( false );
	}	
}

//////////////////////////////////////////////////////////

function invokeInsertDialog()
{			
	var aDialogArguments = new Array();
	
	aDialogArguments[0] = "insert";

	var astrReturn = FrontPage.ShowHTMLDialog( "InsertPr.htm", aDialogArguments );
	
	if( astrReturn )
	{
		var strName		= String( astrReturn[0] );
		var strValue	= String( astrReturn[1] );
		
		if( 0 < strName.length )
		{			
			insertProperty( strName, strValue );
		}
	}
}

//////////////////////////////////////////////////////////

function invokeEditDialog()
{
	var oSelected = theForm.lvProperties.SelectedItem;

	if( oSelected )
	{
		var aDialogArguments = new Array();
		
		aDialogArguments[0] = "edit";		
		aDialogArguments[1] = getPropName( oSelected );
		aDialogArguments[2] = getPropValue( oSelected );

		var astrReturn = FrontPage.ShowHTMLDialog( "InsertPr.htm", aDialogArguments );

		if( astrReturn )
		{
			var strName		= String( astrReturn[0] );	
			var strValue	= String( astrReturn[1] );
				
			if( 0 < strName.length )
			{			
				deleteSelectedProperty();
				var oProperty = insertProperty( strName, strValue );
				
				oProperty.selected = true;
			}
		}		
	}
}

//////////////////////////////////////////////////////////

function invokeVisibilityDialog()
{
	var oProperties 	= theForm.lvProperties.ListItems;
	var strVisibility 	= "";
	var nProperty		= -1;
	
	for( var nProperty = 1; nProperty <= oProperties.Count; nProperty++ )
	{
		var oProperty			= oProperties.Item( nProperty )
		var strPropertyName 	= getPropName( oProperty );
		
		if( "style.visibility" == strPropertyName.toLowerCase() )
		{
			strVisibility = getPropValue( oProperty );
			
			nProperty = oProperty.Index;
			
			break;
		}
	}
	
	var aDialogArguments = new Array();
	
	aDialogArguments[0] = "visibility";
	aDialogArguments[1] = strVisibility;

	var astrReturn = FrontPage.ShowHTMLDialog( "InsertPr.htm", aDialogArguments );

	if( astrReturn )
	{
		var strValue 	= String( astrReturn[0] );			
		var oProperty 	= insertProperty( "style.visibility", strValue );
			
		oProperty.selected = true;
	}
}

//////////////////////////////////////////////////////////

function insertProperty( strName, strValue )
{	
	strName = trimStringLeft( trimStringRight( strName ) );
	strValue = trimStringLeft( trimStringRight( strValue ) );
	
	var oProperty	= null;
	var oProperties = theForm.lvProperties.ListItems;

	for( var nProperty = 1; nProperty <= oProperties.Count; nProperty++ )
	{
		var oItem			= oProperties.Item( nProperty )
		var strPropertyName = getPropName( oItem );
		
		if( strName == strPropertyName ) 
		{
			oProperty = oItem;
			
			break;
		}
	}
	
	if( null == oProperty )
	{		
		var nIndex	= oProperties.Count + 1;
		var strKey	= "KEY_" + strName;
	
		oProperty = addPropertyLVI( nIndex, strKey, "" );
	}
	
	setPropName( oProperty, strName );
	setPropValue( oProperty, strValue );
	
	return oProperty;
}

//////////////////////////////////////////////////////////

function clearPropertyLV()
{
	theForm.lvProperties.ListItems.Clear();
	
	syncEditButtons();
}

//////////////////////////////////////////////////////////

function removePropertyLVI( nIndex )
{
	theForm.lvProperties.ListItems.Remove( nIndex );
	
	syncEditButtons();
}

//////////////////////////////////////////////////////////

function addPropertyLVI( nIndex, strKey, strName )
{
	var oProperty = theForm.lvProperties.ListItems.Add( nIndex, strKey,	strName );	
	
	oProperty.Tag = new Object();
	
	oProperty.Tag.strName = "";
	oProperty.Tag.strValue = "";	
	
	syncEditButtons();
	
	return oProperty;
}

//////////////////////////////////////////////////////////

function getPropName( oItem )
{
	return oItem.Tag.strName;
}

//////////////////////////////////////////////////////////

function getPropValue( oItem )
{
	return oItem.Tag.strValue;
}

//////////////////////////////////////////////////////////

function setPropName( oItem, strName )
{
	oItem.SubItems( 1 ) = strName;
	oItem.Tag.strName = strName;
}

//////////////////////////////////////////////////////////

function setPropValue( oItem, strValue )
{
	oItem.SubItems( 2 ) = strValue;
	oItem.Tag.strValue = strValue;
}

//////////////////////////////////////////////////////////

function convertCSSDOMtoName( strCSSDOM )
{
	var strCSSName = String( "" );
	var bPrevLowercase = false;	

	for( var nPos = 0; nPos < strCSSDOM.length; nPos++ )
	{
		if( isUpperCase( strCSSDOM.charAt( nPos ) ) )
		{
			if( bPrevLowerCase )
			{
				strCSSName += "-";							
			}

			strCSSName += strCSSDOM.charAt( nPos );
			
			bPrevLowerCase = false;			
		}
		else
		{
			strCSSName += strCSSDOM.charAt( nPos );
		
			bPrevLowerCase = true;
		}
	}
	
	return strCSSName;
}

function convertCSSNametoDOM( strCSSName )
{
	var strCSSDOM = String( "" );
	var bNextIsUppercase = false;	

	for( var nPos = 0; nPos < strCSSName.length; nPos++ )
	{
		var strChar = String( strCSSName.charAt( nPos ) );
	
		if( "-" == strChar )
		{
			bNextIsUppercase = true;
			
			continue;
		}		
	
		if( bNextIsUppercase )
		{
			strCSSDOM += strChar.toUpperCase();
			
			bNextIsUppercase = false
		}
		else
		{
			strCSSDOM += strChar.toLowerCase();
		}		
	}
	
	return strCSSDOM;
}


//////////////////////////////////////////////////////////

function gatherCSSProperties()
{
	var strCSS		= String( "" );	
	var oProperties = theForm.lvProperties.ListItems;

	for( var nProperty = 1; nProperty <= oProperties.Count; nProperty++ )
	{
		var oItem				= oProperties.Item( nProperty )
		var strPropertyName		= getPropName( oItem );
		var strPropertyValue	= getPropValue( oItem );

		var nMatchPos = strPropertyName.search( "^style." );
		
		if( -1 != nMatchPos )
		{	
			var strCSSName = convertCSSDOMtoName( strPropertyName.substr( nMatchPos + 6 ) ) + ":" + strPropertyValue + ";"
			
			strCSS += strCSSName;						
		}
	}
	
	return strCSS;
}

//////////////////////////////////////////////////////////

function splitCSSProperties( strCSS )
{
	function retValue( strName, strValue )
	{
		this.strName	= String( strName );
		this.strValue	= String( strValue );
	}
	
	var aoReturnValue	= new Array();
	var oCSSNames			= new VBArray( FrontPage.SplitArgs( strCSS, ";" ) );
	var	astrCSSNames		= oCSSNames.toArray();	
	
	for( var nStyle = 0; nStyle < astrCSSNames.length; nStyle++ )
	{
		var strStyle		= trimStringLeft( trimStringRight( astrCSSNames[nStyle] ) );
		
		if( 0 < strStyle.length )
		{
			var oStylePair			= new VBArray( FrontPage.SplitArgs( strStyle, ":" ) );
			var	astrStylePair		= oStylePair.toArray();	

			var strStyleName	= trimStringLeft( trimStringRight( astrStylePair[0] ) );
			var strStyleValue	= trimStringLeft( trimStringRight( astrStylePair[1] ) );
			var strStyleDOMName	= "style." + convertCSSNametoDOM( strStyleName );
			
			aoReturnValue[aoReturnValue.length] = new retValue( strStyleDOMName, strStyleValue );
		}
	}		
	
	return aoReturnValue;
}

//////////////////////////////////////////////////////////

function insertCSSProperties( strCSS )
{
	var aoStyles = splitCSSProperties( strCSS );
	
	for( var nStyle = 0; nStyle < aoStyles.length; nStyle++ )
	{
		var oStyle = aoStyles[nStyle];
	
		insertProperty( oStyle.strName, oStyle.strValue );
	}
}

//////////////////////////////////////////////////////////

function deleteCSSProperties( strCSS )
{
	var aoStyles = splitCSSProperties( strCSS );
	
	for( var nStyle = 0; nStyle < aoStyles.length; nStyle++ )
	{
		var oStyle = aoStyles[nStyle];
	
		deleteProperty( oStyle.strName );
	}
}

//////////////////////////////////////////////////////////

function invokeFontDialog()
{	
	var strCSSIn = gatherCSSProperties();	

	var strCSSOut = FrontPage.ShowFontDialog( strCSSIn );

	deleteCSSProperties( strCSSIn );
	
	insertCSSProperties( strCSSOut );
}

//////////////////////////////////////////////////////////

function invokePositionDialog()
{
	var strCSSIn = gatherCSSProperties();		

	var strCSSOut = FrontPage.ShowPositionDialog( strCSSIn );

	deleteCSSProperties( strCSSIn );
	
	insertCSSProperties( strCSSOut );
}

//////////////////////////////////////////////////////////

function invokeBordersShadingDialog()
{
	var strCSSIn = gatherCSSProperties();	

	var strCSSOut = FrontPage.ShowBordersShadingDialog( strCSSIn );

	deleteCSSProperties( strCSSIn );
	
	insertCSSProperties( strCSSOut );
}

/////////////////////////////////////////////////////////////////
//
// DHTML Scripting methods
//
/////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////
//
// $changeProp()
//
//  Arguments:
//		ID/name of element to change
//		1|0 change property restore?
//		( name, value ) pairs of properties to change

function FP_changeProp() {//v1.0
 var args=arguments,d=document,i,j,id=args[0],o=FP_getObjectByID(id),s,ao,v,x;
 d.$cpe=new Array(); if(o) for(i=2; i<args.length; i+=2) { v=args[i+1]; s="o"; 
 ao=args[i].split("."); for(j=0; j<ao.length; j++) { s+="."+ao[j]; if(null==eval(s)) { 
  s=null; break; } } x=new Object; x.o=o; x.n=new Array(); x.v=new Array();
 x.n[x.n.length]=s; eval("x.v[x.v.length]="+s); d.$cpe[d.$cpe.length]=x;
 if(s) eval(s+"=v"); }
}

/////////////////////////////////////////////////////////////////
//
// DHTML Scripting Extensions API methods
//
/////////////////////////////////////////////////////////////////

function getScript()
{
	return "FP_changeProp,FP_getObjectByID";
}

//////////////////////////////////////////////////////////

function getScriptCall()
{	
	var strArgs			= String( "" );
	var oProperties		= document.theForm.lvProperties.ListItems;	
	var oDoc			= FrontPage.ActiveDocument;

	for( var nProperty = 1; nProperty <= oProperties.Count; nProperty++ )
	{
		var oItem			= oProperties.Item( nProperty )
		var strPropertyName	= getPropName( oItem );
		var strPropertyValue= getPropValue( oItem );
		var strAdjective	= "";
		
		if( 0 < strArgs.length ) 
		{
			strArgs += ",";
		}
		
		strPropertyName = escapeString( strPropertyName );
		strPropertyValue = escapeString( strPropertyValue );
		
		// add appropriate adjective
		if( "src" == strPropertyName )
		{
			strAdjective = FP_ADJECTIVE_HREF;
			
			strPropertyValue = FrontPage.EncodeUrl( strPropertyValue );
		}
		else if( "id" == strPropertyName )
		{
			strAdjective = FP_ADJECTIVE_ID;
		}
		
		strArgs += "'" + strPropertyName + "'," + strAdjective + "'" + strPropertyValue + "'";
	}			
	
	var strID = getElementName( oSelectedElement );
	
	if( 0 >= strID.length )
	{
		strID = oSelectedElement.id = generateUniqueID( FrontPage.ActiveDocument, getDefaultIDPrefix( oSelectedElement.tagName ) );
	}	
	
	var bRestore = theForm.chkRestore.checked;
	var strRestore = bRestore ? "1" : "0";
	
	if( bRestore )
	{
		var oElmt		= getBehaviorElement();
		var strMouseOut = oElmt.getAttribute( "onmouseout" );		
		
		if( !strMouseOut ||
			-1 == strMouseOut.search( "FP_changePropRestore()" ) )
		{		
			oDoc.addScript( oElmt, "onmouseout", "FP_changePropRestore()" );
		}
	}
	else
	{		
		var oElmt = getBehaviorElement();
		var n;
		var strStatement;
		
		for( 
			strStatement = oDoc.getScript( oElmt, "onmouseout", n = 1 );
			strStatement != "";
			strStatement = oDoc.getScript( oElmt, "onmouseout", ++n ) )
		{
			if( -1 != strStatement.search( "FP_changePropRestore()" ) )
			{
				oDoc.removeScript( oElmt, "onmouseout", n );
				
				n--;
			}
		}
	}

	return "FP_changeProp(" + FP_ADJECTIVE_ID + "'" + strID + "'," + strRestore + "," + strArgs + ")";
}

//////////////////////////////////////////////////////////

function isScriptEnabled()
{		
	return true;
}

//////////////////////////////////////////////////////////

function initPropertyDialog( strScriptCall )
{
	var aArguments	= extractArgumentsFromScriptCall( strScriptCall );
	var nArguments	= aArguments.length;
	
	if( 2 <= nArguments )	
		if( aArguments[1].strArgument == '1' )
			theForm.chkRestore.checked = true;			
	
	var strSelectedName = unescapeString( trimQuotes( aArguments[0].strArgument ) );				
	
	if( setSelectedElement( strSelectedName ) )
	{
		for( var nProperty = 2; nProperty < aArguments.length; nProperty += 2 )
		{
			var strPropertyName		= unescapeString( trimQuotes( aArguments[nProperty].strArgument ) );
			var strPropertyValue	= unescapeString( trimQuotes( aArguments[nProperty+1].strArgument ) );
			
			if( aArguments[nProperty+1].bIsURL )
				strPropertyValue = FrontPage.DecodeUrl( strPropertyValue );
			
			insertProperty( strPropertyName, strPropertyValue );			
		}					
	}
	else
	{
		theForm.radCurrentElement.checked = true;
	
		alert( L_strSelectedNotFound_ErrorMessage );
	}		
} 

// SIG // Begin signature block
// SIG // MIIatAYJKoZIhvcNAQcCoIIapTCCGqECAQExCzAJBgUr
// SIG // DgMCGgUAMGcGCisGAQQBgjcCAQSgWTBXMDIGCisGAQQB
// SIG // gjcCAR4wJAIBAQQQEODJBs441BGiowAQS9NQkAIBAAIB
// SIG // AAIBAAIBAAIBADAhMAkGBSsOAwIaBQAEFCVHMVkuejJL
// SIG // rdgVoTsdnnx8EzkkoIIVkzCCA8QwggMtoAMCAQICEEe/
// SIG // GZXfjVJGQ/fbbUgNMaQwDQYJKoZIhvcNAQEFBQAwgYsx
// SIG // CzAJBgNVBAYTAlpBMRUwEwYDVQQIEwxXZXN0ZXJuIENh
// SIG // cGUxFDASBgNVBAcTC0R1cmJhbnZpbGxlMQ8wDQYDVQQK
// SIG // EwZUaGF3dGUxHTAbBgNVBAsTFFRoYXd0ZSBDZXJ0aWZp
// SIG // Y2F0aW9uMR8wHQYDVQQDExZUaGF3dGUgVGltZXN0YW1w
// SIG // aW5nIENBMB4XDTAzMTIwNDAwMDAwMFoXDTEzMTIwMzIz
// SIG // NTk1OVowUzELMAkGA1UEBhMCVVMxFzAVBgNVBAoTDlZl
// SIG // cmlTaWduLCBJbmMuMSswKQYDVQQDEyJWZXJpU2lnbiBU
// SIG // aW1lIFN0YW1waW5nIFNlcnZpY2VzIENBMIIBIjANBgkq
// SIG // hkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqcqypMzNIK8K
// SIG // fYmsh3XwtE7x38EPv2dhvaNkHNq7+cozq4QwiVh+jNtr
// SIG // 3TaeD7/R7Hjyd6Z+bzy/k68Numj0bJTKvVItq0g99bbV
// SIG // XV8bAp/6L2sepPejmqYayALhf0xS4w5g7EAcfrkN3j/H
// SIG // tN+HvV96ajEuA5mBE6hHIM4xcw1XLc14NDOVEpkSud5o
// SIG // L6rm48KKjCrDiyGHZr2DWFdvdb88qiaHXcoQFTyfhOpU
// SIG // wQpuxP7FSt25BxGXInzbPifRHnjsnzHJ8eYiGdvEs0dD
// SIG // mhpfoB6Q5F717nzxfatiAY/1TQve0CJWqJXNroh2ru66
// SIG // DfPkTdmg+2igrhQ7s4fBuwIDAQABo4HbMIHYMDQGCCsG
// SIG // AQUFBwEBBCgwJjAkBggrBgEFBQcwAYYYaHR0cDovL29j
// SIG // c3AudmVyaXNpZ24uY29tMBIGA1UdEwEB/wQIMAYBAf8C
// SIG // AQAwQQYDVR0fBDowODA2oDSgMoYwaHR0cDovL2NybC52
// SIG // ZXJpc2lnbi5jb20vVGhhd3RlVGltZXN0YW1waW5nQ0Eu
// SIG // Y3JsMBMGA1UdJQQMMAoGCCsGAQUFBwMIMA4GA1UdDwEB
// SIG // /wQEAwIBBjAkBgNVHREEHTAbpBkwFzEVMBMGA1UEAxMM
// SIG // VFNBMjA0OC0xLTUzMA0GCSqGSIb3DQEBBQUAA4GBAEpr
// SIG // +epYwkQcMYl5mSuWv4KsAdYcTM2wilhu3wgpo17IypMT
// SIG // 5wRSDe9HJy8AOLDkyZNOmtQiYhX3PzchT3AxgPGLOIez
// SIG // 6OiXAP7PVZZOJNKpJ056rrdhQfMqzufJ2V7duyuFPrWd
// SIG // tdnhV/++tMV+9c8MnvCX/ivTO1IbGzgn9z9KMIID/zCC
// SIG // AuegAwIBAgIQDekr8NTYKYgYMgUJXpp2iDANBgkqhkiG
// SIG // 9w0BAQUFADBTMQswCQYDVQQGEwJVUzEXMBUGA1UEChMO
// SIG // VmVyaVNpZ24sIEluYy4xKzApBgNVBAMTIlZlcmlTaWdu
// SIG // IFRpbWUgU3RhbXBpbmcgU2VydmljZXMgQ0EwHhcNMDMx
// SIG // MjA0MDAwMDAwWhcNMDgxMjAzMjM1OTU5WjBXMQswCQYD
// SIG // VQQGEwJVUzEXMBUGA1UEChMOVmVyaVNpZ24sIEluYy4x
// SIG // LzAtBgNVBAMTJlZlcmlTaWduIFRpbWUgU3RhbXBpbmcg
// SIG // U2VydmljZXMgU2lnbmVyMIIBIjANBgkqhkiG9w0BAQEF
// SIG // AAOCAQ8AMIIBCgKCAQEAslAoSN3TaHqEGERmdV1+xLif
// SIG // Yyb/PUOcfBE4ECVVc9l1J2n9TrkgXNMK+aAbKu1VViFh
// SIG // 2B7b5Lwza8fv3aM3ZY4bkwy2Ux5cfGY1XwWKRf52Tt9T
// SIG // gKKBIJ2uiFyiCPflMPnuIjdMQgrO38YfxNZV6YE/tVKj
// SIG // LKoBevKiqo01/p/mXWoFnz1r47+WwP7MYPlA5wegROuB
// SIG // UW6lKvK2ihAo7Y/cBqCGUJp7SggNMB3KEJ5r9+lYrgSp
// SIG // QJmyKOiPFqw841NvS9M1nbVvZB2zliy7Ped56216+Rbm
// SIG // Jq2v75lTt0Aslbh5qv7UUqspdH5C7DkeomoW5lm7JGjY
// SIG // AIBDEIeAawIDAQABo4HKMIHHMDQGCCsGAQUFBwEBBCgw
// SIG // JjAkBggrBgEFBQcwAYYYaHR0cDovL29jc3AudmVyaXNp
// SIG // Z24uY29tMAwGA1UdEwEB/wQCMAAwMwYDVR0fBCwwKjAo
// SIG // oCagJIYiaHR0cDovL2NybC52ZXJpc2lnbi5jb20vdHNz
// SIG // LWNhLmNybDAWBgNVHSUBAf8EDDAKBggrBgEFBQcDCDAO
// SIG // BgNVHQ8BAf8EBAMCBsAwJAYDVR0RBB0wG6QZMBcxFTAT
// SIG // BgNVBAMTDFRTQTIwNDgtMS01NDANBgkqhkiG9w0BAQUF
// SIG // AAOCAQEAh3hw2k5SASBb4HnJgjDE/bkZlr2RAMO9zc3G
// SIG // 9A7Y//lNwDNiMBHF9XQb1JLeX5wgE7F8Rb5QzYPngBeD
// SIG // pyeTZxNG+8q4mEEDzJtRWwWLf6hv8xtQGyQu8mmNbCL3
// SIG // u8oWle0MdMBod9nrmWKHwXOQ+Il0eiOro5h7l7H3jylx
// SIG // TS51G0hB2vC1DSBU1negl4Jjaf0Jz4rwdbsJm9n5EVUm
// SIG // mmEyvnoCsHuGvqLDiyIseNE1drySc1z5ueZMFQojzOTS
// SIG // 1DQuSUAVPA9geiTGpWbvls9w6z7n9A1+3NF8o3ZxacGc
// SIG // T0cwNSGxoq8aYjwr2Y6qKgd72BizXHvinaVv/jyJrTCC
// SIG // BBIwggL6oAMCAQICDwDBAIs8PIgR0T72Y+zfQDANBgkq
// SIG // hkiG9w0BAQQFADBwMSswKQYDVQQLEyJDb3B5cmlnaHQg
// SIG // KGMpIDE5OTcgTWljcm9zb2Z0IENvcnAuMR4wHAYDVQQL
// SIG // ExVNaWNyb3NvZnQgQ29ycG9yYXRpb24xITAfBgNVBAMT
// SIG // GE1pY3Jvc29mdCBSb290IEF1dGhvcml0eTAeFw05NzAx
// SIG // MTAwNzAwMDBaFw0yMDEyMzEwNzAwMDBaMHAxKzApBgNV
// SIG // BAsTIkNvcHlyaWdodCAoYykgMTk5NyBNaWNyb3NvZnQg
// SIG // Q29ycC4xHjAcBgNVBAsTFU1pY3Jvc29mdCBDb3Jwb3Jh
// SIG // dGlvbjEhMB8GA1UEAxMYTWljcm9zb2Z0IFJvb3QgQXV0
// SIG // aG9yaXR5MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB
// SIG // CgKCAQEAqQK9wXDmO/JOGyifl3heMOqiqY0lX/j+lUyj
// SIG // t/6doiA+fFGim6KPYDJr0UJkee6sdslU2vLrnIYcj5+E
// SIG // ZrPFa3piI9YdPN4PAZLolsS/LWaammgmmdA6LL8MtVgm
// SIG // wUbnCj44liypKDmo7EmDQuOED7uabFVhrIJ8oWAtd0zp
// SIG // mbRkO5pQHDEIJBSfqeeRKxjmPZhjFGBYBWWfHTdSh/en
// SIG // 75QCxhvTv1VFs4mAvzrsVJROrv2nem10Tq8YzJYJKCEA
// SIG // V5BgaTe7SxIHPFb/W/ukZgoIptKBVlfvtjteFoF3BNr2
// SIG // vq6Alf6wzX/WpxpyXDzKvPAIoyIwswaFybMgdxOF3wID
// SIG // AQABo4GoMIGlMIGiBgNVHQEEgZowgZeAEFvQcO9pcp4j
// SIG // UX4Usk2O/8uhcjBwMSswKQYDVQQLEyJDb3B5cmlnaHQg
// SIG // KGMpIDE5OTcgTWljcm9zb2Z0IENvcnAuMR4wHAYDVQQL
// SIG // ExVNaWNyb3NvZnQgQ29ycG9yYXRpb24xITAfBgNVBAMT
// SIG // GE1pY3Jvc29mdCBSb290IEF1dGhvcml0eYIPAMEAizw8
// SIG // iBHRPvZj7N9AMA0GCSqGSIb3DQEBBAUAA4IBAQCV6AvA
// SIG // jfOXGDXtuAEk2HcR81xgMp+eC8s+BZGIj8k65iHy8FeT
// SIG // LLWgR8hi7/zXzDs7Wqk2VGn+JG0/ycyq3gV83TGNPZ8Q
// SIG // cGq7/hJPGGnA/NBD4xFaIE/qYnuvqhnIKzclLb5loRKK
// SIG // JQ9jo/dUHPkhydYV81KsbkMyB/2CF/jlZ2wNUfa98VLH
// SIG // vefEMPwgMQmIHZUpGk3VHQKl8YDgA7Rb9LHdyFfuZUnH
// SIG // UlS2tAMoEv+Q1vAIj364l8WrNyzkeuSod+N2oADQaj/B
// SIG // 0jaK4EESqDVqG2rbNeHUHATkqEUEyFozOG5NHA1itwqi
// SIG // jNPVVD9GzRxVpnDbEjqHk3Wfp9KgMIIEyTCCA7GgAwIB
// SIG // AgIQaguZT8AA3qoR1NhAmqi+5jANBgkqhkiG9w0BAQQF
// SIG // ADBwMSswKQYDVQQLEyJDb3B5cmlnaHQgKGMpIDE5OTcg
// SIG // TWljcm9zb2Z0IENvcnAuMR4wHAYDVQQLExVNaWNyb3Nv
// SIG // ZnQgQ29ycG9yYXRpb24xITAfBgNVBAMTGE1pY3Jvc29m
// SIG // dCBSb290IEF1dGhvcml0eTAeFw0wMDEyMTAwODAwMDBa
// SIG // Fw0wNTExMTIwODAwMDBaMIGmMQswCQYDVQQGEwJVUzET
// SIG // MBEGA1UECBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMHUmVk
// SIG // bW9uZDEeMBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0
// SIG // aW9uMSswKQYDVQQLEyJDb3B5cmlnaHQgKGMpIDIwMDAg
// SIG // TWljcm9zb2Z0IENvcnAuMSMwIQYDVQQDExpNaWNyb3Nv
// SIG // ZnQgQ29kZSBTaWduaW5nIFBDQTCCASAwDQYJKoZIhvcN
// SIG // AQEBBQADggENADCCAQgCggEBAKKEFVPYCzAONJX/OhvC
// SIG // 8y97bTcjTfPSjOX9r/3FAjQfJMflodxU7H4CdEer2zJY
// SIG // FhRRKTjxfrK0jDpHtTlOblTCMQw6bfvNzctQnBuup9jZ
// SIG // SiY/tcXLj5biSfJt2OmWPt4Fz/CmVTetL2DNgGFCoUlU
// SIG // Sg8Yt0vZk5kwWkd1ZLTTu922qwydT7hzOxg6qrSHjLCI
// SIG // sE1PH04RtTOA3w06ZG9ExzS9SpObvKYd+QUjTmApj8wq
// SIG // 8oSama2o2wpwe9Y0QZClt2bHXBsdozMOm1QDGj+YkLjM
// SIG // 5z0EdEMcj/c55rOsSHprKg5iAWE5dm79PpgHSxTxAUb9
// SIG // FQDgR9pP5AXkgCUCAQOjggEoMIIBJDATBgNVHSUEDDAK
// SIG // BggrBgEFBQcDAzCBogYDVR0BBIGaMIGXgBBb0HDvaXKe
// SIG // I1F+FLJNjv/LoXIwcDErMCkGA1UECxMiQ29weXJpZ2h0
// SIG // IChjKSAxOTk3IE1pY3Jvc29mdCBDb3JwLjEeMBwGA1UE
// SIG // CxMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSEwHwYDVQQD
// SIG // ExhNaWNyb3NvZnQgUm9vdCBBdXRob3JpdHmCDwDBAIs8
// SIG // PIgR0T72Y+zfQDAQBgkrBgEEAYI3FQEEAwIBADAdBgNV
// SIG // HQ4EFgQUKVy5G7bNM+67nll99+XKLsQNNCgwGQYJKwYB
// SIG // BAGCNxQCBAweCgBTAHUAYgBDAEEwCwYDVR0PBAQDAgFG
// SIG // MA8GA1UdEwEB/wQFMAMBAf8wDQYJKoZIhvcNAQEEBQAD
// SIG // ggEBAEVY4ppBf/ydv0h3d66M2eYZxVe0Gr20uV8CoUVq
// SIG // OVn5uSecLU2e/KLkOIo4ZCJC37kvKs+31gbK6yq/4BqF
// SIG // fNtRCD30ItPUwG2IgRVEX2SDZMSplCyK25A3Sg+36NRh
// SIG // j3Z24dkl/ySElY0EVlSUoRw6PoK87qWHjByMS3lftUn6
// SIG // XjJpOh9UrXVN32TnMDzbZElE+/vEHEJx5qA9Re5rAJ+s
// SIG // Qr26EbNW5PvVoiqB2B9OolW+J49wpqJsG/9UioK8gUum
// SIG // obFmeqkXp8sGwEfrprPpMRVTPSoEv/9zSNyLJ0P8Y+ju
// SIG // JIdbvjbR6DH1Mtle33l6ujCsaYZK+4wRvxuNVFkwggTh
// SIG // MIIDyaADAgECAgphDn2nAAAAAABIMA0GCSqGSIb3DQEB
// SIG // BQUAMIGmMQswCQYDVQQGEwJVUzETMBEGA1UECBMKV2Fz
// SIG // aGluZ3RvbjEQMA4GA1UEBxMHUmVkbW9uZDEeMBwGA1UE
// SIG // ChMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSswKQYDVQQL
// SIG // EyJDb3B5cmlnaHQgKGMpIDIwMDAgTWljcm9zb2Z0IENv
// SIG // cnAuMSMwIQYDVQQDExpNaWNyb3NvZnQgQ29kZSBTaWdu
// SIG // aW5nIFBDQTAeFw0wMzEwMjUwNTU5MTRaFw0wNTAxMjUw
// SIG // NjA5MTRaMHQxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpX
// SIG // YXNoaW5ndG9uMRAwDgYDVQQHEwdSZWRtb25kMR4wHAYD
// SIG // VQQKExVNaWNyb3NvZnQgQ29ycG9yYXRpb24xHjAcBgNV
// SIG // BAMTFU1pY3Jvc29mdCBDb3Jwb3JhdGlvbjCCASIwDQYJ
// SIG // KoZIhvcNAQEBBQADggEPADCCAQoCggEBALIthwm1XN9V
// SIG // metSYufT9ONFcakyv5TyDukNrf6dxwRqWE6cpNHYREH7
// SIG // ZH4PZe7IF9pN29nWULQMVltsFohLvwPuUEiD7E+Ik5pR
// SIG // 45QZf/qzl6XOYG2f3UyTOL3NNFlx5obO6YOZoJa46uBE
// SIG // WxNCuTpITlRy9wiW5ADEggF2Q69hwtv65cXwAhPd+DW0
// SIG // Dw1SNkZ0Q7GiypzdfpnxNRF3+xUmAY7P4LgEeCoV/XLG
// SIG // YHaRDOdPshgYG2mJtPEvIRtm6sqRx0YNuRdYcVhWhmUj
// SIG // 0QIyrmSgb9pSlf373Y009cEMNaNH1+kbavoPRbToMh1w
// SIG // Gb3R+eVkH+uHN+pv1A2Dj/0CAwEAAaOCAUAwggE8MA4G
// SIG // A1UdDwEB/wQEAwIGwDAdBgNVHQ4EFgQUgo+nF2RkckTV
// SIG // MK4WhOJTkMs+ntQwEwYDVR0lBAwwCgYIKwYBBQUHAwMw
// SIG // gakGA1UdIwSBoTCBnoAUKVy5G7bNM+67nll99+XKLsQN
// SIG // NCihdKRyMHAxKzApBgNVBAsTIkNvcHlyaWdodCAoYykg
// SIG // MTk5NyBNaWNyb3NvZnQgQ29ycC4xHjAcBgNVBAsTFU1p
// SIG // Y3Jvc29mdCBDb3Jwb3JhdGlvbjEhMB8GA1UEAxMYTWlj
// SIG // cm9zb2Z0IFJvb3QgQXV0aG9yaXR5ghBqC5lPwADeqhHU
// SIG // 2ECaqL7mMEoGA1UdHwRDMEEwP6A9oDuGOWh0dHA6Ly9j
// SIG // cmwubWljcm9zb2Z0LmNvbS9wa2kvY3JsL3Byb2R1Y3Rz
// SIG // L0NvZGVTaWduUENBLmNybDANBgkqhkiG9w0BAQUFAAOC
// SIG // AQEASkjcMrB6Zz+LKwG0DPXbeUFd4Dftv1Fzf9b5fmxt
// SIG // f5+8paRIHqjj7ab1PIZqkVlM1FMU697uzcPckKMIsYtF
// SIG // Ov1qcPDOBAY2LjC0j0yr6t1DWc2DoNfupEmuECRPmf/V
// SIG // Avnshkyl2CLoARct6xOQmZoRgDMMGYh+BhnUMe1MyX67
// SIG // JMexveVUO+p3EImHbOCcgI3SEORKl6nqVxnXeCsNmzUn
// SIG // sTi+0r+tx/DmbVqfEPbK8fLgMKsBphPItjlkvQkJ7Yu2
// SIG // FBhqeinGdEs7R/EUycveF92yXNjMOYc8OUjFMHyOdVfu
// SIG // 7RugDYVyNWRJvqFJOOKanEp8gmQNAXhfVv6IdDGCBI0w
// SIG // ggSJAgEBMIG1MIGmMQswCQYDVQQGEwJVUzETMBEGA1UE
// SIG // CBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMHUmVkbW9uZDEe
// SIG // MBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSsw
// SIG // KQYDVQQLEyJDb3B5cmlnaHQgKGMpIDIwMDAgTWljcm9z
// SIG // b2Z0IENvcnAuMSMwIQYDVQQDExpNaWNyb3NvZnQgQ29k
// SIG // ZSBTaWduaW5nIFBDQQIKYQ59pwAAAAAASDAJBgUrDgMC
// SIG // GgUAoIGqMBkGCSqGSIb3DQEJAzEMBgorBgEEAYI3AgEE
// SIG // MBwGCisGAQQBgjcCAQsxDjAMBgorBgEEAYI3AgEVMCMG
// SIG // CSqGSIb3DQEJBDEWBBTFmpGcHQPJdr80Sn5NiECNteZl
// SIG // 7TBKBgorBgEEAYI3AgEMMTwwOqAYgBYAYwBoAG4AZwBw
// SIG // AHIAbwBwAC4AagBzoR6AHGh0dHA6Ly9vZmZpY2UubWlj
// SIG // cm9zb2Z0LmNvbSAwDQYJKoZIhvcNAQEBBQAEggEAatLt
// SIG // Mpfh4AqtHv5Xv8+DXcLw8tYqW1yd1CCzq1W6LLFkFQ16
// SIG // fsdXuSCWcG5ubZ9CLL+q7O758TPmuA2AfKS+dH3G5pbC
// SIG // 61SRbZEZb1NGjY32z712BtoNEtm5/vWgIbdi5gn6vUbj
// SIG // 18dybfxmgwfWffxmRPawf3IKuLcs68GiWs56iDIAtcYw
// SIG // oGLYd5l09adl8BTlO3PwzFV38Ol/yrHxesg3lLfZ/YFS
// SIG // XlF6aa21ub+IVpa96/isXs1s8WJjvKU68GOR0eUfoCDy
// SIG // si2v2HzmE/KoBk6UqwoebxaH8qlOUWO4gxmHAwwGLjaC
// SIG // r9OFsSaNvvh6MvvbkNN0zRT3sdgHA6GCAf8wggH7Bgkq
// SIG // hkiG9w0BCQYxggHsMIIB6AIBATBnMFMxCzAJBgNVBAYT
// SIG // AlVTMRcwFQYDVQQKEw5WZXJpU2lnbiwgSW5jLjErMCkG
// SIG // A1UEAxMiVmVyaVNpZ24gVGltZSBTdGFtcGluZyBTZXJ2
// SIG // aWNlcyBDQQIQDekr8NTYKYgYMgUJXpp2iDAMBggqhkiG
// SIG // 9w0CBQUAoFkwGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEH
// SIG // ATAcBgkqhkiG9w0BCQUxDxcNMDQwNDE2MDkzNzIwWjAf
// SIG // BgkqhkiG9w0BCQQxEgQQSqkSK3PLKuRhADC3SLCDzzAN
// SIG // BgkqhkiG9w0BAQEFAASCAQAXfeUVhAqTedcb2ZHktNBg
// SIG // nglcI02fRgo6GHcdBjW4V6l+kfEmkT8gycnDcVKfNzD+
// SIG // FFLeLgYgqzObTe8uRPRdacdeBcqLEEtHEU4WSPVTddie
// SIG // QDvt/8BQyiUAjJ0kRWyYKM37v7eG4ST8pLpWnO8mNPeQ
// SIG // 3ABR+ASFEJ3jwTgCxnX7//774fUhOZ6Y3wn90hqdiM4T
// SIG // jhQ9hBVl9qgz8Ru7fgBjegcMCY0GydY4Pt2z+MYDbLMN
// SIG // bJ4DBNBu7oWBo7pPaaanTLHZfz5LC7UWF+NtmzU5sEjW
// SIG // LdWZfNpczuEI0d27AdiA+oU2NQNv9MwEHIIeL3P5NHiD
// SIG // stJsUl30YWsV
// SIG // End signature block
