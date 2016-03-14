var g_strOrigFrame	= null;
var g_strOrigStatus = null;


//////////////////////////////////////////////////////////

function getScript()
{
	return "FP_jumpMenu";
}

//////////////////////////////////////////////////////////

function getScriptCall()
{
	var oSelection = theForm.FrameNames.options(theForm.FrameNames.selectedIndex);
	var strFrame = oSelection.value;
	var strFramePath = oSelection.strFramePath;
	
	if( oSelection.oFrame && !oSelection.oFrame.id && !oSelection.oFrame.name )
		oSelection.oFrame.name = strFrame;
		
	var strStatus = String( theForm.FirstItem.status );
	var strScriptCall = 	"FP_jumpMenu(this," +
							"\'" + strFramePath + "\'," +
							strStatus + ")";
													
	var oActiveDoc	= FrontPage.ActiveDocument;
	var oSelect	= null;

	var rng		= oActiveDoc.selection.createRange();
	var bNewMenu	= ( 0 == rng.compareEndPoints("StartToEnd", rng) ) ? true : false;		

	if( bNewMenu )
	{		
		oSelect = oActiveDoc.createElement( "select" );
		
		oSelect.size = 1;		
		oSelect.setAttribute( "onchange", strScriptCall );				
	}
	else
	{
		oSelect = getBehaviorElement();							
	}
	
	var strID = oSelect.id ? oSelect.id : oSelect.name ? oSelect.name : '';
	
	// auto-ID
	if( '' == strID )
	{
		strID = generateUniqueID( oActiveDoc );
		
		oSelect.id = strID;
	}
	
	if( bNewMenu )
	{
		rng.pasteHTML( oSelect.outerHTML );	
		
		oSelect = oActiveDoc.all[oSelect.id];	
	}	

	while( 0 < oSelect.length ) 
		oSelect.remove( 0 );	 

	var oListItems = theForm.lvOptions.ListItems;
	
	for( var i = 1; i<= oListItems.Count; i++ )
	{
		var oListItem = oListItems( i );
		strURL = getPropValue( oListItem );
		
		if( strURL.length > 0 )
		{
			strURL = FrontPage.MakeRel( oActiveDoc, strURL );
		}
		
		var oOption = oActiveDoc.createElement( "OPTION" );
			
		oOption.value = strURL;		

		oSelect.add( oOption );				
		
		oOption.text = getPropName( oListItem );
	}	

	if( bNewMenu ) 
	{

		rng.moveToElementText( oSelect );
		rng.select();

		return "";
	}
	else
	{	
		// sync any Jump Menu Go's...
		var strOldJumpMenuGo = 'FP_jumpMenuGo(' + 
							FP_ADJECTIVE_ID + "'" + strID + "'," + 
							"'" + g_strOrigFrame + "'," + 
							g_strOrigStatus + ")";
		var strNewJumpMenuGo = 'FP_jumpMenuGo(' + 
							FP_ADJECTIVE_ID + "'" + strID + "'," + 
							"'" + strFramePath + "'," + 
							strStatus + ")";
							
		// save selected element index
		
		var nSourceIndex = getBehaviorElement().sourceIndex;			
							
		strOldJumpMenuGo = escapeRegExpExpString( strOldJumpMenuGo );			

		var re = new RegExp( strOldJumpMenuGo, "g" );			

		var strNewHTML = oActiveDoc.documentHTML.replace( re, strNewJumpMenuGo );

		oActiveDoc.documentHTML = strNewHTML;	
		
		rng = oActiveDoc.body.createTextRange();	

		var oNewSelect = oActiveDoc.all( nSourceIndex );

		if (oNewSelect.tagName == 'option')
			oNewSelect = oNewSelect.parentElement;
		
		rng.moveToElementText(oNewSelect);

		rng.select();
	}
		
	return strScriptCall;	
}

//////////////////////////////////////////////////////////

function isScriptEnabled( oDoc )
{
	var rng = oDoc.selection.createRange();
	if (rng.compareEndPoints("StartToEnd", rng) == 0)
		return "onchange";
	
	var el = getBehaviorElement( oDoc );
	if (el.tagName == "select")
		return "onchange";

	return false;
}

//////////////////////////////////////////////////////////

