//////////////////////////////////////////////////////////

function FP_setTextFieldText(id,txt,form) {//v1.0
 var el=FP_getObjectByID(id); if(el!=null) el.value=txt;
}

//////////////////////////////////////////////////////////

function getScript()
{
	return "FP_setTextFieldText,FP_getObjectByID";
}

//////////////////////////////////////////////////////////

function getScriptCall()
{
	var sCallString;
	var sTextFieldText;
	var sTextName;
	var eText;
	var oDoc = FrontPage.ActiveDocument;
	sTextFieldText = String( theForm.Message.value );
	sTextFieldText = sTextFieldText.substr( 0, 10000 );	// limit text to 10000 characters
	sTextFieldText=encodeTextArea(sTextFieldText);

	sValue = theForm.TextFieldName.children(theForm.TextFieldName.selectedIndex).value;
	sTextName = sValue.substr(0,sValue.indexOf(', '));
	form = sValue.substring(sValue.indexOf(', ')+2,sValue.length);
	if (sTextName.indexOf('unnamed')>=0)
	{
		sTextName = sTextName.substring(7,sTextName.length);
		nNoName = 1;
		for(i=0; i<oDoc.all.tags("textarea").Length; i++)
		{
			elemTextArea = oDoc.all.tags("textarea").item(i);
			if ((elemTextArea.id == '') && (elemTextArea.name == ''))
			{
				if(nNoName == sTextName)
					eText = elemTextArea;
				nNoName++;
			}
			
		}
		for(i=0; i<oDoc.all.tags("input").Length; i++)
		{
			elemTextArea = oDoc.all.tags("input").item(i);
			if ((elemTextArea.id == '') && (elemTextArea.name == '') && (elemTextArea.type == "text"))
			{
				if(nNoName == sTextName)
					eText = elemTextArea;
				nNoName++;
			}
			
		}
		if (eText==null)
			alert(L_NOTAG_ErrorMessage);
		
	}
	else
	{
		if(form == '' || 
		   (form.charAt(0) >= '0' && form.charAt(0) <= '9'))
		{
			var elemCol = oDoc.all(sTextName);
			try
			{
			if (elemCol.length != null)
				for( i=0; i<elemCol.length; i++)
				{
					elem = elemCol(i);
					if ((elem.tagname == "textarea") && (elem.form == null))	
						eText=elem;
					if ((elem.tagname == "input") && (elem.form ==null))
					{
						if (elem.type=="text")
							eText=elem
					}
				}
			}
			catch(e)
			{
				eText=elemCol;			
			}
			if(eText == null)
				alert(L_NOTAG_ErrorMessage);
		}
		else
		{
			// possibly a form number
			var nForm=parseInt( form );

			if( !isNaN( nForm ) )
			{
				var aForms = oDoc.all.tags("form");

				for( var n = 0; n < aForms.length; n++ )
				{
					var oForm = aForms.item( n );
	
					if( oForm.name == form )											
						break;					
				}
			}
			else
			{
				var oForm = oDoc.all.tags("form").item(form);
			}

			if( oForm )
				eText = oForm.all( sTextName );
		}
	}
	if (eText != null)
	{
		if (eText.name == '')
		{
			if (eText.id == '')
			{
				eText.name = generateUniqueID( oDoc );
				sTextName = eText.name;
			}
			else
				sTextName = eText.id;
		}
		else
			sTextName = eText.name;
	}
	else
		alert(L_NOTAG_ErrorMessage);
	sCallString='FP_setTextFieldText(';
	sCallString += FP_ADJECTIVE_ID + '\'' + sTextName;
	sCallString += '\', \'' + sTextFieldText;
	sCallString += '\', ' + FP_ADJECTIVE_ID + '\'' + form;
	sCallString += '\')';
	return sCallString;
}

//////////////////////////////////////////////////////////

function isScriptEnabled( oDoc )
{
	// script available when textfields in document
	var aoTextAreas = oDoc.all.tags("textarea");

	for( var i = 0; i < aoTextAreas.length; i++ )
	{
		var oElmt = aoTextAreas(i);
		
		if( isValidElmt( oDoc, oElmt ) )
    		return true;    	    		
    }
    		
    var aoInputs = oDoc.all.tags("input");
    
	for( var i = 0; i< aoInputs.length; i++)
	{	
		var oElmt = aoInputs(i);
	
		if( !isValidElmt( oDoc, oElmt ) )
			continue;
	
	    var t=oElmt.Type;
	    if (t.toLowerCase() == "text")
        	return true;
	}
	return false;	
}

//////////////////////////////////////////////////////////


