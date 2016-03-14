
// globals

var oImages		= new Array();

/////////////////////////////////////////////////////////////////
//
// DHTML Scripting methods
//
/////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////
//
// FP_swapImg()
//
//  Arguments:
//		( { 1 | 0 } preload flag, { 1 | 0 } restore flag, id of <img> element, path of image to swap ), repeat...

function FP_swapImg() {//v1.0
 var doc=document,args=arguments,elm,n; doc.$imgSwaps=new Array(); for(n=2; n<args.length;
 n+=2) { elm=FP_getObjectByID(args[n]); if(elm) { doc.$imgSwaps[doc.$imgSwaps.length]=elm;
 elm.$src=elm.src; elm.src=args[n+1]; } }
}
 
/////////////////////////////////////////////////////////////////
//
// DHTML Scripting Extensions API methods
//
/////////////////////////////////////////////////////////////////

function getScript()
{
	return "FP_swapImg,FP_preloadImgs,FP_getObjectByID";
}

//////////////////////////////////////////////////////////

function getScriptCall()
{
	var aImageURLs		= new Array();
	var strArgumentList	= new String( "" );		
	var oListItems		= theForm.lvImages.ListItems;	
	var oDoc			= FrontPage.ActiveDocument;

	for( var nListItem = 1; nListItem <= oListItems.Count; nListItem++ )
	{
		var oListItem		= oListItems.Item( nListItem );
		var strSwapImage	= trimStringLeft( trimStringRight( getPropSwapURL( oListItem ) ) );
		var oImage			= oImages[nListItem-1];
		var strImageID		= getImageID( oImage );
		
		if( 0 < strSwapImage.length )			 
		{
			// assign <img> tag a unique ID if it doesn't already have one
			if( 0 >= strImageID.length )
			{
				strImageID = oImage.id = generateUniqueID( oDoc, getDefaultIDPrefix( "img" ) );
			}
		
			if( 0 < strArgumentList.length )
			{
				strArgumentList += ",";
			}

			strArgumentList += FP_ADJECTIVE_ID + "'" + strImageID + "'," + FP_ADJECTIVE_URL + "'" + strSwapImage + "'";

			aImageURLs[aImageURLs.length] = strSwapImage;
		}
	}
	
	// handle preloading
	
	var bPreloadImages	= theForm.chkPreloadImages.checked;
			
	if( bPreloadImages )			
		managePreloads( oDoc, aImageURLs, new Array( 0 ) );		// add preloads
	else
		managePreloads( oDoc, new Array( 0 ), aImageURLs );		// remove preloads

	var strPreload = bPreloadImages ? "1" : "0";	
	
	// handle restore on mouseout
	
	var bRestore = theForm.chkRestore.checked;
	
	if( bRestore )
	{
		var oElmt		= getBehaviorElement();
		var strMouseOut = oElmt.getAttribute( "onmouseout" );		
		
		if( !strMouseOut ||
			-1 == strMouseOut.search( "FP_swapImgRestore()" ) )
		{		
			oDoc.addScript( oElmt, "onmouseout", "FP_swapImgRestore()" );
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
			if( -1 != strStatement.search( "FP_swapImgRestore()" ) )
			{
				oDoc.removeScript( oElmt, "onmouseout", n );
				
				n--;
			}
		}
	}
	
	var strRestore = bRestore ? "1" : "0";

	return "FP_swapImg(" + strPreload + "," + strRestore + "," + strArgumentList + ")";	
}

//////////////////////////////////////////////////////////

function isScriptEnabled( oDoc )
{
	var oImgs = gatherImgTags( oDoc );
	
	return ( 0 < oImgs.length ) ? "onmouseover" : false;	
}

//////////////////////////////////////////////////////////