function initPropertyDialog( strScriptCall )
{
	var aArguments = extractArgumentsFromScriptCall( strScriptCall );
	nArgs= aArguments.length;
	if (nArgs != 0)
	{
		var sFrame = aArguments[1].strArgument;
		sFrame=trimQuotes(sFrame);
		for(i=0; i<theForm.FrameNames.options.length; i++)
		{
			if (sFrame == theForm.FrameNames.options(i).strFramePath)
				theForm.FrameNames.children(i).selected=true;
			else
				theForm.FrameNames.children(i).selected=false;
		}
		if (aArguments[2].strArgument=='true')
			theForm.FirstItem.status = true;
		else
			theForm.FirstItem.status = false;
	}
	
	// save original parameters for JMG syncronization
	g_strOrigFrame	= trimQuotes( aArguments[1].strArgument );
	g_strOrigStatus = trimQuotes( aArguments[2].strArgument );
}

//////////////////////////////////////////////////////////

function Validate()
{
	if (theForm.lvOptions.ListItems.Count > 0)
	{
		window.returnValue = RETURN_OK;
		window.close();
	}
	else
		alert(L_NOOptions_ErrorMessage);

}

//////////////////////////////////////////////////////////

function CancelAction()
{
	window.returnValue = RETURN_CANCEL;	
	window.close();
}

//////////////////////////////////////////////////////////

function initOptions()
{	
	var el = getBehaviorElement();
	
	if( "select" == el.tagName )
	{
		for(var i=0; i<el.options.length; i++)
		{	
			child = el.options(i);
			insertOption(child.text, child.value, theForm.lvOptions.ListItems.Count + 1);
		}
	}
}

//////////////////////////////////////////////////////////