function initPropertyDialog(strScriptCall)
{
	//The arguments should be (id, txt, form)
	var aArguments = extractArgumentsFromScriptCall( strScriptCall );
	var bFound=false;
	nArgs= aArguments.length;
	if (nArgs != 0)
	{
		var id=aArguments[0].strArgument;
		id=trimQuotes(id);
		form = trimQuotes(aArguments[2].strArgument);
		if(form.length==0)
			form = L_NotInForm_ListBox;
		oTextFieldSelector=document.all.tags("select").item("TextFieldName");
		for(i=0; i<oTextFieldSelector.children.length; i++)
		{
			sChildText = oTextFieldSelector.children(i).text;
			if ((sChildText.indexOf(id)>=0) && (sChildText.indexOf(form)>=0))
			{
				oTextFieldSelector.children(i).selected=true;
				bFound = true;
			}
			else
				oTextFieldSelector.children(i).selected=false;
		}
		if (!bFound)
			alert(L_FIELDNOTFOUND_ErrorMessage);
		var sMessage = aArguments[1].strArgument;
		sMessage=trimQuotes(sMessage);
		sMessage=decodeTextArea(sMessage);
		theForm.Message.value = sMessage;
	}
}

//////////////////////////////////////////////////////////

function Validate()
{
	if (theForm.Message.value=="")
	{
		alert(L_NOMESSAGE_ErrorMessage);
		theForm.Message.focus();
		return;
	}
	if (theForm.TextFieldName.children.length == 0)
	{
		alert(L_NOVALIDFIELDS_ErrorMessage);
		return;
	}
	if(isTextBoxTextValid(theForm.Message.value))
	{
		window.returnValue = RETURN_OK;
		window.close();
	}
	else
		theForm.Message.focus();
}

//////////////////////////////////////////////////////////

function CancelAction()
{
	window.returnValue = RETURN_CANCEL;	
	window.close();
}

//////////////////////////////////////////////////////////

function initDialog()
{
	var nNoName=1;
	var sOptionText;
	var sValue='';
	var elemTextArea;
	var elemText;
	var nFormNumber = 0;
	var oDoc = FrontPage.ActiveDocument;
	
	for(i=0; i<oDoc.all.tags("textarea").Length; i++)
	{	
		elemTextArea = oDoc.all.tags("textarea").item(i);

		if( !isValidElmt( oDoc, elemTextArea ) )
			continue;
	
		oOption=document.createElement("option");
		sValue = '';		
				
		var strID;		
		
		if (elemTextArea.id != '')
		{
			strID = '\"' + elemTextArea.id +'\"';
			sValue = elemTextArea.id;
		}
		else
		{
			if (elemTextArea.name != '')
			{
				strID = '\"' + elemTextArea.name +'\"';
				sValue = elemTextArea.name;
			}
			else
			{
				strID = L_Unnamed_ListBox + nNoName;
				sValue = 'unnamed' + nNoName;
				nNoName++;
			}
		}
		sValue += ', ';
		if (elemTextArea.form == null)
		{
			sOptionText = strBuildParam( L_TargetForm_ListBox, L_Textarea_ListBox, strID, L_NotInForm_ListBox, '' );
		}
		else
		{
			if(elemTextArea.form.id != '')
			{
				sOptionText = strBuildParam( L_TargetForm_ListBox, L_Textarea_ListBox, strID, L_InForm_ListBox, '\"' + elemTextArea.form.id + '\"' );
				sValue += elemTextArea.form.id;
			}
			else if(elemTextArea.form.name != '')
				{
					sOptionText = strBuildParam( L_TargetForm_ListBox, L_Textarea_ListBox, strID, L_InForm_ListBox, '\"' + elemTextArea.form.name + '\"' );
					sValue += elemTextArea.form.name;
				}
			else
			{
				nFormNumber = FindFormNumber(oDoc, elemTextArea.form);
				sOptionText = strBuildParam( L_TargetForm_ListBox, L_Textarea_ListBox, strID, L_InForm_ListBox, L_Unnamed_ListBox + nFormNumber );
				sValue += nFormNumber;
			}
		}
		oOption.text = sOptionText;
		oOption.value = sValue;
		theForm.TextFieldName.options.add(oOption);
	}
	for(i=0;i<oDoc.all.tags("input").Length;i++)
	{
		elemText = oDoc.all.tags("input").item(i);

		if( !isValidElmt( oDoc, elemText ) )
			continue;
		
		t=elemText.Type;
    	        if (t.toLowerCase() == "text")
		{
			sValue = '';
			oOption=document.createElement("option");
			
			var strID;
			
			if (elemText.id != '')
			{
				strID = '\"' + elemText.id + '\"';
				sValue = elemText.id;
			}
			else if (elemText.name != '')
			{
				strID = '\"' + elemText.name + '\"';
				sValue = elemText.name;
			}
			else
			{
				strID = L_Unnamed_ListBox + nNoName;
				sValue = 'unnamed' + nNoName;
				nNoName++;
			}
			
			sValue += ', ';
			if (elemText.form == null)
				sOptionText = strBuildParam( L_TargetForm_ListBox, L_Text_ListBox, strID, L_NotInForm_ListBox, '' );
			else
			{
				if(elemText.form.id != '')
				{
					sOptionText = strBuildParam( L_TargetForm_ListBox, L_Text_ListBox, strID, L_InForm_ListBox, '\"' + elemText.form.id + '\"' );	
					sValue += elemText.form.id;
				}
				else if(elemText.form.name != '')
				{
					sOptionText = strBuildParam( L_TargetForm_ListBox, L_Text_ListBox, strID, L_InForm_ListBox, '\"' + elemText.form.name + '\"' );	
					sValue += elemText.form.name;
				}
				else
				{
					nFormNumber = FindFormNumber(oDoc,elemText.form);
					sOptionText = strBuildParam( L_TargetForm_ListBox, L_Text_ListBox, strID, L_InForm_ListBox, L_Unnamed_ListBox + nFormNumber );	
					sValue += nFormNumber;
				}
			}
			oOption.text = sOptionText;
			oOption.value = sValue;
			theForm.TextFieldName.options.add(oOption);
		}
	}
	if(theForm.TextFieldName.options(0) != null)
		theForm.TextFieldName.options(0).selected=true;
}