function initPropertyDialog( strScriptCall )
{
	var aScriptCallArgs	= extractArgumentsFromScriptCall( strScriptCall );
	var nArguments	= aScriptCallArgs.length;
	var oListItems	= theForm.lvImages.ListItems;	
	var nListItems	= oListItems.Count;

	var bPreload	= aScriptCallArgs[0].strArgument == '0' ? false : true;
	var bRestore	= aScriptCallArgs[1].strArgument == '0' ? false : true;

	theForm.chkPreloadImages.checked = bPreload;
	theForm.chkRestore.checked = bRestore;

	for( var nArg = 2; nArg < nArguments; nArg += 2 )
	{
		var	strImageID		= trimQuotes( aScriptCallArgs[nArg].strArgument );
		var strImageSwapURL = unescapeString( trimStringLeft( trimStringRight( trimQuotes( aScriptCallArgs[nArg+1].strArgument ) ) ) );
		
		for( var nListItem = 1; nListItem <= nListItems; nListItem++ )
		{
			var oListItem		= oListItems.Item( nListItem );
			var strItemImageID	= getPropID( oListItem );
		
			if( strImageID == strItemImageID )
			{
				setPropSwapURL( oListItem, strImageSwapURL );
			}
		}
	}		

	selectionChanged();	
} 

//////////////////////////////////////////////////////////

function removeScript( oDoc, strScriptCall )
{	
	var aScriptCallArgs	= extractArgumentsFromScriptCall( strScriptCall );
	var nArguments		= aScriptCallArgs.length;

	if( 0 >= aScriptCallArgs.length )
		return;
		
	// '1' indicates this swap image added a preload, let's remove it
	if( aScriptCallArgs[0].strArgument == '1' )
	{	
		var astrToAdd = new Array();
		var astrToRemove = new Array();
		
		for( var nArg = 2; nArg + 1 < aScriptCallArgs.length; nArg += 2 )
		{
			astrToRemove[astrToRemove.length] = trimQuotes( aScriptCallArgs[nArg+1].strArgument );
		}			

		managePreloads( oDoc, astrToAdd, astrToRemove );		
	}
}

//////////////////////////////////////////////////////////

function onOk()
{
	var bCanSwap	= false;
	var oListItems	= theForm.lvImages.ListItems;
	
	for( var nListItem = 1; nListItem <= oListItems.Count; nListItem++ )
	{
		var oListItem		= oListItems.Item( nListItem );
		var strSwapImage	= trimStringLeft( trimStringRight( getPropSwapURL( oListItem ) ) );
		
		if( 0 < strSwapImage.length )
		{
			bCanSwap = true;
			
			break;
		}
	}

	if( bCanSwap )
	{
		window.returnValue = RETURN_OK;
		window.close();
	}
	else
	{
		alert( L_OkFailed_ErrorMessage );
		
		theForm.edFilename.focus();
	}
}

//////////////////////////////////////////////////////////

function onCancel()
{
	window.returnValue = RETURN_CANCEL;
	window.close();
}

//////////////////////////////////////////////////////////

function onResize()
{
	resizeColumnHeaders();
}
//////////////////////////////////////////////////////////

function initializeForm()
{	
	window.onresize = onResize;
	
	var oColumnHeaders = theForm.lvImages.ColumnHeaders;
		
	oColumnHeaders.Clear();

	var colHeader0 = oColumnHeaders.Add(
						oColumnHeaders.Count + 1,
						"", 
						"",
						0 );	

	var colHeader1 = oColumnHeaders.Add(
						oColumnHeaders.Count + 1,
						L_HeaderColumn1_Text, 
						L_HeaderColumn1_Text,
						theForm.lvImages.clientWidth / 3 );	
		
	var colHeader2 = oColumnHeaders.Add(
						oColumnHeaders.Count + 1,
						L_HeaderColumn2_Text, 
						L_HeaderColumn2_Text,
						theForm.lvImages.clientWidth / 3 );	

	var colHeader3 = oColumnHeaders.Add(
						oColumnHeaders.Count + 1,
						L_HeaderColumn3_Text, 
						L_HeaderColumn3_Text,
						theForm.lvImages.clientWidth / 3 );	
					
	theForm.lvImages.Font.Name		= g_fontName;
	theForm.lvImages.Font.Charset	= g_fontCharSet;
					
	if( document.dir == "rtl" )
	{
		colHeader1.Alignment = 1;	// lvwColumnRight
		colHeader2.Alignment = 1;	// lvwColumnRight
		colHeader3.Alignment = 1;	// lvwColumnRight
	}	
					
	populateImageList();	
	
	resizeColumnHeaders();
	
	selectSelectedImage();
		
	selectionChanged();	
	
	var oSel = getBehaviorElement();
		
	// check if we find an existing restore
	var strMouseOut = oSel.getAttribute( 'onmouseout' );
	
	if( strMouseOut )
	{
		if( -1 != strMouseOut.indexOf( "FP_swapImgRestore()" ) )
			theForm.chkRestore.checked = true;
	}	
			
	safeFocus( theForm.edFilename );
}