function initializeForm()
{		
	window.onresize = onResize;
	var oColumnHeaders = theForm.lvOptions.ColumnHeaders;
		
	oColumnHeaders.Clear();
	
	var colHeader0 = oColumnHeaders.Add(
						oColumnHeaders.Count + 1,
						"", 
						"",
						0 );					

	var colHeader1 = oColumnHeaders.Add(
						oColumnHeaders.Count + 1,
						L_COLUMN1_TXT, 
						L_COLUMN1_TXT,
						theForm.lvOptions.clientWidth / 2 );
								
	var colHeader2 = oColumnHeaders.Add(
						oColumnHeaders.Count + 1,
						L_COLUMN2_TXT, 
						L_COLUMN2_TXT,
						theForm.lvOptions.clientWidth / 2-5 );
						
	theForm.lvOptions.Font.Name		= g_fontName;
	theForm.lvOptions.Font.Charset	= g_fontCharSet;
	
	if( document.dir == "rtl" )
	{
		colHeader1.Alignment = 1;	// lvwColumnRight
		colHeader2.Alignment = 1;	// lvwColumnRight
	}	
						
	initFrameNames();
	initOptions();

	theForm.FirstItem.status = false;

	if (theForm.lvOptions.ListItems.Count == 0)
		disableButtons();
	safeFocus(theForm.btnAdd);
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function onResize()
{
	resizeColumnHeaders();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function resizeColumnHeaders()
{
	var nColumn1Width = theForm.lvOptions.clientWidth / 2;
	var nColumn2Width = theForm.lvOptions.clientWidth - nColumn1Width - 5;
	
	nColumn2Width = 0 < nColumn2Width ? nColumn2Width : 0;
	
	theForm.lvOptions.ColumnHeaders.Item( 2 ).Width	= nColumn1Width;
	theForm.lvOptions.ColumnHeaders.Item( 3 ).Width	= nColumn2Width;
}

//////////////////////////////////////////////////////////

function initFrameNames()
{
	var oOption;
	for (i=0; i < theForm.FrameNames.options.length; i++)
	{
		theForm.FrameNames.options.remove(0);
	}
	oOption=document.createElement("option");
	oOption.text = L_PageOption_ListBox;
	oOption.value = 'none';
	oOption.strFramePath = "window";
	theForm.FrameNames.options.add(oOption);
	oOption=document.createElement("option");
	oOption.text = L_NewWindow_ListBox;
	oOption.value = 'new';
	oOption.strFramePath = "_new";
	theForm.FrameNames.options.add(oOption);

	var oDoc=FrontPage.ActiveDocument;
	var oParentFrames = oDoc.parentWindow.parent != oDoc.parentWindow ? oDoc.parentWindow.parent.frames : null;
	
	var nNoName = 0;
	
	if( oParentFrames )
	{
		if(oParentFrames.length > 0 )
		{
			oOption = document.createElement("option");
			oOption.text = L_SameFrame_ListBox;
			oOption.value = 'same';
			oOption.strFramePath = "window";
			theForm.FrameNames.options.add(oOption);		
			
			oOption = document.createElement("option");
			oOption.text = L_WholePage_ListBox;
			oOption.value = 'whole';
			
			// calculate path to top-most window frame
			var strTopWindowPath = "parent";			
			
			for( 
				var oParent = oDoc.parentWindow.parent;
				oParent != oParent.parent;
				oParent = oParent.parent )
			{
				strTopWindowPath += ".parent";				
			}
			
			oOption.strFramePath = strTopWindowPath;
			theForm.FrameNames.options.add(oOption);
			
			oOption = document.createElement("option");
			oOption.text = L_ParentFrame_ListBox;
			oOption.value = 'parent';
			oOption.strFramePath = "parent"
			theForm.FrameNames.options.add(oOption);
		}
				
		for (i=0; i < oParentFrames.length; i++)
		{
			var oFrame=oParentFrames(i);

			var sName = oFrame.id ? oFrame.id : oFrame.name;		
			var sValue = sName;
					
			if ( sName == "" )
			{
				sName = L_Unnamed_ListBox + nNoName;
				nNoName++;	
				
				sValue = generateUniqueID( oFrame.parent.Document );
			}
			oOption = document.createElement("option");
			oOption.text = L_Frame_ListBox + " \"" + sName + "\"";
			oOption.value = sValue;
			oOption.oFrame = oFrame;
			oOption.strFramePath = "parent.frames[\\'" + sValue + "\\']";
			theForm.FrameNames.options.add(oOption);
		}
	}
		
	for (i=0; i < oDoc.frames.length; i++)
	{
		var oFrame = oDoc.frames(i);
		var sName = oFrame.id ? oFrame.id : oFrame.name;

		sValue = sName;
		
		if (sName == "")
		{
			sName = L_Unnamed_ListBox + nNoName;
			nNoName++;
			
			sValue = generateUniqueID( oFrame.parent.Document );
		}
		oOption = document.createElement("option");
		oOption.text = L_Frame_ListBox + " \"" + sName + "\"";
		oOption.value = sValue;
		oOption.oFrame = oFrame;
		oOption.strFramePath = "frames[\\'" + sValue + "\\']";
		theForm.FrameNames.options.add(oOption);
	}

	if(theForm.FrameNames.options(0) != null)
		theForm.FrameNames.options(0).selected=true;
}
//////////////////////////////////////////////////////////

function invokeInsertDialog(sType)
{			
	var aDialogArguments = new Array();
	aDialogArguments[0] = sType;
	if (sType == "edit")
	{
		var oSelected = theForm.lvOptions.SelectedItem;

		if( oSelected )
		{
			aDialogArguments[1] = getPropName( oSelected );
			aDialogArguments[2] = getPropValue( oSelected );
		}
		else
			return;
	}
	
	var astrReturn = FrontPage.ShowHTMLDialog( "InsertOp.htm", aDialogArguments );		
	if( astrReturn )
	{
		switch(sType)
		{
			case "insert":
			{
				var sName		= String( astrReturn[0] );
				var strValue	= String( astrReturn[1] );
		
				if( 0 < sName.length )
				{				
					var oSelected = theForm.lvOptions.SelectedItem;
						
					nIndex = oSelected ? oSelected.Index + 1 : theForm.lvOptions.ListItems.Count + 1;
					insertOption( sName, strValue, nIndex );
				}
				break;
			}
			case "edit":
			{
				var strName		= String( astrReturn[0] );	
				var strValue	= String( astrReturn[1] );
					
				if( 0 < strName.length )
				{
					nIndex = theForm.lvOptions.SelectedItem.index;			
					deleteOption();
					insertOption( strName, strValue, nIndex );
				}
				break;
			}
		}
	}
}

//////////////////////////////////////////////////////////

function insertOption( sName, strValue, nIndex )
{	
	var oOption	= null;
	var oOptions = theForm.lvOptions.ListItems;
//	oOption = oOptions.Add(nIndex, '', sName);
//	oOption.SubItems( 1 ) = strValue;

	oOption = oOptions.Add(nIndex, '', '');
	
	oOption.Tag = new Object();
	
	oOption.Tag.strName = "";
	oOption.Tag.strValue = "";
	
	setPropName( oOption,  sName );
	setPropValue( oOption, strValue );

	theForm.lvOptions.SelectedItem = oOption;
	if (theForm.lvOptions.ListItems.Count > 0)
		enableButtons();
		
	updateUpDownControls();
}

//////////////////////////////////////////////////////////

function deleteOption()
{
	var oSelected = theForm.lvOptions.SelectedItem;
	
	if( oSelected )
	{
		var nIndex = oSelected.Index;
		theForm.lvOptions.ListItems.Remove(nIndex);
		if(theForm.lvOptions.ListItems.Count > 0)
		{
			if(nIndex <= theForm.lvOptions.ListItems.Count)
				theForm.lvOptions.SelectedItem = theForm.lvOptions.ListItems(nIndex);
			else
				theForm.lvOptions.SelectedItem = theForm.lvOptions.ListItems(nIndex-1);
		}
	}
	if (theForm.lvOptions.ListItems.Count == 0)
		disableButtons();
		
	updateUpDownControls();
}

//////////////////////////////////////////////////////////

function moveOptionUp()
{
	var oSelected = theForm.lvOptions.SelectedItem;
	
	if( oSelected )
	{
		var nIndex = oSelected.index;

		var sName = getPropName( oSelected );
		var sValue = getPropValue( oSelected );

		if (nIndex > 1)
		{
			deleteOption();
			insertOption(sName, sValue, nIndex-1);
		}
	}
	
	updateUpDownControls();
}

//////////////////////////////////////////////////////////

function moveOptionDown()
{
	var oSelected = theForm.lvOptions.SelectedItem;
	
	if( oSelected )
	{
		var nIndex = oSelected.index;

		var sName = getPropName( oSelected );
		var sValue = getPropValue( oSelected );

		if (nIndex < theForm.lvOptions.ListItems.Count)
		{
			deleteOption();
			insertOption(sName, sValue, nIndex+1);
		}
	}
	
	updateUpDownControls();
}

//////////////////////////////////////////////////////////

function enableButtons()
{
	theForm.Modify.disabled=false;
	theForm.Remove.disabled=false;
	theForm.MoveUp.disabled=false;
	theForm.MoveDown.disabled=false;
}

function disableButtons()
{
	theForm.Modify.disabled=true;
	theForm.Remove.disabled=true;
	theForm.MoveUp.disabled=true;
	theForm.MoveDown.disabled=true;
}

//////////////////////////////////////////////////////////

function updateUpDownControls()
{
	var oSelected = theForm.lvOptions.SelectedItem;
	
	if( oSelected )
	{
		var nIndex = oSelected.index;
		
		if( 1 < nIndex )
			theForm.MoveUp.disabled = false;
		else
			theForm.MoveUp.disabled = true;
		
		if( theForm.lvOptions.ListItems.Count > nIndex )
			theForm.MoveDown.disabled = false;
		else
			theForm.MoveDown.disabled = true;
	}
	else
	{
		theForm.MoveUp.disabled = true;
		theForm.MoveDown.disabled = true;
	}
}

//////////////////////////////////////////////////////////

function selectionChanged()
{
	updateUpDownControls();
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

// SIG // Begin signature block
// SIG // MIIasgYJKoZIhvcNAQcCoIIaozCCGp8CAQExCzAJBgUr
// SIG // DgMCGgUAMGcGCisGAQQBgjcCAQSgWTBXMDIGCisGAQQB
// SIG // gjcCAR4wJAIBAQQQEODJBs441BGiowAQS9NQkAIBAAIB
// SIG // AAIBAAIBAAIBADAhMAkGBSsOAwIaBQAEFL/qlH2bZcIu
// SIG // AKq1OYHY7mswYxixoIIVkzCCA8QwggMtoAMCAQICEEe/
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
// SIG // 7RugDYVyNWRJvqFJOOKanEp8gmQNAXhfVv6IdDGCBIsw
// SIG // ggSHAgEBMIG1MIGmMQswCQYDVQQGEwJVUzETMBEGA1UE
// SIG // CBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMHUmVkbW9uZDEe
// SIG // MBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSsw
// SIG // KQYDVQQLEyJDb3B5cmlnaHQgKGMpIDIwMDAgTWljcm9z
// SIG // b2Z0IENvcnAuMSMwIQYDVQQDExpNaWNyb3NvZnQgQ29k
// SIG // ZSBTaWduaW5nIFBDQQIKYQ59pwAAAAAASDAJBgUrDgMC
// SIG // GgUAoIGoMBkGCSqGSIb3DQEJAzEMBgorBgEEAYI3AgEE
// SIG // MBwGCisGAQQBgjcCAQsxDjAMBgorBgEEAYI3AgEVMCMG
// SIG // CSqGSIb3DQEJBDEWBBTGDYKau81EU/bQRVsdspCcVLCK
// SIG // GzBIBgorBgEEAYI3AgEMMTowOKAWgBQAagBtAHAAbQBl
// SIG // AG4AdQAuAGoAc6EegBxodHRwOi8vb2ZmaWNlLm1pY3Jv
// SIG // c29mdC5jb20gMA0GCSqGSIb3DQEBAQUABIIBAKpWWf6Y
// SIG // OqvAtb2wCQKwmojgNGMnB6sS0YBsoxe6OyiFet9eGJPs
// SIG // Ju8EDiiAOkpRZoWfLZPHRsZnouq2++gp+wLBLudtNNkE
// SIG // AhWbj5tMw5VdECDDkdmDApa2KOa3zL3KCJDrMtGy36Or
// SIG // eFu3x9Rxm89gnzBIZAsbHet2/NdLRcSmoiSjlq+yX2Gv
// SIG // cSvP9oK/wIQ8kaT+DBfkm9agsvMYZD218OtKmfJae8eV
// SIG // KCmPWyR3wkXbaDllEqVVvVgLiu1QIt7+ze3CW5AESfBJ
// SIG // yCHfdaqjnsBMvxBIGNBZ7MEw5T7a2oY52K78MGWGvQWz
// SIG // MNceTxCNVAfZE8Dlc+Buxjamlz2hggH/MIIB+wYJKoZI
// SIG // hvcNAQkGMYIB7DCCAegCAQEwZzBTMQswCQYDVQQGEwJV
// SIG // UzEXMBUGA1UEChMOVmVyaVNpZ24sIEluYy4xKzApBgNV
// SIG // BAMTIlZlcmlTaWduIFRpbWUgU3RhbXBpbmcgU2Vydmlj
// SIG // ZXMgQ0ECEA3pK/DU2CmIGDIFCV6adogwDAYIKoZIhvcN
// SIG // AgUFAKBZMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEw
// SIG // HAYJKoZIhvcNAQkFMQ8XDTA0MDQxNjA5MzcxN1owHwYJ
// SIG // KoZIhvcNAQkEMRIEEC7gk+cQaTJf1HyLrrI8OJ8wDQYJ
// SIG // KoZIhvcNAQEBBQAEggEAiM3FMFSF59JYRhXkDkv9lZCS
// SIG // Rgx3XqUj24StW2LdBSye+4P/34zzK6crZa1bh05sqndN
// SIG // eSR/RmozuGf5F+1rDQx4LVM4/PeaasBoy+DQBN5SrfKY
// SIG // xm7T/gRaj3DTkFfOX7TOO7vDYSBGcFtlSpSYnPvJQ0tC
// SIG // Ow/zmmvESZvIL7ASRAJcf8tl8MgFCUp/hTWadVW4mouh
// SIG // TJtefF12V/hRx6W5BHprjaHbNICELdAL52lLpwab19OA
// SIG // atXSMF8mZAiuHaiIFuBp8jKkdVuKbj8B0u1cCAwtBbe3
// SIG // Ozxs/OfH0XjDvjLitSM46Ox198tGs9nsHEn9e185ELYj
// SIG // zp0qoB3QPg==
// SIG // End signature block