//////////////////////////////////////////////////////////

function FindFormNumber(oDoc, elemForm)
{
	var formCollection = oDoc.all.tags("form");
	var i=0;
	for(i=0; i<formCollection.Length; i++)
	{
		if(elemForm == formCollection.item(i))
			return i;
	}
	return -1;
}

// SIG // Begin signature block
// SIG // MIIatAYJKoZIhvcNAQcCoIIapTCCGqECAQExCzAJBgUr
// SIG // DgMCGgUAMGcGCisGAQQBgjcCAQSgWTBXMDIGCisGAQQB
// SIG // gjcCAR4wJAIBAQQQEODJBs441BGiowAQS9NQkAIBAAIB
// SIG // AAIBAAIBAAIBADAhMAkGBSsOAwIaBQAEFNUFcXqzGxjK
// SIG // WuK56jWqU4kg3IFZoIIVkzCCA8QwggMtoAMCAQICEEe/
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
// SIG // CSqGSIb3DQEJBDEWBBSUP+izcC+TwhMuYih4HPXw+7YM
// SIG // vjBKBgorBgEEAYI3AgEMMTwwOqAYgBYAcwBlAHQAdAB4
// SIG // AHQAdABmAC4AagBzoR6AHGh0dHA6Ly9vZmZpY2UubWlj
// SIG // cm9zb2Z0LmNvbSAwDQYJKoZIhvcNAQEBBQAEggEAhFF5
// SIG // 3P43tSRIdsZZHK4M0KeP+aarl8lwngZP74NQSjLyZHzv
// SIG // Xecqtdb8nopKZOVg6TiioaiIBLE9iu32v0+EDJEuuD6T
// SIG // WRnhOBdAYAWGuq9TjP+o5XXjZbQjNTis4STdBkA74E6n
// SIG // TaSlUDe8FLobSQdElGuCBV5LEnjaIPAPDu/Ho+eDixOh
// SIG // UXvCBG6IIuHTAKgOdHxOtPT4Id+MtjpKvn/SceWSBUXI
// SIG // j2Llh6CP/jq9Gl1rf/EOVYu2rqSnZt2s/kYF2xdPUhge
// SIG // FsFd/1tD14tVpVYiqiG2MJ1SI7nK65MNOehw7nN0ZQbE
// SIG // i5z4XBMGn6OiFMtRU0jJ44smpie7SqGCAf8wggH7Bgkq
// SIG // hkiG9w0BCQYxggHsMIIB6AIBATBnMFMxCzAJBgNVBAYT
// SIG // AlVTMRcwFQYDVQQKEw5WZXJpU2lnbiwgSW5jLjErMCkG
// SIG // A1UEAxMiVmVyaVNpZ24gVGltZSBTdGFtcGluZyBTZXJ2
// SIG // aWNlcyBDQQIQDekr8NTYKYgYMgUJXpp2iDAMBggqhkiG
// SIG // 9w0CBQUAoFkwGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEH
// SIG // ATAcBgkqhkiG9w0BCQUxDxcNMDQwNDE2MDkzNzE2WjAf
// SIG // BgkqhkiG9w0BCQQxEgQQolMdKAKFf+L9XGCosInL7jAN
// SIG // BgkqhkiG9w0BAQEFAASCAQCo2Hob/14VtWKT1ge64blF
// SIG // wJxjhB2KmWWPKrFRLJ0Wx9FnpjLNEGEKUROJCcf6FGD6
// SIG // vKgSP/Sp4RPBw/O2/KZgl0W2+HuykFw4V94kk5KR0bfu
// SIG // HdI2icZvcyg00q5I0fjjIZbb170nekfByeR+MSfq/3/D
// SIG // IpmnO6z2SSlxYwhlX6JmbXmxhussqD5jkfkCp0JLf7bI
// SIG // 9CyCCBXP8pWRJKSWOSM39eaye0BCxnBbRoUuTOc+bUen
// SIG // rXABwvywGgWfxA3uG0wWmEvdViZShrqtkwxDETMY02Gb
// SIG // Ce9xYdWJdnEPiuacrBKpIbrs7aKWYSEtnSi7a33Dufu8
// SIG // rED7tFqdxkLY
// SIG // End signature block