//////////////////////////////////////////////////////////

function resizeColumnHeaders()
{
	var nColumn1Width = theForm.lvImages.clientWidth * 0.2;
	var nColumn2Width = theForm.lvImages.clientWidth * 0.4;
	var nColumn3Width = theForm.lvImages.clientWidth - nColumn1Width - nColumn2Width - 5;
	
	nColumn3Width = 0 < nColumn3Width ? nColumn3Width : 0;
	
	theForm.lvImages.ColumnHeaders.Item( 2 ).Width	= nColumn1Width;
	theForm.lvImages.ColumnHeaders.Item( 3 ).Width	= nColumn2Width;
	theForm.lvImages.ColumnHeaders.Item( 4 ).Width	= nColumn3Width;
}

//////////////////////////////////////////////////////////

function selectSelectedImage()
{
	var oSel = getBehaviorElement();

	if( "img" == oSel.tagName )
	{
		for( nImg = 0; nImg < oImages.length; nImg++ )
		{
			if( oImages[nImg] == oSel )
			{
				var oListItems = theForm.lvImages.ListItems;
			
				theForm.lvImages.SelectedItem = oListItems.Item( nImg+1 );
				
				return;
			}
		}
	}
}

//////////////////////////////////////////////////////////

function populateImageList()
{
	oImages = gatherImgTags( FrontPage.ActiveDocument );
	
	for( var nImg = 0; nImg < oImages.length; nImg++ )
	{
		addImage( oImages[nImg] );			
	}
	
	var oListItems = theForm.lvImages.ListItems;
	
	if( 0 < oListItems.Count )
	{	
		theForm.lvImages.SelectedItem = oListItems.Item( 1 );
	}
	
	theForm.lvImages.Refresh();
}

//////////////////////////////////////////////////////////

function gatherImgTags( oDoc )
{	
	var		aoImgs		= oDoc.all.tags( "img" );
	var		oImgs		= new Array();
	
	for( var nImg = 0; nImg < aoImgs.length; nImg++ )
	{
		var	oImg= aoImgs[nImg];
		
		if( isValidElmt( oDoc, oImg ) )		
			oImgs[oImgs.length] = oImg;
	}
	
	return oImgs;
}

//////////////////////////////////////////////////////////

function addImage( oImage )
{
	var oListItems	= theForm.lvImages.ListItems;
	var nIndex		= oListItems.Count + 1;	
	var	strID 		= getImageID( oImage );
			
	if( "" == strID )
	{
		strID = L_UnnamedImage_Text;
	}
		
	var oListItem = oListItems.Add( 
		nIndex,
		"_" + strID + nIndex,			// key
		"" );
		
	oListItem.Tag = new Object();
	
	oListItem.Tag.strID = "";
	oListItem.Tag.strURL = "";
	oListItem.Tag.strSwapURL = "";

	var strImageURL = oImage.src;

	if( null != FrontPage.ActivePageWindow.Web )
	{
		var strWebUrl = FrontPage.ActivePageWindow.Web.Url + '\\';
		
		strImageURL = FrontPage.MakeRel( strWebUrl, strImageURL );
	}
	else
	{
		strImageURL = FrontPage.MakeAbs( "", strImageURL );
	}
			
	setPropID( oListItem, strID );
	setPropURL( oListItem, strImageURL );
}

//////////////////////////////////////////////////////////

function fileURLChanged()
{
	var oSelection = theForm.lvImages.SelectedItem;

	if( oSelection )
	{
		var strValue = String( theForm.edFilename.value );		
				
		setPropSwapURL( oSelection, strValue );
	}
}

//////////////////////////////////////////////////////////

function selectionChanged()
{
	var oSelection = theForm.lvImages.SelectedItem;

	if( oSelection )
	{
		theForm.edFilename.value = getPropSwapURL( oSelection );
	}
}

//////////////////////////////////////////////////////////

function getFileURL()
{
	var oDlgFilePicker = FrontPage.FileDialog( 3 );
	
	oDlgFilePicker.Filters.Clear();
	oDlgFilePicker.Filters.Add( L_FilterImages_Text, "*.gif; *.jpg; *.jpeg; *.png; *.bmp", 1 );
	oDlgFilePicker.AllowMultiSelect = false;

	// set initial path to Browse dialog if this is first time run
    var strInitialPath = "";
   
	// only set an initial folder if one hasn't been set already (i.e. the first invocation
	// of the browse dialog
    if( "" == oDlgFilePicker.InitialFileName )
    {    	
	    if( FrontPage.ActivePageWindow.Web )
	    {
			// use web root for webs
			strInitialPath = FrontPage.ActivePageWindow.Web.Url;
		}
		else
		{
			// use My Documents for non-webs
			var oWshShell = new ActiveXObject( "WScript.Shell" );
				
			strInitialPath = oWshShell.SpecialFolders( "MyDocuments" );
		}
					
		if( strInitialPath.length && strInitialPath[strInitialPath.length-1] != '\\' && strInitialPath[strInitialPath.length-1] != '/' )		
			strInitialPath = strInitialPath + '\\';		
	}							
			
    oDlgFilePicker.InitialFileName = strInitialPath;	
	
	oDlgFilePicker.Show();
	
	var oSelectedItems = oDlgFilePicker.SelectedItems;
	
	if( 0 < oSelectedItems.Count )
	{
		var strPickedURL = oSelectedItems.Item( 1 );
		
		// make relative if possible, use document as base URL
		strPickedURL = FrontPage.MakeRel( FrontPage.ActiveDocument.location.href, strPickedURL );
		
		theForm.edFilename.value = strPickedURL;
		
		fileURLChanged();
	}	
}

function getImageID( oImage )
{
	var	strID = oImage.id;
	
	if( "" == strID )
	{
		strID = oImage.name;
	}
		
	return strID;
}

function toScriptArgumentsString( astrArgs )
{
	var strReturn = "";

	for( var nArg= 0; nArg < astrArgs.length; nArg++ )
	{			
		if( 0 < strReturn.length )
		{
			strReturn += ",";
		}

		strReturn += "/*url*/'" + astrArgs[nArg] + "'";
	}
	
	return strReturn;
}

//////////////////////////////////////////////////////////

function getPropID( oItem )
{
	return oItem.Tag.strID;
}

//////////////////////////////////////////////////////////

function getPropURL( oItem )
{
	return oItem.Tag.strURL;
}

//////////////////////////////////////////////////////////

function getPropSwapURL( oItem )
{
	return oItem.Tag.strSwapURL;
}

//////////////////////////////////////////////////////////

function setPropID( oItem, strID )
{
	oItem.SubItems( 1 ) = strID;
	oItem.Tag.strID = strID;
}

//////////////////////////////////////////////////////////

function setPropURL( oItem, strURL )
{
	oItem.SubItems( 2 ) = strURL;
	oItem.Tag.strURL = strURL;
}

//////////////////////////////////////////////////////////

function setPropSwapURL( oItem, strSwapURL )
{
	oItem.SubItems( 3 ) = strSwapURL;
	oItem.Tag.strSwapURL = strSwapURL;
}

// SIG // Begin signature block
// SIG // MIIasgYJKoZIhvcNAQcCoIIaozCCGp8CAQExCzAJBgUr
// SIG // DgMCGgUAMGcGCisGAQQBgjcCAQSgWTBXMDIGCisGAQQB
// SIG // gjcCAR4wJAIBAQQQEODJBs441BGiowAQS9NQkAIBAAIB
// SIG // AAIBAAIBAAIBADAhMAkGBSsOAwIaBQAEFObR68mKDaNq
// SIG // RgF/isyaLsjvcBYaoIIVkzCCA8QwggMtoAMCAQICEEe/
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
// SIG // CSqGSIb3DQEJBDEWBBQYuRHJivlf/YVzBU1vzPTqJg3Y
// SIG // ITBIBgorBgEEAYI3AgEMMTowOKAWgBQAcwB3AGEAcABp
// SIG // AG0AZwAuAGoAc6EegBxodHRwOi8vb2ZmaWNlLm1pY3Jv
// SIG // c29mdC5jb20gMA0GCSqGSIb3DQEBAQUABIIBAEhRmtkY
// SIG // F8H92T60lHEcYjz08kqDWP3IAxdQwaax8fYn3HEO2KM6
// SIG // /kF/GXl1gKqyKccqHkVUgyzn6QVq+dHKe0pkBlXqW30Y
// SIG // wgu+J0MyP8yf1q/JIoXCQ8WQeK0SFLmtqLz+c4dOpN+L
// SIG // iLTfwJtm3tYTmcN+9htfTyvWKxPmWFYx5QppCFjGc0xA
// SIG // Do0Uyh852Gp2Rq7+cSfLZ67axC3RTWIZJ+nGBJVvrqdA
// SIG // 2GlNScZ0PE9AuOaZJOK2iUE9ezjU/k8ME7NSPksVFCeU
// SIG // R6iPUDL+uJzIaU1JGaLSf7TovvWDtRMBX1fu8LycAawn
// SIG // 70KCt4VTon+m9mo9mCTLW3SlOM+hggH/MIIB+wYJKoZI
// SIG // hvcNAQkGMYIB7DCCAegCAQEwZzBTMQswCQYDVQQGEwJV
// SIG // UzEXMBUGA1UEChMOVmVyaVNpZ24sIEluYy4xKzApBgNV
// SIG // BAMTIlZlcmlTaWduIFRpbWUgU3RhbXBpbmcgU2Vydmlj
// SIG // ZXMgQ0ECEA3pK/DU2CmIGDIFCV6adogwDAYIKoZIhvcN
// SIG // AgUFAKBZMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEw
// SIG // HAYJKoZIhvcNAQkFMQ8XDTA0MDQxNjA5MzcyMlowHwYJ
// SIG // KoZIhvcNAQkEMRIEEL5RHYCgoVIUFdICRMdGVc8wDQYJ
// SIG // KoZIhvcNAQEBBQAEggEAbYnrOmz6u0fBXHBvUotyN5tV
// SIG // z4gcCTuM9z8Bityuy8BqZ4Fa9IKIWbMZDbB3GRFgp82M
// SIG // ZC574ivG8BbSGTKC42HuHafhZ2Lu7pYwxtf6aKIcK8u6
// SIG // tIpaJpb5c8ITh1X9RUa2m6frBCRgcMvSYi5spPw8KV+6
// SIG // C19SEhpk3sgqqKHyyR99fyMWXP53+bjaakhfhKvfqTFv
// SIG // mqIrRUP19Lx04i1cV6fuslDPB/lbk49B3hlSA8wj1vYO
// SIG // gunhdSnkhvUCsnYsMPSbyhzETPVjCBH9vTQgnNZcWPHL
// SIG // 4i2YNzG1kYkpRc9JpYs+gaTE3cyJonN3iy0L7CCNSSUt
// SIG // h3tBwltqow==
// SIG // End signature block
